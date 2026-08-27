package controller;

import model.ItemPedido;
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
    /**
     *Cria e valida um pedido de venda.
     * Verifica se o pedido possui itens e se existe estoque disponível.
     * @param pedido Pedido de venda que será criado.
     */
    public void criarPedido(PedidoVenda pedido) {
        if (pedido == null) {
            System.out.println("Erro: pedido não informado.");
            return;
        }
        if (pedido.getItens() == null || pedido.getItens().isEmpty()) {
            System.out.println("Erro: o pedido não possui itens.");
            return;
        }

        // Valida os itens e o estoque
        for (ItemPedido item : pedido.getItens()) {
            if (item == null || item.getProduto() == null) {
                System.out.println("Erro: item ou produto inválido.");
                return;
            }
            if (item.getQuantidade() <= 0) {
                System.out.println("Erro: a quantidade deve ser maior que zero.");
                return;
            }
            if (item.getQuantidade() > item.getProduto().getEstoqueAtual()) {
                System.out.println(
                    "Estoque insuficiente para o produto: "
                    + item.getProduto().getDescricao());
                return;
            }
        }

        // Calcula o total do pedido
       calcularValorTotal();

        // Aqui será feita a integração com PedidoDAO
        // PedidoDAO dao = new PedidoDAO();
        // dao.salvar(pedido);

        System.out.println("Pedido criado com sucesso!");
        System.out.println("Número do pedido: " + pedido.getIdPedido());
        System.out.println("Valor total: R$ " + pedido.getValorTotal());
    }

    /**
     * Consolida um pedido para o processo de faturamento.
     * @param idPedido ID do pedido enviado para faturamento.
     */
    public void consolidarParaFaturamento(int idPedido) {

        if (idPedido <= 0) {
            System.out.println("Erro: ID do pedido inválido.");
            return;
        }

        // Busca do pedido DAO
        // PedidoDAO dao = new PedidoDAO();
        // PedidoVenda pedido = dao.buscarPorId(idPedido);
        
        // Depois terá alteração de status
        // pedido.setStatusPedido("FATURAMENTO");
        // dao.atualizar(pedido);

        System.out.println(
            "Pedido " + idPedido + " enviado para faturamento."
        );
    }
}
