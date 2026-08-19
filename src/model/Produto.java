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
}
