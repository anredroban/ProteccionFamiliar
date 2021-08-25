import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_proteccionFamiliar_gestionNovedadesgestionCliente_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/gestionNovedades/gestionCliente.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
invokeTag('captureMeta','sitemesh',1,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("main")],-1)
printHtmlPart(0)
invokeTag('javascript','asset',2,['src':("gestionNovedades/gestionCliente.js")],-1)
printHtmlPart(1)
invokeTag('stylesheet','asset',3,['src':("usogeneral/datetimepicker.css")],-1)
printHtmlPart(0)
invokeTag('javascript','asset',4,['src':("usogeneral/datetimepicker.js")],-1)
printHtmlPart(0)
invokeTag('javascript','asset',6,['src':("usogeneral/customdatetimepicker.js")],-1)
printHtmlPart(2)
if(true && (cliente.nombreCompletoTitular != null && cliente.nombreCompletoTitular != '')) {
printHtmlPart(3)
expressionOut.print(cliente.nombreCompletoTitular)
printHtmlPart(4)
}
printHtmlPart(5)
if(true && (cliente.idTitular != null && cliente.idTitular != '')) {
printHtmlPart(6)
expressionOut.print(cliente.idTitular)
printHtmlPart(4)
}
printHtmlPart(5)
if(true && (cliente.fecha != null && cliente.fecha != '')) {
printHtmlPart(7)
expressionOut.print(cliente.fecha)
printHtmlPart(4)
}
printHtmlPart(5)
if(true && (cliente.cantidadTarjetasAdi != null && cliente.cantidadTarjetasAdi != '')) {
printHtmlPart(8)
expressionOut.print(cliente.cantidadTarjetasAdi)
printHtmlPart(4)
}
printHtmlPart(5)
if(true && (cliente.ciudadTrabajo != null && cliente.ciudadTrabajo != '')) {
printHtmlPart(9)
expressionOut.print(cliente.ciudadTrabajo)
printHtmlPart(4)
}
printHtmlPart(5)
if(true && (cliente.provinciaTrabajo != null && cliente.provinciaTrabajo != '')) {
printHtmlPart(10)
expressionOut.print(cliente.provinciaTrabajo)
printHtmlPart(4)
}
printHtmlPart(5)
if(true && (cliente.direccionTrabajo != null && cliente.direccionTrabajo != '')) {
printHtmlPart(11)
expressionOut.print(cliente.direccionTrabajo)
printHtmlPart(4)
}
printHtmlPart(5)
if(true && (cliente.ciudadDomicilio != null && cliente.ciudadDomicilio != '')) {
printHtmlPart(12)
expressionOut.print(cliente.ciudadDomicilio)
printHtmlPart(4)
}
printHtmlPart(5)
if(true && (cliente.provinciaDomicilio != null && cliente.provinciaDomicilio != '')) {
printHtmlPart(13)
expressionOut.print(cliente.provinciaDomicilio)
printHtmlPart(4)
}
printHtmlPart(5)
if(true && (cliente.direccionDomicilio != null && cliente.direccionDomicilio != '')) {
printHtmlPart(14)
expressionOut.print(cliente.direccionDomicilio)
printHtmlPart(4)
}
printHtmlPart(5)
if(true && (cliente.proceso != null && cliente.proceso != '')) {
printHtmlPart(15)
expressionOut.print(cliente.proceso)
printHtmlPart(4)
}
printHtmlPart(5)
if(true && (cliente.lugarEntrega != null && cliente.lugarEntrega != '')) {
printHtmlPart(16)
expressionOut.print(cliente.lugarEntrega)
printHtmlPart(4)
}
printHtmlPart(5)
if(true && (cliente.estado != null && cliente.estado != '')) {
printHtmlPart(17)
expressionOut.print(cliente.estado)
printHtmlPart(4)
}
printHtmlPart(5)
if(true && (cliente.errorInformacion != null && cliente.errorInformacion != '')) {
printHtmlPart(18)
expressionOut.print(cliente.errorInformacion)
printHtmlPart(4)
}
printHtmlPart(5)
if(true && (cliente.revisionDireccionDomicilio != null && cliente.revisionDireccionDomicilio != '')) {
printHtmlPart(19)
expressionOut.print(cliente.revisionDireccionDomicilio)
printHtmlPart(4)
}
printHtmlPart(5)
if(true && (cliente.revisionDireccionTrabajo != null && cliente.revisionDireccionTrabajo != '')) {
printHtmlPart(20)
expressionOut.print(cliente.revisionDireccionTrabajo)
printHtmlPart(4)
}
printHtmlPart(21)
if(true && (cliente.celular != null && cliente.celular != '')) {
printHtmlPart(22)
expressionOut.print(cliente.celular)
printHtmlPart(4)
}
printHtmlPart(5)
if(true && (cliente.telefonoCasa != null && cliente.telefonoCasa != '')) {
printHtmlPart(23)
expressionOut.print(cliente.telefonoCasa)
printHtmlPart(4)
}
printHtmlPart(5)
if(true && (cliente.telefonoTrabajo != null && cliente.telefonoTrabajo != '')) {
printHtmlPart(24)
expressionOut.print(cliente.telefonoTrabajo)
printHtmlPart(4)
}
printHtmlPart(25)
if(true && (cliente.codigoAsignacion != null && cliente.codigoAsignacion != '')) {
printHtmlPart(26)
expressionOut.print(cliente.codigoAsignacion)
printHtmlPart(4)
}
printHtmlPart(27)
createTagBody(1, {->
printHtmlPart(28)
expressionOut.print(cliente.id)
printHtmlPart(29)
invokeTag('select','g',144,['class':("form-control"),'name':("status"),'id':("status"),'from':(callcenter.Estado.list()),'optionKey':("id"),'optionValue':({it.nombre	}),'noSelection':(['': '-- Seleccione --'])],-1)
printHtmlPart(30)
invokeTag('select','g',148,['class':("form-control"),'name':("substatus"),'id':("substatus"),'from':("")],-1)
printHtmlPart(31)
invokeTag('select','g',153,['id':("subSubStatus"),'class':("form-control"),'name':("subSubStatus"),'from':("")],-1)
printHtmlPart(32)
invokeTag('textField','g',157,['id':("recall"),'name':("recall"),'class':("recall form-control")],-1)
printHtmlPart(33)
invokeTag('select','g',167,['class':("form-control"),'name':("provinciaDomic"),'id':("provinciaDomic"),'from':(callcenter.Provincia.list()),'optionKey':("id"),'optionValue':({it.nombre	}),'noSelection':(['': '-- Seleccione --'])],-1)
printHtmlPart(34)
invokeTag('select','g',173,['class':("form-control"),'name':("ciudadDomic"),'id':("ciudadDomic"),'from':(callcenter.Ciudad.list()),'optionKey':("id"),'optionValue':({it.nombre	}),'noSelection':(['': '-- Seleccione --'])],-1)
printHtmlPart(35)
invokeTag('select','g',180,['class':("form-control"),'name':("sectorDomic"),'id':("sectorDomic"),'from':(callcenter.Parroquia.list()),'optionKey':("id"),'optionValue':({it.nombre	}),'noSelection':(['': '-- Seleccione --'])],-1)
printHtmlPart(36)
invokeTag('textField','g',184,['class':("form-control"),'id':("callePrincipalDomic"),'name':("callePrincipalDomic")],-1)
printHtmlPart(37)
invokeTag('textField','g',189,['class':("form-control"),'id':("numeracionDomic"),'name':("numeracionDomic")],-1)
printHtmlPart(38)
invokeTag('textField','g',194,['class':("form-control"),'id':("calleTransversalDomic"),'name':("calleTransversalDomic")],-1)
printHtmlPart(39)
invokeTag('select','g',201,['class':("form-control"),'name':("tipoVivienda"),'id':("tipoVivienda"),'from':(['EDIFICIO':'EDIFICIO', 'CASA':'CASA', 'DEPARTAMENTO':'DEPARTAMENTO', 'CONJUNTO':'CONJUNTO']),'optionKey':("key"),'optionValue':("value"),'noSelection':(['': '-- Seleccione --'])],-1)
printHtmlPart(40)
invokeTag('textArea','g',205,['class':("form-control"),'name':("referenciaDomic"),'id':("referenciaDomic")],-1)
printHtmlPart(41)
invokeTag('select','g',215,['class':("form-control"),'name':("provinciaTrab"),'id':("provinciaTrab"),'from':(callcenter.Provincia.list()),'optionKey':("id"),'optionValue':({it.nombre	}),'noSelection':(['': '-- Seleccione --'])],-1)
printHtmlPart(42)
invokeTag('select','g',221,['class':("form-control"),'name':("ciudadTrab"),'id':("ciudadTrab"),'from':(callcenter.Ciudad.list()),'optionKey':("id"),'optionValue':({it.nombre	}),'noSelection':(['': '-- Seleccione --'])],-1)
printHtmlPart(35)
invokeTag('select','g',228,['class':("form-control"),'name':("sectorTrab"),'id':("sectorTrab"),'from':(callcenter.Parroquia.list()),'optionKey':("id"),'optionValue':({it.nombre	}),'noSelection':(['': '-- Seleccione --'])],-1)
printHtmlPart(43)
invokeTag('textField','g',233,['class':("form-control"),'id':("callePrincipalTrab"),'name':("callePrincipalTrab")],-1)
printHtmlPart(37)
invokeTag('textField','g',238,['class':("form-control"),'id':("numeracionTrab"),'name':("numeracionTrab")],-1)
printHtmlPart(38)
invokeTag('textField','g',243,['class':("form-control"),'id':("calleTransversalTrab"),'name':("calleTransversalTrab")],-1)
printHtmlPart(44)
invokeTag('select','g',250,['class':("form-control"),'name':("tipoTrab"),'id':("tipoTrab"),'from':(['EDIFICIO':'EDIFICIO', 'CASA':'CASA', 'DEPARTAMENTO':'DEPARTAMENTO', 'CONJUNTO':'CONJUNTO']),'optionKey':("key"),'optionValue':("value"),'noSelection':(['': '-- Seleccione --'])],-1)
printHtmlPart(45)
invokeTag('textArea','g',254,['class':("form-control"),'name':("referenciaTrab"),'id':("referenciaTrab")],-1)
printHtmlPart(46)
invokeTag('select','g',266,['class':("form-control"),'name':("entrega"),'id':("entrega"),'from':(['DOMICILIO':'Domicilio', 'TRABAJO':'Trabajo']),'optionKey':("key"),'optionValue':("value"),'noSelection':(['': '-- Seleccione --'])],-1)
printHtmlPart(47)
invokeTag('textField','g',270,['class':("form-control"),'id':("nombreContacto"),'name':("nombreContacto")],-1)
printHtmlPart(48)
invokeTag('select','g',276,['class':("form-control"),'name':("rangoVisita"),'id':("rangoVisita"),'from':(['MANANA':'Mañana', 'TARDE':'Tarde']),'optionKey':("key"),'optionValue':("value"),'noSelection':(['': '-- Seleccione --'])],-1)
printHtmlPart(49)
invokeTag('select','g',282,['class':("form-control"),'name':("facturacion"),'id':("facturacion"),'from':(['SI':'Si', 'NO':'No']),'optionKey':("key"),'optionValue':("value"),'noSelection':(['': '-- Seleccione --'])],-1)
printHtmlPart(50)
invokeTag('select','g',288,['class':("form-control"),'name':("estadoCtaDigital"),'id':("estadoCtaDigital"),'from':(['SI':'Si', 'NO':'No']),'optionKey':("key"),'optionValue':("value"),'noSelection':(['': '-- Seleccione --'])],-1)
printHtmlPart(51)
invokeTag('textField','g',292,['class':("form-control"),'id':("telefonoDomContacto"),'name':("telefonoDomContacto")],-1)
printHtmlPart(52)
invokeTag('textField','g',296,['class':("form-control"),'id':("telefonoTrabContacto"),'name':("telefonoTrabContacto")],-1)
printHtmlPart(53)
invokeTag('textField','g',300,['class':("form-control"),'id':("celularContacto"),'name':("celularContacto")],-1)
printHtmlPart(54)
invokeTag('textArea','g',319,['class':("form-control"),'id':("observaciones"),'name':("observaciones"),'value':(cliente.observaciones)],-1)
printHtmlPart(55)
invokeTag('submitButton','g',323,['class':("btn btn-primary"),'name':("save"),'id':("btnGuardarGestion"),'value':("Guardar")],-1)
printHtmlPart(56)
})
invokeTag('form','g',323,['action':("guardarCliente"),'name':("management-client")],1)
printHtmlPart(57)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1628261213706L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
