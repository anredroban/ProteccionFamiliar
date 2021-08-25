import utilitarios.Util
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_proteccionFamiliar_layouts_sidebar_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/layouts/_sidebar.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
if(true && (actionName == "dashboard")) {
printHtmlPart(1)
}
printHtmlPart(2)
expressionOut.print(createLink(uri: '/'))
printHtmlPart(3)
if(true && (Util.checkAccess(session.user.usuario, '/permiso/index') || Util.checkAccess(session.user.usuario, '/rol/index') || Util.checkAccess(session.user.usuario, '/usuario/index'))) {
printHtmlPart(4)
if(true && (Util.checkAccess(session.user.usuario, '/permiso/index'))) {
printHtmlPart(5)
if(true && (controllerName == "permiso")) {
printHtmlPart(6)
}
printHtmlPart(7)
expressionOut.print(createLink(uri: '/permiso/'))
printHtmlPart(8)
}
printHtmlPart(9)
if(true && (Util.checkAccess(session.user.usuario, '/rol/index'))) {
printHtmlPart(5)
if(true && (controllerName == "rol")) {
printHtmlPart(10)
}
printHtmlPart(7)
expressionOut.print(createLink(uri: '/rol/'))
printHtmlPart(11)
}
printHtmlPart(9)
if(true && (Util.checkAccess(session.user.usuario, '/usuario/index'))) {
printHtmlPart(5)
if(true && (controllerName == "usuario" && actionName != "dashboard")) {
printHtmlPart(12)
}
printHtmlPart(7)
expressionOut.print(createLink(uri: '/usuario/'))
printHtmlPart(13)
}
printHtmlPart(14)
if(true && (controllerName == "gestion" && actionName == "cargarBase")) {
printHtmlPart(15)
}
printHtmlPart(16)
expressionOut.print(createLink(uri: '/gestion/cargarBase'))
printHtmlPart(17)
if(true && (controllerName == "gestion" && actionName == "retirarBase")) {
printHtmlPart(18)
}
printHtmlPart(16)
expressionOut.print(createLink(uri: '/gestion/retirarBase'))
printHtmlPart(19)
if(true && (controllerName == "asignarBaseNew" && actionName == "index")) {
printHtmlPart(18)
}
printHtmlPart(16)
expressionOut.print(createLink(uri: '/asignarBaseNew/index'))
printHtmlPart(20)
if(true && (Util.checkAccess(session.user.usuario, '/reportes/bitacoraConversiones')||Util.checkAccess(session.user.usuario, '/reportes/gerencial'))) {
printHtmlPart(21)
if(true && (Util.checkAccess(session.user.usuario, '/reportes/bitacoraAdicional'))) {
printHtmlPart(22)
if(true && (actionName == "bitacoraAdicional")) {
printHtmlPart(18)
}
printHtmlPart(23)
expressionOut.print(createLink(uri: '/reportes/bitacoraAdicional'))
printHtmlPart(24)
}
printHtmlPart(25)
if(true && (Util.checkAccess(session.user.usuario, '/reportes/tiemposBreak'))) {
printHtmlPart(22)
if(true && (actionName == "tiemposBreak")) {
printHtmlPart(18)
}
printHtmlPart(23)
expressionOut.print(createLink(uri: '/reportes/tiemposBreak'))
printHtmlPart(26)
}
printHtmlPart(9)
if(true && (Util.checkAccess(session.user.usuario, '/reportes/baseGestionada'))) {
printHtmlPart(27)
if(true && (actionName == "baseGestionada")) {
printHtmlPart(18)
}
printHtmlPart(23)
expressionOut.print(createLink(uri: '/reportes/baseGestionada'))
printHtmlPart(28)
}
printHtmlPart(9)
if(true && (Util.checkAccess(session.user.usuario, '/reportes/loginAgentes'))) {
printHtmlPart(22)
if(true && (actionName == "loginAgentes")) {
printHtmlPart(18)
}
printHtmlPart(23)
expressionOut.print(createLink(uri: '/reportes/loginAgentes'))
printHtmlPart(29)
}
printHtmlPart(30)
}
printHtmlPart(31)
if(true && (Util.checkAccess(session.user.usuario, '/provincia/index')||Util.checkAccess(session.user.usuario, '/ciudad/index')||Util.checkAccess(session.user.usuario, '/parroquia/index'))) {
printHtmlPart(32)
if(true && (Util.checkAccess(session.user.usuario, '/configuracion/cambiarInicioGestion'))) {
printHtmlPart(33)
if(true && (controllerName == "configuracion" && actionName == "cambiarInicioGestion")) {
printHtmlPart(34)
}
printHtmlPart(35)
expressionOut.print(createLink(uri: '/configuracion/cambiarInicioGestion'))
printHtmlPart(36)
}
printHtmlPart(37)
if(true && (Util.checkAccess(session.user.usuario, '/configuracion/deshabilitarBases'))) {
printHtmlPart(33)
if(true && (controllerName == "configuracion" && actionName == "deshabilitarBases")) {
printHtmlPart(34)
}
printHtmlPart(35)
expressionOut.print(createLink(uri: '/configuracion/deshabilitarBases'))
printHtmlPart(38)
}
printHtmlPart(39)
}
printHtmlPart(40)
}
printHtmlPart(41)
if(true && (controllerName == "gestion" && actionName == "index")) {
printHtmlPart(1)
}
printHtmlPart(2)
expressionOut.print(createLink(uri: '/gestion/index'))
printHtmlPart(42)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1628261213708L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
