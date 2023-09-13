<%@ page import="br.edu.imepac.utils.* " %>
<%@ page import="br.edu.imepac.model.Usuario" %>
<%@ page import="java.util.List" %>
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
<h2>Listagem de usuários</h2>

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

<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>excluir</th>

    </tr>

    <%
        List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
        if (usuarios != null) {
            for (Usuario usuario : usuarios) {
    %>
    <tr>
        <td><%= usuario.getId() %></td>
        <td><%= usuario.getNome() %></td>
        <td><%= usuario.getEmail() %></td>
        <td><a href="excluirUsuario?id=<%= usuario.getId() %>">Apagar</a></td>
    </tr>
    <%
            }
        }
    %>
</table>

</body>
</html>