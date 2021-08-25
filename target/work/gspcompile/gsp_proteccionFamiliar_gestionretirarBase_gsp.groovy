import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_proteccionFamiliar_gestionretirarBase_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/gestion/retirarBase.gsp" }
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
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',20,[:],1)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(4)
createTagBody(2, {->
printHtmlPart(5)
invokeTag('select','g',34,['class':("form-control"),'name':("usuario"),'optionKey':("id"),'from':(utilitarios.Util.getOperDisponiblesAsignacion()),'optionValue':({it.nombre}),'noSelection':(['': 'TODOS'])],-1)
printHtmlPart(6)
invokeTag('select','g',38,['class':("form-control"),'name':("tipo"),'from':(['REGESTIONABLE', 'SIN GESTION', 'RELLAMADAS']),'noSelection':(['': 'TODOS'])],-1)
printHtmlPart(7)
invokeTag('textField','g',42,['class':("form-control"),'required':(""),'name':("desde")],-1)
printHtmlPart(8)
invokeTag('textField','g',46,['class':("form-control"),'required':(""),'name':("hasta")],-1)
printHtmlPart(9)
invokeTag('select','g',50,['class':("form-control"),'name':("nombrebase"),'from':(utilitarios.Util.getNombresBase()),'noSelection':(['': 'TODOS'])],-1)
printHtmlPart(10)
})
invokeTag('form','g',59,['action':("retirarBase")],2)
printHtmlPart(11)
if(true && (updateRealizado == true)) {
printHtmlPart(12)
expressionOut.print(resultado)
printHtmlPart(13)
}
printHtmlPart(14)
})
invokeTag('captureBody','sitemesh',72,[:],1)
printHtmlPart(15)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1629211646993L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
