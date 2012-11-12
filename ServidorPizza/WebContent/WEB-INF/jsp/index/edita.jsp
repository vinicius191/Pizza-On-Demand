<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link href="css/style.css" rel="stylesheet" type="text/css" media="all">
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
        <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
        <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
        <!-- Inclusão do Jquery Validate -->
        <script type="text/javascript" src="http://ajax.microsoft.com/ajax/jquery.validate/1.6/jquery.validate.js" ></script>
        <title>Pizza - On Demand! | Editar Cadastro</title>
    </head>
    <body>
        <div id="container">
            <table border="0" width="850px" align="center" style="text-align: center; color: #5a0805; font-size: 14px;">
                <tr>
                    <td colspan="2" style="text-align: center;">
                        <img src="imagens/logo_inicial.png" width=20% height=35% />
                    </td>
                </tr>
                <tr><td colspan="2" style="height: 30px;"></td></tr>
                <tr>
                    <td height="150px" style="text-align: left; padding-left: 18px;">
                <c:if test="${pizzaria.imagemPerfil != null}">
                    <img class="imagem" src="<c:url value="/mostraImagem/${pizzaria.id}/imagem"/>" width="150" height="150" />
                </c:if>
                <c:if test="${pizzaria.imagemPerfil == null}">
                    <div class="imagem" style="width: 150px; height: 150px;">
                        <div style="padding-top: 67px; padding-left: 28px; color: #d1cfbd;">
                            Sem Imagem
                        </div>
                    </div>
                </c:if>                    
                </td>
                <td style="vertical-align: top;">
                    <div style="text-align: justify; background-image: url('imagens/fundomensagem.png'); background-repeat: no-repeat; width: 574px; height: 164px;">
                        <c:if test="${pizzaria.mensagemPerfil != null}">
                        <div style="width: 500px; margin-left: 45px; padding-top: 25px;">
                            ${pizzaria.mensagemPerfil}
                        </div>
                        </c:if>
                        <c:if test="${pizzaria.mensagemPerfil == null}">
                        <div style="width: 500px; margin-left: 45px; padding-top: 67px; color: #d1cfbd; text-align: center;">
                            Sem Mensagem de Perfil
                        </div>
                        </c:if>
                        <c:if test="${pizzaria.mensagemPerfil == ''}">
                        <div style="width: 500px; margin-left: 45px; padding-top: 42px; color: #d1cfbd; text-align: center;">
                            Sem Mensagem de Perfil
                        </div>
                        </c:if>
                    </div>
                </td>			
                </tr>
                <tr>
                    <td width="220px" style="padding-top: 30px; text-align: left; vertical-align: top;">
    
                        <jsp:include page="../template/menu.jsp" />

                    </td>
                    <td style="padding-top: 30px; text-align: left;  vertical-align: top;">
                        <jsp:include page="../template/conteudo.jsp" />
                        <div style="padding: 20px;">
                            <form id="formAtualizaCadastro" action="${pageContext.request.contextPath}/atualizarPizzaria/${pizzaria.id}" method="POST">
                                <table border="0" width="94%" align="left" style="color: #5a0805; font-size: 18px;">
<!--                                    <tr>
                                        <td colspan="2" style="text-align: center; font-size: 12px;">
                                            <img src="imagens/logo_inicial.png" width=20% height=35% />
                                        </td>
                                    </tr>-->
                                    <!-- 	<tr> -->
                                    <!-- 		<td colspan="2" style="text-align: center; font-weight:bold; padding-top: 20px; padding-bottom: 10px;">Cadastro da sua Pizzaria</td> -->
                                    <!-- 	</tr> -->
<!--                                    <tr><td colspan="2" style="height: 30px; text-align: right; font-size: 14px;"><a href="${pageContext.request.contextPath}/">&laquo; Voltar</a></td></tr>-->
                                    <tr>
                                        <td colspan="2" style="padding-top: 20px; border-bottom: 1px solid #773e3c; font-size: 14px; font-weight: bold; padding-bottom: 10px;">Dados de acesso ao Sistema</td>
                                    </tr>
                                    <tr><td colspan="2" style="height: 20px;"></td></tr>	
                                    <tr>
                                        <td style="font-size: 14px; width: 200px; padding-left: 3px;">Email da Pizzaria: </td>
                                        <td>
                                            <input type="text" name="pizzaria.email" value="${pizzaria.email}" id="pizzaria.email" style="width: 40%; height: 20px; border: 1px solid #5a0805; padding-left: 3px;" />
                                            
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="font-size: 14px; width: 200px; padding-left: 3px;">Senha: </td>
                                        <td>
                                            <input type="password" name="pizzaria.senha" value="${pizzaria.senha}" id="pizzaria.senha" style="width: 40%; height: 20px; border: 1px solid #5a0805; padding-left: 3px;" />
                                            
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" style="padding-top: 20px; border-bottom: 1px solid #773e3c; font-size: 14px; font-weight: bold; padding-bottom: 10px;">Dados da sua Pizzaria</td>
                                    </tr>
                                    <tr><td colspan="2" style="height: 20px;"></td></tr>
                                    <tr>
                                        <td style="font-size: 14px; width: 200px; padding-left: 3px;">Razão Social: </td>
                                        <td><input type="text" name="pizzaria.razao_social" value="${pizzaria.razao_social}" style="width: 60%; height: 20px; border: 1px solid #5a0805; padding-left: 3px;" /></td>
                                    </tr>
                                    <tr>
                                        <td style="font-size: 14px; width: 200px; padding-left: 3px;">CNPJ da Pizzaria: </td>
                                        <td>
                                            <input type="text" name="pizzaria.cnpj" value="${pizzaria.cnpj}" id="cnpj" style="width: 40%; height: 20px; border: 1px solid #5a0805; padding-left: 3px;" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="font-size: 14px; width: 200px; padding-left: 3px;">Telefone 1 da Pizzaria: </td>
                                        <td>
                                            <input type="text" name="pizzaria.telefone1" value="${pizzaria.telefone1}" id="telefone1" style="width: 40%; height: 20px; border: 1px solid #5a0805; padding-left: 3px;" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="font-size: 14px; width: 200px; padding-left: 3px;">Telefone 2 da Pizzaria: </td>
                                        <td>
                                            <input type="text" name="pizzaria.telefone2" value="${pizzaria.telefone2}" id="telefone2" style="width: 40%; height: 20px; border: 1px solid #5a0805; padding-left: 3px;" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="font-size: 14px; width: 200px; padding-left: 3px;">Telefone 3 da Pizzaria: </td>
                                        <td>
                                            <input type="text" name="pizzaria.telefone3" value="${pizzaria.telefone3}" id="telefone3" style="width: 40%; height: 20px; border: 1px solid #5a0805; padding-left: 3px;" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" style="padding-top: 20px; border-bottom: 1px solid #773e3c; font-size: 14px; font-weight: bold; padding-bottom: 10px;">Sua localização</td>
                                    </tr>
                                    <tr><td colspan="2" style="height: 20px;"></td></tr>	
                                    <tr>
                                        <td style="font-size: 14px; width: 200px; padding-left: 3px;">Endereço da Pizzaria: </td>
                                        <td><input type="text" name="pizzaria.endereco" value="${pizzaria.endereco}" id="pizzaria.endereco" style="width: 60%; height: 20px; border: 1px solid #5a0805; padding-left: 3px;" /></td>
                                    </tr>
                                    <tr>
                                        <td style="font-size: 14px; width: 200px; padding-left: 3px;">Complemento: </td>
                                        <td><input type="text" name="pizzaria.complemento" value="${pizzaria.complemento}" style="width: 40%; height: 20px; border: 1px solid #5a0805; padding-left: 3px;" /></td>
                                    </tr>
                                    <tr>
                                        <td style="font-size: 14px; width: 200px; padding-left: 3px;">CEP: </td>
                                        <td><input type="text" name="pizzaria.cep" value="${pizzaria.cep}" id="cep" style="width: 40%; height: 20px; border: 1px solid #5a0805; padding-left: 3px;" /></td>
                                    </tr>
                                    <tr>
                                        <td style="font-size: 14px; width: 200px; padding-left: 3px;">Bairro: </td>
                                        <td><input type="text" name="pizzaria.bairro" value="${pizzaria.bairro}" style="width: 40%; height: 20px; border: 1px solid #5a0805; padding-left: 3px;" /></td>
                                    </tr>
                                    <tr>
                                        <td style="font-size: 14px; width: 200px; padding-left: 3px;">Cidade: </td>
                                        <td><input type="text" name="pizzaria.cidade" value="${pizzaria.cidade}" style="width: 40%; height: 20px; border: 1px solid #5a0805; padding-left: 3px;" /></td>
                                    </tr>
                                    <tr>
                                        <td style="font-size: 14px; width: 200px; padding-left: 3px;">Estado: </td>
                                        <!--                        <td><input type="text" name="pizzaria.estado" style="width: 40%; height: 20px; border: 1px solid #5a0805; padding-left: 3px;" /></td>-->
                                        <td>
                                            <select name="pizzaria.estado" id="pizzaria_estado" style="width: 40%; height: 20px; border: 1px solid #5a0805; padding-left: 3px;">
                                                <option value="">Selecione</option>
                                                <option value="AC">AC</option>
                                                <option value="AL">AL</option>
                                                <option value="AM">AM</option>
                                                <option value="AP">AP</option>
                                                <option value="BA">BA</option>
                                                <option value="CE">CE</option>
                                                <option value="DF">DF</option>
                                                <option value="ES">ES</option>
                                                <option value="GO">GO</option>
                                                <option value="MA">MA</option>
                                                <option value="MG">MG</option>
                                                <option value="MS">MS</option>
                                                <option value="MT">MT</option>
                                                <option value="PA">PA</option>
                                                <option value="PB">PB</option>
                                                <option value="PE">PE</option>
                                                <option value="PI">PI</option>
                                                <option value="PR">PR</option>
                                                <option value="RJ">RJ</option>
                                                <option value="RN">RN</option>
                                                <option value="RO">RO</option>
                                                <option value="RR">RR</option>
                                                <option value="RS">RS</option>
                                                <option value="SC">SC</option>
                                                <option value="SE">SE</option>
                                                <option value="SP">SP</option>
                                                <option value="TO">TO</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="font-size: 14px; width: 200px; padding-left: 3px;">Latitude: </td>
                                        <td><input type="text" name="pizzaria.latitude" value="${pizzaria.latitude}" id="pizzaria.latitude" style="width: 40%; height: 20px; border: 1px solid #5a0805; padding-left: 3px;" /></td>
                                    </tr>
                                    <tr>
                                        <td style="font-size: 14px; width: 200px; padding-left: 3px;">Longitude: </td>
                                        <td><input type="text" name="pizzaria.longitude" value="${pizzaria.longitude}" id="pizzaria.longitude" style="width: 40%; height: 20px; border: 1px solid #5a0805; padding-left: 3px;" /></td>
                                    </tr>
                                    <tr><td colspan="2" style="height: 20px;"></td></tr>
                                    <tr>
                                        <td colspan="2" style="height: 30px; font-size: 11px;">
                                            <div style="padding: 20px; border:1px dotted #5a0805; line-height: 1.8em">Precisamos saber a Latitude e a Longitude da sua Pizzaria. Siga os passos abaixo para descobrir estes dados.<br/><br/>
                                                1) Acesse <a href="http://www.procriativo.com.br/include/app/google-longitude-latitude.php" target="_blank">http://www.procriativo.com.br/include/app/google-longitude-latitude.php</a> digite seu endereço e clique em "Procurar".<br/>
                                                2) Copie os número de "Latitude / Longitude" que aparecem abaixo do mapa e cole nos campos deste formulário.</div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" style="height: 50px; text-align: center;">
                                            <input type="submit" id="enviar" value="Atualizar" style="width: 80px; font-size: 14px; color: white; height: 30px; background-color: #b96d69; border: 0px" />
                                            <input type="reset" id="enviar" value="Limpar" style="margin-left: 80px;  width: 80px; font-size: 14px; color: white; height: 30px; background-color: #b96d69; border: 0px" />
                                        </td>	
                                    </tr>
                                </table>
                            </form>                      
                        </div>
                    </td>
                </tr>
                <tr>
                </tr>
            </table>
            <div style="display: none;" id="dialog" class="errorList"></div>
            <div id="footer">
                Copyright 2012  Pizza - On Demand! - Todos os direitos Reservados.
            </div>
        </div>

        <script type="text/javascript">
            //Execute the function when window load
            $(document).ready(function() {
   
                $('#pizzaria_estado').val( '${pizzaria.estado}' );
   
                var docHeight = $(window).height();
                var footerHeight = $('#footer').height();
                var footerTop = $('#footer').position().top + footerHeight;
   
                if (footerTop < docHeight) {
                    $('#footer').css('margin-top', 10 + (docHeight - footerTop) + 'px');
                }
            });
        </script>

    </body>
</html>