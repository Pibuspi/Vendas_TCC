package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaBalcaoview extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtProduto;
    private JTextField txtQuantidade;
    private JTextField txtVendas;
    private JTextField txtPreco;

    private JTable tabela;
    private DefaultTableModel modelo;

    public TelaBalcaoview() {

        setTitle("Seleção de Produtos");
        setSize(880, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painelDados = new JPanel(new GridBagLayout());

        painelDados.setBorder(
            BorderFactory.createTitledBorder("Dados do Produto")
        );

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        painelDados.add(new JLabel("Produto:"), gbc);

        txtProduto = new JTextField();
        gbc.gridx = 1;
        gbc.weightx = 1;
        painelDados.add(txtProduto, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0;
        painelDados.add(new JLabel("Quantidade:"), gbc);

        txtQuantidade = new JTextField();
        gbc.gridx = 3;
        gbc.weightx = 1;
        painelDados.add(txtQuantidade, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        painelDados.add(new JLabel("Vendas:"), gbc);

        txtVendas = new JTextField();
        gbc.gridx = 1;
        gbc.weightx = 1;
        painelDados.add(txtVendas, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0;
        painelDados.add(new JLabel("Preço:"), gbc);

        txtPreco = new JTextField();
        gbc.gridx = 3;
        gbc.weightx = 1;
        painelDados.add(txtPreco, gbc);


        // =====================================================
        // BOTÕES
        // =====================================================

        JPanel painelBotoes = new JPanel();

        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnSelecionar = new JButton("Selecionar");

        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnSelecionar);

        
        String[] colunas = {
            "Produto",
            "Quantidade",
            "Vendas",
            "Preço"
        };

        
        modelo = new DefaultTableModel(colunas, 0);
        tabela = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabela);


        setLayout(new BorderLayout(10, 10));

        JPanel painelCentro = new JPanel(new BorderLayout());

        painelCentro.add(painelBotoes, BorderLayout.NORTH);
        painelCentro.add(scroll, BorderLayout.CENTER);

        setLayout(new BorderLayout(5, 5));

        add(painelDados, BorderLayout.NORTH);
        add(painelCentro, BorderLayout.CENTER);

        
        btnAdicionar.addActionListener(e -> {

            String produto = txtProduto.getText();
            String quantidade = txtQuantidade.getText();
            String vendas = txtVendas.getText();
            String preco = txtPreco.getText();

            modelo.addRow(new Object[]{
                produto,
                quantidade,
                vendas,
                preco
            });

            limparCampos();
        });

        btnSelecionar.addActionListener(e -> {

            int linha = tabela.getSelectedRow();

            if (linha == -1) {
                JOptionPane.showMessageDialog(
                    this,
                    "Selecione um produto na tabela."
                );
                return;
            }

            txtProduto.setText(
                modelo.getValueAt(linha, 0).toString()
            );

            txtQuantidade.setText(
                modelo.getValueAt(linha, 1).toString()
            );

            txtVendas.setText(
                modelo.getValueAt(linha, 2).toString()
            );

            txtPreco.setText(
                modelo.getValueAt(linha, 3).toString()
            );
        });

        btnAtualizar.addActionListener(e -> {

            int linha = tabela.getSelectedRow();

            if (linha == -1) {
                JOptionPane.showMessageDialog(
                    this,
                    "Selecione um produto antes de atualizar."
                );
                return;
            }

            modelo.setValueAt(txtProduto.getText(), linha, 0);
            modelo.setValueAt(txtQuantidade.getText(), linha, 1);
            modelo.setValueAt(txtVendas.getText(), linha, 2);
            modelo.setValueAt(txtPreco.getText(), linha, 3);

            limparCampos();
        });

        btnExcluir.addActionListener(e -> {

            int linha = tabela.getSelectedRow();

            if (linha == -1) {
                JOptionPane.showMessageDialog(
                    this,
                    "Selecione um produto antes de excluir."
                );
                return;
            }

            modelo.removeRow(linha);

            limparCampos();
        });
    }

    private void limparCampos() {

        txtProduto.setText("");
        txtQuantidade.setText("");
        txtVendas.setText("");
        txtPreco.setText("");
    }
}