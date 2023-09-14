<%@ page import="br.edu.imepac.utils.MensagemStatus" %>
<%@ page import="br.edu.imepac.utils.StatusMensagemEnum" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Registration</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>

<%
    MensagemStatus mensagemStatus = (MensagemStatus) request.getAttribute("mensagemStatus");
    if (mensagemStatus != null) {
        if (mensagemStatus.getStatus() == StatusMensagemEnum.ERROR) {
%>
    <div class="erro"><%=mensagemStatus.getMensagem()%>
        <p>Deu erro</p>
    </div>
<%
    } else if (mensagemStatus.getStatus() == StatusMensagemEnum.SUCCESS) {
%>
    <div class="sucesso"><%=mensagemStatus.getMensagem()%>
    </div>
<%
        }
    }
%>

<form id="registration-form">
    <!-- Registration fields go here -->
    <label for="username">Nome de usuario:</label>
    <input type="text" id="username" name="username"
           value="<%=(request.getParameter("nome")==null)?"":request.getParameter("nome")%>">

    <label for="email">Email:</label>
    <input type="email" id="email" name="email"
           value="<%=(request.getParameter("email")==null)?"":request.getParameter("email")%>">

    <label for="password">Senha:</label>
    <input type="password" id="password" name="password"
           value="<%=(request.getParameter("senha")==null)?"":request.getParameter("senha")%>">

    <button type="submit">Cadastrar</button>
</form>
</body>
</html>
