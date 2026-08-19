package controller;

import model.PedidoVenda;

/**
 * Controller: PedidoController.java
 * Responsável: Rafael
 * -----------------------------------------------------------------------------
 * Descrição detalhada: Controla o fluxo de digitação de pedidos (Sales Order Entry),
 * validando a disponibilidade de estoque em tempo real e integrando com o DAO para persistência.
 * 
 * O que fazer em Controller (Rafael):
 * - Implementar lógica de adição de itens ao pedido.
 * - Conectar com `PedidoDAO` para salvar no banco.
 */
public class PedidoController {
    public void criarPedido(PedidoVenda pedido) {}
    public void consolidarParaFaturamento(int idPedido) {}
}
