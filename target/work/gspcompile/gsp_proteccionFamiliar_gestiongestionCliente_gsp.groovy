import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_proteccionFamiliar_gestiongestionCliente_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/gestion/gestionCliente.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
invokeTag('captureMeta','sitemesh',1,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("main")],-1)
printHtmlPart(0)
createTagBody(1, {->
createClosureForHtmlPart(1, 2)
invokeTag('captureTitle','sitemesh',2,[:],2)
})
invokeTag('wrapTitleTag','sitemesh',2,[:],1)
printHtmlPart(2)
invokeTag('stylesheet','asset',4,['src':("gestion/gestionCliente.css")],-1)
printHtmlPart(0)
invokeTag('stylesheet','asset',5,['src':("usogeneral/bootstrap-datepicker.min.css")],-1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
if(true && (cliente.registroExitoso == 'SI')) {
printHtmlPart(5)
}
printHtmlPart(6)
if(true && (cliente.nombre != null && cliente.nombre != '')) {
printHtmlPart(7)
expressionOut.print(cliente.nombre)
printHtmlPart(8)
}
printHtmlPart(9)
if(true && (cliente.identificacion != null && cliente.identificacion != '')) {
printHtmlPart(10)
invokeTag('hiddenField','g',38,['name':("identificacion"),'id':("identificacion"),'value':(cliente.identificacion)],-1)
printHtmlPart(11)
invokeTag('hiddenField','g',40,['name':("apellidoPaterno"),'id':("apellidoPaterno"),'value':(cliente.apellido1)],-1)
printHtmlPart(11)
invokeTag('hiddenField','g',45,['name':("apellidoMaterno"),'id':("apellidoMaterno"),'value':(cliente.apellido2)],-1)
printHtmlPart(11)
invokeTag('hiddenField','g',47,['name':("nombres"),'id':("nombres"),'value':(cliente.nombres)],-1)
printHtmlPart(11)
invokeTag('hiddenField','g',47,['name':("estadoCivil"),'id':("estadoCivil"),'value':(cliente.estado_civil)],-1)
printHtmlPart(11)
invokeTag('hiddenField','g',48,['name':("genero"),'id':("genero"),'value':(cliente.genero)],-1)
printHtmlPart(12)
expressionOut.print(cliente.identificacion)
printHtmlPart(8)
}
printHtmlPart(13)
if(true && (cliente.genero != null && cliente.genero != '')) {
printHtmlPart(14)
expressionOut.print(cliente.genero)
printHtmlPart(8)
}
printHtmlPart(9)
if(true && (cliente.fecha_nacimiento != null && cliente.fecha_nacimiento != '')) {
printHtmlPart(15)
expressionOut.print(cliente.fecha_nacimiento)
printHtmlPart(8)
}
printHtmlPart(9)
if(true && (cliente.edad != null && cliente.edad != '')) {
printHtmlPart(16)
expressionOut.print(cliente.edad)
printHtmlPart(8)
}
printHtmlPart(9)
if(true && (cliente.estado_civil != null && cliente.estado_civil != '')) {
printHtmlPart(17)
expressionOut.print(cliente.estado_civil)
printHtmlPart(8)
}
printHtmlPart(9)
if(true && (cliente.marca_tarjeta != null && cliente.marca_tarjeta != '')) {
printHtmlPart(18)
expressionOut.print(cliente.marca_tarjeta)
printHtmlPart(8)
}
printHtmlPart(19)
if(true && (cliente.telefono1)) {
printHtmlPart(20)
expressionOut.print(cliente.telefono1)
printHtmlPart(11)
invokeTag('select','g',103,['class':("form-control"),'id':("estadoTel1"),'name':("estadoTel1"),'from':(['MANEJADA','NO_CONTESTA','LLAMADA_RECHAZADA','CONTESTADORA']),'noSelection':(['': '-- Seleccione --']),'optionValue':("value"),'optionKey':("value")],-1)
printHtmlPart(21)
}
printHtmlPart(22)
if(true && (cliente.telefono2 && cliente.telefono2.trim() != '')) {
printHtmlPart(23)
expressionOut.print(cliente.telefono2)
printHtmlPart(11)
invokeTag('select','g',111,['class':("form-control"),'id':("estadoTel2"),'name':("estadoTel2"),'noSelection':(['': '-- Seleccione --']),'from':(['MANEJADA','NO_CONTESTA','LLAMADA_RECHAZADA','CONTESTADORA'])],-1)
printHtmlPart(8)
}
printHtmlPart(22)
if(true && (cliente.telefono3 && cliente.telefono3.trim() != '')) {
printHtmlPart(24)
expressionOut.print(cliente.telefono3)
printHtmlPart(11)
invokeTag('select','g',118,['class':("form-control"),'id':("estadoTel3"),'name':("estadoTel3"),'noSelection':(['': '-- Seleccione --']),'from':(['MANEJADA','NO_CONTESTA','LLAMADA_RECHAZADA','CONTESTADORA'])],-1)
printHtmlPart(8)
}
printHtmlPart(22)
if(true && (cliente.telefono4 && cliente.telefono4.trim() != '')) {
printHtmlPart(25)
expressionOut.print(cliente.telefono4)
printHtmlPart(11)
invokeTag('select','g',125,['class':("form-control"),'id':("estadoTel4"),'name':("estadoTel4"),'noSelection':(['': '-- Seleccione --']),'from':(['MANEJADA','NO_CONTESTA','LLAMADA_RECHAZADA','CONTESTADORA'])],-1)
printHtmlPart(8)
}
printHtmlPart(22)
if(true && (cliente.telefono5 && cliente.telefono5.trim() != '')) {
printHtmlPart(26)
expressionOut.print(cliente.telefono5)
printHtmlPart(11)
invokeTag('select','g',132,['class':("form-control"),'id':("estadoTel5"),'name':("estadoTel5"),'noSelection':(['': '-- Seleccione --']),'from':(['MANEJADA','NO_CONTESTA','LLAMADA_RECHAZADA','CONTESTADORA'])],-1)
printHtmlPart(8)
}
printHtmlPart(22)
if(true && (cliente.telefono6 && cliente.telefono6.trim() != '')) {
printHtmlPart(27)
expressionOut.print(cliente.telefono6)
printHtmlPart(11)
invokeTag('select','g',139,['class':("form-control"),'id':("estadoTel6"),'name':("estadoTel6"),'noSelection':(['': '-- Seleccione --']),'from':(['MANEJADA','NO_CONTESTA','LLAMADA_RECHAZADA','CONTESTADORA'])],-1)
printHtmlPart(8)
}
printHtmlPart(22)
if(true && (cliente.telefono7 && cliente.telefono7.trim() != '')) {
printHtmlPart(28)
expressionOut.print(cliente.telefono7)
printHtmlPart(11)
invokeTag('select','g',146,['class':("form-control"),'id':("estadoTel7"),'name':("estadoTel7"),'noSelection':(['': '-- Seleccione --']),'from':(['MANEJADA','NO_CONTESTA','LLAMADA_RECHAZADA','CONTESTADORA'])],-1)
printHtmlPart(8)
}
printHtmlPart(22)
if(true && (cliente.telefono8 && cliente.telefono8.trim() != '')) {
printHtmlPart(29)
expressionOut.print(cliente.telefono8)
printHtmlPart(11)
invokeTag('select','g',153,['class':("form-control"),'id':("estadoTel8"),'name':("estadoTel8"),'noSelection':(['': '-- Seleccione --']),'from':(['MANEJADA','NO_CONTESTA','LLAMADA_RECHAZADA','CONTESTADORA'])],-1)
printHtmlPart(8)
}
printHtmlPart(22)
if(true && (cliente.telefono9 && cliente.telefono9.trim() != '')) {
printHtmlPart(30)
expressionOut.print(cliente.telefono9)
printHtmlPart(11)
invokeTag('select','g',160,['class':("form-control"),'id':("estadoTel9"),'name':("estadoTel9"),'noSelection':(['': '-- Seleccione --']),'from':(['MANEJADA','NO_CONTESTA','LLAMADA_RECHAZADA','CONTESTADORA'])],-1)
printHtmlPart(8)
}
printHtmlPart(22)
if(true && (cliente.telefono10 && cliente.telefono10.trim() != '')) {
printHtmlPart(31)
expressionOut.print(cliente.telefono10)
printHtmlPart(11)
invokeTag('select','g',167,['class':("form-control"),'id':("estadoTel10"),'name':("estadoTel10"),'noSelection':(['': '-- Seleccione --']),'from':(['MANEJADA','NO_CONTESTA','LLAMADA_RECHAZADA','CONTESTADORA'])],-1)
printHtmlPart(8)
}
printHtmlPart(32)
if(true && (cliente.email && cliente.email.trim() != '')) {
printHtmlPart(33)
expressionOut.print(cliente.email)
printHtmlPart(8)
}
printHtmlPart(22)
if(true && (cliente.codigoAsignacion && cliente.codigoAsignacion.trim() != '')) {
printHtmlPart(34)
expressionOut.print(cliente.codigoAsignacion)
printHtmlPart(8)
}
printHtmlPart(35)
expressionOut.print(cliente.id)
printHtmlPart(36)
expressionOut.print(cliente.identificacion)
printHtmlPart(37)
invokeTag('select','g',194,['class':("form-control"),'name':("status"),'id':("status"),'from':(callcenter.Estado.list()),'optionKey':("id"),'optionValue':({it.nombre	}),'noSelection':(['': '-- Seleccione --'])],-1)
printHtmlPart(38)
invokeTag('select','g',199,['class':("form-control"),'name':("substatus"),'id':("substatus"),'from':(""),'noSelection':(['': '-- Seleccione --'])],-1)
printHtmlPart(39)
invokeTag('select','g',206,['id':("subSubStatus"),'class':("form-control"),'name':("subSubStatus"),'from':(""),'optionKey':("id")],-1)
printHtmlPart(40)
invokeTag('select','g',216,['class':("form-control"),'name':("motivoNoAceptaSeguro"),'id':("motivoNoAceptaSeguro"),'from':(callcenter.MotivosnoAceptaSeguro.list()),'optionKey':("nombre"),'optionValue':({it.nombre	}),'noSelection':(['': '-- Seleccione --'])],-1)
printHtmlPart(41)
invokeTag('select','g',225,['class':("form-control"),'name':("agendamiento"),'id':("agendamiento"),'from':(['AGENDAR PARA MI':'AGENDAR PARA MI', 'AGENDAR PARA CUALQUIERA':'AGENDAR PARA CUALQUIERA']),'optionKey':("key"),'optionValue':("value"),'noSelection':(['': '-- Seleccione --'])],-1)
printHtmlPart(42)
invokeTag('textField','g',228,['id':("recall"),'name':("recall"),'class':("recall form-control")],-1)
printHtmlPart(43)
invokeTag('textField','g',235,['maxlength':("10"),'minlength':("9"),'id':("telefonoContactado"),'name':("telefonoContactado"),'class':("form-control")],-1)
printHtmlPart(44)
invokeTag('select','g',243,['class':("form-control"),'id':("estadoTelefonoContactado"),'name':("estadoTelefonoContactado"),'from':(['MANEJADA','NO_CONTESTA','LLAMADA_RECHAZADA','CONTESTADORA']),'noSelection':(['': '-- Seleccione --'])],-1)
printHtmlPart(45)
invokeTag('textField','g',269,['class':("form-control"),'id':("emailPersonalGestion"),'name':("emailPersonalGestion"),'value':(cliente.email)],-1)
printHtmlPart(46)
invokeTag('textField','g',273,['class':("form-control"),'id':("lugarNacimiento"),'name':("lugarNacimiento"),'value':("")],-1)
printHtmlPart(47)
invokeTag('textField','g',277,['class':("datepicker form-control"),'id':("fechaNacimientoGestion"),'name':("fechaNacimientoGestion"),'value':(cliente.fecha_nacimiento),'onkeypress':("return soloLetras(event)")],-1)
printHtmlPart(48)
invokeTag('select','g',280,['class':("form-control"),'id':("gestionLinea"),'name':("gestionLinea"),'optionKey':("key"),'optionValue':("value"),'from':(['SI':'SI','NO':'NO']),'noSelection':(['': '-- Seleccione --'])],-1)
printHtmlPart(49)
invokeTag('textField','g',283,['class':("form-control"),'id':("codigoOtp"),'name':("codigoOtp"),'value':("")],-1)
printHtmlPart(50)
invokeTag('select','g',290,['class':("form-control"),'id':("tipoPlanGestion"),'name':("tipoPlanGestion"),'optionKey':("key"),'optionValue':("value"),'from':(['TITULAR':'TITULAR','TITULAR+CONYUGUE':'TITULAR+CONYUGUE']),'noSelection':(['': '-- Seleccione --'])],-1)
printHtmlPart(51)
invokeTag('select','g',291,['class':("form-control"),'id':("estado_civil"),'name':("estado_civil"),'from':(['SOLTERO', 'CASADO', 'DIVORCIADO', 'VIUDO', 'UNION LIBRE', 'CONCUBINATO', 'CASADO SEPAR. BIENES']),'noSelection':(['': '-- Seleccione --']),'optionValue':("value")],-1)
printHtmlPart(52)
invokeTag('select','g',293,['class':("form-control"),'id':("generoGestion"),'name':("generoGestion"),'optionValue':("value"),'optionKey':("key"),'from':(['MASCULINO' :'MASCULINO', 'FEMENINO': 'FEMENINO']),'noSelection':(['': '-- Seleccione --'])],-1)
printHtmlPart(53)
invokeTag('textField','g',297,['class':("form-control"),'id':("telefonoGestion1"),'name':("telefonoGestion1"),'value':(""),'maxlength':("10"),'minlength':("9")],-1)
printHtmlPart(54)
invokeTag('textField','g',300,['class':("form-control"),'id':("telefonoGestion2"),'name':("telefonoGestion2"),'value':(""),'maxlength':("10"),'minlength':("9")],-1)
printHtmlPart(55)
invokeTag('textField','g',303,['class':("form-control"),'id':("telefonoGestion3"),'name':("telefonoGestion3"),'value':(""),'maxlength':("10"),'minlength':("9")],-1)
printHtmlPart(56)
invokeTag('select','g',310,['class':("form-control"),'name':("provinciaAsegurada"),'id':("provinciaAsegurada"),'from':(callcenter.Provincia.list()),'optionKey':("id"),'optionValue':({it.nombre	}),'noSelection':(['': '-- Seleccione --'])],-1)
printHtmlPart(57)
invokeTag('select','g',313,['class':("form-control"),'name':("ciudadAsegurada"),'id':("ciudadAsegurada"),'from':(""),'optionKey':("id"),'optionValue':({it.nombre	}),'noSelection':(['': '-- Seleccione --'])],-1)
printHtmlPart(58)
invokeTag('select','g',315,['class':("form-control"),'name':("sectorAsegurada"),'id':("sectorAsegurada"),'from':(""),'optionKey':("id"),'optionValue':({it.nombre	}),'noSelection':(['': '-- Seleccione --'])],-1)
printHtmlPart(59)
invokeTag('textField','g',320,['class':("form-control"),'id':("callePrincipalAsegurada"),'name':("callePrincipalAsegurada")],-1)
printHtmlPart(60)
invokeTag('textField','g',330,['class':("form-control"),'id':("numeracionAsegurada"),'name':("numeracionAsegurada")],-1)
printHtmlPart(61)
invokeTag('textField','g',340,['class':("form-control"),'id':("calleTransversalAsegurada"),'name':("calleTransversalAsegurada")],-1)
printHtmlPart(62)
invokeTag('textArea','g',345,['class':("form-control"),'name':("referenciaAsegurada"),'id':("referenciaAsegurada")],-1)
printHtmlPart(63)
invokeTag('textArea','g',351,['class':("form-control"),'id':("observacionesGestion"),'name':("observacionesGestion"),'value':(cliente.observaciones)],-1)
printHtmlPart(64)
})
invokeTag('form','g',356,['action':("guardarCliente"),'id':("idFormPDF"),'name':("idFormPDF")],1)
printHtmlPart(65)
invokeTag('select','g',372,['class':("form-control"),'name':("producto"),'from':(['Negocio Protegido PYME-Estructura', 'Negocio Protegido PYME-Contenido']),'noSelection':(['': '-- Seleccione --'])],-1)
printHtmlPart(66)
invokeTag('select','g',379,['class':("form-control"),'id':("plan"),'name':("plan"),'optionKey':("nombre"),'noSelection':(['': '-- Seleccione --']),'from':("")],-1)
printHtmlPart(67)
invokeTag('javascript','asset',394,['src':("usogeneral/objetos.js")],-1)
printHtmlPart(0)
invokeTag('javascript','asset',394,['src':("gestion/gestionCliente1.js")],-1)
printHtmlPart(0)
invokeTag('stylesheet','asset',395,['src':("usogeneral/datetimepicker.css")],-1)
printHtmlPart(0)
invokeTag('javascript','asset',398,['src':("usogeneral/datetimepicker.js")],-1)
printHtmlPart(0)
invokeTag('javascript','asset',400,['src':("usogeneral/customdatetimepicker.js")],-1)
printHtmlPart(0)
invokeTag('javascript','asset',400,['src':("usogeneral/bootstrap-datepicker.min.js")],-1)
printHtmlPart(0)
invokeTag('javascript','asset',400,['src':("usogeneral/customdatepicker.js")],-1)
printHtmlPart(0)
invokeTag('javascript','asset',400,['src':("usogeneral/bootstrap-datepicker.es.min.js")],-1)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1628267707467L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
