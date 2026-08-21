package model;

/**
 * Model: ItemDevolucao.java
 * Responsável: Pietro
 * -----------------------------------------------------------------------------
 * Representa um item incluído em uma solicitação de devolução ou troca. Ele mantém
 * a rastreabilidade comercial do produto, a quantidade devolvida, a condição física
 * informada e o valor calculado para a ocorrência.
 */
public class ItemDevolucao {
    private String codigoSku;
    private String descricaoProduto;
    private int quantidadeDevolvida;
    private String situacaoProduto;
    private double valorUnitario;

    public ItemDevolucao(String codigoSku, String descricaoProduto, int quantidadeDevolvida,
                         String situacaoProduto, double valorUnitario) {
        if (codigoSku == null || codigoSku.isBlank()) {
            throw new IllegalArgumentException("O SKU do item devolvido é obrigatório.");
        }
        if (descricaoProduto == null || descricaoProduto.isBlank()) {
            throw new IllegalArgumentException("A descrição do item devolvido é obrigatória.");
        }
        if (quantidadeDevolvida <= 0) {
            throw new IllegalArgumentException("A quantidade devolvida deve ser maior que zero.");
        }
        if (valorUnitario < 0) {
            throw new IllegalArgumentException("O valor unitário não pode ser negativo.");
        }
        this.codigoSku = codigoSku;
        this.descricaoProduto = descricaoProduto;
        this.quantidadeDevolvida = quantidadeDevolvida;
        this.situacaoProduto = situacaoProduto == null ? "Não informada" : situacaoProduto;
        this.valorUnitario = valorUnitario;
    }

    public double calcularTotalItem() {
        return quantidadeDevolvida * valorUnitario;
    }

    public String getCodigoSku() { return codigoSku; }
    public String getDescricaoProduto() { return descricaoProduto; }
    public int getQuantidadeDevolvida() { return quantidadeDevolvida; }
    public String getSituacaoProduto() { return situacaoProduto; }
    public double getValorUnitario() { return valorUnitario; }
}
