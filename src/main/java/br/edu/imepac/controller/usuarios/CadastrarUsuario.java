package br.edu.imepac.controller.usuarios;

import br.edu.imepac.database.DataBaseConnection;
import br.edu.imepac.utils.MensagemStatus;
import br.edu.imepac.utils.StatusMensagemEnum;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CadastrarUsuario extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        if (nome.isEmpty()) {
            MensagemStatus mensagemStatus = new MensagemStatus();
            mensagemStatus.setMensagem("O campo nome é obrigatório!");
            mensagemStatus.setStatus(StatusMensagemEnum.ERRO);
            req.setAttribute("mensagemStatus", mensagemStatus);
            req.getRequestDispatcher("cadastrarUsuario.jsp").forward(req, resp);
            return;
        }
        if (email.isEmpty()) {
            MensagemStatus mensagemStatus = new MensagemStatus();
            mensagemStatus.setMensagem("O campo email é obrigatório!");
            mensagemStatus.setStatus(StatusMensagemEnum.ERRO);
            req.setAttribute("mensagemStatus", mensagemStatus);
            req.getRequestDispatcher("cadastrarUsuario.jsp").forward(req, resp);
            return;
        }
        if (senha.isEmpty()) {
            MensagemStatus mensagemStatus = new MensagemStatus();
            mensagemStatus.setMensagem("O campo senha é obrigatório!");
            mensagemStatus.setStatus(StatusMensagemEnum.ERRO);
            req.setAttribute("mensagemStatus", mensagemStatus);
            req.getRequestDispatcher("cadastrarUsuario.jsp").forward(req, resp);
            return;
        }

        try {
            Connection connection = DataBaseConnection.getInstance();

            String cadastrarUsuarioQuery = "INSERT INTO usuarios(nome, email, senha) VALUES(?, ?, ?);";

            PreparedStatement preparedStatement = connection.prepareStatement(cadastrarUsuarioQuery);
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, senha);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            MensagemStatus mensagemStatus = new MensagemStatus();
            mensagemStatus.setMensagem("Erro inesperado. Contate o administrador!");
            mensagemStatus.setStatus(StatusMensagemEnum.ERRO);
            req.setAttribute("mensagemStatus", mensagemStatus);
            req.getRequestDispatcher("cadastrarUsuario.jsp").forward(req, resp);
            return;
        }

        //retornar uma mensagem de resposta ao usuario
        MensagemStatus mensagemStatus = new MensagemStatus();
        mensagemStatus.setMensagem("Dados cadastrados!");
        mensagemStatus.setStatus(StatusMensagemEnum.SUCESSO);
        req.setAttribute("mensagemStatus", mensagemStatus);
        req.getRequestDispatcher("cadastrarUsuario.jsp").forward(req, resp);
    }
}
