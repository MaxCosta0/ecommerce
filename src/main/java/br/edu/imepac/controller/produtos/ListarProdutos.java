package br.edu.imepac.controller.produtos;

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
import java.sql.ResultSet;

public class ListarProdutos extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Connection connection = DataBaseConnection.getInstance();

            String listarProdutosQuery = "SELECT * FROM produtos";
            PreparedStatement preparedStatement = connection.prepareStatement(listarProdutosQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            MensagemStatus mensagemStatus = new MensagemStatus();
            mensagemStatus.setStatus(StatusMensagemEnum.SUCESSO);
            req.setAttribute("produtos", resultSet);
            req.getRequestDispatcher("listarProdutos.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            MensagemStatus mensagemStatus = new MensagemStatus();
            mensagemStatus.setMensagem("Erro inesperado. Contate o administrador!");
            mensagemStatus.setStatus(StatusMensagemEnum.ERRO);
            req.setAttribute("mensagemStatus", mensagemStatus);
            req.getRequestDispatcher("listarProdutos.jsp").forward(req, resp);
            return;
        }

    }
}
