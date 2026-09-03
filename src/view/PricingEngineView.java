package view;

import controller.PricingEngineController;
import model.RegraPreco;
import model.ResultadoPrecificacao;

import javax.swing.*;
import java.awt.*;

public class PricingEngineView extends JFrame {
    private JTextField txtSku, txtCusto, txtPrecoVenda, txtDesconto;
    private JLabel lblStatus, lblMargemReal, lblMensagem;
    private PricingEngineController controller;

    public PricingEngineView() {
        controller = new PricingEngineController();
        setTitle("ERP - Motor de Preços & Alçadas Comerciais");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel de Formulário
        JPanel panelForm = new JPanel(new GridLayout(6, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panelForm.add(new JLabel("SKU do Produto:"));
        txtSku = new JTextField("90881");
        panelForm.add(txtSku);

        panelForm.add(new JLabel("Custo (R$):"));
        txtCusto = new JTextField("120.00");
        panelForm.add(txtCusto);

        panelForm.add(new JLabel("Preço de Venda (R$):"));
        txtPrecoVenda = new JTextField("135.00");
        panelForm.add(txtPrecoVenda);

        panelForm.add(new JLabel("Desconto Aplicado (%):"));
        txtDesconto = new JTextField("6.0");
        panelForm.add(txtDesconto);

        JButton btnSimular = new JButton("Simular Precificação");
        panelForm.add(btnSimular);
        
        lblStatus = new JLabel("Status: AGUARDANDO");
        panelForm.add(lblStatus);

        add(panelForm, BorderLayout.CENTER);

        // Painel Inferior de Resultados
        JPanel panelResultado = new JPanel(new GridLayout(2, 1));
        panelResultado.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        
        lblMargemReal = new JLabel("Margem Calculada: -");
        lblMargemReal.setFont(new Font("Arial", Font.BOLD, 14));
        panelResultado.add(lblMargemReal);

        lblMensagem = new JLabel("Mensagem do Sistema: Preencha os dados e clique em simular.");
        panelResultado.add(lblMensagem);

        add(panelResultado, BorderLayout.SOUTH);

        // Ação do Botão
        btnSimular.addActionListener(e -> executarSimulacao());
    }

    private void executarSimulacao() {
        try {
            String sku = txtSku.getText();
            double custo = Double.parseDouble(txtCusto.getText());
            double precoVenda = Double.parseDouble(txtPrecoVenda.getText());
            double desconto = Double.parseDouble(txtDesconto.getText());

            RegraPreco regra = new RegraPreco(sku, custo, 125.0, 18.0, 15.0);
            ResultadoPrecificacao resultado = controller.validarMargemLucro(regra, precoVenda, desconto);

            lblMargemReal.setText(String.format("Margem Calculada: %.2f%% (Mínima exigida: 18%%)", resultado.getMargemReal()));
            lblMensagem.setText(resultado.getMensagem());

            if (resultado.getStatus().equals("LIBERADO")) {
                lblStatus.setText("Status: LIBERADO");
                lblStatus.setForeground(new Color(0, 150, 0));
            } else if (resultado.getStatus().equals("PENDENTE")) {
                lblStatus.setText("Status: PENDENTE GERENTE");
                lblStatus.setForeground(new Color(200, 150, 0));
            } else {
                lblStatus.setText("Status: HARD STOP");
                lblStatus.setForeground(Color.RED);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira valores numéricos válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}