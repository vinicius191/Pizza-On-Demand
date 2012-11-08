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
        <title>Pizza - On Demand! | Cadastro de Produtos</title>
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
                            <form id="formAtualizaCadastro" action="${pageContext.request.contextPath}/cadastraProduto/${pizzaria.id}" method="POST">
                                <table border="0" width="94%" align="left" style="color: #5a0805; font-size: 18px;">
                                    <tr>
                                        <td colspan="2" style="padding-top: 20px; border-bottom: 1px solid #773e3c; font-size: 14px; font-weight: bold; padding-bottom: 10px;">Cadastro de produtos</td>
                                    </tr>
                                    <tr><td colspan="2" style="height: 20px;"></td></tr>
                                    <tr>
                                        <td style="font-size: 14px; padding-left: 3px;">Tipo do produto: </td>
                                        <td><input type="text" name="pizzaria.endereco" value="${pizzaria.endereco}" id="pizzaria.endereco" style="width: 60%; height: 20px; border: 1px solid #5a0805; padding-left: 3px;" />
                                        </td>                                        
                                    </tr>
                                    <tr>
                                        <td colspan="2" style="border: 1px solid black;">
                                            <span style="font-size: 11px;">(1 - Pizza, 2 - Bebidas, 3 - Outros)</span>
                                        </td>
                                    </tr> 
                                    <tr style="height: 40px;">
                                        <td style="font-size: 14px; padding-left: 3px;">Titulo do produto: </td>
                                        <td>
                                            <input type="text" name="produto.descricao" id="produto.descricao" style="width: 40%; height: 20px; border: 1px solid #5a0805; padding-left: 3px;" />
                                            
                                        </td>
                                    </tr>
                                    <tr style="height: 40px;">
                                        <td style="font-size: 14px; padding-left: 3px;">Detalhe do produto: </td>
                                        <td>
                                            <input type="text" name="produto.descricao" id="produto.descricao" style="width: 40%; height: 20px; border: 1px solid #5a0805; padding-left: 3px;" />
                                            
                                        </td>
                                    </tr>
                                    <tr style="height: 40px;">
                                        <td style="font-size: 14px; padding-left: 3px;">Descricão de item extra: </td>
                                        <td><input type="text" name="produto.extraDescricao" style="width: 60%; height: 20px; border: 1px solid #5a0805; padding-left: 3px;" /></td>
                                    </tr>
                                    <tr style="height: 40px;">
                                        <td style="font-size: 14px; padding-left: 3px;">Preço do item extra: </td>
                                        <td>
                                            <input type="text" name="produto.extraPreco" style="width: 40%; height: 20px; border: 1px solid #5a0805; padding-left: 3px;" />
                                        </td>
                                    </tr>
                                    <tr style="height: 40px;">
                                        <td style="font-size: 14px; border: 1px solid black; padding-left: 3px;">Preço 1: </td>
                                        <td>
                                            <input type="text" name="produto.preco1" style="width: 40%; height: 20px; border: 1px solid #5a0805; padding-left: 3px;" />
                                    </tr>
                                    <tr>
                                        <td colspan="2" style="border: 1px solid black;">
                                            <span style="font-size: 11px;">(para pizza pequena - se o produto não for pizza, não preencha)</span>
                                        </td>
                                    </tr> 
                                    <tr style="height: 40px;">
                                        <td style="font-size: 14px; padding-left: 3px;">Preço 2: </td>
                                        <td>
                                            <input type="text" name="produto.preco2" style="width: 40%; height: 20px; border: 1px solid #5a0805; padding-left: 3px;" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <span style="font-size: 11px;">(para pizza média - se o produto não for pizza, não preencha)</span>
                                        </td>
                                    </tr>
                                    <tr style="height: 40px;">
                                        <td style="font-size: 14px; padding-left: 3px;">Preço 3: </td>
                                        <td>
                                            <input type="text" name="produto.preco3"  style="width: 40%; height: 20px; border: 1px solid #5a0805; padding-left: 3px;" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <span style="font-size: 11px;">(para pizza grande - se o produto não for pizza, não preencha)</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" style="height: 50px;"></td>
                                    </tr>    
                                    <tr>
                                        <td colspan="2" style="height: 50px; text-align: center;">
                                            <input type="submit" id="enviar" value="Cadastrar" style="font-size: 14px; color: white; height: 30px; background-color: #b96d69; border: 0px" />
                                            <input type="reset" id="enviar" value="Limpar" style="margin-left: 40px; font-size: 14px; color: white; height: 30px; background-color: #b96d69; border: 0px" />
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
