$(document).ready(function(){
    $identificacionTitular = $("#identificacionTitular");
    $status = $("#status");
    $substatus = $("#substatus");
    $subSubStatus = $("#subSubStatus");
    $recall = $("#recall");
    $provinciaDomic = $("#provinciaDomic");
    $ciudadDomic = $("#ciudadDomic");
    $sectorDomic = $("#sectorDomic");

    $callePrincipalDomic = $("#callePrincipalDomic");
    $numeracionDomic = $("#numeracionDomic");
    $calleTransversalDomic = $("#calleTransversalDomic");
    $tipoVivienda = $("#tipoVivienda");
    $referenciaDomic = $("#referenciaDomic");
    $provinciaTrab = $("#provinciaTrab");
    $ciudadTrab = $("#ciudadTrab");
    $sectorTrab = $("#sectorTrab");
    $callePrincipalTrab = $("#callePrincipalTrab");
    $numeracionTrab = $("#numeracionTrab");
    $calleTransversalTrab = $("#calleTransversalTrab");
    $tipoTrab = $("#tipoTrab");
    $referenciaTrab = $("#referenciaTrab");
    $entrega = $("#entrega");
    $nombreContacto = $("#nombreContacto");
    $rangoVisita = $("#rangoVisita");
    $facturacion = $("#facturacion");
    $estadoCtaDigital = $("#estadoCtaDigital");
    $seguroDesgravamen = $("#seguroDesgravamen");
    $aceptacionArcotel = $("#aceptacionArcotel");
    $telefonoDomContacto = $("#telefonoDomContacto");
    $telefonoTrabContacto = $("#telefonoTrabContacto");
    $celularContacto = $("#celularContacto");
    $recallDiv = $("#recallDiv");
    $subSubStatusDiv = $("#subSubStatusDiv");
    $productoPrincipalDiv = $("#productoPrincipalDiv");
    $productoSecundarioDiv = $("#productoSecundarioDiv");
    $recallDiv = $("#recallDiv");
    $subSubStatusDiv = $("#subSubStatusDiv");
    $subSubStatus = $("#subSubStatus");
    $asistencia = $("#asistencia");
    $nombresExequial = $("#nombresExequial");
    $edadExequial = $("#edadExequial");
    $parentescoExequial = $("#parentescoExequial");
    $datosExtraTotal = $("#datosExtraTotal");
    $datosExequial = $("#datosExequial");
    $tipoCobroDiv = $("#tipoCobroDiv");
    $btnGuardarAdicional = $("#btnGuardarAdicional");
    $cedula = $("#cedula");
    $producto = $("#producto");
    $plan = $("#plan");
    $valor = $("#valor");
    $primerNombre = $("#primerNombre");
    $segundoNombre = $("#segundoNombre");
    $primerApellido = $("#primerApellido");
    $segundoApellido = $("#segundoApellido");
    $nombreTarjeta = $("#nombreTarjeta");
    $cupoOtorgado = $("#cupoOtorgado");
    $sexo = $("#sexo");
    $estadoCivil = $("#estadoCivil");
    $parentesco = $("#parentesco");
    $fechaNacimiento = $("#fechaNacimiento");
    $nacionalidad = $("#nacionalidad");
    $observaciones = $("#observaciones");
    $bodyTablaAdicionales = $("#bodyTablaAdicionales");
    $nacionalidadSelect = $("#nacionalidadSelect");
    $nacionalidadDiv = $("#nacionalidadDiv");
    $gestionLineaDiv = $("#gestionLineaDiv");
    $btnGuardarGestion = $("#btnGuardarGestion");
    $debitoAsistencia = $("#debitoAsistencia");
    $datos247 = $("#datos247");
    $cobertura247 = $("#cobertura247");
    $tipoCobro247 = $("#tipoCobro247");
    $tipoCobro = $("#tipoCobro");
    $telefonoContactado = $("#telefonoContactado");
    $telefonoContactadoDiv = $("#telefonoContactadoDiv");
    $divMotivo = $("#divMotivo");
    $correo = $("#correo");
    $telefonoCelular = $("#telefonoCelular");
    $productoAceptado = $("#productoAceptado");
    $tipoTarjeta = $("#tipoTarjeta");
    $codigoOtp = $("#codigoOtp");
    $gestionLinea = $("#gestionLinea");
    $btnGuardarAPI = $("#btnGuardarAPI");
    $tipoPlanGestion = $("#tipoPlanGestion");
    $btnGuardarAPIDIV= $("#btnGuardarAPIDIV");



    $adicionalesArray = [];
    $tdDelete = "<td><a href='#' class='linkDelete' onclick='return false'><i class='fa fa-trash'></i> Eliminar</a></td>";

    init();

    //Cuando se cambia el ESTADO
    $status.change(function(){
        esconderCamposEstados();
       // $telefonoContactadoDiv.hide();
        $telefonoContactado.val("");
        if($status.val() == ""){
            emptySelect('substatus');
        }else{
            $statusId = this.value;
            if($statusId == 1){
                $telefonoContactadoDiv.show();
            }
            $.get(baseUrl + "/FuncionesAjax/getSubStatusByStatus", {
                id: $statusId
            }, function (data) {
                fillSelect('substatus', data);
            });
        }
    });


    $btnGuardarAPI.click(function (e) {
        if(!validateManagementData()){
            e.preventDefault()
            return false
        }else{
            var form = jQuery('#idFormPDF').serialize().split("&");;
            var data={};
            var parsed={};
            for(var key in form) {
                data[form[key].split("=")[0]] = decodeURIComponent(form[key].split("=")[1].split('+').join(' '));
            }
            for(var d in data){
                if (d=='identificacion' || d=='apellidoPaterno' || d== 'apellidoMaterno' || d=='nombres' || d=='estadoCivil' || d=='genero'
                    || d=='fechaNacimientoGestion' || d=='lugarNacimiento' || d=='telefonoContactado' || d=='emailPersonalGestion' || d=='tipoPlanGestion' ){
                    parsed[decodeURIComponent(d)] = data[d];
                    console.log(d+' ==>'+ data[d]);
                }


            }
            data = parsed;
            var dat=JSON.stringify(data);


            e.preventDefault()
            return false
        }
    });

    //Cuando cambia el SUBESTADO
    $substatus.change(function(){
        esconderCamposEstados();
        if($substatus.val() == ""){
            emptySelect('subSubStatus');
        }else{
            $subStatusId = this.value;
            $.get(baseUrl + "/FuncionesAjax/getSubSubStatusBySubStatus", {
                id: $subStatusId
            }, function (data) {
                if(fillSelect('subSubStatus', data) > 0){
                    $subSubStatusDiv.show();
                }else{
                    $("#subSubStatusDiv").hide();
                }
                if(data[2] == 'Rellamada'){ //Tipo de subestado
                    $("#recallDiv").show();
                }else{
                    $("#recallDiv").hide();
                }
                if(data[3]){ //Si el estado habilita la gestión del producto principal
                    $productoPrincipalDiv.show();
                }else{
                    $productoPrincipalDiv.hide();
                }
                if(data[4]){ //Si el estado habilita la gestión del producto secundario
                    $("#encuestaVehicular").show();
                }else{
                    $("#encuestaVehicular").hide();
                }
            });
        }
    });

    //CUANDO CAMBIA EL SUB SUB ESTADO
    $subSubStatus.change(function(){
        if($subSubStatus.val() == 'EXCESO DE LIQUIDEZ'){
            $divMotivo.show();
        }else{
            $divMotivo.hide();
        }
    });

    //Cuando cambia el TIPO DE ASISTENCIA
    $asistencia.change(function(){
        $datosExequial.hide();
        $datosExtraTotal.hide();
        $datos247.hide();
        if($asistencia.val().toString().indexOf("AE") != -1){
            $datosExequial.show();
        }else{
            $nombresExequial.val("");
            $edadExequial.val("");
            $parentescoExequial.val($("#parentescoExequial option:first").val());
        }
        if($asistencia.val().toString().indexOf("AT") != -1){
            $datosExtraTotal.show();
        }else{
            $tipoCobro.val($("#tipoCobro option:first").val());
        }
        if($asistencia.val().toString().indexOf("A247") != -1){
            $datos247.show();
        }else{
            $cobertura247.val($("#cobertura247 option:first").val());
            $tipoCobro247.val($("#tipoCobro247 option:first").val());
        }
    });

    //Cuando cambia la PROVINCIA DE ASEGURADA
    $("#provinciaAsegurada").change(function(){
        if($("#provinciaAsegurada").val() == ""){
            emptySelect('ciudadAsegurada');
            emptySelect('sectorAsegurada');
        }else {
            $id = this.value;
            $.get(baseUrl + "/FuncionesAjax/getCiudades", {id: $id}, function (data) {
                fillSelect('ciudadAsegurada', data);
            });
        }
    });

    //Cuando se cambia la CIUDAD DE ASEGURADA
    $("#ciudadAsegurada").change(function () {
        if($("#ciudadAsegurada").val() == ""){
            emptySelect('sectorAsegurada');
        }else {
            $id = this.value;
            $.get(baseUrl + "/FuncionesAjax/getParroquias", {id: $id}, function (data) {
                fillSelect('sectorAsegurada', data);
            });
        }
    });

    //Cuando cambia la PROVINCIA DE ENTREGA
    $("#provinciaEntrega").change(function(){
        if($("#provinciaEntrega").val() == ""){
            emptySelect('ciudadEntrega');
            emptySelect('sectorEntrega');
        }else {
            $id = this.value;
            $.get(baseUrl + "/FuncionesAjax/getCiudades", {id: $id}, function (data) {
                fillSelect('ciudadEntrega', data);
            });
        }
    });

    //Cuando se cambia la CIUDAD DE ENTREGA
    $("#ciudadEntrega").change(function () {
        if($("#ciudadEntrega").val() == ""){
            emptySelect('sectorEntrega');
        }else {
            $id = this.value;
            $.get(baseUrl + "/FuncionesAjax/getParroquias", {id: $id}, function (data) {
                fillSelect('sectorEntrega', data);
            });
        }
    });

    //Cuando se cambia el TIPO DE PRODUCTO
    $("#producto").change(function () {
        if($("#producto").val() == ""){
            emptySelect('plan');
        }else {
            $id = this.value;
            $.get(baseUrl + "/FuncionesAjax/getPlanes", {id: $id}, function (data) {
                fillSelect('plan', data);
            });
        }
    });

    //Cuando selecciona el PLAN
    $("#plan").change(function () {
        if($("#plan").val() == ""){
            $("#valor").val("");
        }else {
            $id = this.value;
            $idProducto = $("#producto").val();
            $.get(baseUrl + "/FuncionesAjax/getValoresPlan", {id: $id, idProducto: $idProducto}, function (data) {
                $("#valor").val(data);
            });
        }
    });

    //Cuando se da clic en AGREGAR EN EL MODAL DEL ADICIONAL
    $btnGuardarAdicional.click(function (e) {
        //Se hace las validaciones de rutina
        if(!validarDatosAdicional()){
            e.preventDefault()
            return false
        }

        //Se valida adicionales repetidos
      //  if(getAdicionalByCedula($cedula.val().trim()) != null){
       //     alert("Ya ingresó un adicional con número de cédula "+$cedula.val().trim());
        //    e.preventDefault();
         //   return false;
        //}

        $adicional = new Adicional($producto.val(), $plan.val(), $valor.val());
        $adicionalesArray.push($adicional);
        mostrarAdicionalesEnTabla();
        limpiarCamposAdicional();
    });

    //Cuando se va a ELIMINAR UN ADICIONAL
    $bodyTablaAdicionales.on("click", ".linkDelete", function () {
        $adicionalABorrar = $(this).parent().parent().attr("id");
        if(confirm("Se eliminará el producto "+$adicionalABorrar)){
            borrarAdicional($adicionalABorrar);
        }
    });


    //Se hace que el NOMBRE TARJETA se inicialice con Primer Nombre y Primer apellido
    $primerNombre.keyup(function(){
        var delta = this.value;
        $nombreTarjeta.val(delta);

    });
    $primerApellido.keyup(function(){
        var dev = this.value;
        $nombreTarjeta.val($primerNombre.val()+" "+this.value);

    });

    //Cuando se cambia la NACIONALIDAD en el Modal de Adicional
    $nacionalidadSelect.change(function () {
        $nacionalidad.val($nacionalidadSelect.val());
        if($nacionalidadSelect.val() == ""){
            $nacionalidadDiv.show();
        }else{
            $nacionalidadDiv.hide();
        }
    });

    //Cuando se cambia el codigo OTP en el Modal de Adicional
    $gestionLinea.change(function () {
        $codigoOtp.val("");
        if($gestionLinea.val() == "SI"){
            $gestionLineaDiv.show();
        }else{
            $gestionLineaDiv.hide();
        }
    });

    $("#aceptacionCreditoVehicular").change(function () {
        $("#motivoNoAceptaVehicular").val($("#motivoNoAceptaVehicular option:first").val());
        $("#observacionesNoAcepta").val("");
        if($("#aceptacionCreditoVehicular").val() == "SI"){
            $("#aceptacionVehicular").show();
            $("#noAceptacionVehicular").hide();
        }if($("#aceptacionCreditoVehicular").val() == "NO"){
            $("#noAceptacionVehicular").show();
            $("#aceptacionVehicular").hide();
        }
    });

    $("#aceptacionArcotel").change(function () {
        $("#utilizacionTarjetaCredito").val($("#utilizacionTarjetaCredito option:first").val());
        $("#tarjetasEfectivo").val($("#tarjetasEfectivo option:first").val());
        $("#recibeInformacion").val($("#recibeInformacion option:first").val());
        if($("#aceptacionArcotel").val() == "SI"){
            $("#divArcotel").show();
        }else{
            $("#divArcotel").hide();
        }
    });

    $("#aceptacionPad").change(function () {
      /*  $("#utilizacionTarjetaCredito").val($("#utilizacionTarjetaCredito option:first").val());
        $("#tarjetasEfectivo").val($("#tarjetasEfectivo option:first").val());
        $("#recibeInformacion").val($("#recibeInformacion option:first").val());*/
        if($("#aceptacionPad").val() == "SI"){
            $("#ScriptAceptaPAD").show();
            $("#ScriptNoAceptaPAD").hide();
        }if($("#aceptacionPad").val() == "NO"){
            $("#ScriptNoAceptaPAD").show();
            $("#ScriptAceptaPAD").hide();
        }
    });

    $("#motivoNoAceptaVehicular").change(function () {
      //  $("#motivoNoAceptaVehicular").val($("#motivoNoAceptaVehicular option:first").val());
      //  $("#observacionesNoAcepta").val("");
        if($("#motivoNoAceptaVehicular").val() == "Necesita un valor mayor… ¿qué valor necesita?" || $("#motivoNoAceptaVehicular").val() == "Lo va a utilizar más adelante… ¿Por favor cuénteme un tiempo aproximado en el cual espera comprar un vehículo?" || $("#motivoNoAceptaVehicular").val() == "Otros"){
            $("#divObservacionesNoAcepta").show();
        }else{
            $("#divObservacionesNoAcepta").hide();
        }
    });



    //Cuando se da clic en GUARDAR GESTION
    $btnGuardarGestion.click(function (e) {
        if(!validateManagementData()){
            e.preventDefault()
            return false
        }else{
            $btnGuardarGestion.hide();
        }
    });
});

//This function empties a select component
function emptySelect(idSelect) {

    var select = document.getElementById(idSelect);
    var option = document.createElement('option');
    var NumberItems = select.length;

    while (NumberItems > 0) {
        NumberItems--;
        select.remove(NumberItems);
    }

    option.text = '-- Seleccione --';
    option.value = ''
    select.add(option, null);
}

//This function fills a select component
function fillSelect(idSelect, data) {

    var select = document.getElementById(idSelect);
    var numberSubstatus = data[0].length;

    emptySelect(idSelect)

    if (numberSubstatus > 0) {
        for (var i = 0; i < numberSubstatus; i++) {
            var option = document.createElement('option');
            option.text = data[1][i];
            option.value = data[0][i];
            select.add(option, null);
        }
    }

    return numberSubstatus;
}

function esconderCamposEstados(){
    $recallDiv.hide();
    $subSubStatusDiv.hide();
    $("#encuestaVehicular").hide();
    $productoPrincipalDiv.hide();
    $productoSecundarioDiv.hide();
    $divMotivo.hide();
    $("#datosExequial").hide();
    $datos247.hide();
    $("#nombresExequial").val("");
    $("#edadExequial").val("");
    $("#parentescoExequial").val($("#parentescoExequial option:first").val());
    $debitoAsistencia.val($("#debitoAsistencia option:first").val());
    $tipoCobro.val($("#tipoCobro option:first").val());
    $cobertura247.val($("#cobertura247 option:first").val());
    $tipoCobro247.val($("#tipoCobro247 option:first").val());
    $("#asistencia").val($("#asistencia option:last").val());
}

function init(){
    esconderCamposEstados();
    $status.val($("#status option:first").val());
    $provinciaDomic.val($("#provinciaDomic option:first").val());
    $provinciaTrab.val($("#provinciaTrab option:first").val());
    $nacionalidadDiv.hide();
    $gestionLineaDiv.hide();
    $("#divArcotel").hide();
    $("#divObservacionesNoAcepta").hide();
    $("#noAceptacionVehicular").hide();
    $("#aceptacionVehicular").hide();
    $("#ScriptAceptaPAD").hide();
    $("#ScriptNoAceptaPAD").hide();
    $nacionalidadSelect.val($("#nacionalidadSelect option:first").val());
    $gestionLinea.val($("#gestionLinea option:first").val());
    $codigoOtp.val("");

   // $telefonoContactadoDiv.hide();
    $telefonoContactado.val("");
}

function mostrarAdicionalesEnTabla(){
    $bodyTablaAdicionales.html("");
    for(var i = 0; i < $adicionalesArray.length; i++){
        /*$contenidoTr =  createTd($adicionalesArray[i].producto)+createTd($adicionalesArray[i].plan)+createTd($adicionalesArray[i].valor)+$tdDelete;
        $bodyTablaAdicionales.append(createTr($adicionalesArray[i].producto, $contenidoTr));*/
        $contenidoTr = createTd($adicionalesArray[i].producto)+createTd($adicionalesArray[i].plan)+createTd($adicionalesArray[i].valor+$tdDelete);
        $bodyTablaAdicionales.append(createTr($adicionalesArray[i].producto, $contenidoTr));
    }
    $("#hidenAdicionales").val(JSON.stringify($adicionalesArray));
}

function createTd($content){
    return '<td>' + $content + '</td>';
}

function createTr($id, $content){
    return '<tr id="'+$id+'">' + $content + '</tr>';
}

function getAdicionalByCedula($cedula){
    $adicionalEncontrado = null;
    for(var i = 0; i < $adicionalesArray.length; i++){
        if($adicionalesArray[i].cedula == $cedula){
            $adicionalEncontrado = $adicionalesArray[i];
            break;
        }
    }
    return $adicionalEncontrado;
}

function borrarAdicional($cedula) {
    for(var i = 0; $adicionalesArray.length; i++){
        if($adicionalesArray[i].producto === $cedula){
            $adicionalesArray.splice(i, 1);
            break;
        }
    }
    mostrarAdicionalesEnTabla();
}

function validateManagementData(){
    $isValid = true;

    if ($("#number1").is(":visible")) {
        if ($("#estadoTel1").val() == "") {
            alert("Debe seleccionar un estado para el numero de Telefono 1");
            $isValid = false;
            return
        }
    }
    if ($("#number2").is(":visible")) {
        if ($("#estadoTel2").val() == "") {
            alert("Debe seleccionar un estado para el numero de Telefono 2");
            $isValid = false;
            return
        }
    }
    if ($("#number3").is(":visible")) {
        if ($("#estadoTel3").val() == "") {
            alert("Debe seleccionar un estado para el numero de Telefono 3");
            $isValid = false;
            return
        }
    }
    if ($("#number4").is(":visible")) {
        if ($("#estadoTel4").val() == "") {
            alert("Debe seleccionar un estado para el numero de Telefono 4");
            $isValid = false;
            return
        }
    }
    if ($("#number5").is(":visible")) {
        if ($("#estadoTel5").val() == "") {
            alert("Debe seleccionar un estado para el numero de Telefono 5");
            $isValid = false;
            return
        }
    }
    if ($("#number6").is(":visible")) {
        if ($("#estadoTel6").val() == "") {
            alert("Debe seleccionar un estado para el numero de Telefono 6");
            $isValid = false;
            return
        }
    }
    if ($("#number7").is(":visible")) {
        if ($("#estadoTel7").val() == "") {
            alert("Debe seleccionar un estado para el numero de Telefono 7");
            $isValid = false;
            return
        }
    }
    if ($("#number8").is(":visible")) {
        if ($("#estadoTel8").val() == "") {
            alert("Debe seleccionar un estado para el numero de Telefono 8");
            $isValid = false;
            return
        }
    }
    if ($("#number9").is(":visible")) {
        if ($("#estadoTel9").val() == "") {
            alert("Debe seleccionar un estado para el numero de Telefono 9");
            $isValid = false;
            return
        }
    }
    if ($("#number10").is(":visible")) {
        if ($("#estadoTel10").val() == "") {
            alert("Debe seleccionar un estado para el numero de Telefono 10");
            $isValid = false;
            return
        }
    }

    if($("#status").val() == ""){
        alert("Debe escoger un estado");
        $isValid = false;
        return
    }else{
        if($("#substatus").val() == ""){
            alert("Debe escoger un subestado");
            $isValid = false
            return
        }else{
            if($("#recall").is(":visible")){
                if($("#agendamiento").val() == ""){
                    alert("Campo agendamiento vacio")
                    $isValid = false;
                    return
                }
                if($("#recall").val() == ""){
                    alert("Debe ingresar una fecha para la rellamada")
                    $isValid = false;
                    return
                }else{
                    if (calcularDias($("#recall").val()) > 3){
                        alert("La fecha de rellamada no puede sobrepasar los tres días")
                        $isValid = false;
                        return
                    }
                }
            }
            if($("#subSubStatus").is(":visible")){
                if($("#subSubStatus").val() == ""){
                    alert("Debe escoger un sub subestado")
                    $isValid = false;
                    return
                }
            }
            if($divMotivo.is(":visible")){
                if($("#motivoNoAceptaSeguro").val() == ""){
                    alert("Debe escoger el motivo no desea seguro")
                    $isValid = false;
                    return
                }
            }

            if ($telefonoContactadoDiv.is(":visible")) {
                if ($telefonoContactado.val() === "") {
                    alert("Ingrese el teléfono al cual pudo contactar al cliente");
                    $isValid = false;
                    return
                } else {
                    if ($telefonoContactado.val().substring(0, 1) != '0') {
                        alert("El teléfono contactado es incorrecto");
                        $isValid = false;
                        return
                    } else {
                        if (!validarSiNumeroContactado($telefonoContactado.val())) {
                            alert("El teléfono contactado no es un número válido");
                            $isValid = false;
                            return
                        }else{
                            if ($telefonoContactado.val().length ==  9 && $telefonoContactado.val().trim().substring(0, 2) == "09" ) {
                                alert("Esta ingresando un telefono convencional incorrecto. Verifique!");
                                $isValid = false;
                                return
                            }else {
                                if ($telefonoContactado.val().length ==  10 && $telefonoContactado.val().trim().substring(0, 2) != "09" ) {
                                    alert("Esta ingresando un telefono celular incorrecto. Verifique!");
                                    $isValid = false;
                                    return
                                }
                            }
                        }
                    }
                }
                if ($("#estadoTelefonoContactado").val() === "") {
                    alert("Ingrese el estado del teléfono contactado");
                    $isValid = false;
                    return
                }
            }
        }
    }

    if($productoPrincipalDiv.is(":visible")){

        if($("#emailPersonalGestion").val() == ""){
            alert("Campo correo electrónico vacio");
            $isValid = false;
            return;
        }
        if($("#lugarNacimiento").val() == ""){
            alert("Campo lugar nacimiento vacio");
            $isValid = false;
            return;
        }
        if($("#fechaNacimientoGestion").val() == ""){
            alert("Ingrese la fecha de nacimiento del cliente");
            $isValid = false;
            return;
        }else{
            if ($("#fechaNacimientoGestion").val().length !=  10) {
                alert("Formato incorrecto para la fecha de nacimiento DD/MM/AAAA");
                $isValid = false;
                return
            }
        }
        if($("#gestionLinea").val() == ""){
            alert("Campo gestión en línea vacio");
            $isValid = false;
            return;
        }
        if($gestionLineaDiv.is(":visible")) {
            if ($codigoOtp.val() == "") {
                alert("Ingrese el codigo OTP que brinda el cliente");
                $isValid = false;
                return;
            }
        }
        if ($tipoPlanGestion.val() == "") {
            alert("Seleccione el plan que desea el cliente");
            $isValid = false;
            return;
        }

        if($("#estado_civil").val() == ""){
            alert("Campo estado civil vacio");
            $isValid = false;
            return;
        }

        if($("#genero").val() == ""){
            alert("Campo género vacio");
            $isValid = false;
            return;
        }

        if ($("#telefonoGestion1").val() === "") {
            alert("Ingrese el teléfono 1");
            $isValid = false;
            return
        } else {
            if ($("#telefonoGestion1").val().substring(0, 1) != '0') {
                alert("El teléfono 1 es incorrecto");
                $isValid = false;
                return
            } else {
                if (!validarSiNumeroContactado($("#telefonoGestion1").val())) {
                    alert("El teléfono 1 no es un número válido");
                    $isValid = false;
                    return
                }else{
                    if ($("#telefonoGestion1").val().length ==  9 && $("#telefonoGestion1").val().trim().substring(0, 2) == "09" ) {
                        alert("Esta ingresando un telefono convencional incorrecto en el campo telefono 1. Verifique!");
                        $isValid = false;
                        return
                    }else {
                        if ($("#telefonoGestion1").val().length ==  10 && $("#telefonoGestion1").val().trim().substring(0, 2) != "09" ) {
                            alert("Esta ingresando un telefono celular incorrecto en el campo telefono 1. Verifique!");
                            $isValid = false;
                            return
                        }
                    }
                }
            }
        }

        if ($("#telefonoGestion2").val() === "") {
            alert("Ingrese el teléfono 2");
            $isValid = false;
            return
        } else {
            if ($("#telefonoGestion2").val().substring(0, 1) != '0') {
                alert("El teléfono 2 es incorrecto");
                $isValid = false;
                return
            } else {
                if (!validarSiNumeroContactado($("#telefonoGestion2").val())) {
                    alert("El teléfono 2 no es un número válido");
                    $isValid = false;
                    return
                }else{
                    if ($("#telefonoGestion2").val().length ==  9 && $("#telefonoGestion2").val().trim().substring(0, 2) == "09" ) {
                        alert("Esta ingresando un telefono convencional incorrecto en el campo telefono 2. Verifique!");
                        $isValid = false;
                        return
                    }else {
                        if ($("#telefonoGestion2").val().length ==  10 && $("#telefonoGestion2").val().trim().substring(0, 2) != "09" ) {
                            alert("Esta ingresando un telefono celular incorrecto en el campo telefono 2. Verifique!");
                            $isValid = false;
                            return
                        }
                    }
                }
            }
        }

        if ($("#telefonoGestion3").val() === "") {
            alert("Ingrese el teléfono 3");
            $isValid = false;
            return
        } else {
            if ($("#telefonoGestion3").val().substring(0, 1) != '0') {
                alert("El teléfono 3 es incorrecto");
                $isValid = false;
                return
            } else {
                if (!validarSiNumeroContactado($("#telefonoGestion3").val())) {
                    alert("El teléfono 3 no es un número válido");
                    $isValid = false;
                    return
                }else{
                    if ($("#telefonoGestion3").val().length ==  9 && $("#telefonoGestion3").val().trim().substring(0, 2) == "09" ) {
                        alert("Esta ingresando un telefono convencional incorrecto en el campo telefono 3. Verifique!");
                        $isValid = false;
                        return
                    }else {
                        if ($("#telefonoGestion3").val().length ==  10 && $("#telefonoGestion3").val().trim().substring(0, 2) != "09" ) {
                            alert("Esta ingresando un telefono celular incorrecto en el campo telefono 3. Verifique!");
                            $isValid = false;
                            return
                        }
                    }
                }
            }
        }

        if($("#provinciaAsegurada").val() == ""){
            alert("Ingrese la provincia del asegurada");
            $isValid = false;
            return;
        }
        if($("#ciudadAsegurada").val() == ""){
            alert("Ingrese la ciudad de domicilio");
            $isValid = false;
            return;
        }
        if($("#sectorAsegurada").val() == ""){
            alert("Ingrese la parroquia del asegurada");
            $isValid = false;
            return;
        }
        if($("#callePrincipalAsegurada").val() == ""){
            alert("Ingrese la calle principal del asegurada");
            $isValid = false;
            return;
        }
        if($("#numeracionAsegurada").val() == ""){
            alert("Ingrese la numeración del asegurada");
            $isValid = false;
            return;
        }
        if($("#calleTransversalAsegurada").val() == ""){
            alert("Ingrese la calle transversal del asegurada");
            $isValid = false;
            return;
        }
        if($("#referenciaAsegurada").val() == ""){
            alert("Ingrese la referencia del asegurada");
            $isValid = false;
            return;
        }
        /*
        if($("#provinciaEntrega").val() == ""){
            alert("Ingrese la provincia de entrega");
            $isValid = false;
            return;
        }
        if($("#ciudadEntrega").val() == ""){
            alert("Ingrese la ciudad de entrega");
            $isValid = false;
            return;
        }
        if($("#sectorEntrega").val() == ""){
            alert("Ingrese la parroquia de entrega");
            $isValid = false;
            return;
        }
        if($("#callePrincipalEntrega").val() == ""){
            alert("Ingrese la calle principal de entrega");
            $isValid = false;
            return;
        }
        if($("#numeracionEntrega").val() == ""){
            alert("Ingrese la numeración de entrega");
            $isValid = false;
            return;
        }
        if($("#calleTransversalEntrega").val() == ""){
            alert("Ingrese la calle transversal de entrega");
            $isValid = false;
            return;
        }
        if($("#referenciaEntrega").val() == ""){
            alert("Ingrese la referencia de entrega");
            $isValid = false;
            return;
        }

        if($("#horaEntrega").val() == ""){
            alert("Ingrese la hora de entrega");
            $isValid = false;
            return;
        }*/






        //Se valida que se haya ingresado adicionales
       /* if($adicionalesArray.length == 0){
            alert("Debe ingresar los productos del cliente");
            $isValid = false;
            return;
        }*/

        //Se valida que la información concatenada no exceda los 150 caracteres
       /* $informacionConcatenadaDomicilio = $callePrincipalDomic.val() + " " + $numeracionDomic.val() + " " + $calleTransversalDomic.val()+" " + $sectorDomic.val() + " " + $referenciaDomic.val();
        $informacionConcatenadaTrabajo = $callePrincipalTrab.val() + " " + $numeracionTrab.val() + " " + $calleTransversalTrab.val() + " " + $sectorTrab.val() + " " + $referenciaTrab.val();
        $longitudDomicilio = $informacionConcatenadaDomicilio.length;
        $longitudTrabajo = $informacionConcatenadaTrabajo.length;

        if($longitudDomicilio > 296){
            alert("La longitud en total de la dirección de domicilio tiene "+$longitudDomicilio+" caracteres. Sólo se permite hasta 150");
            $isValid = false;
            return;
        }

        if($longitudTrabajo > 296){
            alert("La longitud en total de la dirección de trabajo tiene "+$longitudDomicilio+" caracteres. Sólo se permite hasta 150");
            $isValid = false;
            return;
        }*/



    }

    return $isValid;
}

function formatearCedulaTitular($cedula){
    if($cedula.length == 9){
        $cedula = '0' + $cedula;
    }else{
        if($cedula.length > 10){
            $cedula = $cedula.substr($cedula.length-10, 10);
        }
    }
    return $cedula;
}

function validarDatosAdicional(){
    $isValid = true;
    if($('#producto').val() == ""){
        alert("Seleccione el producto que desee el cliente");
        $isValid = false;
        return;
    }
    if($('#plan').val() == ""){
        alert("Seleccione el plan que desee el cliente");
        $isValid = false;
        return;
    }

    return $isValid;
}

/**
 * Función basada en función de internet: https://gist.github.com/vickoman/7800717
 */
function esCedulaValida($entrada){
    $esValida = true;
    if($entrada.length != 10){
        $esValida = false;
    }else{
        //Los dos primeros dígitos me indican la provincia
        $region = parseInt($entrada.substring(0, 2));
        if ($region <= 0 || $region > 30){
            $esValida = false;
        }else{
            $ultimoDigito = $entrada.substring(9, 10);
            //Saco los pares y los sumo
            $pares = parseInt($entrada.substring(1,2)) + parseInt($entrada.substring(3,4)) + parseInt($entrada.substring(5,6)) + parseInt($entrada.substring(7,8));

            //Agrupo los impares, los multiplico por un factor de 2, si la resultante es > que 9 le restamos el 9 a la resultante
            $numero1 = $entrada.substring(0,1);
            $numero1 = ($numero1 * 2);
            if( $numero1 > 9 ){ $numero1 = ($numero1 - 9); }

            $numero3 = $entrada.substring(2,3);
            $numero3 = ($numero3 * 2);
            if( $numero3 > 9 ){ $numero3 = ($numero3 - 9); }

            $numero5 = $entrada.substring(4,5);
            $numero5 = ($numero5 * 2);
            if( $numero5 > 9 ){ $numero5 = ($numero5 - 9); }

            $numero7 = $entrada.substring(6,7);
            $numero7 = ($numero7 * 2);
            if( $numero7 > 9 ){ $numero7 = ($numero7 - 9); }

            $numero9 = $entrada.substring(8,9);
            $numero9 = ($numero9 * 2);
            if( $numero9 > 9 ){ $numero9 = ($numero9 - 9); }

            $impares = $numero1 + $numero3 + $numero5 + $numero7 + $numero9;

            //Suma total
            $suma_total = ($pares + $impares);


            //extraemos el primero digito
            if($suma_total >= 10) //Si la suma total es de dos cifras
                $primer_digito_suma = String($suma_total).substring(0,1);
            else
                $primer_digito_suma = '0';


            //Obtenemos la decena inmediata
            $decena = (parseInt($primer_digito_suma) + 1)  * 10;


            //Obtenemos la resta de la decena inmediata - la suma_total esto nos da el digito validador
            $digito_validador = $decena - $suma_total;

            //Si el digito validador es = a 10 toma el valor de 0
            if($digito_validador == 10)
                $digito_validador = 0;

            if($digito_validador != $ultimoDigito){
                $esValida = false;
            }
        }
    }

    return $esValida;
}

/**
 * Función bajada de internet https://es.stackoverflow.com/questions/31373/obtener-la-edad-a-partir-de-la-fecha-de-nacimiento-con-javascript-y-php
 * @param fecha
 * @returns {number}
 */
function calcularEdad(fecha) {
    var hoy = new Date();
    var cumpleanos = new Date(fecha);
    var edad = hoy.getFullYear() - cumpleanos.getFullYear();
    var m = hoy.getMonth() - cumpleanos.getMonth();

    if (m < 0 || (m === 0 && hoy.getDate() < cumpleanos.getDate())) {
        edad--;
    }

    return edad;
}

/**
 * Valida si el valor ingresado es numérico
 * @param numero
 */
function validarSiNumeroContactado(numero){
    $esNumero = true;

    return $esNumero;
}
function validarSiNumero(numero){
    $esNumero = true;
    if (!/^([0-9])*$/.test(numero)){
        $esNumero = false;
    }
    return $esNumero;
}

$('#horaEntrega').on('keypress', function(e) {
    if(checkIfNumberNoSpace(e.which, e)==0){
        return false;
    }else{
        return;
    }
});
$('#fechaNacimiento').on('keypress', function(e) {
    if(checkIfNumberNoSpace(e.which, e)==0){
        return false;
    }else{
        return;
    }
});

$('#valor').on('keypress', function(e) {
    if(checkIfNumberNoSpace(e.which, e)==0){
        return false;
    }else{
        return;
    }
});
$('#valor').on('keydown', function (e)
{
    try {
        if ((e.keyCode == 8 || e.keyCode == 46))
            return false;
        else
            return true;
    }
    catch (Exception)
    {
        return false;
    }
});

$(".accordion-titulo").click(function(){

    var contenido=$(this).next(".accordion-content");

    if(contenido.css("display")=="none"){ //open
        contenido.slideDown(250);
        $(this).addClass("open");
    }
    else{ //close
        contenido.slideUp(250);
        $(this).removeClass("open");
    }

});

/**
 *@description Funcion que evita que puedan ingresar numeros en campos
 * @author Andres Redroban
 * */

function checkIfNumberNoSpace(codeKey,e){
    if (codeKey == 32)
        return 0;
    // Asignando numero y no espacios
    if ($.inArray(codeKey, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
        // Allow: Ctrl+A
        (codeKey == 97 && e.ctrlKey === true) ||
        // Allow: Ctrl+C
        (codeKey == 99 && e.ctrlKey === true) ||
        // Allow: Ctrl+X
        (codeKey == 120 && e.ctrlKey === true) ||
        // Allow: home, end, left, right, tab
        (codeKey == 0)) {
        // let it happen, don't do anything
        return 1;
    }
    if ((codeKey < 48 || codeKey > 57)) {
        return 0;
    }
}

/**
 * Valida que en el valor ingresado sólo hayan letras y espacios
 * @param entrada
 * @returns {boolean}
 */
function validarSiSoloLetras(entrada){
    $esSoloTexto = true;
    $filtro = /^([A-Za-z ])*$/;
    if(!$filtro.test(entrada)){
        $esSoloTexto = false;
    }
    return $esSoloTexto;
}

/**
 * Valida si el correo ingresado es correcto
 * @param email
 * @author Andres Redroban
 */
function validarEmail(email)
{
    var regex = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    return regex.test(email) ? true : false;
}

/**
 * Función tomada como ejemplo de internet https://www.lawebdelprogramador.com/foros/JavaScript/1594805-Calcular-la-cantidad-de-dias-entre-dos-fechas-Javascript-y-HTML.html
 * @param fecha
 * @returns {contdias}
 * @author Andres Redrobán
 * @description La siguiente función calcula el numero de dias tomando como referencia el facha actual y la fecha ingresada desde el sistema.
 */
function calcularDias(fecha){
    var fechaini = new Date();
    var fechafin = new Date(fecha);
    var diasdif= fechafin.getTime()-fechaini.getTime();
    var contdias = Math.round(diasdif/(1000*60*60*24));
    return contdias;
}

/**
 * Limpia los campos luego de agregar un adicional
 */
function limpiarCamposAdicional(){
    $valor.val("");
    $producto.val($("#producto option:first").val());
    $plan.val($("#plan option:first").val());

}
function soloLetras(e){
    key = e.keyCode || e.which;
    tecla = String.fromCharCode(key).toLowerCase();
    letras = " áéíóúabcdefghijklmnñopqrstuvwxyz";
    especiales = "8-37-39-46";

    tecla_especial = false
    for(var i in especiales){
        if(key == especiales[i]){
            tecla_especial = true;
            break;
        }
    }

    if(letras.indexOf(tecla)==-1 && !tecla_especial){
        return false;
    }
}