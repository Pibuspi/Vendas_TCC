package controller;

import model.PedidoVenda;

/**
 * Classe Controller: PedidoController
 * -----------------------------------------------------------------------------
 * Descrição: Controla o fluxo de digitação de pedidos (*Sales Order Entry*),
 * validação de estoque em tempo real, integração fiscal e consolidação para
 * faturamento.
 * 
 * Implementar por: Equipe de Controle de Vendas e Faturamento
 */
public class PedidoController {

    public void criarPedido(PedidoVenda pedido) {
        // Lógica de inserção e validação inicial.
    }

    public void consolidarParaFaturamento(int idPedido) {
        // Envio para o módulo fiscal e financeiro.
    }
}
