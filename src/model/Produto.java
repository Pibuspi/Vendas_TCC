package model;

/**
 * Classe Model: Produto
 * -----------------------------------------------------------------------------
 * Descrição: Representa o SKU (Stock Keeping Unit) e itens de catálogo no ERP.
 * Armazena preço de tabela, custo unitário (crucial para o Pricing Engine evitar
 * margens negativas) e saldo de estoque em tempo real.
 * 
 * Implementar por: Equipe de Desenvolvimento (Catálogo e Estoque)
 */
public class Produto {
    private int idProduto;
    private String codigoSku;
    private String descricao;
    private double precoTabela;
    private double custoUnitario;
    private int estoqueAtual;
    private String ncm;

    // Construtores, Getters e Setters devem ser implementados aqui.
}
