import callcenter.Clientes
import com.pw.security.*
import utilitarios.Util
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_proteccionFamiliar_usuariodashboardAgente_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/usuario/dashboardAgente.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
printHtmlPart(1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
invokeTag('captureMeta','sitemesh',13,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("main")],-1)
printHtmlPart(4)
createTagBody(2, {->
createClosureForHtmlPart(5, 3)
invokeTag('captureTitle','sitemesh',14,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',14,[:],2)
printHtmlPart(6)
invokeTag('javascript','asset',15,['src':("moment.min.js")],-1)
printHtmlPart(4)
invokeTag('javascript','asset',15,['src':("dashboard.js")],-1)
printHtmlPart(4)
invokeTag('javascript','asset',16,['src':("portlet.js")],-1)
printHtmlPart(7)
})
invokeTag('captureHead','sitemesh',16,[:],1)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(8)
expressionOut.print(Util.getFechaInicialMes())
printHtmlPart(9)
expressionOut.print(createLink(uri:'/usuario/'))
printHtmlPart(10)
expressionOut.print(Util.getOperadoresLogueados())
printHtmlPart(11)
expressionOut.print(createLink(uri:'#'))
printHtmlPart(12)
expressionOut.print(Util.getContactados())
printHtmlPart(13)
expressionOut.print(createLink(uri:'#'))
printHtmlPart(14)
expressionOut.print(Util.getCantidadVentas())
printHtmlPart(15)
expressionOut.print(createLink(uri:'#'))
printHtmlPart(16)
expressionOut.print(Util.getCantidadAdicionales())
printHtmlPart(17)
for( _it2002359879 in (inicioSesion) ) {
changeItVariable(_it2002359879)
printHtmlPart(18)
expressionOut.print(it[0])
printHtmlPart(19)
expressionOut.print(it[1])
printHtmlPart(19)
expressionOut.print(it[2]?:'Activa')
printHtmlPart(20)
}
printHtmlPart(21)
for( _it446254216 in (tablaResult) ) {
changeItVariable(_it446254216)
printHtmlPart(18)
expressionOut.print(it[0])
printHtmlPart(19)
expressionOut.print(it[1])
printHtmlPart(19)
expressionOut.print(it[2]?:0)
printHtmlPart(19)
expressionOut.print(it[4]?:0)
printHtmlPart(19)
expressionOut.print(it[5]?:0)
printHtmlPart(19)
expressionOut.print(it[6]?:0)
printHtmlPart(19)
expressionOut.print(it[7]?:0)
printHtmlPart(20)
}
printHtmlPart(22)
expressionOut.print(totalGestionados)
printHtmlPart(23)
expressionOut.print(totalContactados)
printHtmlPart(23)
expressionOut.print(totalExitosos)
printHtmlPart(23)
expressionOut.print(totalAdicionales)
printHtmlPart(23)
expressionOut.print(totalPorcentajeEfectividad)
printHtmlPart(23)
expressionOut.print(totalColocacionPorCliente)
printHtmlPart(24)
expressionOut.print(fechaHoraActualizacion)
printHtmlPart(25)
expressionOut.print(totalEfectivosGrupo)
printHtmlPart(26)
expressionOut.print(totalMetaGrupo)
printHtmlPart(26)
expressionOut.print(totalFaltantesGrupo)
printHtmlPart(26)
expressionOut.print(totalPorcentajeMetaGrupo)
printHtmlPart(26)
expressionOut.print(totalPromedioHoraGrupo)
printHtmlPart(27)
for( _it1181712988 in (tableTiemposAgentes) ) {
changeItVariable(_it1181712988)
printHtmlPart(28)
expressionOut.print(it[0])
printHtmlPart(19)
expressionOut.print(it[1]?:0)
printHtmlPart(19)
expressionOut.print(it[2]?:0)
printHtmlPart(19)
expressionOut.print(it[3]?:0)
printHtmlPart(19)
expressionOut.print(it[4]?:0)
printHtmlPart(29)
expressionOut.print(it[5]?:0)
printHtmlPart(19)
expressionOut.print(it[6]?:0)
printHtmlPart(19)
expressionOut.print(it[7]?:0)
printHtmlPart(19)
expressionOut.print(it[8]?:0)
printHtmlPart(19)
expressionOut.print(it[9]?:0)
printHtmlPart(29)
expressionOut.print(it[10]?:0)
printHtmlPart(19)
expressionOut.print(it[11]?:0)
printHtmlPart(19)
expressionOut.print(it[12]?:0)
printHtmlPart(20)
}
printHtmlPart(30)
invokeTag('javascript','asset',312,['src':("highcharts/data.js")],-1)
printHtmlPart(1)
invokeTag('javascript','asset',312,['src':("highcharts/drilldown.js")],-1)
printHtmlPart(1)
invokeTag('javascript','asset',313,['src':("highcharts/exporting.js")],-1)
printHtmlPart(1)
invokeTag('javascript','asset',314,['src':("highcharts/highcharts.js")],-1)
printHtmlPart(7)
})
invokeTag('captureBody','sitemesh',314,[:],1)
printHtmlPart(31)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1628261213724L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
