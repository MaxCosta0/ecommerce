package br.edu.imepac.utils;

public class MensagemStatus {
    private String mensagem;
    private StatusMensagemEnum status;

    public void setMensagem(String mensagem){
        this.mensagem = mensagem;
    }

    public String getMensagem(){
        return mensagem;
    }

    public StatusMensagemEnum getStatus() {
        return status;
    }

    public void setStatus(StatusMensagemEnum status) {
        this.status = status;
    }
}
