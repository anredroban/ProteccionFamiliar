import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_proteccionFamiliar_gestionretirarBaseNovedades_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/gestion/retirarBaseNovedades.gsp" }
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
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
createTagBody(2, {->
printHtmlPart(6)
invokeTag('select','g',34,['name':("usuario"),'optionKey':("id"),'from':(com.pw.security.Usuario.findAll{rol.nombre == 'OPERADOR'}),'optionValue':({it.nombre}),'noSelection':(['': 'TODOS'])],-1)
printHtmlPart(7)
invokeTag('select','g',38,['name':("tipo"),'from':(['REGESTIONABLE', 'SIN GESTION']),'noSelection':(['': 'TODOS'])],-1)
printHtmlPart(8)
invokeTag('select','g',42,['name':("nombrebase"),'from':(utilitarios.Util.getNombresBaseNovedades()),'noSelection':(['': 'TODOS'])],-1)
printHtmlPart(9)
})
invokeTag('form','g',47,['action':("retirarBaseNovedades")],2)
printHtmlPart(10)
if(true && (updateRealizado == true)) {
printHtmlPart(11)
expressionOut.print(resultado)
printHtmlPart(12)
}
printHtmlPart(13)
})
invokeTag('captureBody','sitemesh',60,[:],1)
printHtmlPart(14)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1629211647138L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
