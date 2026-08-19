package model;

/**
 * Model: Devolucao.java
 * Responsável: Pietro
 * -----------------------------------------------------------------------------
 * Descrição detalhada: Representa o registro de devolução e críticas de vendas
 * (Return Orders), vinculando o pedido original, o motivo da devolução, o valor
 * estornado e o status do processo.
 * 
 * O que fazer em Model (Pietro):
 * - Estruturar atributos de ID, pedido, motivo, status e valor do estorno.
 * - Implementar Getters e Setters.
 */
public class Devolucao {
    private int idDevolucao;
    private PedidoVenda pedido;
    private String motivoDevolucao;
    private String statusDevolucao;
    private double valorEstornado;
}
