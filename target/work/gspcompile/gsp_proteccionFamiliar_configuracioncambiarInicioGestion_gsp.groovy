import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_proteccionFamiliar_configuracioncambiarInicioGestion_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/configuracion/cambiarInicioGestion.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
invokeTag('captureMeta','sitemesh',1,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("main")],-1)
printHtmlPart(0)
invokeTag('stylesheet','asset',2,['src':("usogeneral/bootstrap-datepicker.min.css")],-1)
printHtmlPart(1)
expressionOut.print(callcenter.Configuracion.findById(1).fechaInicioGestion.toString().substring(0, 10))
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
invokeTag('textField','g',10,['required':(""),'class':("form-control datepickerdown pointer"),'name':("nuevaFecha")],-1)
printHtmlPart(4)
invokeTag('submitButton','g',14,['name':("btnCambiarFecha"),'class':("form-control btn btn-success"),'value':("Cambiar")],-1)
printHtmlPart(5)
})
invokeTag('form','g',16,['action':("cambiarInicioGestion")],1)
printHtmlPart(6)
invokeTag('javascript','asset',17,['src':("usogeneral/bootstrap-datepicker.min.js")],-1)
printHtmlPart(6)
invokeTag('javascript','asset',18,['src':("usogeneral/bootstrap-datepicker.es.min.js")],-1)
printHtmlPart(6)
invokeTag('javascript','asset',19,['src':("usogeneral/customdatepicker.js")],-1)
printHtmlPart(7)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1628261213698L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
