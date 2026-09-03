package model;

public class ResultadoPrecificacao {
    private double margemReal;
    private String status; // LIBERADO, PENDENTE_GERENTE, HARD_STOP
    private String mensagem;

    public ResultadoPrecificacao(double margemReal, String status, String mensagem) {
        this.margemReal = margemReal;
        this.status = status;
        this.mensagem = mensagem;
    }

    public double getMargemReal() { return margemReal; }
    public String getStatus() { return status; }
    public String getMensagem() { return mensagem; }
}