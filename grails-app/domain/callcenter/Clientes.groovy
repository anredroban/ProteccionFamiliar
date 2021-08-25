package callcenter
import java.util.Date;
import com.pw.security.*;

class Clientes {

	//Campos de la base
	String codigoCampania//SI
	String nombreCampania//SI
	String nombre //SI
	String identificacion//SI
	String edad//SI
	String telefono1//SI
	String telefono2//SI
	String telefono3//SI
	String telefono5//SI
	String telefono4//SI
	String telefono6//SI
	String telefono7//SI
	String telefono8//SI
	String telefono9//SI
	String telefono10//SI

	/**//*Carga de BDD*/
	String canalventa
	String tipo_documento
	String grupo
	String tipo_figura
	String numero_identificacion
	String nombres
	String apellido1
	String apellido2
	String estado_civil
	String genero
	String fecha_nacimiento
	String nacionalidad
	String profesion
	String ocupacion
	String ingresos
	String direccion
	String telefono_1
	String telefono_2
	String telefono_3
	String email
	String fecha_emision
	String canal
	String codigo_provincia
	String codigo_ciudad
	String fecha_facturacion
	String nombre_plan
	String tipo_doc_rel
	String nrodocrel
	String tipo_pariente
	String cuota
	String numerocuotas
	String valorcobrado
	String marca_tarjeta


	/*Campos gestion*/
	String emailPersonalGestion
	String provinciaAsegurada
	String ciudadAsegurada
	String sectorAsegurada
	String callePrincipalAsegurada
	String numeracionAsegurada
	String calleTransversalAsegurada
	String referenciaAsegurada
	String provinciaEntrega
	String ciudadEntrega
	String sectorEntrega
	String callePrincipalEntrega
	String numeracionEntrega
	String calleTransversalEntrega
	String referenciaEntrega
	String horaEntrega
	String lugarNacimiento
	String fechaNacimientoGestion
	String gestionLinea
	String codigoOtp
	String tipoPlanGestion
	String telefonoGestion1
	String telefonoGestion2
	String telefonoGestion3


	//Campos que SIEMPRE van en la gestión
	Date fechaGestion
	int intentos
	String estadoGestion
	Subestado subestadoGestion
	String subSubEstado
	Usuario usuario
	String nombreBase
	String nombreVendedor
	Date fechaRellamada
	String observaciones
	String motivoNoDesea
	String telefonoContactado
	boolean isActive
	String registroExitoso
	String codigoAsignacion //SI
	String agendamientoAsesor
	String motivoNoAceptaSeguro

	/*Creacion de campos solicitados por el area de Reporting 2019-05-11*/
	String gama
	String regional
	String rangoEdad
	String rangoCupo
	String segmentacionAd1
	String segmentacionAd2
	String segmentacionAd3
	String segmentacionAd4
	String segmentacionAd5
	String easyCodeRegional
	String easyCodeEdad
	String easyCodeCupo
	String easyCodeEdadCupo
	String easyCodeGamaEdad
	String easyCodeAd1
	String easyCodeAd2
	String easyCodeAd3
	String easyCodeAd4
	String easyCodeAd5
	String prioridadCampania
	String fechaCaducidad
	String deudaProtegida
	String metaContactabilidad
	String metaEfectividadTelefonica
	String metaEfectividadReal
	String tipoGestion
	String plataforma
	String estadoTelefonoContactado
	String numeroOperaciones
	String bloqueSegmentacion
	String tipoProductoGestion
	String creadas_nocreadas
	String imputable
	String detalle_imputable
	String fecha_envio_creacion
	String poliza
	String contratado
	String romo
	String cantonCasa
	String nombre_base_original
	String parentesco


	static constraints = {
		//Campos de la base
		codigoCampania nullable: true
		nombreCampania nullable: true
		identificacion nullable: true
		nombre nullable: true
		edad nullable: true
		telefono1 nullable: true
		telefono2 nullable: true
		telefono3 nullable: true
		telefono4 nullable: true
		telefono5 nullable: true
		telefono6 nullable: true
		telefono7 nullable: true
		telefono8 nullable: true
		telefono9 nullable: true
		telefono10 nullable: true
		codigoAsignacion nullable: true
		nombre_base_original nullable: true


		/*----Encuesta----*/

		//Campos que SIEMPRE van en la gestión
		fechaGestion nullable: true
		intentos nullable: true
		estadoGestion nullable: true
		subestadoGestion nullable: true
		subSubEstado nullable: true
		usuario nullable: true
		nombreBase nullable: true
		nombreVendedor nullable: true
		fechaRellamada nullable: true
		observaciones nullable: true
		motivoNoDesea nullable: true

		isActive nullable: true
		telefonoContactado nullable: true
		motivoNoAceptaSeguro nullable: true
		agendamientoAsesor nullable: true
		registroExitoso nullable: true
		tipoPlanGestion nullable: true
		creadas_nocreadas nullable: true
		imputable nullable: true
		detalle_imputable nullable: true
		fecha_envio_creacion nullable: true

		/*Creacion de campos solicitados por el area de Reporting 2019-05-11*/
		gama nullable: true
		regional nullable: true
		rangoEdad nullable: true
		rangoCupo nullable: true
		segmentacionAd1 nullable: true
		segmentacionAd2 nullable: true
		segmentacionAd3 nullable: true
		segmentacionAd4 nullable: true
		segmentacionAd5 nullable: true
		easyCodeRegional nullable: true
		easyCodeEdad nullable: true
		easyCodeCupo nullable: true
		easyCodeEdadCupo nullable: true
		easyCodeGamaEdad nullable: true
		easyCodeAd1 nullable: true
		easyCodeAd2 nullable: true
		easyCodeAd3 nullable: true
		easyCodeAd4 nullable: true
		easyCodeAd5 nullable: true
		prioridadCampania nullable: true
		fechaCaducidad nullable: true
		deudaProtegida nullable: true
		metaContactabilidad nullable: true
		metaEfectividadTelefonica nullable: true
		metaEfectividadReal nullable: true
		tipoGestion nullable: true
		plataforma nullable: true
		estadoTelefonoContactado nullable: true
		bloqueSegmentacion nullable: true
		numeroOperaciones nullable: true
		tipoProductoGestion nullable: true

		canalventa nullable: true
		tipo_documento nullable: true
		grupo nullable: true
		tipo_figura nullable: true
		numero_identificacion nullable: true
		nombres nullable: true
		apellido1 nullable: true
		apellido2 nullable: true
		estado_civil nullable: true
		genero nullable: true
		fecha_nacimiento nullable: true
		edad nullable: true
		nacionalidad nullable: true
		profesion nullable: true
		ocupacion nullable: true
		ingresos nullable: true
		direccion nullable: true
		telefono_1 nullable: true
		telefono_2 nullable: true
		telefono_3 nullable: true
		email nullable: true
		fecha_emision nullable: true
		canal nullable: true
		codigo_provincia nullable: true
		codigo_ciudad nullable: true
		fecha_facturacion nullable: true
		nombre_plan nullable: true
		tipo_doc_rel nullable: true
		nrodocrel nullable: true
		tipo_pariente nullable: true
		cuota nullable: true
		numerocuotas nullable: true
		valorcobrado nullable: true
		marca_tarjeta nullable: true


		emailPersonalGestion nullable: true
		provinciaAsegurada nullable: true
		ciudadAsegurada nullable: true
		sectorAsegurada nullable: true
		callePrincipalAsegurada nullable: true
		numeracionAsegurada nullable: true
		calleTransversalAsegurada nullable: true
		referenciaAsegurada nullable: true
		provinciaEntrega nullable: true
		ciudadEntrega nullable: true
		sectorEntrega nullable: true
		callePrincipalEntrega nullable: true
		numeracionEntrega nullable: true
		calleTransversalEntrega nullable: true
		referenciaEntrega nullable: true
		horaEntrega nullable: true
		lugarNacimiento nullable: true
		fechaNacimientoGestion nullable: true
		gestionLinea nullable: true
		codigoOtp nullable: true
		poliza nullable: true
		contratado nullable: true
		romo nullable: true
		cantonCasa nullable: true
		parentesco nullable: true
		telefonoGestion1 nullable: true
		telefonoGestion2 nullable: true
		telefonoGestion3 nullable: true
	}

	static mapping = {
		version false
		observaciones type: 'text'
	}

	static String[] getFields(){
		/*String[] fields = ["codigoCampania",
						   "nombreCampania",
						   "identificacion",
						   "nombre",
						   'canalventa',
						   'tipo_documento',
						   'grupo',
						   'tipo_figura',
						   'numero_identificacion',
						   'nombres',
						   'apellido1',
						   'apellido2',
						   'estado_civil',
						   'genero',
						   'fecha_nacimiento',
						   'edad',
						   'nacionalidad',
						   'profesion',
						   'ocupacion',
						   'ingresos',
						   'direccion',
						   'telefono_1',
						   'telefono_2',
						   'telefono_3',
						   'email',
						   'fecha_emision',
						   'canal',
						   'codigo_provincia',
						   'codigo_ciudad',
						   'fecha_facturacion',
						   'nombre_plan',
						   'tipo_doc_rel',
						   'nrodocrel',
						   'tipo_pariente',
						   'cuota',
						   'numerocuotas',
						   'valorcobrado',
						   'marca_tarjeta',
						   "telefono1",
						   "telefono2",
						   "telefono3",
						   "telefono4",
						   "telefono5",
						   "telefono6",
						   "telefono7",
						   "telefono8",
						   "telefono9",
						   "telefono10",
						   "codigoAsignacion"
						   , "gama"
						   , "regional"
						   , "rangoEdad"
						   , "rangoCupo"
						   , "segmentacionAd1"
						   , "segmentacionAd2"
						   , "segmentacionAd3"
						   , "segmentacionAd4"
						   , "segmentacionAd5"
						   , "easyCodeRegional"
						   , "easyCodeEdad"
						   , "easyCodeCupo"
						   , "easyCodeEdadCupo"
						   , "easyCodeGamaEdad"
						   , "easyCodeAd1"
						   , "easyCodeAd2"
						   , "easyCodeAd3"
						   , "easyCodeAd4"
						   , "easyCodeAd5"
						   , "prioridadCampania"
						   , "fechaCaducidad"
						   , "deudaProtegida"
						   , "metaContactabilidad"
						   , "metaEfectividadTelefonica"
						   , "metaEfectividadReal"
						   , "tipoGestion"
						   , "plataforma"
						   , "numeroOperaciones"
						   , "bloqueSegmentacion"
						   , "tipoProductoGestion"
		]*/

		String[] fields = ["codigoCampania",
						   "nombreCampania",
						   "romo",
						   "poliza",
						   "estado_civil",
						   "nombre",
						   "parentesco",
						   "fecha_nacimiento",
						   "edad",
						   "genero",
						   "cantonCasa",
						   "identificacion",
						   "identificacion",
						   "telefono1",
						   "telefono2",
						   "telefono3",
						   "telefonoContactado",
						   "email",
						   "contratado"
						   ]
		return fields
	}
}
