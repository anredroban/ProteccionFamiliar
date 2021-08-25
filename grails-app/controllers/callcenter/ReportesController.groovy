package callcenter
import com.pw.security.*
import groovy.sql.Sql
import org.hibernate.criterion.CriteriaSpecification

import jxl.Workbook
import jxl.WorkbookSettings
import jxl.format.Alignment
import jxl.format.Border
import jxl.format.BorderLineStyle
import jxl.format.Colour
import jxl.format.VerticalAlignment
import jxl.write.Label
import jxl.write.WritableFont
import jxl.write.WritableSheet
import jxl.write.WritableWorkbook
import utilitarios.ExcelUtils
import utilitarios.Util

import java.text.DecimalFormat
import java.text.SimpleDateFormat

//import pl.touk.excel.export.WebXlsxExporter

class ReportesController {
	def dataSource
	static beforeInterceptor = {
		String accion = actionUri;
		if(!accion.equals("/usuario/login") && !accion.equals("/usuario/logout")){
			if(!session.user){
				redirect(uri: "/usuario/login");
				return false;
			}else{
				boolean tienePermiso = utilitarios.Util.checkAccess(session.user.usuario, accion)
				if(!tienePermiso){
					render "No tienes permiso para ingresar a este sitio-> "+accion;
				}
			}
		}
	}

    def index() { }


	def bitacoraAdicional(){
		Date fechaActual = new Date()
		SimpleDateFormat objSDF = new SimpleDateFormat('yyyyMMdd')
		String fecha = objSDF.format(fechaActual)
		if(params.fechas){
			//Obtenemos los datos
			Date[] fechas = Util.formatearFechasReporte(params.fechas.toString())
			ArrayList<SubSubestado> subestados = Subestado.findAllByEnableManagement(true)
			def nombresBase = params.list("nombreBase")
			def condiciones = [fechaInicio: fechas[0], fechaFin: fechas[1], subestados: subestados]
			def condicionesGestion = [fechaInicio: fechas[0], fechaFin: fechas[1]]
			String sql = "from Adicional a where a.clientes.fechaGestion between :fechaInicio and :fechaFin and a.clientes.subestadoGestion in (:subestados)"
			String sqlGestion = "from Clientes where fechaGestion between :fechaInicio and :fechaFin and subestadoGestion in (:subestados)"
			String sqlHistorial = "from Historial where fechaGestion between :fechaInicio and :fechaFin order by cliente"
			def adicionalesList = Clientes.executeQuery(sql, condiciones)
			def adicionalesGestionList = Clientes.executeQuery(sqlGestion, condiciones)
			def historialGestionList = Clientes.executeQuery(sqlHistorial, condicionesGestion)

			//Empezamos a crear y llenar el Excel
			def webrootDir = servletContext.getRealPath(grailsApplication.config.uploadFolder)
			File file = new File(webrootDir, "temporal.xls")
			WorkbookSettings workbookSettings = new WorkbookSettings()
			workbookSettings.setLocale(new Locale("es", "ES"))
			workbookSettings.setEncoding("Cp1252")
			WritableWorkbook workbook = Workbook.createWorkbook(file, workbookSettings)
			workbook.createSheet("VENTAS", 0)
			//workbook.createSheet("VENTAS", 1)
			WritableFont cellFont = new WritableFont(WritableFont.createFont("Calibri"), 11, WritableFont.BOLD)
			WritableFont cellFont2 = new WritableFont(WritableFont.createFont("Calibri"), 11)
			WritableSheet sheet = workbook.getSheet(0)
			//WritableSheet sheetGestion = workbook.getSheet(1)
			/*String[] headers = ['CANALVENTA',	'TIPO_DOCUMENTO',	'GRUPO',	'TIPO_FIGURA',	'NUMERO_IDENTIFICACION',	'NOMBRES',	'APELLIDO1',
								'APELLIDO2',	'ESTADO_CIVIL',	'GENERO',	'FECHA_NACIMIENTO',	'NACIONALIDAD',	'PROFESION',	'OCUPACION',
								'INGRESOS',	'DIRECCION',	'TELEFONO1',	'TELEFONO2',	'TELEFONO3',	'EMAIL',	'FECHA_EMISION',
								'CANAL',	'CODIGO_PROVINCIA',	'CODIGO_CIUDAD',	'FECHA_FACTURACION',	'NOMBRE_PLAN',	'TIPO_DOC_REL',
								'NRODOCREL',	'TIPO_PARIENTE',	'CUOTA',	'NUMEROCUOTAS',	'VALORCOBRADO',	'MARCA_TARJETA','' ,'TIPO PLAN','VALOR PLAN','FECHA GESTION','NOMBRE VENDEDOR'
								,'ID REGISTRO','CODIGO CAMPANA','CREADAS NO CREADAS','IMPUTABLE','DETALLE IMPUTABLE','FECHA ENVIO CREACION']
			*/
			String[] headers = ['Ramo',	'Póliza',	'Estado Civil',	'Asegurado',	'Parentesco',	'Fecha Nacimiento',	'Edad',
								'Sexo','Canton Casa',	'Cedula', 'Nro_Telefono_Completo_1',	'Nro_Telefono_Completo_2',	'Nro_Telefono_Completo_3',	'Num_Celular',	'Ult_Num_Telefono_Contacto',
								'Correo Electronico',	'CONTRATADO', 'CODIGO_CAMPANA', 'NOMBRE_CAMPANA', 'FECHA GESTION', 'ID SISTEMA', 'CREADAS/NO CREADAS', 'IMPUTABLE AIG/PW', 'DETALLE IMPUTABLE',
			                    'FECHA ENVIO CREACION', 'NOMBRE VENDEDOR', 'NOMBRE BASE ORIGINAL']

			String[] headersGestion = ['Tipo Cliente',	'Destinatario (a quién va dirigido)',	'CI RUC',	'Dirección Entrega',
									   'Horario de Entrega',	'Destinatario Teléfono 1',	'Póliza',	'Producto / Campaña',	'Ciudad',
									   'Callcenter',	'Aceptación',	'Costo',	'Dirección Documentos',	'Evicertia',	'Agente',
									   'Fecha Venta',]

			ExcelUtils.addCells(headers, sheet, 0, Colour.GOLD, Alignment.LEFT, VerticalAlignment.CENTRE, cellFont, Border.ALL, BorderLineStyle.HAIR)
			//ExcelUtils.addCells(headersGestion, sheetGestion, 0, Colour.GOLD, Alignment.LEFT, VerticalAlignment.CENTRE, cellFont, Border.ALL, BorderLineStyle.HAIR)
			for(int i = 0; i < adicionalesGestionList.size(); i++){
				String[] campos = new String[headers.length]
				Clientes cli = adicionalesGestionList.get(i)
				campos[0] = cli.romo
				campos[1] = cli.poliza
				campos[2] = cli.estado_civil
				campos[3] = cli.nombre
				campos[4] = ""
				campos[5] = cli.fechaNacimientoGestion
				campos[6] = cli.edad
				campos[7] = cli.genero
				campos[8] = cli.ciudadAsegurada//+" / "+cli.sectorAsegurada+" / "+cli.callePrincipalAsegurada+" / "+ cli.calleTransversalAsegurada+" / "+ cli.numeracionAsegurada+" / "+cli.referenciaAsegurada
				campos[9] = cli.identificacion
				campos[10] = cli.telefonoGestion1
				campos[11] = cli.telefonoGestion2
				campos[12] = cli.telefonoGestion3
				campos[13] = cli.telefonoContactado
				campos[14] = cli.telefonoContactado
				campos[15] = cli.emailPersonalGestion
				campos[16] = ""
				campos[17] = cli.codigoCampania
				campos[18] = cli.nombreCampania
				campos[19] = cli.fechaGestion.toString()
				campos[20] = cli.id
				campos[21] = cli.creadas_nocreadas
				campos[22] = cli.imputable
				campos[23] = cli.detalle_imputable
				campos[24] = cli.fecha_envio_creacion
				campos[25] = cli.nombreVendedor
				campos[26] = cli.nombre_base_original
				ExcelUtils.addCells(campos, sheet, i+1, Colour.WHITE, Alignment.LEFT, VerticalAlignment.CENTRE, cellFont2, null, null)
			}

	/*		for(int i = 0; i < adicionalesGestionList.size(); i++){
				String[] campos = new String[headers.length]
				Clientes cli = adicionalesGestionList.get(i)
				campos[0] = cli.canalventa
				campos[1] = cli.tipo_documento
				campos[2] = cli.grupo
				campos[3] = cli.tipo_figura
				campos[4] = cli.numero_identificacion
				campos[5] = cli.nombres
				campos[6] = cli.apellido1
				campos[7] = cli.apellido2
				campos[8] = cli.estado_civil
				campos[9] = cli.genero
				campos[10] = cli.fecha_nacimiento
				campos[11] = cli.nacionalidad
				campos[12] = cli.profesion
				campos[13] = cli.ocupacion
				campos[14] = cli.ingresos
				campos[15] = cli.direccion
				campos[16] = cli.telefono1
				campos[17] = cli.telefono2
				campos[18] = cli.telefono3
				campos[19] = cli.email
				campos[20] = cli.fecha_emision
				campos[21] = cli.canal
				campos[22] = cli.codigo_provincia
				campos[23] = cli.codigo_ciudad
				campos[24] = cli.fecha_facturacion
				campos[25] = cli.nombre_plan
				campos[26] = cli.tipo_doc_rel
				campos[27] = cli.nrodocrel
				campos[28] = cli.tipo_pariente
				campos[29] = cli.cuota
				campos[30] = cli.numerocuotas
				campos[31] = cli.valorcobrado
				campos[32] = cli.marca_tarjeta
				campos[33] = ""
				campos[34] = cli.tipoPlanGestion
				campos[35] = getValorPlan(cli.tipoPlanGestion)
				campos[36] = cli.fechaGestion.toString()
				campos[37] = cli.nombreVendedor
				campos[38] = cli.id
				campos[39] = cli.codigoCampania
				campos[40] = cli.creadas_nocreadas
				campos[41] = cli.imputable
				campos[42] = cli.detalle_imputable
				campos[43] = cli.fecha_envio_creacion
				ExcelUtils.addCells(campos, sheet, i+1, Colour.WHITE, Alignment.LEFT, VerticalAlignment.CENTRE, cellFont2, null, null)
			}*/
			/*for(int j = 0; j < adicionalesList.size(); j++){
				String[] campos2 = new String[headersGestion.length]
				Adicional adi = adicionalesList.get(j)
				Clientes cli = adi.clientes
				campos2[0] = 'TITULAR'
				campos2[1] = cli.nombre
				campos2[2] = cli.identificacion
				campos2[3] = cli.provinciaEntrega + " / " + cli.ciudadEntrega + " / " + cli.callePrincipalEntrega+" / "+cli.numeracionEntrega+" / "+cli.calleTransversalEntrega+" / "+cli.sectorEntrega+" / "+cli.referenciaEntrega
				campos2[4] = cli.horaEntrega
				campos2[5] = cli.telefonoContactado
				campos2[6] = Producto.findByNombreAndMarca(adi.producto, adi.plan).poliza
				campos2[7] = adi.producto
				campos2[8] = cli.ciudadEntrega
				campos2[9] = "PLUS WIRELESS"
				campos2[10] = "SI"
				campos2[11] = adi.valor
				campos2[12] = cli.provinciaAsegurada + " / " + cli.ciudadAsegurada + " / " + cli.callePrincipalAsegurada+" / "+cli.numeracionAsegurada+" / "+cli.calleTransversalAsegurada+" / "+cli.sectorAsegurada+" / "+cli.referenciaAsegurada
				campos2[13] = "NO"
				campos2[14] = cli.nombreVendedor
				campos2[15] = formatearFechaddMmYyyyHhMm(cli.fechaGestion)
				ExcelUtils.addCells(campos2, sheetGestion, j+1, Colour.WHITE, Alignment.LEFT, VerticalAlignment.CENTRE, cellFont2, null, null)
			}*/

			workbook.write()
			workbook.close()
			response.setHeader("Content-disposition", "filename=BitacoraVentas.xls")
			response.setContentType("application/octet-stream")
			response.outputStream << file.getBytes()
			return
		}
	}

	private String getValorPlan(String plan){
		String salida = ""
		if (plan == 'PLAN A')
			salida = '4,99'
		if (plan == 'PLAN B')
			salida = '9,16'
		if (plan == 'PLAN C')
			salida = '14,68'

		return salida
	}

	def ventas(){
		Date fechaActual = new Date()
		SimpleDateFormat objSDF = new SimpleDateFormat('yyyyMMdd')
		String fecha = objSDF.format(fechaActual)
		if(params.fechas){
			//Obtenemos los datos
			Date[] fechas = Util.formatearFechasReporte(params.fechas.toString())
			ArrayList<SubSubestado> subestados = Subestado.findAllByEnableManagement(true)
			def nombresBase = params.list("nombreBase")
			def condiciones = [fechaInicio: fechas[0], fechaFin: fechas[1], subestados: subestados]
			String sql = "from Adicional a where a.clientes.fechaGestion between :fechaInicio and :fechaFin and a.clientes.subestadoGestion in (:subestados)"
			String sqlPad = "from Clientes where fechaGestion between :fechaInicio and :fechaFin and subestadoGestion in (:subestados) and aceptacionPad = 'SI'"
			def adicionalesList = Clientes.executeQuery(sql, condiciones)
			def adicionalesListPad = Clientes.executeQuery(sqlPad, condiciones)
			//Empezamos a crear y llenar el Excel
			def webrootDir = servletContext.getRealPath(grailsApplication.config.uploadFolder)
			File file = new File(webrootDir, "temporal.xls")
			WorkbookSettings workbookSettings = new WorkbookSettings()
			workbookSettings.setLocale(new Locale("es", "ES"))
			workbookSettings.setEncoding("Cp1252")
			WritableWorkbook workbook = Workbook.createWorkbook(file, workbookSettings)
			workbook.createSheet("Kardex Ventas", 0)
			workbook.createSheet("Kardex PAD", 1)
			WritableFont cellFont = new WritableFont(WritableFont.createFont("Calibri"), 11, WritableFont.BOLD)
			WritableFont cellFont2 = new WritableFont(WritableFont.createFont("Calibri"), 11)
			WritableSheet sheet = workbook.getSheet(0)
			WritableSheet sheetPad = workbook.getSheet(1)
			String[] headers = ['ID', 'NOMBRE SOCIO ADICIONAL', 'NOMBRE EN PLASTICO', 'CEDULA SOCIO ADICIONAL', 'CUPO SOCIO ADICIONAL', 'CORREO ADICIONAL', 'NUMERO CELULAR ADICIONAL',
								'CEDULA SOCIO PRINCIPAL', "NOMBRE DEL PRINCIPAL", 'NUMERO DE LLAMADA', 'FECHA DE VENTA', 'PROVINCIA', 'CIUDAD', 'DIRECCION', 'AGENTE', 'CAMPAC1A',
								'PRODUCTO', 'NOMBRE BASE', 'TELEFONO CONVERCIONAL','','ID CLIENTE PRINCIPAL','ID CLIENTE ADICIONAL', 'PRODUCTO DINERS PRINCIPAL', 'PRODUCTO VISA PRINCIPAL',
								'PRODUCTO VENDER', 'FECHA NACIMIENTO ADICIONAL', 'PRODUCTO ACEPTADO ADICIONAL', 'PARENTESCO', 'SEGURO', 'NOMBRE VENDEDOR', 'GESTION EN LINEA', 'CODIGO OTP',
								'ACEPTACION ARCOTEL', 'PREGUNTA 1', 'PREGUNTA 2', 'PREGUNTA 3', 'PREGUNTA 4', 'TIPO DIRECCION',
								'ACEPTACION VEHICULAR', 'MOTIVO NO ACEPTA', 'OBSERVACION', 'MONTO AHORRO PAD', 'CODIGO OTP PAD', '', 'ID SISTEMA', 'NOMBRE', 'FECHA GESTION']
			String[] headersPad = ['Identification',	'First name',	'Middle name',	'Last name',	'Second last name',	'Numero tarjeta',
								   'Monto mensual',	'Email',	'Cell phone number',	'Otp',	'Origen',	'Otp date',	'User',	'Nombre usuario',
								   'Channel',	'Caso']
			ExcelUtils.addCells(headers, sheet, 0, Colour.GOLD, Alignment.LEFT, VerticalAlignment.CENTRE, cellFont, Border.ALL, BorderLineStyle.HAIR)
			ExcelUtils.addCells(headersPad, sheetPad, 0, Colour.GOLD, Alignment.LEFT, VerticalAlignment.CENTRE, cellFont, Border.ALL, BorderLineStyle.HAIR)
			for(int i = 0; i < adicionalesList.size(); i++){
				String[] campos = new String[headers.length]
				Adicional adi = adicionalesList.get(i)
				Clientes cli = adi.clientes
//				campos[0] = formatearFecha(cli.fechaGestion.toString())//fechaGestion
				campos[0] = cli.callIdLlamada
				campos[1] = adi.primerNombre + " " + adi.segundoNombre + " " + adi.primerApellido + " " + adi.segundoApellido
				campos[2] = adi.nombreTarjeta
				campos[3] = adi.cedula
				campos[4] = adi.cupoOtorgado
				campos[5] = adi.correo.toUpperCase()
				campos[6] = adi.telefonoCelular
				campos[7] = cli.identificacion
				campos[8] = cli.nombres.toUpperCase() + ' ' + cli.apellidos.toUpperCase()
				campos[9] = cli.telefonoContactado
				campos[10] = formatearFecha(cli.fechaGestion.toString())//fechaGestion
				campos[11] = cli.provinciaDomic
				campos[12] = cli.ciudadDomic
				campos[13] = cli.callePrincipalDomic+" "+cli.numeracionDomic+" "+cli.calleTransversalDomic+" "+cli.sectorDomic+" "+cli.referenciaDomic
				campos[14] = cli.usuario.usuario.toUpperCase()
				campos[15] = cli.nombreBase.toUpperCase()
				campos[16] = adi.productoAceptado
				campos[17] = cli.nombreArchivoCliente.toUpperCase()
				campos[18] = cli.telefonoDomContacto
				campos[19] = ""
				campos[20] = cli.id
				campos[21] = adi.id
				campos[22] = cli.productoDiners
				campos[23] = cli.productoVisa
				campos[24] = cli.productoVender
				campos[25] = adi.fechaNacimiento
				campos[26] = adi.tipoTarjeta
				campos[27] = adi.parentesco
				campos[28] = cli.seguroDesgravamen
				campos[29] = cli.nombreVendedor
				campos[30] = adi.gestionLinea
				campos[31] = adi.codigoOtp
				campos[32] = cli.aceptacionArcotel
				campos[33] = cli.utilizacionTarjetaCredito
				campos[34] = cli.tarjetasEfectivo
				campos[35] = cli.recibeInformacion
				campos[36] = cli.canalPrefiere
				campos[37] = cli.entrega
				campos[38] = cli.aceptacionCreditoVehicular
				campos[39] = cli.motivoNoAceptaVehicular
				campos[40] = cli.observacionesNoAcepta
				campos[41] = cli.montoAhorroPad
				campos[42] = cli.codigoOtpPad
				campos[43] = ""
				campos[44] = cli.id
				campos[45] = cli.nombre
				campos[46] = cli.fechaGestion.toString()
				ExcelUtils.addCells(campos, sheet, i+1, Colour.WHITE, Alignment.LEFT, VerticalAlignment.CENTRE, cellFont2, null, null)
			}

			for(int i = 0; i < adicionalesListPad.size(); i++){
				String[] campos = new String[headers.length]
				Clientes cli = adicionalesListPad.get(i)
				campos[0] = cli.identificacion
				campos[1] = cli.primerNombre
				campos[2] = cli.segundoNombre
				campos[3] = cli.primerApellido
				campos[4] = cli.segundoApellido
				campos[5] = ""
				campos[6] = cli.montoAhorroPad
				campos[7] = cli.correoGestion
				campos[8] = cli.telefonoContactado
				campos[9] = cli.codigoOtpPad
				campos[10] = ""
				campos[11] = ""
				campos[12] = cli.usuario.usuario
				campos[13] = cli.usuario.nombre
				campos[14] = ""
				campos[15] = ""
				ExcelUtils.addCells(campos, sheetPad, i+1, Colour.WHITE, Alignment.LEFT, VerticalAlignment.CENTRE, cellFont2, null, null)
			}

			workbook.write()
			workbook.close()
			response.setHeader("Content-disposition", "filename=KardexVentas.xls")
			response.setContentType("application/octet-stream")
			response.outputStream << file.getBytes()
			return
		}
	}



	private int getCaracteres(String texto){
		return texto.length()
	}




	def indicadoresEasyCode(){
		boolean visibilizar = false

		if(params.fechas){
			visibilizar = true
			DecimalFormat df = new DecimalFormat("#.00")
			Date[] fechas = Util.formatearFechasReporte(params.fechas.toString())
			def nombresBase = params.list("nombreBase")
			Date fechaInicio = fechas[0]
			Date fechaFin = fechas[1]
			int totalGestionados = 0
			int totalContactados = 0
			int totalExitosos = 0
			int totalAdicionales = 0
			int totalTarjetasCreadas = 0
			int totalDiferenciaTarjetas = 0
			String totalPorcentajeContactabilidad = ""
			String totalPorcentajeEfectividad = ""
			String totalPorcentajeReales = ""
			def subestados = Subestado.executeQuery("from Subestado where id in (1, 2, 3)")
			def ventasPorUsuario = Clientes.executeQuery("select nombreVendedor, count(*) from Clientes where subestadoGestion in (:subestados) and fechaGestion between :fechaInicio and :fechaFin and nombreBase in (:nombresBase) group by nombreVendedor order by nombreVendedor", [subestados: subestados, fechaInicio: fechaInicio, fechaFin: fechaFin, nombresBase: nombresBase])
			def adicionalesPorUsuario = Adicional.executeQuery("select clientes.nombreVendedor, count(*) from Adicional where clientes.subestadoGestion in (:subestados) and clientes.fechaGestion between :fechaInicio and :fechaFin and clientes.nombreBase in (:nombresBase) group by clientes.nombreVendedor order by clientes.nombreVendedor", [subestados: subestados, fechaInicio: fechaInicio, fechaFin: fechaFin, nombresBase: nombresBase])
			def gestionadosAgente = Clientes.executeQuery("select nombreVendedor, count(*) from Historial where intentos != 0 and fechaGestion between :fechaInicio and :fechaFin and nombreBase in(:nombresBase) group by nombreVendedor order by nombreVendedor", [fechaInicio: fechaInicio, fechaFin: fechaFin, nombresBase: nombresBase])
			def contactadosAgente = Clientes.executeQuery("select nombreVendedor, count(*) from Historial where intentos != 0 and estadoGestion = 'CONTACTADO' and fechaGestion between :fechaInicio and :fechaFin and nombreBase in (:nombresBase) group by nombreVendedor order by nombreVendedor", [fechaInicio: fechaInicio, fechaFin: fechaFin, nombresBase: nombresBase])
			def tarjetasCreadasAgente = Adicional.executeQuery("select clientes.nombreVendedor, count(*) from Adicional where clientes.subestadoGestion in (:subestados) and clientes.fechaGestion between :fechaInicio and :fechaFin and clientes.nombreBase in (:nombresBase) and tarjetaCreada = true group by clientes.nombreVendedor order by clientes.nombreVendedor", [subestados: subestados, fechaInicio: fechaInicio, fechaFin: fechaFin, nombresBase: nombresBase])

			String[][] tablaResult = new String[gestionadosAgente.size()][10]
			//Lleno la matriz de resultados con los resultados de las onsultas anteriores
			for(int i = 0; i < tablaResult.size(); i++){
				tablaResult[i][0] = gestionadosAgente[i][0]
				tablaResult[i][1] = gestionadosAgente[i][1]

				//Lleno información de contactados y % de contactabilidad
				for(int j = 0; j < contactadosAgente.size(); j++){
					if(contactadosAgente[j][0] == gestionadosAgente[i][0]){
						tablaResult[i][2] = contactadosAgente[j][1]
						tablaResult[i][3] = df.format((contactadosAgente[j][1]/ gestionadosAgente[i][1])*100).replace(",", ".")
						break
					}
				}
				//LLeno la información de las ventas
				for(int j = 0; j < ventasPorUsuario.size(); j++){
					if(ventasPorUsuario[j][0] == gestionadosAgente[i][0]){
						tablaResult[i][4] = ventasPorUsuario[j][1]
						break
					}
				}

				//Lleno la información de las TCA y % efectividad
				for(int j = 0; j < adicionalesPorUsuario.size(); j++){
					if(adicionalesPorUsuario[j][0] == gestionadosAgente[i][0]){
						tablaResult[i][5] = adicionalesPorUsuario[j][1]
						tablaResult[i][6] = df.format((adicionalesPorUsuario[j][1]/ gestionadosAgente[i][1])*100).replace(",", ".")
						break
					}
				}

				//Lleno la información de tarjetas creadas
				for(int j = 0; j < tarjetasCreadasAgente.size(); j++){
					if(tarjetasCreadasAgente[j][0] == gestionadosAgente[i][0]){
						tablaResult[i][7] = tarjetasCreadasAgente[j][1]
						if(contactadosAgente[j][1] != null && contactadosAgente[j][1] != 0){
							tablaResult[i][8] = df.format((tarjetasCreadasAgente[j][1]/ contactadosAgente[i][1])*100).replace(",", ".")
						}else{
							tablaResult[i][8] = 0
						}
						println "********"
						println adicionalesPorUsuario[j][1]
						println tarjetasCreadasAgente[j][1]
						println "********"
						tablaResult[i][9] = adicionalesPorUsuario[j][1] - tarjetasCreadasAgente[j][1]
					}
				}
			}
			//Recorro la matriz de resultados para obtener los totales
			for(int i = 0; i < tablaResult.size(); i++){
				totalGestionados = totalGestionados + tablaResult[i][1].toInteger()
				if(tablaResult[i][2] != null)
					totalContactados = totalContactados + tablaResult[i][2].toInteger()
				if(tablaResult[i][4] != null)
					totalExitosos = totalExitosos + tablaResult[i][4].toInteger()
				if(tablaResult[i][5] != null)
					totalAdicionales = totalAdicionales + tablaResult[i][5].toInteger()
				if(tablaResult[i][7] != null)
					totalTarjetasCreadas = totalTarjetasCreadas + tablaResult[i][7].toInteger()
			}

			if(totalGestionados == 0){
				totalPorcentajeContactabilidad = "0.00"
				totalPorcentajeEfectividad = "0.00"
			}else{
				totalPorcentajeContactabilidad = df.format((totalContactados/totalGestionados)*100)
				totalPorcentajeEfectividad = df.format((totalExitosos/totalGestionados)*100)
			}
			if(totalContactados == 0){
				totalPorcentajeReales = '0.00'
			}else{
				totalPorcentajeReales = df.format((totalTarjetasCreadas/totalContactados)*100)
			}
			totalDiferenciaTarjetas = totalAdicionales-totalTarjetasCreadas
			[visibilizar: visibilizar, tablaResult: tablaResult, totalGestionados: totalGestionados, totalContactados: totalContactados, totalExitosos: totalExitosos, totalPorcentajeContactabilidad: totalPorcentajeContactabilidad, totalAdicionales: totalAdicionales, totalPorcentajeEfectividad: totalPorcentajeEfectividad, totalTarjetasCreadas: totalTarjetasCreadas, totalPorcentajeReales: totalPorcentajeReales, totalDiferenciaTarjetas: totalDiferenciaTarjetas]
		}
	}

	def bitacoraNovedades(){
		if(params.fechas){
			//Obtenemos los datos
			Date[] fechas = Util.formatearFechasReporte(params.fechas.toString())
			ArrayList<SubSubestado> subestados = Subestado.findAllByEnableManagement(true)
			def nombresBase = params.list("nombreBase")
			def condiciones = [fechaInicio: fechas[0], fechaFin: fechas[1], subestados: subestados, nombresBase: nombresBase]
			String sql = "from ClientesNovedades c where c.fechaGestion between :fechaInicio and :fechaFin and c.subestadoGestion in (:subestados) and c.nombreBase in (:nombresBase)"
			def clientes = ClientesNovedades.executeQuery(sql, condiciones)

			//Empezamos a crear y llenar el Excel
			def webrootDir = servletContext.getRealPath(grailsApplication.config.uploadFolder)
			File file = new File(webrootDir, "temporal.xls")
			WorkbookSettings workbookSettings = new WorkbookSettings()
			workbookSettings.setLocale(new Locale("es", "ES"))
			workbookSettings.setEncoding("Cp1252")
			WritableWorkbook workbook = Workbook.createWorkbook(file, workbookSettings)
			workbook.createSheet("BitacoraNovedades", 0)
			WritableSheet sheet = workbook.getSheet(0)
			String[] headers = ["FECHA_GESTION", "MES_GESTION", "AÑO_GESTION", "FECHA_CONSOLIDADO", "NOMBRE_CALLCENTER", "USUARIO", "TIPO_ID", "ID_ADICIONAL",
								"NACIONALIDAD", "PRIMER_APELLIDO", "SEGUNDO_APELLIDO", "PRIMER_NOMBRE", "SEGUNDO_NOMBRE", "NOMBRE_TARJETA",
								"FECHA_NACIMIENTO", "SEXO", "ESTADO_CIVIL", "PARENTESCO", "OBSERVACION", "CUPO", "CUENTA_TITULAR", "ID_TITULAR", "PRODUCTO",
								"NOMBRE_COMPLETO_TITULAR", "PROVINCIA_TRABAJO", "CIUDAD_TRABAJO", "CALLE_PRINCIPAL_TRABAJO", "NUMERACION_TRABAJO", "CALLE_TRANSVERSAL_TRABAJO",
								"SECTOR_BARRIO_TRABAJO", "CASA_DEPARTAMENTO_TRABAJO", "REFERENCIA_TRABAJO", "INFORMACION_CONCATENADA_TRABAJO", "CARACTERES_TRABAJO",
								"PROVINCIA_DOMICILIO", "CIUDAD_DOMICILIO", "CALLE_PRINCIPAL_DOMICILIO", "NUMERACION_DOMICILIO", "CALLE_TRANSVERSAL_DOMICILIO",
								"SECTOR_BARRIO_DOMICILIO", "CASA_DEPARTAMENTO_DOMICILIO", "REFERENCIA_DOMICILIO", "INFORMACION_CONCATENADA_DOMICILIO", "CARACTERES_DOMICILIO",
								"TRABAJO_DOMICILIO", "PERSONA_DE_CONTACTO", "RANGO_VISITA", "CELULAR", "TELEFONO_TRABAJO", "TELEFONO_CASA", "EMISION_ESTADO_CTA_DIGITAL",
								"FECHA_CREACION", "NUMERO_TARJETA_ADICIONAL", "CREADA", "MOTIVO", "ESTADO", "CODIGO_CAMPANA"]
			ExcelUtils.addCells(headers, sheet, 0, Colour.GRAY_25, Alignment.LEFT, VerticalAlignment.CENTRE, null, Border.ALL, BorderLineStyle.HAIR)

			for(int i = 0; i < clientes.size(); i++){

				ArrayList<Adicional> adicionales = new ArrayList<>()

				ClientesNovedades clienteNovedades = clientes.get(i)
				String identificacionNovedad = clienteNovedades.idTitular.length() > 10 ? clienteNovedades.idTitular.substring(clienteNovedades.idTitular.length()-10, clienteNovedades.idTitular.length()) : clienteNovedades.idTitular
				Clientes clientePrincipal = Clientes.executeQuery("from Clientes where concat('0', identificacion) like :identificacionNovedad order by id desc", [identificacionNovedad: '%'+identificacionNovedad+'%', max: 1]).get(0)
				if(clientePrincipal)
					adicionales = Adicional.executeQuery("from Adicional a where a.clientes = :clientePrincipal", [clientePrincipal: clientePrincipal])

				for(int j = 0; j < adicionales.size(); j++){
					Adicional adi = adicionales.get(j)
					String[] campos = new String[headers.length]
					campos[0] = formatearFecha(clienteNovedades.fechaGestion.toString())//fechaGestion
					campos[1] = getMesGestion(clienteNovedades.fechaGestion.toString())//mesGestion
					campos[2] = getAnioGestion(clienteNovedades.fechaGestion.toString())//anioGestion
					campos[3] = formatearFecha(clienteNovedades.fechaGestion.toString())//fechaConsolidado
					campos[4] = "PLUS WIRELESS"//nombreCall
					campos[5] = clienteNovedades.nombreVendedor.toUpperCase()//usuario
					campos[6] = "CEDULA"//tipoId
					campos[7] = adi.cedula//idAdicional
					campos[8] = adi.nacionalidad//nacionalidad
					campos[9] = adi.primerApellido
					campos[10] = adi.segundoApellido
					campos[11] = adi.primerNombre
					campos[12] = adi.segundoNombre
					campos[13] = adi.nombreTarjeta
					campos[14] = formatearFecha(adi.fechaNacimiento.replace('/', '-'))
					campos[15] = adi.sexo
					campos[16] = adi.estadoCivil
					campos[17] = adi.parentesco
					campos[18] = ''
					campos[19] = adi.cupoOtorgado
					campos[20] = clientePrincipal?.cuenta
					campos[21] = formatearCedula(clienteNovedades.idTitular)//idTitular
					campos[22] = clientePrincipal?.producto
					campos[23] = concatenarNombres(clientePrincipal?.nombres, clientePrincipal?.apellidos)
					campos[24] = clienteNovedades.provinciaTrab
					campos[25] = clienteNovedades.ciudadTrab
					campos[26] = clienteNovedades.callePrincipalTrab
					campos[27] = clienteNovedades.numeracionTrab
					campos[28] = clienteNovedades.calleTransversalTrab
					campos[29] = clienteNovedades.sectorTrab
					campos[30] = clienteNovedades.tipoTrab
					campos[31] = clienteNovedades.referenciaTrab
					campos[32] = clienteNovedades.callePrincipalTrab+" "+clienteNovedades.numeracionTrab+" "+clienteNovedades.calleTransversalTrab+" "+clienteNovedades.sectorTrab+" "+clienteNovedades.referenciaTrab
					campos[33] = campos[32].length()
					campos[34] = clienteNovedades.provinciaDomic
					campos[35] = clienteNovedades.ciudadDomic
					campos[36] = clienteNovedades.callePrincipalDomic
					campos[37] = clienteNovedades.numeracionDomic
					campos[38] = clienteNovedades.calleTransversalDomic
					campos[39] = clienteNovedades.sectorDomic
					campos[40] = clienteNovedades.tipoVivienda
					campos[41] = clienteNovedades.referenciaDomic
					campos[42] = clienteNovedades.callePrincipalDomic+" "+clienteNovedades.numeracionDomic+" "+clienteNovedades.calleTransversalDomic+" "+clienteNovedades.sectorDomic+" "+clienteNovedades.referenciaDomic
					campos[43] = campos[42].length()
					campos[44] = clienteNovedades.entrega
					campos[45] = clienteNovedades.nombreContacto
					campos[46] = clienteNovedades.rangoVisita
					campos[47] = clienteNovedades.celularContacto
					campos[48] = clienteNovedades.telefonoTrabContacto
					campos[49] = clienteNovedades.telefonoDomContacto
					campos[50] = clienteNovedades.estadoCtaDigital
					campos[51] = ''
					campos[52] = ''
					campos[53] = ''
					campos[54] = ''
					campos[55] = ''
					campos[56] = clienteNovedades.nombreBase
					ExcelUtils.addCells(campos, sheet, i+1, Colour.WHITE, Alignment.LEFT, VerticalAlignment.CENTRE, null, null, null)
				}

			}

			workbook.write()
			workbook.close()
			response.setHeader("Content-disposition", "filename=BitacoraNovedades.xls")
			response.setContentType("application/octet-stream")
			response.outputStream << file.getBytes()
			return
		}
	}

	private String formatearFecha(String fecha){
		String salida = ""
		if(fecha != null && fecha.trim() != ""){
			String[] fechaArray = fecha.toString().split(' ')
			String[] fechaDescompuesta = fechaArray[0].split('-')
			salida = fechaDescompuesta[2] + "/" + fechaDescompuesta[1] + "/" + fechaDescompuesta[0]
		}
		return salida
	}

	private String formatearFechaddMmYyyyHhMm(Date entrada){

		SimpleDateFormat objSDF = new SimpleDateFormat('dd/MM/yyyy HH:mm')
		String fecha = objSDF.format(entrada)

		/*String salida = ""
		if(fecha != null && fecha.trim() != ""){
			String[] fechaArray = fecha.toString().split(' ')
			String[] fechaDescompuesta = fechaArray[0].split('-')
			salida = fechaDescompuesta[2] + "/" + fechaDescompuesta[1] + "/" + fechaDescompuesta[0]
		}*/
		return fecha
	}



	private String getMesGestion(String fecha){
		String salida = ""
		if(fecha != null && fecha.trim() != ""){
			String[] fechaArray = fecha.toString().split(' ')
			String[] fechaDescompuesta = fechaArray[0].split('-')
			String[] meses = ["ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"]
			salida = meses[fechaDescompuesta[1].toInteger()-1]
		}
		return salida
	}

	private String getAnioGestion(String fecha){
		String salida = ""
		if(fecha != null && fecha.trim() != ""){
			String[] fechaArray = fecha.toString().split(' ')
			String[] fechaDescompuesta = fechaArray[0].split('-')
			salida = fechaDescompuesta[0]
		}
		return salida
	}

	private String formatearCedula(String cedula){
		cedula = cedula.trim()
		if(cedula.length() > 10){
			cedula = cedula.reverse().substring(0, 10).reverse()
		}else{
			cedula = cedula.padLeft(10, '0')
		}
		return cedula
	}

	private String concatenarNombres(String nombres, String apellidos){
		String nom = ""
		String ape = ""
		if(nombres != null)
			nom = nombres
		if(apellidos != null)
			ape = apellidos
		return nom + " " + ape
	}

	private String separarNombres(String nombres, String apellidos, String tipo){
		String separador = ""
		String [] partsNombres = nombres.trim().split(' ')
		String [] partsApellidos = apellidos.trim().split(' ')
		if(nombres != null){
			for (int i= 0; i < partsNombres.length; i++) {
				for (int j = 0; j < partsApellidos.length; j++) {
					if (tipo.equalsIgnoreCase("1")) {
						separador = partsNombres[0]
					}
					if (tipo.equalsIgnoreCase("2")) {
						separador = partsNombres[i]
					}
					if (tipo.equalsIgnoreCase("3")) {
						separador = partsApellidos[0]
					}
					if (tipo.equalsIgnoreCase("4")) {
						separador = partsApellidos[j]
						if (separador == null){
							separador = ""
						}
					}
				}
			}
		}

		return separador
	}

	private String separarApellidos(String apellidos, String tipo){
		String separador1 = ""
		//String [] partsNombres = nombres.trim().split(' ')
		String [] partsApe = apellidos.trim().split(' ')
		if(apellidos != null){
			for (int i= 0; i < partsApe.length; i++) {
			//	for (int j = 0; j < partsApellidos.length; j++) {
					if (tipo.equalsIgnoreCase("1")) {
						separador1 = partsApe[i]
					}
					if (tipo.equalsIgnoreCase("2")) {
						separador1 = partsApe[i]
					}
			//	}
			}
		}

		return separador1
	}

	def indicadoresGestion(){
		boolean visibilizar = false
		if(params.fechas){
			visibilizar = true
			DecimalFormat df = new DecimalFormat("#.00")
			Date[] fechas = Util.formatearFechasReporte(params.fechas.toString())
			def nombresBase = params.list("nombreBase")
			Date fechaInicio = fechas[0]
			Date fechaFin = fechas[1]

			int Exitosas = 0
			int Contactados = 0
			int NoDesea = 0
			int exitosasPdp = 0
			int exitosasCredito = 0
			double Efectividad = 0
			double EfectividadPdp = 0
			double EfectividadCredito = 0
			double NoDeseaPorcentaje = 0

			int totalGestionados = 0
			int totalContactados = 0
			int totalNoContactados = 0
			int totalExitosos = 0
			int totalCruzadasCU2 = 0
			int totalCruzadasCU3 = 0
			int totalNoDeseaCU5 = 0
			int totalAdicionales = 0
			int totalPDP = 0
			int totalTarjetasCreadas = 0
			int totalDiferenciaTarjetas = 0
			String totalPorcentajeContactabilidad = ""
			String totalPorcentajeEfectividad = ""
			String totalPorcentajeEfectividadPdp = ""
			String totalPorcentajeEfectividadCredito = ""
			String totalPorcentajeReales = ""
			def subestadosEfectivos = Subestado.executeQuery("from Subestado where id in (1,3)")
			def subestadosCruzadas = Subestado.executeQuery("from Subestado where id in (2)")
			def subestadosCU3 = Subestado.executeQuery("from Subestado where id in (3)")
			def subestadosCU5 = Subestado.executeQuery("from Subestado where id in (5)")
			def ventasPorUsuario = Clientes.executeQuery("select nombreVendedor, count(*), substring(fechaGestion,1,10) from Historial where (motivoNoDesea = 'TDC ADICIONAL' or motivoNoDesea = 'TDC ADICIONAL+ASISTENCIA') and fechaGestion between :fechaInicio and :fechaFin group by nombreVendedor, substring(fechaGestion,1,10) order by nombreVendedor", [fechaInicio: fechaInicio, fechaFin: fechaFin])
			def cruzadasPorUsuario = Clientes.executeQuery("select nombreVendedor, count(*), substring(fechaGestion,1,10) from Historial where (motivoNoDesea = 'PLAN DEUDA PROTEGIDA' or motivoNoDesea = 'TDC ADICIONAL+ASISTENCIA') and fechaGestion between :fechaInicio and :fechaFin group by nombreVendedor , substring(fechaGestion,1,10) order by nombreVendedor", [fechaInicio: fechaInicio, fechaFin: fechaFin])
			def CU3PorUsuario = Clientes.executeQuery("select nombreVendedor, count(*), substring(fechaGestion,1,10) from Historial where estadoGestion = 'CONTACTOS EFECTIVOS' and cliente.deudaProtegida = 'SI OFERTAR PLAN DEUDA PROTEGIDA' and fechaGestion between :fechaInicio and :fechaFin group by nombreVendedor, substring(fechaGestion,1,10) order by nombreVendedor", [fechaInicio: fechaInicio, fechaFin: fechaFin])
			def noDeseaPorUsuario = Clientes.executeQuery("select nombreVendedor, count(*), substring(fechaGestion,1,10) from Historial where subestadoGestion in (:subestadosCU5) and fechaGestion between :fechaInicio and :fechaFin group by nombreVendedor , substring(fechaGestion,1,10) order by nombreVendedor", [subestadosCU5: subestadosCU5, fechaInicio: fechaInicio, fechaFin: fechaFin])
			def gestionadosAgente = Clientes.executeQuery("select nombreVendedor, count(*), substring(fechaGestion,1,10) from Historial where intentos != 0 and fechaGestion between :fechaInicio and :fechaFin group by nombreVendedor, substring(fechaGestion,1,10) order by nombreVendedor", [fechaInicio: fechaInicio, fechaFin: fechaFin])
			def contactadosAgente = Clientes.executeQuery("select nombreVendedor, count(*), substring(fechaGestion,1,10) from Historial where intentos != 0 and estadoGestion = 'CONTACTOS EFECTIVOS' and fechaGestion between :fechaInicio and :fechaFin group by nombreVendedor, substring(fechaGestion,1,10) order by nombreVendedor", [fechaInicio: fechaInicio, fechaFin: fechaFin])
			def noContactadosAgente = Clientes.executeQuery("select nombreVendedor, count(*), substring(fechaGestion,1,10) from Historial where intentos != 0 and estadoGestion = 'NO CONTACTO' and fechaGestion between :fechaInicio and :fechaFin group by nombreVendedor, substring(fechaGestion,1,10) order by nombreVendedor", [fechaInicio: fechaInicio, fechaFin: fechaFin])
			def adicionalesPorUsuario = Adicional.executeQuery("select clientes.nombreVendedor, count(*), substring(clientes.fechaGestion,1,10) from Adicional where clientes.subestadoGestion in (:subestadosEfectivos) and clientes.fechaGestion between :fechaInicio and :fechaFin group by clientes.nombreVendedor, substring(clientes.fechaGestion,1,10) order by clientes.nombreVendedor", [subestadosEfectivos: subestadosEfectivos, fechaInicio: fechaInicio, fechaFin: fechaFin])

			String[][] tablaResult = new String[gestionadosAgente.size()][13]
			//Lleno la matriz de resultados con los resultados de las onsultas anteriores
			for(int i = 0; i < tablaResult.size(); i++){
				tablaResult[i][0] = gestionadosAgente[i][0]
				tablaResult[i][1] = gestionadosAgente[i][1]
				tablaResult[i][10] = gestionadosAgente[i][2]

				//Lleno información de contactados y % de contactabilidad
				for(int j = 0; j < contactadosAgente.size(); j++){
					if(contactadosAgente[j][0] == gestionadosAgente[i][0] && contactadosAgente[j][2] == gestionadosAgente[i][2]){
						tablaResult[i][2] = contactadosAgente[j][1]
						Contactados = tablaResult[i][2].toInteger()
						break
					}
				}
				if(tablaResult[i][2] == null){
					Contactados = 0
				}
				//Lleno información de no contactados
				for(int j = 0; j < adicionalesPorUsuario.size(); j++){
					if(adicionalesPorUsuario[j][0] == gestionadosAgente[i][0] && adicionalesPorUsuario[j][2] == gestionadosAgente[i][2]){
						tablaResult[i][11] = adicionalesPorUsuario[j][1]
						break
					}
				}
				//LLeno la información de las ventas
				for(int j = 0; j < ventasPorUsuario.size(); j++){
					if(ventasPorUsuario[j][0] == gestionadosAgente[i][0] && ventasPorUsuario[j][2] == gestionadosAgente[i][2]){
						tablaResult[i][4] = ventasPorUsuario[j][1]
						Exitosas = tablaResult[i][4].toInteger()
						break
					}
				}
				if(tablaResult[i][4] == null){
					Exitosas = 0
				}
				//LLeno la información de las ventas cruzadas
				for(int j = 0; j < cruzadasPorUsuario.size(); j++){
					if(cruzadasPorUsuario[j][0] == gestionadosAgente[i][0] && cruzadasPorUsuario[j][2] == gestionadosAgente[i][2]){
						tablaResult[i][5] = cruzadasPorUsuario[j][1]
						exitosasPdp = tablaResult[i][5].toInteger()
						break
					}
				}
				if(tablaResult[i][5] == null){
					exitosasPdp = 0
				}

				//LLeno la información de las ventas CU3
				for(int j = 0; j < CU3PorUsuario.size(); j++){
					if(CU3PorUsuario[j][0] == gestionadosAgente[i][0] && CU3PorUsuario[j][2] == gestionadosAgente[i][2]){
						tablaResult[i][6] = CU3PorUsuario[j][1]
						exitosasCredito = tablaResult[i][6].toInteger()
						break
					}
				}
				if(tablaResult[i][6] == null){
					exitosasCredito = 0
				}

				//LLeno la información de los No Desea
				for(int j = 0; j < noDeseaPorUsuario.size(); j++){
					if(noDeseaPorUsuario[j][0] == gestionadosAgente[i][0] && noDeseaPorUsuario[j][2] == gestionadosAgente[i][2]){
						tablaResult[i][7] = noDeseaPorUsuario[j][1]
						NoDesea = tablaResult[i][7].toInteger()
						break
					}
				}
				if(tablaResult[i][7] == null){
					NoDesea = 0
				}

				//Pra calcular los procentajes de efectividad y no desea
				if(Exitosas != 0 && Exitosas != null){
					Efectividad = (Exitosas / Contactados) * 100
					tablaResult[i][8] = df.format(Double.parseDouble(Efectividad.toString())).replace(".",",")
					//tablaResult[i][8] = 0
				}else{
					tablaResult[i][8] = 0
				}

				if(exitosasPdp != 0 && exitosasPdp != null && exitosasCredito != 0 && exitosasCredito != null){
					EfectividadPdp = (exitosasPdp / exitosasCredito) * 100
					tablaResult[i][3] = df.format(Double.parseDouble(EfectividadPdp.toString())).replace(".",",")
					// println(exitosasPdp + " " + exitosasCredito + " " + EfectividadPdp)
				}else{
					tablaResult[i][3] = 0
				}

				if(NoDesea != 0 && NoDesea != null){
					NoDeseaPorcentaje = (NoDesea / Contactados) * 100
					tablaResult[i][9] = df.format(Double.parseDouble(NoDeseaPorcentaje.toString())).replace(".",",")
					//tablaResult[i][9] = 0
				}else{
					tablaResult[i][9] = 0
				}

				if(exitosasCredito != 0 && exitosasCredito != null){
					EfectividadCredito = (exitosasCredito / Contactados) * 100
					tablaResult[i][12] = df.format(Double.parseDouble(EfectividadCredito.toString()))
					//tablaResult[i][9] = 0
				}else{
					tablaResult[i][12] = 0
				}
				//println(Contactados + " " + Exitosas + " " + NoDesea)
			}

			//Recorro la matriz de resultados para obtener los totales
			for(int i = 0; i < tablaResult.size(); i++){
				totalGestionados = totalGestionados + tablaResult[i][1].toInteger()
				if(tablaResult[i][2] != null)
					totalContactados = totalContactados + tablaResult[i][2].toInteger()

				if(tablaResult[i][4] != null)
					totalExitosos = totalExitosos + tablaResult[i][4].toInteger()
				if(tablaResult[i][5] != null)
					totalCruzadasCU2 = totalCruzadasCU2 + tablaResult[i][5].toInteger()
				if(tablaResult[i][6] != null)
					totalCruzadasCU3 = totalCruzadasCU3 + tablaResult[i][6].toInteger()
				if(tablaResult[i][7] != null)
					totalNoDeseaCU5 = totalNoDeseaCU5 + tablaResult[i][7].toInteger()
				if(tablaResult[i][11] != null)
					totalAdicionales = totalAdicionales + tablaResult[i][11].toInteger()


			}

			if(totalGestionados == 0){
				totalPorcentajeContactabilidad = "0.00"
				totalPorcentajeEfectividad = "0.00"
				totalPorcentajeEfectividadPdp = "0.00"
				totalPorcentajeEfectividadCredito = "0.00"
			}else{
				totalPorcentajeContactabilidad = df.format((totalExitosos/totalContactados)*100)
				totalPorcentajeEfectividad = df.format((totalNoDeseaCU5/totalContactados)*100)
				if(totalCruzadasCU3 != 0){
					totalPorcentajeEfectividadPdp = df.format((totalCruzadasCU2/totalCruzadasCU3)*100)
				}

				totalPorcentajeEfectividadCredito = df.format((totalCruzadasCU3/totalContactados)*100)
			}
			[visibilizar: visibilizar, tablaResult: tablaResult, totalGestionados: totalGestionados, totalContactados: totalContactados,
			 totalNoContactados: totalNoContactados, totalExitosos:totalExitosos, totalCruzadasCU2: totalCruzadasCU2, totalCruzadasCU3: totalCruzadasCU3,
			 totalNoDeseaCU5: totalNoDeseaCU5, totalPorcentajeContactabilidad: totalPorcentajeContactabilidad, totalPorcentajeEfectividad: totalPorcentajeEfectividad,
			 totalPorcentajeEfectividadPdp: totalPorcentajeEfectividadPdp, totalPorcentajeEfectividadCredito: totalPorcentajeEfectividadCredito, totalAdicionales: totalAdicionales]
		}
	}

	def tiemposBreak() {
		if (params.fechas) {
			Date[] fechas = Util.formatearFechasReporte(params.fechas.toString())
			ArrayList<SubSubestado> subestados = Subestado.executeQuery("from Subestado where id in (1)")
			def nombresBase = params.list("nombreBase")
			def condiciones = [fechaInicio: fechas[0], fechaFin: fechas[1]]
			String sqlPrincipales = "from BreakTime where dateBreak between :fechaInicio and :fechaFin"
			def principalesList = BreakTime.executeQuery(sqlPrincipales, condiciones)
			//Empezamos a crear y llenar el Excel
			def webrootDir = servletContext.getRealPath(grailsApplication.config.uploadFolder)
			File file = new File(webrootDir, "temporal.xls")
			WorkbookSettings workbookSettings = new WorkbookSettings()
			workbookSettings.setLocale(new Locale("es", "ES"))
			workbookSettings.setEncoding("UTF-8")
			WritableWorkbook workbook = Workbook.createWorkbook(file)
			workbook.createSheet("Clientes Efectivos", 0)
			WritableFont cellFont = new WritableFont(WritableFont.createFont("Calibri"), 11, WritableFont.BOLD)
			cellFont.setColour(Colour.WHITE);
			WritableFont cellFont2 = new WritableFont(WritableFont.createFont("Calibri"), 11)
			WritableSheet sheetPrincipales = workbook.getSheet(0)
			String[] headersPrincipales = ["FECHA/HORA", "TIEMPO", "OPCION", "NOMBRE USUARIO"]
			ExcelUtils.addCells(headersPrincipales, sheetPrincipales, 0, Colour.GREEN, Alignment.LEFT, VerticalAlignment.CENTRE, cellFont, Border.ALL, BorderLineStyle.THIN)
			for (int i = 0; i < principalesList.size(); i++) {
				String[] campos = new String[headersPrincipales.length]
				BreakTime cli = principalesList.get(i)
				campos[0] = cli.dateBreak.toString()
				campos[1] = cli.timeBreak.toString()
				campos[2] = cli.typeBreak
				campos[3] = cli.user.nombre
				ExcelUtils.addCells(campos, sheetPrincipales, i + 1, Colour.WHITE, Alignment.LEFT, VerticalAlignment.CENTRE, cellFont2, null, null)
			}
			workbook.write()
			workbook.close()
			response.setHeader("Content-disposition", "filename=tiemposBreakAsesores.xls")
			response.setContentType("application/octet-stream")
			response.outputStream << file.getBytes()
			return
		}
	}

	def baseGestionada(){
		if(params.fechas){

			Date[] fechas = Util.formatearFechasReporte(params.fechas.toString())
			def nombresBase = params.list("nombreBase")
			def subestados = params.list("subestados")
			def condiciones = [fechaInicio: fechas[0], fechaFin: fechas[1], nombresBase: nombresBase]
			String sql = "from Clientes where fechaGestion between :fechaInicio and :fechaFin and nombreBase in (:nombresBase)"


			def base = Clientes.executeQuery(sql, condiciones)




			//Empezamos a crear y llenar el Excel
			def webrootDir = servletContext.getRealPath(grailsApplication.config.uploadFolder)
			File file = new File(webrootDir, "temporal.xls")
			WorkbookSettings workbookSettings = new WorkbookSettings()
			workbookSettings.setLocale(new Locale("es", "ES"))
			workbookSettings.setEncoding("Cp1252")
			WritableWorkbook workbook = Workbook.createWorkbook(file, workbookSettings)
			workbook.createSheet("baseGestionada", 0)
			WritableSheet sheet = workbook.getSheet(0)
			String[] headers = [
					"CEDULA",
					"NOMBRES",
					"ESTADO",
					"SUBESTADO",
					"SUBSUBESTADO",
					"FECHA GESTION",
					"NOMBRE BASE",
					"NOMBRE VENDEDOR",
					"INTENTOS",
					"OBSERVACIONES",
					"TELEFONO CONTACTADO"
			]
			ExcelUtils.addCells(headers, sheet, 0, Colour.GRAY_25, Alignment.LEFT, VerticalAlignment.CENTRE, null, Border.ALL, BorderLineStyle.HAIR)

			for(int i = 0; i < base.size(); i++){
				String[] campos = new String[headers.length]
				Clientes c = base.get(i)

				campos[0] = c.identificacion
				campos[1] = c.nombre
				campos[2] = c.estadoGestion
				campos[3] = c.subestadoGestion.nombre
				campos[4] = c.subSubEstado
				campos[5] = c.fechaGestion.toString()
				campos[6] = c.nombreBase
				campos[7] = c.nombreVendedor
				campos[8] = c.intentos
				campos[9] = c.observaciones
				campos[10] = c.telefonoContactado
				ExcelUtils.addCells(campos, sheet, i+1, Colour.WHITE, Alignment.LEFT, VerticalAlignment.CENTRE, null, null, null)
			}
			workbook.write()
			workbook.close()
			response.setHeader("Content-disposition", "filename=baseGestionadaDiferido.xls")
			response.setContentType("application/octet-stream")
			response.outputStream << file.getBytes()
			return

		}
	}


	def bitacoraGestion(){
		if(params.nombresBase){
			//Date[] fechas = Util.formatearFechasReporte(params.fechas.toString())
			def nombresBase = params.list("nombresBase")
			def subestados = params.list("subestados")
			def condiciones = [nombresBase: nombresBase]
			String sql = "from Clientes where nombreBase in (:nombresBase)"
			def base = Clientes.executeQuery(sql, condiciones)

			//Empezamos a crear y llenar el Excel
			def webrootDir = servletContext.getRealPath(grailsApplication.config.uploadFolder)
			File file = new File(webrootDir, "temporal.xls")
			WorkbookSettings workbookSettings = new WorkbookSettings()
			workbookSettings.setLocale(new Locale("es", "ES"))
			workbookSettings.setEncoding("Cp1252")
			workbookSettings.setUseTemporaryFileDuringWrite(true)
			WritableWorkbook workbook = Workbook.createWorkbook(file, workbookSettings)
			workbook.createSheet("baseGestionada", 0)
			WritableFont cellFont = new WritableFont(WritableFont.TAHOMA, 10, WritableFont.BOLD)
			WritableFont cellFont2 = new WritableFont(WritableFont.createFont("Calibri"), 11)
			cellFont.setColour(Colour.WHITE)
			WritableSheet sheet = workbook.getSheet(0)
			String[] headers = ['EASYCODE',	'BASE',	'CAMPAC1A',	'CEDULA',	'CLIENTE',	'CAMPANA',	'TELEFONO LLAMADA',
								'AGENTE',	'CODIGO_AGENTE',	'FECHA_GESTION',	'ESTADO GESTION',	'ESTADO TELEFONICO',
								'GESTION',	'TIPO GESTION',	'CONTEO DE MARCACIONES',	'SEGURO',	'PRODUCTO A OFERTAR',
								'NUMERO ADICIONALES',	'PRODUCTO VENDIDO',	'GRUPO',	'CIUDAD',	'ZONA',]
			ExcelUtils.addCells(headers, sheet, 0, Colour.GREEN, Alignment.LEFT, VerticalAlignment.CENTRE, cellFont, Border.ALL, BorderLineStyle.THIN)
			for(int i = 0; i < base.size(); i++){
				String[] campos = new String[headers.length]
				Clientes c = base.get(i)
				String numeroAdicionales = ""
				String producto = ""
				if (c.subestadoGestion != null){
					if(c.subestadoGestion.enableManagement.equals(true)){
						Clientes idCliente = Clientes.findById(c.id.toString().toLong())
						def condicionesAdicional = [idCliente: idCliente]
						String sqlAdicional = "from Adicional a where clientes = :idCliente"
						def datosAdiciones = Adicional.executeQuery(sqlAdicional, condicionesAdicional)
						for(int q = 0; q < datosAdiciones.size(); q++){
							Adicional princ48 = datosAdiciones.get(q)
							producto = princ48.productoAceptado
						}
						numeroAdicionales = datosAdiciones.size()

					}else{
						numeroAdicionales = "0"
						producto = ""
					}
				}
				campos[0] = c.id
				campos[1] = c.nombreArchivoCliente
				campos[2] = c.nombreBase
				campos[3] = c.identificacion
				campos[4] = c.nombres.toUpperCase() + ' ' + c.apellidos.toUpperCase()
				campos[5] = 'ADC_' + c.productoVender
				campos[6] = c.telefonoContactado
				if (c.usuario == null){
					campos[7] = c.usuario
					campos[8] = c.usuario
				}else{
					campos[7] = c.usuario.usuario.toUpperCase()
					campos[8] = c.usuario.id
				}

				if (c.fechaGestion == null){
					campos[9] = c.fechaGestion
				}else{
					campos[9] = c.fechaGestion.toString().substring(0,10).replace("/","-")
				}
				campos[10] = c.estadoGestion
				campos[11] = c.estadoTelefonoContactado
				if (c.subestadoGestion == null){
					campos[12] = c.subestadoGestion
				}else{
					campos[12] = c.subestadoGestion.nombre.toUpperCase()
				}
				if(c.subSubEstado == null){
					campos[13] = c.subSubEstado
				}else{
					campos[13] = c.subSubEstado.toUpperCase()
				}
				campos[14] = c.intentos
				campos[15] = c.seguroDesgravamen
				campos[16] = c.productoVender
				campos[17] = numeroAdicionales
				campos[18] = producto
				campos[19] = c.grupo
				campos[20] = c.ciudad
				campos[21] = c.zona
				ExcelUtils.addCells(campos, sheet, i + 1, Colour.WHITE, Alignment.LEFT, VerticalAlignment.CENTRE, cellFont2, null, null)
			}
			workbook.write()
			workbook.close()
			response.setHeader("Content-disposition", "filename=baseGestionada.xls")
			response.setContentType("application/octet-stream")
			response.outputStream << file.getBytes()
			return

		}
	}

	def loginAgentes(){
		boolean visibilizar = false
		if(params.fechas) {
			visibilizar = true
			Date[] fechas = Util.formatearFechasReporte(params.fechas.toString())
			def nombresBase = params.list("nombreBase")
			Date fechaInicio = fechas[0]
			Date fechaFin = fechas[1]
			def consulta = Clientes.executeQuery("select substring(fechaGestion,1,10), nombreVendedor, substring(min(fechaGestion),11,12), substring(max(fechaGestion),11,12) from Clientes where fechaGestion between :fechaInicio and :fechaFin group by substring(fechaGestion,1,10), nombreVendedor", [fechaInicio: fechaInicio, fechaFin: fechaFin])
			String[][] tablaResult = new String[consulta.size()][5]
			//Lleno la matriz de resultados con los resultados de las onsultas anteriores
			for(int i = 0; i < tablaResult.size(); i++) {
				tablaResult[i][0] = consulta[i][0]
				tablaResult[i][1] = consulta[i][1]
				tablaResult[i][2] = consulta[i][2]
				tablaResult[i][3] = consulta[i][3]
			}
			[visibilizar: visibilizar, tablaResult: tablaResult]
		}
	}

}
