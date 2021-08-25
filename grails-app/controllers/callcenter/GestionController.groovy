package callcenter
import com.pw.security.*
import grails.converters.JSON
import groovy.json.JsonSlurper
import jxl.Cell
import jxl.Sheet
import jxl.Workbook
import jxl.WorkbookSettings
import liquibase.util.file.FilenameUtils
import telephony.LastUniqueId

import java.text.SimpleDateFormat

class GestionController {

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

	/**
	 * @author Giovanny Granda
	 * Muestra en pantalla los clientes asignados
	 * @return
	 */
	def index() {
		Usuario usuario = Usuario.findById(session.user.id);
		def plataforma = 'PURE CLOUD'
//		def clientesGestionar = Clientes.executeQuery("from Clientes c where subestadoGestion.rellamar = 'SI' and usuario = :usuario order by c.intentos", [usuario: usuario])

		def clients = Clientes.withCriteria {
			eq('usuario',usuario)
			eq('isActive', true)
			notEqual('plataforma', plataforma)
			subestadoGestion {
				or{
					eq('type', Subestado.constraints.type.inList[0].toString())
					eq('type', Subestado.constraints.type.inList[1].toString())
				}
			}
			order("intentos")
		}
		def clientsNoManagement = Clientes.withCriteria {
			eq('usuario',usuario)
			eq('isActive', true)
			isNull('subestadoGestion')
			notEqual('plataforma', plataforma)
		}

		clients.each {client ->
			clientsNoManagement.add(client)
		}


		[clientesGestionar: clientsNoManagement]
	}

	/**
	 * @author Giovanny Granda
	 * Muestra la pantalla de gestion donde se hara rectificación de datos
	 * @param id
	 * @return
	 */
	def gestionCliente(long id){
		int idCliente = id
		Clientes cliente = Clientes.findById(idCliente)
		session.setAttribute("lastManageId",cliente.id)
		def lastUniqueId = LastUniqueId.findByClient(cliente)
		if(lastUniqueId != null){
			lastUniqueId.uniqueId = ""
		}
        session.user
		Date fecha = new Date()
		SimpleDateFormat objSDF = new SimpleDateFormat('yyyy-MM-dd HH:mm:ss')
		String fechaInicio = objSDF.format(fecha)
		long idManagement = cliente.id
	//	Clientes.executeUpdate("update Clientes set fechaInicioLlamada = :fechaInicio where id = :idManagement", [fechaInicio: fechaInicio, idManagement: idManagement])
		Calendar datos = Calendar.getInstance()
		int dia = datos.get(Calendar.DATE)
		int mes = datos.get(Calendar.MONTH)
		int anio = datos.get(Calendar.YEAR)
		String fechaActual = anio + '-' + (mes+1) + '-' + dia
		SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd")
		Date fecha1 = sfd.parse(fechaActual.toString())
		Date fecha2 = sfd.parse(cliente.fechaCaducidad)
		String salida = fecha1.compareTo(fecha2)
		String motivo = ''
		boolean isActive = cliente.isActive
		boolean validacion = false
		if(isActive == false){
			motivo = 'REGISTRO INACTIVO'
			validacion = true
		}
		if(salida == "1"){
			motivo = 'BASE CADUCADA'
			validacion = true
		}
		if(salida == "1" && isActive == false){
			motivo = 'BASE CADUCADA Y REGISTRO INACTIVO'
			validacion = true
		}

		if(validacion){
			render(view: "modelFechaCaducada",  model: [idCliente: cliente.id, motivo: motivo])
		}
		[cliente: cliente,usuario: session.user]
	}

	/**
	 * @author Giovanny Granda
	 * Guarda la gestion del call center
	 * @param id
	 * @return
	 */
	def guardarCliente(){
		Usuario usuario = Usuario.findById(session.user.id.toString().toLong())
		Date fechaActual = new Date()
		SimpleDateFormat objSDF = new SimpleDateFormat('yyyy-MM-dd HH:mm:ss')
		String fechaFin = objSDF.format(fechaActual)
		Estado estadoGestion = Estado.findById(params.status.toString().toLong())
		Subestado subestadoGestion = Subestado.findById(params.substatus.toString().toLong())
		String subSubestadoGestion = params.subSubStatus
		Clientes cliente = Clientes.findById(params.idCliente.toString().toLong())
		int intentos = cliente.intentos?: 0
		boolean guardarAdicionalesVar = false

		if(cliente.registroExitoso != "SI"){

		if(subestadoGestion.enableManagement){

			cliente.emailPersonalGestion = params.emailPersonalGestion.toString()
			cliente.lugarNacimiento = removeSpecialCharacters(params.lugarNacimiento.toString()).toUpperCase()
			cliente.fechaNacimientoGestion = params.fechaNacimientoGestion
			cliente.gestionLinea = params.gestionLinea
			cliente.codigoOtp = params.codigoOtp
			cliente.tipoPlanGestion = params.tipoPlanGestion
			cliente.estado_civil = params.estado_civil
			cliente.genero = params.generoGestion
			cliente.telefonoGestion1 = params.telefonoGestion1
			cliente.telefonoGestion2 = params.telefonoGestion2
			cliente.telefonoGestion3 = params.telefonoGestion3

			//DIRECCION ASEGURADA
			if (params.provinciaAsegurada.toString() != ""){
				cliente.provinciaAsegurada = removeSpecialCharacters(Provincia.findById(params.provinciaAsegurada.toString().toLong()).nombre)
			}
			if(params.ciudadAsegurada.toString() != ""){
				cliente.ciudadAsegurada = removeSpecialCharacters(Ciudad.findById(params.ciudadAsegurada.toString().toLong()).nombre)
			}
			cliente.sectorAsegurada = removeSpecialCharacters(params.sectorAsegurada.toString())
			cliente.callePrincipalAsegurada = removeSpecialCharacters(params.callePrincipalAsegurada.toString())
			cliente.numeracionAsegurada = removeSpecialCharacters(params.numeracionAsegurada.toString())
			cliente.calleTransversalAsegurada = removeSpecialCharacters(params.calleTransversalAsegurada.toString())
			cliente.referenciaAsegurada = removeSpecialCharacters(params.referenciaAsegurada.toString())

			//DIRECCION ENTREGA
			/*if (params.provinciaEntrega.toString() != ""){
				cliente.provinciaEntrega = removeSpecialCharacters(Provincia.findById(params.provinciaEntrega.toString().toLong()).nombre)
			}
			if(params.ciudadEntrega.toString() != ""){
				cliente.ciudadEntrega = removeSpecialCharacters(Ciudad.findById(params.ciudadEntrega.toString().toLong()).nombre)
			}
			cliente.sectorEntrega = removeSpecialCharacters(params.sectorEntrega.toString())
			cliente.callePrincipalEntrega = removeSpecialCharacters(params.callePrincipalEntrega.toString())
			cliente.numeracionEntrega = removeSpecialCharacters(params.numeracionEntrega.toString())
			cliente.calleTransversalEntrega = removeSpecialCharacters(params.calleTransversalEntrega.toString())
			cliente.referenciaEntrega = removeSpecialCharacters(params.referenciaEntrega.toString())
			cliente.horaEntrega = params.horaEntrega*/

			//guardarAdicionalesVar = true
			cliente.registroExitoso = 'SI'
		}

//		//Si el subestado no habilita el producto secundario estos campos llegarán vacíoa

		if(subestadoGestion.type.toString().equalsIgnoreCase("Rellamada")){
			cliente.fechaRellamada = new Date().parse('yyyy-MM-dd HH:mm:ss', params.recall.toString().replace('/','-') + ':00')
			cliente.agendamientoAsesor = params.agendamiento
		}else {
			cliente.fechaRellamada = null
		}


		cliente.estadoGestion = estadoGestion.nombre
		cliente.subestadoGestion = subestadoGestion
		cliente.subSubEstado = subSubestadoGestion
		cliente.intentos = intentos+1
		cliente.fechaGestion = fechaActual
		cliente.usuario = usuario
		cliente.nombreVendedor = usuario.nombre
		cliente.telefonoContactado = params.telefonoContactado
		cliente.estadoTelefonoContactado = params.estadoTelefonoContactado
		cliente.observaciones = removeSpecialCharacters(params.observacionesGestion.toString())
		/*if(cliente.save(flush: true) && guardarAdicionalesVar){
			guardarAdicionales(cliente, usuario, params.hidenAdicionales.toString())
		}*/
		cliente.save(flush: true)

		//Se guarda informacion en el historial
		Historial historial = new Historial()
		historial.cliente = Clientes.findById(cliente.id.toLong())
		historial.cedula = cliente.identificacion
		historial.nombre = cliente.nombre
		historial.estadoGestion = cliente.estadoGestion
		historial.subestadoGestion = cliente.subestadoGestion
		historial.motivoNoDesea = cliente.subSubEstado
		historial.fechaGestion = fechaActual
		historial.intentos = cliente.intentos
		historial.nombreVendedor = cliente.nombreVendedor
		historial.observacionesGestion = cliente.observaciones
		historial.nombreBase = cliente.nombreBase
		historial.usuario = cliente.usuario
		historial.telefonoContactado = params.telefonoContactado
		historial.plataforma = cliente.plataforma
		historial.estadoTelefonoContactado = cliente.estadoTelefonoContactado
		historial.uniqueId = null
		historial.save(flush: true)
		session.setAttribute("lastManageId","")
		redirect(uri: "/gestion/index")
		}else{
			render(view: "modelValidacion",  model: [estado:cliente.estadoGestion, subestado: cliente.subestadoGestion.nombre, idCliente: cliente.id])
		}
	}

	def retirarBase(){
		boolean updateRealizado = false
		int resultado = 0
		if(params.usuario != null && params.tipo != null && params.nombrebase != null && params.desde && params.hasta){

			String desde = params.desde
			String hasta = params.hasta

			updateRealizado = true
			Usuario usuarioAdministrador = Usuario.findById(1)
			def subestados
			if(params.tipo != "RELLAMADAS"){
				subestados = Subestado.executeQuery("from Subestado where type = 'Regestionable'")
			}else{
				subestados = Subestado.executeQuery("from Subestado where type = 'Rellamada'")
			}
			String sql = "update Clientes set usuario = :usuario where (subestadoGestion in (:subestados) or subestadoGestion is null) and usuario != :usuario and cast(codigoAsignacion as integer) between :desde and :hasta and isActive = true and plataforma != 'PURE CLOUD'"


			def condiciones = [usuario: usuarioAdministrador, subestados: subestados, desde: desde.toString().toInteger(), hasta: hasta.toString().toInteger()]
			String condicionUsuario = ""
			String condicionTipo = ""
			String condicionNombreBase = ""
			String condicionReferidos = ""

			if(params.usuario != ""){
				Usuario usuarioVendedor = Usuario.findById(params.usuario)
				condicionUsuario = " and usuario = :vendedor"
				condiciones.put("vendedor", usuarioVendedor)
			}

			if(params.tipo != ""){
				if(params.tipo == "REGESTIONABLE"){
					condicionTipo = " and intentos > 0"
				}
				if(params.tipo == "SIN GESTION"){
					condicionTipo = " and intentos = 0"
				}
				if(params.tipo == "RELLAMADAS"){
					condicionTipo = " and intentos > 0 and agendamientoAsesor = 'AGENDAR PARA CUALQUIERA'"
				}
			}

			if(params.nombrebase != ""){
				condicionNombreBase = " and nombreBase = :nombreBase"
				condiciones.put("nombreBase", params.nombrebase)
			}else{
				condicionReferidos = " and nombreBase != 'BASE_REFERIDOS'"
			}

			resultado = Clientes.executeUpdate(sql+condicionUsuario+condicionTipo+condicionNombreBase+condicionReferidos, condiciones)

		}
		[updateRealizado: updateRealizado, resultado: resultado]
	}

	def retirarBaseNovedades(){
		boolean updateRealizado = false
		int resultado = 0
		if(params.usuario != null && params.tipo != null && params.nombrebase != null){

			updateRealizado = true
			Usuario usuarioAdministrador = Usuario.findById(1)
			def subestados = Subestado.executeQuery("from Subestado where type = 'Regestionable'")
			String sql = "update ClientesNovedades set usuario = :usuario where (subestadoGestion in (:subestados) or subestadoGestion is null) and usuario != :usuario"


			def condiciones = [usuario: usuarioAdministrador, subestados: subestados]
			String condicionUsuario = ""
			String condicionTipo = ""
			String condicionNombreBase = ""

			if(params.usuario != ""){
				Usuario usuarioVendedor = Usuario.findById(params.usuario)
				condicionUsuario = " and usuario = :vendedor"
				condiciones.put("vendedor", usuarioVendedor)
			}

			if(params.tipo != ""){
				if(params.tipo == "REGESTIONABLE"){
					condicionTipo = " and intentos > 0"
				}
				if(params.tipo == "SIN GESTION"){
					condicionTipo = " and intentos = 0"
				}
			}

			if(params.nombrebase != ""){
				condicionNombreBase = " and nombreBase = :nombreBase"
				condiciones.put("nombreBase", params.nombrebase)
			}

			resultado = ClientesNovedades.executeUpdate(sql+condicionUsuario+condicionTipo+condicionNombreBase, condiciones)

		}
		[updateRealizado: updateRealizado, resultado: resultado]
	}

	def cargarBase(){

	}

	def saveFile(){
		String[] formFields = Clientes.getFields()
		def file = request.getFile('file')
		Cell[] cells
		String[] headers
		if(file.empty){
			flash.message = "Por favor selecciona un archivo"
		}else{
			def webrootDir = servletContext.getRealPath(grailsApplication.config.uploadFolder) //app directory
			File fileDest = new File(webrootDir,file.getOriginalFilename())
			if(fileDest.mkdirs()){
				println "directory created"
			}else{
				println "directory not created or already exists"
			}
			file.transferTo(fileDest)

			//Reading Excel
			String ext = FilenameUtils.getExtension(fileDest.path)
			if(ext.equalsIgnoreCase("xls") || ext.equalsIgnoreCase("xlsx")){
				try {
					WorkbookSettings ws = new WorkbookSettings()
					ws.setEncoding("Cp1252")
					Workbook workbook = Workbook.getWorkbook(fileDest, ws)
					Sheet sheet = workbook.getSheet(0)
					cells = sheet.getRow(0)
					workbook.close()
				}catch (IOException ex){
					flash.error = "Problemas al cargar el archivo"
					render(view: "index")
				}
				headers = new String[cells.length]
				for(int i = 0; i < cells.length; i++){
					headers[i] = cells[i].getContents()
				}
				render(view: "sortExcel", model: [headers: headers, formFields:formFields, filePath:fileDest.path])
			}else{
				flash.error = "El archivo debe ser una hoja de cálculo de Excel"
				render(view: "index")
			}
		}
	}

	/**
	 * Status
	 * @return
	 */
	def getSubStatusByStatus(){
		if(params.id) {
			def status = Estado.findById(params.id)
			def subStatus = Subestado.findAllByEstado(status)
			def array = [subStatus.id, subStatus.nombre, subStatus.type, subStatus.enableManagement]
			render array as JSON
		}
	}

	/**
	 *
	 */
	def saveAditional(){

		if(request.xhr){
			def aditional = new Adicional()
			aditional.cedula = params.cedula
			aditional.primerNombre = params.primerNombre
			aditional.segundoNombre = params.segundoNombre
			aditional.primerApellido = params.primerApellido
			aditional.segundoApellido = params.segundoApellido
			aditional.nombreTarjeta = params.nombreTarjeta
			aditional.cupoOtorgado = params.cupoOtorgado
			aditional.fechaNacimiento = params.fechaNacimiento
			aditional.estadoCivil = params.estadoCivil
			aditional.nacionalidad = params.nacionalidad
			aditional.parentesco = params.parentesco
			aditional.sexo = params.sexo
			aditional.observaciones = params.observaciones
			aditional.usuario = Usuario.findById(params.usuario.id.toString().toLong())
			aditional.clientes = Clientes.findById(params.clientes.id.toString().toLong())

			if(aditional.save()){
				render true
			}else{
				render false
			}
		}
	}

	/**
	 * make by someone
	 * @param value
	 * @return
	 */
	private removeSpecialCharacters(String value){
		if(value != null){
			def newValue = value.toUpperCase().replace("-"," ").replace("!","").replace("@","").replace("#","").replace("\$","")
					.replace("&","").replace("/","").replace("(","").replace(")","").replace("=","")
					.replace("?","").replace("¿","").replace("ç","").replace("{","").replace("}","")
					.replace("\\","").replace("\"","").replace("Á","A").replace("É","E").replace("Í","I")
					.replace("Ó","O").replace("Ú","U").replace("\'","").replace("  "," ").replace("  "," ")
					.replace("  "," ").replace("%","").replace(".","").replace(",","").replace("º","")
					.replace("ª","").replace("|","").replace("\$","").replace("¬","").replace("%","")
					.replace("*","").replace("+","").replace("_","").replace("Ñ", "N")
			return newValue
		}
	}

	/**
	 *
	 * @param cliente
	 * @param entradaJson
	 * @author Giovanny Granda Vera
	 * Toma una entrada en String donde están los adicionales del cliente dado, la transforma a JSON y guarda en la tabla
	 * de adicionales
	 */
	private guardarAdicionales(Clientes cliente, Usuario usuario, String entradaString){
		if(entradaString != ""){
			def jsonSlurper = new JsonSlurper()
			def object = jsonSlurper.parseText(entradaString)
			for (int i = 0; i < object.size(); i++){
				Adicional adicional = new Adicional()
				adicional.clientes = cliente
				adicional.usuario = usuario
				adicional.producto = object[i].producto
				adicional.tarjetaCreada = false
				adicional.plan = object[i].plan
				adicional.valor = object[i].valor
				adicional.cedula = object[i].producto
				/*adicional.nacionalidad = removeSpecialCharacters(object[i].nacionalidad.toString())
				adicional.primerApellido = removeSpecialCharacters(object[i].primerApellido.toString())
				adicional.segundoApellido = removeSpecialCharacters(object[i].segundoApellido.toString())
				adicional.primerNombre = removeSpecialCharacters(object[i].primerNombre.toString())
				adicional.segundoNombre = removeSpecialCharacters(object[i].segundoNombre.toString())
				adicional.nombreTarjeta = removeSpecialCharacters(object[i].nombreTarjeta.toString())
				adicional.cupoOtorgado = removeSpecialCharacters(object[i].cupoOtorgado.toString())
				adicional.fechaNacimiento = object[i].fechaNacimiento*/
				//def adicionalEnBase = Adicional.findByClientesAndCedula(cliente, adicional.cedula)
				//if(!adicionalEnBase)
					adicional.save(flush: true)
			}
		}
	}

}
