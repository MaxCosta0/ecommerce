package br.edu.imepac.controller.usuarios;

import br.edu.imepac.utils.MensagemStatus;
import br.edu.imepac.utils.StatusMensagemEnum;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CadastrarUsuario extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        if (nome.isEmpty()) {
            MensagemStatus mensagemStatus = new MensagemStatus();
            mensagemStatus.setMensagem("O campo nome é obrigatório!");
            mensagemStatus.setStatus(StatusMensagemEnum.ERROR);
            req.setAttribute("mensagemStatus", mensagemStatus);
            req.getRequestDispatcher("cadastrarUsuario.jsp").forward(req, resp);
            return;
        }
        if (email.isEmpty()) {
            MensagemStatus mensagemStatus = new MensagemStatus();
            mensagemStatus.setMensagem("O campo email é obrigatório!");
            mensagemStatus.setStatus(StatusMensagemEnum.ERROR);
            req.setAttribute("mensagemStatus", mensagemStatus);
            req.getRequestDispatcher("cadastrarUsuario.jsp").forward(req, resp);
            return;
        }
        if (senha.isEmpty()) {
            MensagemStatus mensagemStatus = new MensagemStatus();
            mensagemStatus.setMensagem("O campo senha é obrigatório!");
            mensagemStatus.setStatus(StatusMensagemEnum.ERROR);
            req.setAttribute("mensagemStatus", mensagemStatus);
            req.getRequestDispatcher("cadastrarUsuario.jsp").forward(req, resp);
            return;
        }

//        Abrir conexao com o banco de dados
//        Salvar os dados do usuario

        //retornar uma mensagem de resposta ao usuario
        MensagemStatus mensagemStatus = new MensagemStatus();
        mensagemStatus.setMensagem("Dados cadastrados!");
        mensagemStatus.setStatus(StatusMensagemEnum.SUCCESS);
        req.setAttribute("mensagemStatus", mensagemStatus);
        req.getRequestDispatcher("cadastrarUsuario.jsp").forward(req, resp);
    }
}
