package model;

/**
 * Model: ItemPedido.java
 * Responsável: Rafael
 * -----------------------------------------------------------------------------
 * Descrição detalhada: Representa cada linha de produto adicionada durante a digitação
 * do pedido. Armazena a quantidade, preço praticado, desconto e subtotal.
 * 
 * O que fazer em Model (Rafael):
 * - Vincular a entidade `Produto`.
 * - Criar lógica de cálculo de subtotal baseada na quantidade e desconto aplicado.
 */
public class ItemPedido {
    private int idItem;
    private Produto produto;
    private int quantidade;
    private double precoPraticado;
    private double descontoAplicado;
    private double subtotal;
}
