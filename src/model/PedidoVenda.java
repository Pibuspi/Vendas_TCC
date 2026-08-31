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

    public PedidoVenda(int idPedido, Cliente cliente, List<ItemPedido> itens) {
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.itens = itens;
        this.valorTotal = 0.0;
        this.statusPedido = "ABERTO";
        this.margemLucroPercentual = 0.0;
        this.observacoes = "";
    }

    // Calcula o valor total do pedido
    public void calcularValorTotal() {

        valorTotal = 0.0;

        if (itens != null) {
            for (ItemPedido item : itens) {

                if (item != null) {
                    valorTotal += item.getSubtotal();
                }
            }
        }
    }

    // Adiciona um item ao pedido
    public void adicionarItem(ItemPedido item) {

        if (item != null) {

            itens.add(item);
            calcularValorTotal();
        }
    }

    // Atualiza o status do pedido
    public void atualizarStatus(String novoStatus) {

        if (novoStatus != null && !novoStatus.trim().isEmpty()) {
            statusPedido = novoStatus;
        }
    }

    // Verifica se o pedido pode ser liberado
    public boolean podeSerLiberado() {

        if (cliente == null) {
            return false;
        }

        if (itens == null || itens.isEmpty()) {
            return false;
        }

        if (cliente.isBloqueadoPorInadimplencia()) {
            return false;
        }

        if ("CANCELADO".equals(statusPedido)) {
            return false;
        }

        if ("FATURADO".equals(statusPedido)) {
            return false;
        }

        return true;
    }

    // Libera o pedido para faturamento
    public boolean liberar() {

        if (podeSerLiberado()) {

            statusPedido = "LIBERADO";

            return true;
        }

        return false;
    }

    // Bloqueia o pedido
    public void bloquear() {

        if (!"FATURADO".equals(statusPedido)) {
            statusPedido = "BLOQUEADO";
        }
    }

    // Verifica se o pedido já está liberado
    public boolean estaLiberado() {

        return "LIBERADO".equals(statusPedido);
    }

    // Getters e Setters

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
        calcularValorTotal();
    }

    public double getValorTotal() {
        return valorTotal;
    }
    
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    public String getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(String statusPedido) {
        this.statusPedido = statusPedido;
    }

    public double getMargemLucroPercentual() {
        return margemLucroPercentual;
    }

    public void setMargemLucroPercentual(double margemLucroPercentual) {
        this.margemLucroPercentual = margemLucroPercentual;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}

