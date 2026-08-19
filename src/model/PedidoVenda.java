package model;

import java.util.List;

/**
 * Model: PedidoVenda.java
 * Responsável: Rafael
 * -----------------------------------------------------------------------------
 * Descrição detalhada: Representa o cabeçalho do pedido no ciclo Order-to-Cash.
 * Conecta o cliente aos itens adquiridos, calcula o valor total, armazena a margem
 * de lucro calculada pelo motor de preços e gerencia o status do pedido.
 * 
 * O que fazer em Model (Rafael):
 * - Criar estrutura de relacionamento com `Cliente` e lista de `ItemPedido`.
 * - Implementar métodos para somar o valor total e atualizar o status comercial.
 */
public class PedidoVenda {
    private int idPedido;
    private Cliente cliente;
    private List<ItemPedido> itens;
    private double valorTotal;
    private String statusPedido;
    private double margemLucroPercentual;
    private String observacoes;
}
