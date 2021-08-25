import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_proteccionFamiliar_asignarBaseNewindexNovedades_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/asignarBaseNew/indexNovedades.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',3,['gsp_sm_xmlClosingForEmptyTag':("/"),'http-equiv':("Content-Type"),'content':("text/html; charset=UTF-8")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("main")],-1)
printHtmlPart(2)
expressionOut.print(resource(dir: 'javascripts', file: 'asignarBaseNew.js'))
printHtmlPart(3)
createTagBody(2, {->
createClosureForHtmlPart(4, 3)
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(5)
})
invokeTag('captureHead','sitemesh',7,[:],1)
printHtmlPart(6)
createTagBody(1, {->
printHtmlPart(6)
createTagBody(2, {->
printHtmlPart(7)
expressionOut.print(hasErrors(bean: cliente, field: 'asesores', 'error'))
printHtmlPart(8)
invokeTag('message','g',27,['code':("clientes.cedula.label"),'default':("Asesores")],-1)
printHtmlPart(9)
createClosureForHtmlPart(1, 3)
invokeTag('select','g',33,['name':("consultants"),'size':("15"),'id':("consultants"),'required':("true"),'multiple':("multiple"),'from':(utilitarios.Util.getOperadores()),'optionKey':("id"),'optionValue':({ it.nombre })],3)
printHtmlPart(10)
expressionOut.print(hasErrors(bean: cliente, field: 'nombreBase', 'error'))
printHtmlPart(11)
invokeTag('message','g',39,['code':("clientes.cedula.label"),'default':("Nombre de base")],-1)
printHtmlPart(12)
createClosureForHtmlPart(13, 3)
invokeTag('select','g',45,['name':("dbName"),'id':("dbName"),'required':("true"),'from':(utilitarios.Util.getNombresBaseNovedades()),'noSelection':(['': '-Seleccione-'])],3)
printHtmlPart(14)
expressionOut.print(hasErrors(bean: cliente, field: 'nombreBase', 'error'))
printHtmlPart(15)
invokeTag('message','g',51,['code':("clientes.cedula.label"),'default':("Tipo")],-1)
printHtmlPart(12)
createClosureForHtmlPart(13, 3)
invokeTag('select','g',57,['name':("tipoReg"),'id':("tipoReg"),'required':("true"),'from':(['SIN GESTION', 'REGESTIONABLES']),'noSelection':(['': '-Seleccione-'])],3)
printHtmlPart(14)
expressionOut.print(hasErrors(bean: cliente, field: 'allocationNumber', 'error'))
printHtmlPart(16)
invokeTag('message','g',64,['code':("clientes.allocationNumber.label"),'default':("Numero de registros a asignar")],-1)
printHtmlPart(12)
invokeTag('textField','g',67,['required':("true"),'name':("allocationNumber")],-1)
printHtmlPart(17)
if(true && (baseAsignada)) {
printHtmlPart(18)
}
printHtmlPart(19)
})
invokeTag('form','g',81,['controller':("asignarBaseNew"),'action':("indexNovedades")],2)
printHtmlPart(5)
})
invokeTag('captureBody','sitemesh',82,[:],1)
printHtmlPart(20)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1628261213693L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
