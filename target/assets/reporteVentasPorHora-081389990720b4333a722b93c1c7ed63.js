$(document).ready(function(){function e(e,t){$("#reportrange span").html(e.format("YYYY/MM/DD")+" - "+t.format("YYYY/MM/DD"))}e(moment().subtract(29,"days"),moment());$("#reportrange").daterangepicker({ranges:{Hoy:[moment(),moment()],Ayer:[moment().subtract(1,"days"),moment().subtract(1,"days")],"Últimos 7 días":[moment().subtract(6,"days"),moment()],"Últimos 30 días":[moment().subtract(29,"days"),moment()],"Esta semana":[moment().startOf("week").add(1,"days"),moment().endOf("week").add(1,"days")],"Este mes":[moment().startOf("month"),moment().endOf("month")],"Mes Anterior":[moment().subtract(1,"month").startOf("month"),moment().subtract(1,"month").endOf("month")]},locale:{format:"YYYY/MM/DD",separator:" - ",applyLabel:"Aplicar",cancelLabel:"Cancelar",customRangeLabel:"Elegir Rango",daysOfWeek:["Do","Lu","Ma","Mi","Ju","Vi","Sa"],monthNames:["Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"],firstDay:1}},e);$("#btnreporteventasporhora").click(function(){var e=$("#reportrange").val().split("-");var t=e[0].replace("/","-").trim().replace("/","-")+" 00:00:00";var n=e[1].replace("/","-").trim().replace("/","-")+" 23:59:59";var r=$("#nombrebase").val();var i=$("#usuario").val();$.get("/CallCenter/funcionesAjax/getGraficoVentasHoraReporte",{fechaInicio:t,fechaFin:n,nombreBase:r,usuario:i},function(e){if(Object.keys(e).length>0){var t=new Array;var n=new Array;var r=new Array;var i=0;for(var s in e){t[i]=Object.keys(e)[i];r[i]=e[s]["contactados"];n[i]=e[s]["ventas"];i++}$("#container").highcharts({chart:{type:"column"},title:{text:"Ventas y Contactos por Hora"},subtitle:{text:"Netlife"},xAxis:{categories:t,crosshair:true,title:{text:"Horas"}},yAxis:{min:0,title:{text:"Cantidad"}},tooltip:{headerFormat:'<span style="font-size:10px">{point.key}</span><table>',pointFormat:'<tr><td style="color:{series.color};padding:0">{series.name}: </td>'+'<td style="padding:0"><b>{point.y:.0f} </b></td></tr>',footerFormat:"</table>",shared:true,useHTML:true},plotOptions:{column:{pointPadding:.2,borderWidth:0}},series:[{name:"Contactados",data:r},{name:"Ventas",data:n}]})}else{$("#container").html("<div class='col-lg-12' style='text-align: center; color: red; margin-top: 100px'><h5>No Hay Datos para Graficar</h5></div>")}})})})