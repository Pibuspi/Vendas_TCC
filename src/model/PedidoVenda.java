package model;

import java.util.List;

/**
 * Classe Model: PedidoVenda
 * -----------------------------------------------------------------------------
 * Descrição: Representa o cabeçalho do pedido de venda no ciclo Order-to-Cash.
 * Gerencia o relacionamento com o cliente, lista de itens, valor total, margem
 * de lucro calculada e status do pedido (Digitação, Aprovação, Faturamento).
 * 
 * Implementar por: Equipe de Desenvolvimento (Módulo de Vendas)
 */
public class PedidoVenda {
    private int idPedido;
    private Cliente cliente;
    private List<ItemPedido> itens;
    private double valorTotal;
    private String statusPedido;
    private double margemLucroPercentual;
    private String observacoes;

    // Métodos para cálculo de total, verificação de margem e regras de negócio.
}
