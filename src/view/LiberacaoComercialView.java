package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import dao.PedidoDAO;
import model.PedidoVenda;

/**
 * View: LiberacaoComercialView
 * Responsável: Lucas
 * -----------------------------------------------------------------------------
 * Tela 4: Liberação e Consolidação Comercial para Faturamento.
 * - O que fazer em Model: Atualizar status do pedido para 'APROVADO' ou 'FATURADO'.
 * - O que fazer em Controller: Lógica de liberação gerencial e preparação de pacotes para o módulo fiscal/faturamento.
 * - O que fazer em View: Tela de aprovação de pedidos retidos por alçada ou limite de crédito.
 */

public class LiberacaoComercialView extends JPanel {

    private JTable tabelaPedidos;
    private DefaultTableModel modeloTabela;

    private JButton btnLiberar;
    private JButton btnBloquear;
    private JButton btnAtualizar;

    private JLabel lblTotal;

    private PedidoDAO pedidoDAO;

    public LiberacaoComercialView(Connection conexao) {

        pedidoDAO = new PedidoDAO(conexao);

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        criarTabela();
        criarBotoes();

        carregarPedidos();
    }

    // Cria a tabela de pedidos
    private void criarTabela() {

        String[] colunas = {
            "Selecionar",
            "Pedido",
            "Cliente",
            "Valor Total",
            "Status"
        };

        modeloTabela = new DefaultTableModel(colunas, 0) {

            @Override
            public Class<?> getColumnClass(int coluna) {

                if (coluna == 0) {
                    return Boolean.class;
                }

                return String.class;
            }

            @Override
            public boolean isCellEditable(int linha, int coluna) {

                return coluna == 0;
            }
        };

        tabelaPedidos = new JTable(modeloTabela);

        tabelaPedidos.setSelectionMode(
            ListSelectionModel.SINGLE_SELECTION
        );

        tabelaPedidos.getColumnModel()
                     .getColumn(0)
                     .setPreferredWidth(70);

        tabelaPedidos.getColumnModel()
                     .getColumn(1)
                     .setPreferredWidth(80);

        tabelaPedidos.getColumnModel()
                     .getColumn(2)
                     .setPreferredWidth(200);

        tabelaPedidos.getColumnModel()
                     .getColumn(3)
                     .setPreferredWidth(120);

        tabelaPedidos.getColumnModel()
                     .getColumn(4)
                     .setPreferredWidth(150);

        tabelaPedidos.getModel().addTableModelListener(e -> {
            calcularTotalSelecionado();
        });

        add(new JScrollPane(tabelaPedidos), BorderLayout.CENTER);
    }

    // Cria os botões da tela
    private void criarBotoes() {

        JPanel painelBotoes = new JPanel(
            new FlowLayout(FlowLayout.LEFT)
        );

        btnLiberar = new JButton("Liberar para Faturamento");
        btnBloquear = new JButton("Bloquear");
        btnAtualizar = new JButton("Atualizar");

        lblTotal = new JLabel("Total selecionado: R$ 0,00");

        btnLiberar.addActionListener(e -> liberarPedido());

        btnBloquear.addActionListener(e -> bloquearPedido());

        btnAtualizar.addActionListener(e -> carregarPedidos());

        painelBotoes.add(btnLiberar);
        painelBotoes.add(btnBloquear);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(lblTotal);

        add(painelBotoes, BorderLayout.SOUTH);
    }

    // Carrega os pedidos do banco
    private void carregarPedidos() {

        modeloTabela.setRowCount(0);

        try {

            List<PedidoVenda> pedidos =
                pedidoDAO.listarPedidosParaLiberacao();

            for (PedidoVenda pedido : pedidos) {

                String cliente = "";

                if (pedido.getCliente() != null) {

                    cliente = pedido.getCliente().getRazaoSocial();

                    if (cliente == null || cliente.isEmpty()) {
                        cliente = pedido.getCliente().getNomeFantasia();
                    }
                }

                modeloTabela.addRow(new Object[] {
                    false,
                    pedido.getIdPedido(),
                    cliente,
                    String.format(
                        "R$ %.2f",
                        pedido.getValorTotal()
                    ),
                    pedido.getStatusPedido()
                });
            }

            calcularTotalSelecionado();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                this,
                "Erro ao carregar os pedidos:\n" + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Libera o pedido selecionado
    private void liberarPedido() {

        int linha = tabelaPedidos.getSelectedRow();

        if (linha == -1) {

            JOptionPane.showMessageDialog(
                this,
                "Selecione um pedido.",
                "Aviso",
                JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        boolean selecionado =
            (boolean) modeloTabela.getValueAt(linha, 0);

        if (!selecionado) {

            JOptionPane.showMessageDialog(
                this,
                "Marque o pedido na coluna 'Selecionar'.",
                "Aviso",
                JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int idPedido =
            (int) modeloTabela.getValueAt(linha, 1);

        int resposta = JOptionPane.showConfirmDialog(
            this,
            "Deseja liberar o pedido " + idPedido +
            " para faturamento?",
            "Confirmar liberação",
            JOptionPane.YES_NO_OPTION
        );

        if (resposta != JOptionPane.YES_OPTION) {
            return;
        }

        try {

            boolean atualizado =
                pedidoDAO.liberarPedido(idPedido);

            if (atualizado) {

                JOptionPane.showMessageDialog(
                    this,
                    "Pedido liberado para faturamento.",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE
                );

                carregarPedidos();

            } else {

                JOptionPane.showMessageDialog(
                    this,
                    "O pedido não foi encontrado.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
                );
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                this,
                "Erro ao liberar o pedido:\n" + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Bloqueia o pedido selecionado
    private void bloquearPedido() {

        int linha = tabelaPedidos.getSelectedRow();

        if (linha == -1) {

            JOptionPane.showMessageDialog(
                this,
                "Selecione um pedido.",
                "Aviso",
                JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        boolean selecionado =
            (boolean) modeloTabela.getValueAt(linha, 0);

        if (!selecionado) {

            JOptionPane.showMessageDialog(
                this,
                "Marque o pedido na coluna 'Selecionar'.",
                "Aviso",
                JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int idPedido =
            (int) modeloTabela.getValueAt(linha, 1);

        int resposta = JOptionPane.showConfirmDialog(
            this,
            "Deseja bloquear o pedido " + idPedido + "?",
            "Confirmar bloqueio",
            JOptionPane.YES_NO_OPTION
        );

        if (resposta != JOptionPane.YES_OPTION) {
            return;
        }

        try {

            boolean atualizado =
                pedidoDAO.bloquearPedido(idPedido);

            if (atualizado) {

                JOptionPane.showMessageDialog(
                    this,
                    "Pedido bloqueado.",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE
                );

                carregarPedidos();

            } else {

                JOptionPane.showMessageDialog(
                    this,
                    "O pedido não foi encontrado.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
                );
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                this,
                "Erro ao bloquear o pedido:\n" + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Calcula o valor dos pedidos marcados
    private void calcularTotalSelecionado() {

        double total = 0.0;
        int quantidade = 0;

        for (int i = 0; i < modeloTabela.getRowCount(); i++) {

            boolean selecionado =
                (boolean) modeloTabela.getValueAt(i, 0);

            if (selecionado) {

                String valorTexto =
                    (String) modeloTabela.getValueAt(i, 3);

                valorTexto = valorTexto
                    .replace("R$", "")
                    .replace(",", ".")
                    .trim();

                try {

                    total += Double.parseDouble(valorTexto);
                    quantidade++;

                } catch (NumberFormatException e) {
                    // Ignora valores inválidos
                }
            }
        }

        lblTotal.setText(
            String.format(
                "Pedidos: %d | Total selecionado: R$ %.2f",
                quantidade,
                total
            )
        );
    }
}
