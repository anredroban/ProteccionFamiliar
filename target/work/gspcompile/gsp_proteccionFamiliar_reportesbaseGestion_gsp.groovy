import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_proteccionFamiliar_reportesbaseGestion_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/reportes/baseGestion.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':("/"),'http-equiv':("Content-Type"),'content':("text/html; charset=UTF-8")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("main")],-1)
printHtmlPart(1)
createTagBody(2, {->
createClosureForHtmlPart(2, 3)
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(1)
invokeTag('javascript','asset',7,['src':("reporteBaseGestionada.js")],-1)
printHtmlPart(1)
invokeTag('javascript','asset',8,['src':("moment.min.js")],-1)
printHtmlPart(1)
invokeTag('javascript','asset',9,['src':("daterangepicker.js")],-1)
printHtmlPart(1)
invokeTag('stylesheet','asset',10,['src':("daterangepicker.css")],-1)
printHtmlPart(1)
})
invokeTag('captureHead','sitemesh',11,[:],1)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(3)
createTagBody(2, {->
printHtmlPart(4)
invokeTag('select','g',31,['name':("nombrebase"),'from':(utilitarios.Util.getNombresBase()),'noSelection':(['': 'TODOS'])],-1)
printHtmlPart(5)
invokeTag('select','g',36,['name':("usuario"),'optionKey':("id"),'from':(com.pw.security.Usuario.findAll{rol.nombre == 'OPERADOR'; estado == 'ACTIVO'}),'optionValue':({it.nombre}),'noSelection':(['': 'TODOS'])],-1)
printHtmlPart(6)
})
invokeTag('form','g',43,['action':("baseGestion")],2)
printHtmlPart(7)
})
invokeTag('captureBody','sitemesh',52,[:],1)
printHtmlPart(8)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1628261213716L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
