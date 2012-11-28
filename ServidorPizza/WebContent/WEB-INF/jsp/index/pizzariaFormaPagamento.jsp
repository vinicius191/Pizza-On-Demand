<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <!-- CSS -->
        <link href="css/style.css" rel="stylesheet" type="text/css" media="all">
        <link href="css/jquery-ui-1.9.1.custom.min.css" rel="stylesheet" type="text/css" media="all">
        <!-- Scripts -->
        <script src="scripts/jquery-1.8.2.js"></script>
        <script src="scripts/jquery-ui-1.9.1.custom.js"></script>
        <script src="scripts/jquery-ui-1.9.1.custom.min.js"></script>
        <script src="scripts/jquery.cookie.js"></script>
        <script src="scripts/jquery.dcjqaccordion.2.6.min.js"></script>
        <script src="scripts/jquery.hoverIntent.minified.js"></script>
        <!-- Inclusão do Jquery Validate -->
        <script type="text/javascript" src="scripts/jquery.validate.js" ></script>
        <script type="text/javascript" src="scripts/jquery.maskedinput-1.3.js" ></script>
        <script type="text/javascript" src="http://igorescobar.github.com/jQuery-Mask-Plugin/jquery.mask.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pizza - On Demand! | Forma de Pagamento</title>
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
                            <form id="formAtualizaCadastro" action="${pageContext.request.contextPath}/atualizarPizzariaFormaPagamento/${pizzaria.id}" method="POST">
                                <table border="0" width="94%" align="left" style="color: #5a0805; font-size: 18px;">
                                    <tr>
                                        <td colspan="2" style="padding-top: 20px; border-bottom: 1px solid #773e3c; font-size: 14px; font-weight: bold; padding-bottom: 10px;">Selecione as Formas de Pagamento da sua Pizzaria</td>
                                    </tr>
                                    <tr><td colspan="2" style="height: 20px; font-size: 14px; padding-bottom: 20px;"><br>Selecione entre as opções abaixo aquelas com as quais a sua Pizzaria trabalha.</td></tr>
                                    <tr>
                                        <td colspan="2" style="height: 20px; font-size: 14px;">
                                            <c:forEach items="${listaFormaPagamento}" var="lista" varStatus="s">
                                                <input type="checkbox" name="pizzariaFormaPagamento[${s.index}].formaPagamento.id" id="formaPagamentoItem" value="${lista.id}"> ${lista.descricao}<br>      
                                            </c:forEach>
                                        </td>
                                    </tr>
                                    <tr><td colspan="2" style="height: 20px;"></td></tr>
                                    <tr>
                                        <td colspan="2" style="padding-top: 20px; border-bottom: 1px solid #773e3c; font-size: 14px; font-weight: bold; padding-bottom: 10px;">Opções ja esclhidas</td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" style="height: 20px; font-size: 14px;">
                                            <c:if test="${listaPizzariaFormaPagamento != null}">
                                            <c:forEach items="${listaPizzariaFormaPagamento}" var="lista" varStatus="s">
                                                ${lista.descricao}<br> 
                                            </c:forEach>                                                
                                            </c:if>
                                            <c:if test="${listaPizzariaFormaPagamento == null}">
                                                Lista vazia
                                            </c:if>
                                        </td>
                                    </tr>
                                    <tr><td colspan="2" style="height: 20px;"></td></tr>
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