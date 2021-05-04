<%-- 
    Document   : editar
    Created on : 27-abr-2021, 19:49:36
    Author     : Vlxdy Hishikawa
--%>

<%@page import="com.Vlxdy.modelo.Tareas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    Tareas tarea = (Tareas)request.getAttribute("tarea");
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="author" content="VLADIMIR HUANCA">
        <meta name="viewport" content="with=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="Logo.png">
        <title> TAREAS </title>
    </head>
    <style>
        body {
  		background-image: linear-gradient(
     		rgba(0, 0, 0, 0.6),
     		rgba(0, 0, 0, 0.6)
   			), url("Fondo.png");
  			background-repeat: no-repeat;
 			background-attachment: fixed;
  			background-size: 100% 100%;
  			padding: 20px;
  			background-color:#dbffcc;
            }
        #heading { color: #fff; }
        a,h1,h2,h4,li{
		color: white;
                }
        form{
		width: 480px;
		padding: 16px;
		border-radius: 0px;
		margin: auto;
		background-color: #ccf;
	}
	form input[type="number"]{
		width: 200px;
		padding: 3px 10px;
		border:1px solid #f6f6f6;
		border-radius: 3px;
		background-color:#9f9;
		margin:8px 0;
		display: inline-block;
	}
        form input[type="text"]{
		width: 200px;
		padding: 3px 10px;
		border:1px solid #f6f6f6;
		border-radius: 3px;
		background-color:#9f9;
		margin:8px 0;
		display: inline-block;
	}
	form input[type="submit"]{
		width: 100%;
		padding: 8px 16px;
		margin-top: 32px;
		border:1px solid #1e3300;
		border-radius: 5px;
		display: block;
		color: #fff;
		background-color:#1e3300;
	}
	form input[type="submit"]:hover{
            
		cursor:pointer;
	}
        SELECT{ 
                width: 200px;
		padding: 3px 10px;
		border:1px solid #f6f6f6;
		border-radius: 3px;
		background-color:  #f6f6f6;
		margin:8px 0;
		display: inline-block;
            }
    </style>
    <body>
            <% if  (tarea.getId() == 0) { %>
                <h1 align="center">REGISTRO DE TAREAS </h1>
            <% }%>
            <% if  (tarea.getId() != 0) { %>
                 <h1 align="center">MODIFICACION DEL REGISTRO DE LA TAREA "${tarea.tarea}" </h1>
            <% }%> 
        
        <form action="MainController" method="post">
            <h3 align="center">
                <% if  (tarea.getId() == 0) { %>
                    INGRESE LOS DATOS DE LA TAREA
                <% }%>
                <% if  (tarea.getId() != 0) { %>
                    MODIFIQUE LOS DATOS DE LA TAREA
                <% }%> 
            </h3>
            <table>
                <input type="hidden" name="id" value="${tarea.id}">
                <tr>
                    <td>TAREA:</td>
                    <td><input type="text" name="tarea" value="${tarea.tarea}" required></td>
                </tr>
                <tr>
                    <td>PRIORIDAD:</td>
                    <td>
                        <select name="prioridad" required>
                            <option value=""         
                                    <c:if test="${tarea.prioridad == 0}" var="res" >
                                        selected
                                    </c:if>
                                    >Seleccione una opcion</option>
                            <option value="1"
                                    <c:if test="${tarea.prioridad == 1}">
                                        selected
                                    </c:if>
                                    >ALTA</option>

                            <option value="2"
                                    <c:if test="${tarea.prioridad == 2}">
                                        selected
                                    </c:if>
                                    >MEDIA</option>   

                            <option value="3"
                                    <c:if test="${tarea.prioridad == 3}">
                                        selected
                                    </c:if>
                                    >BAJA</option>
                        </select>
                    </td>   
                </tr>
                <tr>
                    <td>COMPLETADO:</td>

                    <td>
                        <input type="radio" name="completado" value="1" 
                               <c:if test="${tarea.completado == 1}"
                                     var="res" scope="request">
                                   checked
                               </c:if>
                               required>Concluido
                        <br>
                        <input type="radio" name="completado" value="2"  
                               <c:if test="${tarea.completado == 2}"
                                     var="res" scope="request">
                                   checked
                               </c:if>
                               required>Pendiente
                    </td> 

                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Enviar" ></td>
                </tr>
            </table>
        </form>
    <footer>
        <p><br><br>
            <h3 id="heading" align="center">DEVELOPED &#x1F497; by <a style="color:yellow;" ><strong>VLADIMIR HUANCA</strong></a></h3>
        </p>
    </footer>
    </body>
</html>
