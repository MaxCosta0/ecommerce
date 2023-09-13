package br.edu.imepac.controller.usuarios;

import br.edu.imepac.database.DataBaseConnection;
import br.edu.imepac.model.Usuario;
import br.edu.imepac.utils.MensagemStatus;
import br.edu.imepac.utils.StatusMensagemEnum;

import javax.servlet.RequestDispatcher;
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

public class ExcluirUsuario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Connection connection = DataBaseConnection.getInstance();

            String sql = "delete from usuarios where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, Long.parseLong(req.getParameter("id")));
            preparedStatement.executeUpdate();

            MensagemStatus mensagemStatus = new MensagemStatus();
            mensagemStatus.setMensagem("Dados excluídos com sucesso!");
            mensagemStatus.setStatus(StatusMensagemEnum.SUCCESS);
            req.setAttribute("mensagemStatus", mensagemStatus);
            RequestDispatcher dispatcher = req.getRequestDispatcher("listagemUsuario");
            dispatcher.forward(req, resp);
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
