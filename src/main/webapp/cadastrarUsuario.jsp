<%@ page import="br.edu.imepac.utils.* " %>
<html>
<head>
    <style>
        .erro {
            background-color: red;
            color: white;
        }

        .sucesso {
            background-color: darkgreen;
            color: white;
        }
    </style>
</head>
<body>
<h2>Hello World!</h2>

<%
    MensagemStatus mensagemStatus = (MensagemStatus) request.getAttribute("mensagemStatus");
    if (mensagemStatus != null) {
        if (mensagemStatus.getStatus() == StatusMensagemEnum.ERROR) {
%>
<div class="erro"><%=mensagemStatus.getMensagem()%>
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

<form action="cadastrarUsuario" method="post">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name"
           value="<%=(request.getParameter("name")==null)?"":request.getParameter("name")%>"><br><br>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email"
           value="<%=(request.getParameter("email")==null)?"":request.getParameter("email")%>"><br><br>

    <input type="submit" value="Submit">
</form>

</body>
</html>