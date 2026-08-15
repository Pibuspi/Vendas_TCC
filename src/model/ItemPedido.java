package model;

/**
 * Classe Model: ItemPedido
 * -----------------------------------------------------------------------------
 * Descrição: Representa cada linha de produto inserida na digitação do pedido.
 * Armazena a quantidade, o preço praticado, o desconto aplicado e o subtotal.
 * 
 * Implementar por: Equipe de Desenvolvimento (Sales Order Entry)
 */
public class ItemPedido {
    private int idItem;
    private Produto produto;
    private int quantidade;
    private double precoPraticado;
    private double descontoAplicado;
    private double subtotal;

    // Métodos de cálculo de subtotal e aplicação de descontos por alçada.
}
