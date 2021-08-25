$(function () {

    $cuadro = $(".cuadro");
    $cuadro.on("mouseenter", function(){
        $(".fa-5x", this).addClass("icon-anim");
    });
    $cuadro.on("mouseleave", function(){
        $(".fa-5x", this).removeClass("icon-anim");
    });
    $cuadro.on("mouseenter", function(){
        // $(".huge", this).addClass("icon-anim");
    });
    $cuadro.on("mouseleave", function(){
        // $(".huge", this).removeClass("icon-anim");
    });
	
	//Grafico de barras
	$.get(baseUrl + "/funcionesAjax/getGraficoVentasHora", function(data){
		
		if(Object.keys(data).length > 0){
		
		var categorias = new Array();
		var dataVentas = new Array();
		var dataContactados = new Array();
		var dataAdicionales = new Array();
		var contador = 0;
		
		for(var k in data){
			categorias[contador] = Object.keys(data)[contador];
			dataContactados[contador] = data[k]['contactados'];
			dataVentas[contador] = data[k]['ventas'];
            dataAdicionales[contador] = data[k]['adicionales'];
			contador++;
			
		}
		
		
		$('#container').highcharts({
	        chart: {
	            type: 'column'
	        },
	        title: {
	            text: 'Exitosos y Contactos por Hora'
	        },
	        subtitle: {
	            text: 'Adicionales'
	        },
	        xAxis: {
	            categories: categorias,
	            crosshair: true,
	            title: {
	                text: 'Horas'
	            }
	        },
	        yAxis: {
	            min: 0,
	            title: {
	                text: 'Cantidad'
	            }
	        },
	        tooltip: {
	            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
	            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
	                '<td style="padding:0"><b>{point.y:.0f} </b></td></tr>',
	            footerFormat: '</table>',
	            shared: true,
	            useHTML: true
	        },
	        plotOptions: {
	            column: {
	                pointPadding: 0.2,
	                borderWidth: 0
	            }
	        },
	        series: [{
	            name: 'Contactados',
	            data: dataContactados

	        }, {
	            name: 'Exitosos',
	            data: dataVentas
	        }, {
                name: 'Adicionales',
                data: dataAdicionales
			}]
	    });
		
		
	}else{
		$('#container').html("<div class='col-lg-12' style='text-align: center; color: red; margin-top: 100px'><h5>No Hay Datos para Graficar</h5></div>");
	}
		
	});
	
	
//	Grafico pie con leyenda
	$.get(baseUrl + "/funcionesAjax/grafMotNoDesea", function(data){
		
		
		if(Object.keys(data).length > 0){
		
		var datos = new Array();
		var contador = 0;
		for(var k in data){
			datos[contador] = {'name': k, 'y': data[k]};
			contador++;
		}
		
		$('#containerpie').highcharts({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                type: 'pie'
            },
            title: {
                text: 'Motivos por los que el cliente no acepta el servicio'
            },
            tooltip: {
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: false
                    },
                    showInLegend: true
                }
            },
            series: [{
                name: 'Porcentaje',
                colorByPoint: true,
                data: datos
            }]
        });
		
		}else{
			$('#containerpie').html("<div class='col-lg-12' style='text-align: center; color: red; margin-top: 100px'><h5>No Hay Datos para Graficar</h5></div>");
		}
	});
	
	
	//Grafico de pie DD
	$.get(baseUrl + "/funcionesAjax/getGrafContVsNocont", function(data){
		
		
		var detalleContactados = new Array()
		var detalleNoContactados = new Array();
		var contadorContactados = 0;
		var contadorNoContactados = 0;
		
		if(data['Contactado'] || data['NoContactado']){
			
			var datoContactados = 0;
			var datoNoContactados = 0;
			
			for(var k in data['DetalleContactados']){			
				detalleContactados[contadorContactados] = [k, data['DetalleContactados'][k]];
				contadorContactados++;			
			}
			
			for(var k in data['DetalleNoContactados']){			
				detalleNoContactados[contadorNoContactados] = [k, data['DetalleNoContactados'][k]];
				contadorNoContactados++;			
			}
			
			if(data['Contactado']){
				datoContactados = data['Contactado']; 
			}
			
			if(data['NoContactado']){
				datoNoContactados = data['NoContactado'];
			}
		
		
		$('#containerpiedd').highcharts({
	        chart: {
	            type: 'pie'
	        },
	        title: {
	            text: 'Contactados VS No Contactados'
	        },
	        subtitle: {
	            text: 'Click en un area del pie para ver detalles'
	        },
	        plotOptions: {
	            series: {
	                dataLabels: {
	                    enabled: true,
	                    format: '{point.name}: {point.y:.0f}'
	                }
	            }
	        },

	        tooltip: {
	            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
	            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.0f}</b><br/>'
	        },
	        series: [{
	            name: 'Brands',
	            colorByPoint: true,
	            data: [{
	                name: 'Contactados',
	                y: datoContactados,
	                drilldown: 'Contactados'
	            }, {
	                name: 'No Contactados',
	                y: datoNoContactados,
	                drilldown: 'No Contactados'
	            }]
	        }],
	        drilldown: {
	            series: [{
	                name: 'Contactados',
	                id: 'Contactados',
	                data: detalleContactados
	            }, {
	                name: 'No Contactados',
	                id: 'No Contactados',
	                data: detalleNoContactados
	            }]
	        }
	    });
		}else{
			$('#containerpiedd').html("<div class='col-lg-12' style='text-align: center; color: red; margin-top: 100px'><h5>No Hay Datos para Graficar</h5></div>");
		}
		
	});

    //	Grafico de lineas Administradores
   /* var fechaInicio = moment().subtract(30, 'days').format("YYYY-MM-DD 00:00:00")
    var fechaFin = moment().format("YYYY-MM-DD 23:59:59")
    $.get(baseUrl + "/funcionesAjax/getGrafLineas"
        , {fechaInicio: fechaInicio, fechaFin: fechaFin}
        , function(data){
            var categorias = new Array();
            var datos = new Array();
            var contador = 0;
            for(var k in data){
                categorias[contador] = k;
                datos[contador] = data[k];
                contador++;
            }
            $('#containerlinereport').highcharts({
                chart: {
                    type: 'line'
                },
                title: {
                    text: 'Adicionales en los últimos 30 días'
                },
                subtitle: {
                    text: 'Seguros'
                },
                xAxis: {
                    categories: categorias
                },
                yAxis: {
                    title: {
                        text: 'Cantidad Adicionales'
                    }
                },
                plotOptions: {
                    line: {
                        dataLabels: {
                            enabled: true
                        },
                        enableMouseTracking: false
                    }
                },
                series: [{
                    name: 'Cantidad Adicionales',
                    data: datos
                }]
            });
        });*/

    //	Grafico de lineas
    var fechaInicio = moment().subtract(15, 'days').format("YYYY-MM-DD 00:00:00");
    var fechaFin = moment().format("YYYY-MM-DD 23:59:59");
    $.get(baseUrl + "/funcionesAjax/getGrafLineas"
        , {fechaInicio: fechaInicio, fechaFin: fechaFin}
        , function(data){
            var categorias = new Array();
            var datos = new Array();
            var contador = 0;
            for(var k in data){
                categorias[contador] = k;
                datos[contador] = data[k];
                contador++;
            }
            $('#containerlinereport').highcharts({
                chart: {
                    type: 'line'
                },
                title: {
                    text: 'Ventas en los últimos 15 días'
                },
                subtitle: {
                    text: 'Seguros AIG'
                },
                xAxis: {
                    categories: categorias
                },
                yAxis: {
                    title: {
                        text: 'Cantidad Seguros'
                    }
                },
                plotOptions: {
                    line: {
                        dataLabels: {
                            enabled: true
                        },
                        enableMouseTracking: true
                    }
                },
                series: [{
                    name: 'Cantidad Seguros',
                    data: datos
                }]
            });
        });



    //	Grafico de lineas Operadores
    var fechaInicioOperador = moment().subtract(15, 'days').format("YYYY-MM-DD 00:00:00")
    var fechaFinOperador = moment().format("YYYY-MM-DD 23:59:59")
    $.get(baseUrl + "/funcionesAjax/getGrafLineas"
        , {fechaInicio: fechaInicioOperador, fechaFin: fechaFinOperador}
        , function(data){
            var categorias = new Array();
            var datos = new Array();
            var contador = 0;
            for(var k in data){
                categorias[contador] = k;
                datos[contador] = data[k];
                contador++;
            }
            $('#containerlinereportoperador').highcharts({
                chart: {
                    type: 'line'
                },
                title: {
                    text: 'Adicionales en los últimos 15 días'
                },
                subtitle: {
                    text: 'Seguros'
                },
                xAxis: {
                    categories: categorias
                },
                yAxis: {
                    title: {
                        text: 'Cantidad Adicionales'
                    }
                },
                plotOptions: {
                    line: {
                        dataLabels: {
                            enabled: true
                        },
                        enableMouseTracking: false
                    }
                },
                series: [{
                    name: 'Cantidad Adicionales',
                    data: datos
                }]
            });
        });

});


