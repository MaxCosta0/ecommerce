package br.edu.imepac.controller.usuarios;

import br.edu.imepac.database.DataBaseConnection;
import br.edu.imepac.model.Usuario;
import br.edu.imepac.utils.MensagemStatus;
import br.edu.imepac.utils.StatusMensagemEnum;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ListagemUsuario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Connection connection = DataBaseConnection.getInstance();

            String sql = "select * from usuarios";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Usuario> usuarios = new ArrayList<>();

            while (resultSet.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(resultSet.getLong("id"));
                usuario.setNome(resultSet.getString("nome"));
                usuario.setEmail(resultSet.getString("email"));

                usuarios.add(usuario);
            }

            req.setAttribute("usuarios", usuarios);
            req.getRequestDispatcher("listagemUsuario.jsp").forward(req, resp);
        } catch (Exception exception) {
            exception.printStackTrace();
            MensagemStatus mensagemStatus = new MensagemStatus();
            mensagemStatus.setMensagem("Erro inesperado. Contate o administrador!");
            mensagemStatus.setStatus(StatusMensagemEnum.ERROR);
            req.setAttribute("mensagemStatus", mensagemStatus);
            req.getRequestDispatcher("listagemUsuario.jsp").forward(req, resp);
        }

    }
}
