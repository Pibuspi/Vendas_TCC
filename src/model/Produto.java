package model;

/**
 * Model: Produto.java
 * Responsável: Rafael
 * -----------------------------------------------------------------------------
 * Descrição detalhada: Representa o SKU e o catálogo de produtos no módulo de vendas.
 * Armazena o preço de tabela, custo unitário (essencial para o Pricing Engine calcular
 * a margem e evitar prejuízos) e o estoque atual em tempo real.
 * 
 * O que fazer em Model (Rafael):
 * - Definir atributos de SKU, descrição, preço de tabela, custo e saldo de estoque.
 * - Implementar construtores e encapsulamento (Getters/Setters).
 */
public class Produto {
    private int idProduto;
    private String codigoSku;
    private String descricao;
    private double precoTabela;
    private double custoUnitario;
    private int estoqueAtual;
    private String ncm;

    public Produto() {
    }

    public Produto(int idProduto, String codigoSku, String descricao,
    double precoTabela, double custoUnitario,
    int estoqueAtual, String ncm) {
        this.idProduto = idProduto;
        this.codigoSku = codigoSku;
        this.descricao = descricao;
        this.precoTabela = precoTabela;
        this.custoUnitario = custoUnitario;
        this.estoqueAtual = estoqueAtual;
        this.ncm = ncm;
    }

    // Getters e Setters
    public int getIdProduto() {
    return idProduto;
    }
    public void setIdProduto(int idProduto) {
    this.idProduto = idProduto;
    }
    public String getCodigoSku() {
    return codigoSku;
    }
    public void setCodigoSku(String codigoSku) {
    this.codigoSku = codigoSku;
    }
    public String getDescricao() {
    return descricao;
    }
    public void setDescricao(String descricao) {
    this.descricao = descricao;
    }
    public double getPrecoTabela() {
    return precoTabela;
    }
    public void setPrecoTabela(double precoTabela) {
    this.precoTabela = precoTabela;
    }
    public double getCustoUnitario() {
    return custoUnitario;
    }
    public void setCustoUnitario(double custoUnitario) {
    this.custoUnitario = custoUnitario;
    }
    public int getEstoqueAtual() {
    return estoqueAtual;
    }
    public void setEstoqueAtual(int estoqueAtual) {
    this.estoqueAtual = estoqueAtual;
    }
    public String getNcm() {
    return ncm;
    }
    public void setNcm(String ncm) {
    this.ncm = ncm;
    }
}
