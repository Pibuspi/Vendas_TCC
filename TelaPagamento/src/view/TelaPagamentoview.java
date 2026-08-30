package view;

import javax.swing.*;
import java.awt.*;

public class TelaPagamentoview extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField txtNome;
    private JTextField txtCpf;
    private JTextField txtDdd;
    private JTextField txtTelefone;
    private JTextField txtCep;
    private JTextField txtEndereco;
    private JTextField txtNumero;
    private JTextField txtUf;

    public TelaPagamentoview() {

        setTitle("Identificação do Consumidor");
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
        painelDados.add(new JLabel("Nome:"), gbc);

        txtNome = new JTextField();
        gbc.gridx = 1;
        gbc.weightx = 1;
        painelDados.add(txtNome, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0;
        painelDados.add(new JLabel("Cpf:"), gbc);

        txtCpf = new JTextField();
        gbc.gridx = 3;
        gbc.weightx = 1;
        painelDados.add(txtCpf, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        painelDados.add(new JLabel("DDD:"), gbc);

        txtDdd = new JTextField();
        gbc.gridx = 1;
        gbc.weightx = 1;
        painelDados.add(txtDdd, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0;
        painelDados.add(new JLabel("Telefone:"), gbc);

        txtTelefone = new JTextField();
        gbc.gridx = 3;
        gbc.weightx = 1;
        painelDados.add(txtTelefone, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        painelDados.add(new JLabel("CEP:"), gbc);

        txtCep = new JTextField();
        gbc.gridx = 1;
        gbc.weightx = 1;
        painelDados.add(txtCep, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0;
        painelDados.add(new JLabel("Endereço:"), gbc);

        txtEndereco = new JTextField();
        gbc.gridx = 3;
        gbc.weightx = 1;
        painelDados.add(txtEndereco, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        painelDados.add(new JLabel("Número:"), gbc);

        txtNumero = new JTextField();
        gbc.gridx = 1;
        gbc.weightx = 1;
        painelDados.add(txtNumero, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0;
        painelDados.add(new JLabel("UF:"), gbc);

        txtUf = new JTextField();
        gbc.gridx = 3;
        gbc.weightx = 1;
        painelDados.add(txtUf, gbc);
        


        // =====================================================
        // BOTÕES
        // =====================================================

        JPanel painelBotoes = new JPanel();

        JButton btnConfirmar = new JButton("Confirmar");      
        painelBotoes.add(btnConfirmar);     

        JPanel painelCentro = new JPanel(new BorderLayout());

        painelCentro.add(painelBotoes, BorderLayout.SOUTH);
   
        setLayout(new BorderLayout(5, 5));

        add(painelDados, BorderLayout.NORTH);
        add(painelCentro, BorderLayout.CENTER);

        
        btnConfirmar.addActionListener(e -> {

            int resposta = JOptionPane.showConfirmDialog(
                this,
                "Deseja confirmar os dados?",
                "Confirmação",
                JOptionPane.YES_NO_OPTION
            );

            if (resposta == JOptionPane.YES_OPTION) {

                String nome = txtNome.getText();

                if (nome.isEmpty()) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Preencha o campo Nome"
                    );
                    return;
                }

                String cpf = txtCpf.getText();

                if (cpf.isEmpty()) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Preencha o campo CPF"
                    );
                    return;
                }

                String ddd = txtDdd.getText();

                if (ddd.isEmpty()) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Preencha o campo DDD"
                    );
                    return;
                }

                String telefone = txtTelefone.getText();

                if (telefone.isEmpty()) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Preencha o campo Telefone"
                    );
                    return;
                }

                String cep = txtCep.getText();

                if (cep.isEmpty()) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Preencha o campo CEP"
                    );
                    return;
                }

                String endereco = txtEndereco.getText();

                if (endereco.isEmpty()) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Preencha o campo Endereço"
                    );
                    return;
                }

                String numero = txtNumero.getText();

                if (numero.isEmpty()) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Preencha o campo Número"
                    );
                    return;
                }

                String uf = txtUf.getText();

                if (uf.isEmpty()) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Preencha o campo UF"
                    );
                    return;
                }

                limparCampos();

                JOptionPane.showMessageDialog(
                    this,
                    "Dados confirmados com sucesso!"
                );
            }
        });}
        

    private void limparCampos() {

        txtNome.setText("");
        txtCpf.setText("");
        txtDdd.setText("");
        txtTelefone.setText("");
        txtCep.setText("");
        txtEndereco.setText("");
        txtNumero.setText("");
        txtUf.setText("");

    }}


