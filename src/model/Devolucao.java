package model;

/**
 * Classe Model: Devolucao
 * -----------------------------------------------------------------------------
 * Descrição: Representa o registro de devolução de vendas e críticas comerciais,
 * vinculada ao pedido original e ao cálculo de estorno financeiro.
 * 
 * Implementar por: Equipe de Pós-Venda e Devoluções
 */
public class Devolucao {
    private int idDevolucao;
    private PedidoVenda pedido;
    private String motivoDevolucao;
    private String statusDevolucao;
    private double valorEstornado;

    // Construtores, Getters e Setters.
}
