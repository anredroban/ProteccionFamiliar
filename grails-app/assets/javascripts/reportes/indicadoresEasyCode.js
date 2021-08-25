$(document).ready(function(){
    $("#dbName").val($("#dbName option:first").val());
    $("#tipoRegx").val($("#tipoRegx option:first").val());
    $("#substatusRegDiv").hide();
    $("#dbName").change(function(){
        $("#tipoReg").val($("#tipoReg option:first").val());
        if($( "#dbName option:selected" ).text() == '-Seleccione-'){
            $("#tipoReg").hide();
        }else{
            $("#tipoReg").show();
        }
    });

    $("#tipoRegx").change(function(){
        $("#subestadosRegestionables").val($("#subestadosRegestionables option:first").val());
        $("#substatusRegDiv").hide();
        if($("#tipoRegx").val() == "REGESTIONABLES"){
            $("#substatusRegDiv").show();
        }
    });

    $("#calcularLnk").click(function(e){

        if($("#nombreBase").val() == ""){
            alert("Escoja una base");
            e.preventDefault();
            return false;
        }

       /*if($("#tipoRegx").val() == ""){
            alert("Escoja un tipo de registros");
            e.preventDefault();
            return false;
        }*/

        $("#resultadoCalculo").html("Espere...");
        $nombreBase = $( "#nombreBase option:selected" ).text();
        $.post(baseUrl + "/funcionesAjax/calucularEasyCode", {nombreBase: $nombreBase}, function(data){
            /*$datos = data+""
            $datos2 = $datos.split(',')*/
            $("#resultadoCalculo").html(data+"");
        })
    });

})