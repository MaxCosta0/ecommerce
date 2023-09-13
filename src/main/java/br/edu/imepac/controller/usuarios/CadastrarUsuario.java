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

public class CadastrarUsuario extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        //validar campos obrigatórios
        if (name.isEmpty()) {
            MensagemStatus mensagemStatus = new MensagemStatus();
            mensagemStatus.setMensagem("O campo nome é obrigatório!");
            mensagemStatus.setStatus(StatusMensagemEnum.ERROR);
            req.setAttribute("mensagemStatus", mensagemStatus);
            req.getRequestDispatcher("cadastrarUsuario.jsp").forward(req, resp);
            return;
        }

        if (email.isEmpty()) {
            //retornar mensagem de campo obrigatorio
            MensagemStatus mensagemStatus = new MensagemStatus();
            mensagemStatus.setMensagem("O campo email é obrigatório!");
            mensagemStatus.setStatus(StatusMensagemEnum.ERROR);
            req.setAttribute("mensagemStatus", mensagemStatus);
            req.getRequestDispatcher("cadastrarUsuario.jsp").forward(req, resp);
            return;
        }

        //criar objeto que representa os dados do formulário
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setNome(name);

        try {
            Connection connection = DataBaseConnection.getInstance();

            String sql = "INSERT INTO usuarios(nome,email) VALUES(?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, usuario.getNome());
            stm.setString(2, usuario.getEmail());
            stm.executeUpdate();

        } catch (Exception exception) {
            exception.printStackTrace();
            MensagemStatus mensagemStatus = new MensagemStatus();
            mensagemStatus.setMensagem("Erro inesperado. Contate o administrador!");
            mensagemStatus.setStatus(StatusMensagemEnum.ERROR);
            req.setAttribute("mensagemStatus", mensagemStatus);
            req.getRequestDispatcher("cadastrarUsuario.jsp").forward(req, resp);
            return;
        }

        //retornar uma mensagem de resposta ao usuario
        MensagemStatus mensagemStatus = new MensagemStatus();
        mensagemStatus.setMensagem("Dados cadastrados!");
        mensagemStatus.setStatus(StatusMensagemEnum.SUCCESS);
        req.setAttribute("mensagemStatus", mensagemStatus);
        req.getRequestDispatcher("cadastrarUsuario.jsp").forward(req, resp);
    }
}
