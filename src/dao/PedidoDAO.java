package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import model.PedidoVenda;

/**
 * DAO: PedidoDAO
 * Responsável: Rafael
 * -----------------------------------------------------------------------------
 * Camada SQL/DAO para a Digitação de Pedidos de Venda.
 * - O que fazer em SQL: Gerenciar as tabelas `pedidos_venda` e `itens_pedido` com chaves estrangeiras e controle de subtotais.
 * - O que fazer em DAO: Gravar o cabeçalho do pedido e seus respectivos itens em transações atômicas (Commit/Rollback).
 */

public class PedidoDAO {

    private Connection conexao;

    public PedidoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    // Busca pedidos que estão aguardando aprovação
    public List<PedidoVenda> listarPedidosParaLiberacao()
            throws SQLException {

        String sql =
            "SELECT p.id_pedido, p.id_cliente, p.valor_total, " +
            "p.status_pedido, " +
            "c.cpf_cnpj, c.razao_social, c.nome_fantasia, " +
            "c.limite_credito, c.alcada_desconto, " +
            "c.bloqueado_por_inadimplencia, " +
            "c.regra_fiscal_pendente " +
            "FROM pedidos_venda p " +
            "INNER JOIN clientes c ON p.id_cliente = c.id_cliente " +
            "WHERE p.status_pedido = ?";

        List<PedidoVenda> pedidos = new ArrayList<>();

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, "AGUARDANDO_APROVACAO");

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {

                    Cliente cliente = new Cliente();

                    cliente.setIdCliente(
                        rs.getInt("id_cliente")
                    );

                    cliente.setCpfCnpj(
                        rs.getString("cpf_cnpj")
                    );

                    cliente.setRazaoSocial(
                        rs.getString("razao_social")
                    );

                    cliente.setNomeFantasia(
                        rs.getString("nome_fantasia")
                    );

                    cliente.setLimiteCredito(
                        rs.getDouble("limite_credito")
                    );

                    cliente.setAlcadaDesconto(
                        rs.getDouble("alcada_desconto")
                    );

                    cliente.setBloqueadoPorInadimplencia(
                        rs.getBoolean(
                            "bloqueado_por_inadimplencia"
                        )
                    );

                    cliente.setRegraFiscalPendente(
                        rs.getBoolean(
                            "regra_fiscal_pendente"
                        )
                    );

                    PedidoVenda pedido =
                        new PedidoVenda(
                            rs.getInt("id_pedido"),
                            cliente,
                            new ArrayList<>()
                        );

                    pedido.setValorTotal(
                        rs.getDouble("valor_total")
                    );

                    pedido.setStatusPedido(
                        rs.getString("status_pedido")
                    );

                    pedidos.add(pedido);
                }
            }
        }

        return pedidos;
    }

    // Busca um pedido pelo ID
    public PedidoVenda buscarPorId(int idPedido)
            throws SQLException {

        String sql =
            "SELECT p.id_pedido, p.id_cliente, p.valor_total, " +
            "p.status_pedido, " +
            "c.cpf_cnpj, c.razao_social, c.nome_fantasia, " +
            "c.limite_credito, c.alcada_desconto, " +
            "c.bloqueado_por_inadimplencia, " +
            "c.regra_fiscal_pendente " +
            "FROM pedidos_venda p " +
            "INNER JOIN clientes c ON p.id_cliente = c.id_cliente " +
            "WHERE p.id_pedido = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idPedido);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    Cliente cliente = new Cliente();

                    cliente.setIdCliente(
                        rs.getInt("id_cliente")
                    );

                    cliente.setCpfCnpj(
                        rs.getString("cpf_cnpj")
                    );

                    cliente.setRazaoSocial(
                        rs.getString("razao_social")
                    );

                    cliente.setNomeFantasia(
                        rs.getString("nome_fantasia")
                    );

                    cliente.setLimiteCredito(
                        rs.getDouble("limite_credito")
                    );

                    cliente.setAlcadaDesconto(
                        rs.getDouble("alcada_desconto")
                    );

                    cliente.setBloqueadoPorInadimplencia(
                        rs.getBoolean(
                            "bloqueado_por_inadimplencia"
                        )
                    );

                    cliente.setRegraFiscalPendente(
                        rs.getBoolean(
                            "regra_fiscal_pendente"
                        )
                    );

                    PedidoVenda pedido =
                        new PedidoVenda(
                            rs.getInt("id_pedido"),
                            cliente,
                            new ArrayList<>()
                        );

                    pedido.setValorTotal(
                        rs.getDouble("valor_total")
                    );

                    pedido.setStatusPedido(
                        rs.getString("status_pedido")
                    );

                    return pedido;
                }
            }
        }

        return null;
    }

    // Atualiza o status do pedido
    public boolean atualizarStatus(
            int idPedido,
            String status) throws SQLException {

        String sql =
            "UPDATE pedidos_venda " +
            "SET status_pedido = ? " +
            "WHERE id_pedido = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, status);
            stmt.setInt(2, idPedido);

            return stmt.executeUpdate() > 0;
        }
    }

    // Libera o pedido para faturamento
    public boolean liberarPedido(int idPedido)
            throws SQLException {

        return atualizarStatus(
            idPedido,
            "LIBERADO"
        );
    }

    // Bloqueia o pedido
    public boolean bloquearPedido(int idPedido)
            throws SQLException {

        return atualizarStatus(
            idPedido,
            "BLOQUEADO"
        );
    }
}
