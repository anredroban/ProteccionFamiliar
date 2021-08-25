<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
<title>Protección Familiar - Retirar Base</title>
<style>
	#btnenviar{
		border-radius: 10px;
		width: 100px;
	}
	#divresultado{
		text-align: center;
		margin-top: 3%;
		padding-top: 3%;
		font-size: 18px;
		margin-bottom: 3%;
	}
</style>
</head>
<body>
  <div class="body">   
			<div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-border-orange">
                            <div class="panel-heading panel-body-orange">
                                <h3 class="panel-title" style="color:white"><i class="fa fa-users fa-fw"></i> Retirar Base</h3>								
                            </div>
                            <div class="panel-body">
                                <div class="table-responsive">
	                                <g:form action="retirarBase">
	                                	<div class="form-group col-lg-3">
	                                		<strong>Usuario: </strong>
	                                		<g:select class="form-control" name="usuario" optionKey="id" from="${utilitarios.Util.getOperDisponiblesAsignacion()}" optionValue="${{it.nombre}}" noSelection="${['': 'TODOS']}" ></g:select>
	                                	</div>
	                                	<div class="form-group col-lg-3">
	                                		<strong>Tipo: </strong>
	                                		<g:select class="form-control" name="tipo" from="${['REGESTIONABLE', 'SIN GESTION', 'RELLAMADAS']}" noSelection="${['': 'TODOS']}"></g:select>
	                                	</div>
										<div class="form-group col-lg-3">
											<strong>Cod desde: </strong>
											<g:textField class="form-control" required="" name="desde"></g:textField>
										</div>
										<div class="form-group col-lg-3">
											<strong>Cod hasta: </strong>
											<g:textField class="form-control" required="" name="hasta"></g:textField>
										</div>
										<div class="form-group col-lg-9" style="margin-top: 20px">
	                                		<strong>Nombre Base: </strong>
	                                		<g:select class="form-control" name="nombrebase" from="${utilitarios.Util.getNombresBase()}" noSelection="${['': 'TODOS']}"></g:select>
	                                	</div>

											<div class="form-group col-lg-3">
												<label></label>
												<input class="form-control btn btn-primary" type="submit" name="btnenviar" id="btnenviar" value="Retirar"/>
											</div>


	                                </g:form>
	                                <g:if test="${updateRealizado == true}">
	                                	<div id="divresultado" class="col-lg-12">
	                                		${resultado} registros fueron retirados
	                                	</div>
	                                </g:if>
                                </div>
								<%--table responsive--%>
									
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.row -->
  </div>
</body>
</html>