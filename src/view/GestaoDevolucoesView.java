package view;

import controller.DevolucaoController;
import model.Devolucao;
import model.ItemDevolucao;
import model.TipoOcorrencia;
import model.TratamentoDevolucao;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * View: GestaoDevolucoesView.java
 * Responsável: Pietro
 * -----------------------------------------------------------------------------
 * Tela 5: Gestão de Devoluções e Críticas de Vendas (Return Orders).
 * Implementa, em Java Swing, a interface comercial descrita no TCC: Solicitação
 * de Devolução/Troca, Análise Comercial e Ordem de Devolução Comercial, além do
 * painel de contexto. A tela não executa emissão fiscal, crédito financeiro ou
 * integração com banco; estas etapas pertencem aos módulos externos responsáveis.
 */
public class GestaoDevolucoesView extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final DevolucaoController controller = new DevolucaoController();
    private Devolucao devolucaoAtual;

    // Aba 1: Solicitação
    private final JTextField txtOcorrencia = new JTextField("DEV-001");
    private final JTextField txtCliente = new JTextField();
    private final JTextField txtPedidoOriginal = new JTextField();
    private final JTextField txtDataVenda = new JTextField(LocalDate.now().minusDays(1).format(FORMATO_DATA));
    private final JTextField txtDataSolicitacao = new JTextField(LocalDate.now().format(FORMATO_DATA));
    private final JComboBox<TipoOcorrencia> cmbTipo = new JComboBox<>(TipoOcorrencia.values());
    private final JTextField txtMotivo = new JTextField();
    private final JTextArea txtObservacaoCliente = new JTextArea(3, 20);
    private final JTextArea txtObservacaoInterna = new JTextArea(3, 20);
    private final JTextField txtAnexo = new JTextField();
    private final DefaultTableModel modeloItens = new DefaultTableModel(
            new String[]{"SKU", "Produto", "Quantidade", "Situação", "Valor Unitário", "Total"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private final JTable tabelaItens = new JTable(modeloItens);

    // Aba 2: Análise Comercial
    private final JTextField txtResponsavel = new JTextField();
    private final JComboBox<String> cmbPrioridade = new JComboBox<>(new String[]{"Baixa", "Normal", "Alta", "Crítica"});
    private final JComboBox<TratamentoDevolucao> cmbTratamento = new JComboBox<>(TratamentoDevolucao.values());
    private final JTextArea txtJustificativa = new JTextArea(5, 20);
    private final JLabel lblStatusAnalise = new JLabel("Solicitação ainda não analisada");

    // Aba 3: Ordem Comercial
    private final JLabel lblNumeroOrdem = new JLabel("Ainda não gerada");
    private final JLabel lblStatusOrdem = new JLabel("Aguardando análise comercial");
    private final JLabel lblDataOrdem = new JLabel("—");
    private final JTextArea txtHistorico = new JTextArea();

    // Painel de contexto
    private final JLabel lblContextoCliente = new JLabel("Cliente: —");
    private final JLabel lblContextoPedido = new JLabel("Pedido original: —");
    private final JLabel lblContextoPrazo = new JLabel("Prazo transcorrido: —");
    private final JLabel lblContextoValor = new JLabel("Valor da ocorrência: R$ 0,00");
    private final JLabel lblContextoAprovacao = new JLabel("Aprovação: —");

    public GestaoDevolucoesView() {
        setTitle("ERP - Gestão de Devoluções e Críticas de Vendas");
        setSize(1280, 760);
        setMinimumSize(new Dimension(1050, 650));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        add(criarTopBar(), BorderLayout.NORTH);

        JTabbedPane abas = new JTabbedPane();
        abas.addTab("1. Solicitação de Devolução / Troca", criarAbaSolicitacao());
        abas.addTab("2. Análise Comercial", criarAbaAnalise());
        abas.addTab("3. Ordem de Devolução Comercial", criarAbaOrdem());

        JSplitPane corpo = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, abas, criarPainelContexto());
        corpo.setResizeWeight(0.78);
        corpo.setDividerLocation(950);
        add(corpo, BorderLayout.CENTER);
    }

    private JPanel criarTopBar() {
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(new Color(30, 58, 95));
        topBar.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));

        JLabel titulo = new JLabel("MÓDULO DE VENDAS  >  RETURN ORDERS  >  GESTÃO DE DEVOLUÇÕES");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 15));
        topBar.add(titulo, BorderLayout.WEST);

        JLabel escopo = new JLabel("Escopo comercial — Fiscal e Financeiro são módulos externos");
        escopo.setForeground(new Color(220, 230, 245));
        topBar.add(escopo, BorderLayout.EAST);
        return topBar;
    }

    private JPanel criarAbaSolicitacao() {
        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JPanel formulario = new JPanel(new GridBagLayout());
        formulario.setBorder(criarBorda("Identificação da Solicitação"));
        GridBagConstraints c = restricoes();
        adicionarCampo(formulario, c, 0, "Nº da Ocorrência *", txtOcorrencia);
        adicionarCampo(formulario, c, 1, "Cliente *", txtCliente);
        adicionarCampo(formulario, c, 2, "Pedido Original *", txtPedidoOriginal);
        adicionarCampo(formulario, c, 3, "Data da Venda (dd/MM/aaaa) *", txtDataVenda);
        adicionarCampo(formulario, c, 4, "Data da Solicitação (dd/MM/aaaa) *", txtDataSolicitacao);
        adicionarCampo(formulario, c, 5, "Tipo de Ocorrência *", cmbTipo);
        adicionarCampo(formulario, c, 6, "Motivo Principal *", txtMotivo);

        JPanel observacoes = new JPanel(new GridLayout(1, 2, 10, 10));
        observacoes.setBorder(criarBorda("Observações e Evidências"));
        observacoes.add(criarAreaTexto("Observação do Cliente", txtObservacaoCliente));
        observacoes.add(criarAreaTexto("Observação Interna", txtObservacaoInterna));

        JPanel itens = new JPanel(new BorderLayout(8, 8));
        itens.setBorder(criarBorda("Itens Envolvidos na Ocorrência"));
        itens.add(new JScrollPane(tabelaItens), BorderLayout.CENTER);
        itens.add(criarPainelItem(), BorderLayout.SOUTH);

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnSalvar = new JButton("Salvar Solicitação");
        btnSalvar.addActionListener(e -> salvarSolicitacao());
        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.addActionListener(e -> limparFormulario());
        botoes.add(btnLimpar);
        botoes.add(btnSalvar);

        JPanel superior = new JPanel(new BorderLayout(10, 10));
        superior.add(formulario, BorderLayout.NORTH);
        superior.add(observacoes, BorderLayout.CENTER);
        painel.add(superior, BorderLayout.NORTH);
        painel.add(itens, BorderLayout.CENTER);
        painel.add(botoes, BorderLayout.SOUTH);
        return painel;
    }

    private JPanel criarPainelItem() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField txtSku = new JTextField(8);
        JTextField txtProduto = new JTextField(16);
        JTextField txtQuantidade = new JTextField(5);
        JTextField txtSituacao = new JTextField(12);
        JTextField txtValor = new JTextField(8);
        JButton btnAdicionar = new JButton("Adicionar Item");
        JButton btnRemover = new JButton("Remover Selecionado");

        painel.add(new JLabel("SKU:"));
        painel.add(txtSku);
        painel.add(new JLabel("Produto:"));
        painel.add(txtProduto);
        painel.add(new JLabel("Qtd.:"));
        painel.add(txtQuantidade);
        painel.add(new JLabel("Situação:"));
        painel.add(txtSituacao);
        painel.add(new JLabel("Valor R$:"));
        painel.add(txtValor);

        btnAdicionar.addActionListener(e -> {
            try {
                String sku = txtSku.getText().trim();
                String produto = txtProduto.getText().trim();
                int quantidade = Integer.parseInt(txtQuantidade.getText().trim());
                String situacao = txtSituacao.getText().trim();
                double valor = converterValor(txtValor.getText());
                if (sku.isEmpty() || produto.isEmpty()) {
                    throw new IllegalArgumentException("Informe SKU e produto.");
                }
                modeloItens.addRow(new Object[]{sku, produto, quantidade, situacao, valor,
                        String.format("%.2f", quantidade * valor)});
                txtSku.setText("");
                txtProduto.setText("");
                txtQuantidade.setText("");
                txtSituacao.setText("");
                txtValor.setText("");
            } catch (RuntimeException ex) {
                mostrarErro(ex.getMessage());
            }
        });
        btnRemover.addActionListener(e -> {
            int linha = tabelaItens.getSelectedRow();
            if (linha >= 0) {
                modeloItens.removeRow(linha);
            } else {
                mostrarErro("Selecione um item para removê-lo.");
            }
        });
        painel.add(btnAdicionar);
        painel.add(btnRemover);
        return painel;
    }

    private JPanel criarAbaAnalise() {
        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JPanel formulario = new JPanel(new GridBagLayout());
        formulario.setBorder(criarBorda("Análise Comercial e Elegibilidade"));
        GridBagConstraints c = restricoes();
        adicionarCampo(formulario, c, 0, "Responsável pela Análise *", txtResponsavel);
        adicionarCampo(formulario, c, 1, "Prioridade", cmbPrioridade);
        adicionarCampo(formulario, c, 2, "Tratamento Recomendado *", cmbTratamento);
        adicionarCampo(formulario, c, 3, "Justificativa Comercial *", new JScrollPane(txtJustificativa));

        JPanel status = new JPanel(new FlowLayout(FlowLayout.LEFT));
        status.setBorder(criarBorda("Status da Análise"));
        lblStatusAnalise.setForeground(new Color(180, 90, 0));
        status.add(lblStatusAnalise);

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnAnalisar = new JButton("Registrar Análise");
        btnAnalisar.addActionListener(e -> registrarAnalise());
        JButton btnAprovar = new JButton("Aprovar Exceção");
        btnAprovar.addActionListener(e -> aprovarExcecao());
        botoes.add(btnAprovar);
        botoes.add(btnAnalisar);

        painel.add(formulario, BorderLayout.NORTH);
        painel.add(status, BorderLayout.CENTER);
        painel.add(botoes, BorderLayout.SOUTH);
        return painel;
    }

    private JPanel criarAbaOrdem() {
        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JPanel dados = new JPanel(new GridLayout(3, 1, 8, 8));
        dados.setBorder(criarBorda("Ordem de Devolução Comercial"));
        dados.add(criarLinha("Número da Ordem:", lblNumeroOrdem));
        dados.add(criarLinha("Status:", lblStatusOrdem));
        dados.add(criarLinha("Data de Geração:", lblDataOrdem));

        txtHistorico.setEditable(false);
        txtHistorico.setLineWrap(true);
        txtHistorico.setWrapStyleWord(true);
        JScrollPane historico = new JScrollPane(txtHistorico);
        historico.setBorder(criarBorda("Histórico da Ocorrência"));

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnGerar = new JButton("Gerar Ordem Comercial");
        btnGerar.addActionListener(e -> gerarOrdem());
        JButton btnEncerrar = new JButton("Encerrar Ocorrência");
        btnEncerrar.addActionListener(e -> encerrarOcorrencia());
        botoes.add(btnEncerrar);
        botoes.add(btnGerar);

        painel.add(dados, BorderLayout.NORTH);
        painel.add(historico, BorderLayout.CENTER);
        painel.add(botoes, BorderLayout.SOUTH);
        return painel;
    }

    private JPanel criarPainelContexto() {
        JPanel painel = new JPanel();
        painel.setLayout(new javax.swing.BoxLayout(painel, javax.swing.BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 1, 0, 0, new Color(210, 210, 210)),
                BorderFactory.createEmptyBorder(16, 14, 16, 14)));
        painel.setPreferredSize(new Dimension(275, 0));

        JLabel titulo = new JLabel("CONTEXTO DA OCORRÊNCIA");
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 13));
        painel.add(titulo);
        painel.add(javax.swing.Box.createVerticalStrut(18));
        adicionarContexto(painel, lblContextoCliente);
        adicionarContexto(painel, lblContextoPedido);
        adicionarContexto(painel, lblContextoPrazo);
        adicionarContexto(painel, lblContextoValor);
        adicionarContexto(painel, lblContextoAprovacao);
        painel.add(javax.swing.Box.createVerticalStrut(16));
        JLabel limite = new JLabel("Fiscal e Financeiro: módulos externos");
        limite.setForeground(new Color(160, 40, 40));
        limite.setAlignmentX(CENTER_ALIGNMENT);
        painel.add(limite);
        return painel;
    }

    private void salvarSolicitacao() {
        try {
            Devolucao devolucao = new Devolucao(
                    txtOcorrencia.getText().trim(),
                    txtPedidoOriginal.getText().trim(),
                    txtCliente.getText().trim(),
                    converterData(txtDataVenda.getText()),
                    converterData(txtDataSolicitacao.getText()),
                    (TipoOcorrencia) cmbTipo.getSelectedItem(),
                    txtMotivo.getText().trim());
            devolucao.setObservacaoCliente(txtObservacaoCliente.getText().trim());
            devolucao.setObservacaoInterna(txtObservacaoInterna.getText().trim());
            devolucao.adicionarAnexo(txtAnexo.getText().trim());
            for (int linha = 0; linha < modeloItens.getRowCount(); linha++) {
                devolucao.adicionarItem(new ItemDevolucao(
                        modeloItens.getValueAt(linha, 0).toString(),
                        modeloItens.getValueAt(linha, 1).toString(),
                        Integer.parseInt(modeloItens.getValueAt(linha, 2).toString()),
                        modeloItens.getValueAt(linha, 3).toString(),
                        converterValor(modeloItens.getValueAt(linha, 4).toString())));
            }
            controller.validarSolicitacao(devolucao);
            devolucaoAtual = devolucao;
            atualizarContexto();
            atualizarHistorico();
            JOptionPane.showMessageDialog(this, "Solicitação salva e validada comercialmente.",
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (RuntimeException ex) {
            mostrarErro(ex.getMessage());
        }
    }

    private void registrarAnalise() {
        try {
            exigirSolicitacaoSalva();
            controller.iniciarAnaliseComercial(devolucaoAtual,
                    txtResponsavel.getText(), txtJustificativa.getText(),
                    (String) cmbPrioridade.getSelectedItem(),
                    (TratamentoDevolucao) cmbTratamento.getSelectedItem());
            lblStatusAnalise.setText(devolucaoAtual.getStatusAnalise());
            atualizarContexto();
            atualizarHistorico();
            JOptionPane.showMessageDialog(this, "Análise comercial registrada.",
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (RuntimeException ex) {
            mostrarErro(ex.getMessage());
        }
    }

    private void aprovarExcecao() {
        try {
            exigirSolicitacaoSalva();
            String aprovador = JOptionPane.showInputDialog(this, "Nome do aprovador:");
            controller.aprovarExcecao(devolucaoAtual, aprovador);
            lblStatusAnalise.setText(devolucaoAtual.getStatusAnalise());
            atualizarContexto();
            atualizarHistorico();
        } catch (RuntimeException ex) {
            mostrarErro(ex.getMessage());
        }
    }

    private void gerarOrdem() {
        try {
            exigirSolicitacaoSalva();
            String numero = controller.gerarOrdemDevolucaoComercial(devolucaoAtual);
            lblNumeroOrdem.setText(numero);
            lblStatusOrdem.setText(devolucaoAtual.getStatusOrdem());
            lblDataOrdem.setText(devolucaoAtual.getDataGeracaoOrdem().format(FORMATO_DATA));
            atualizarHistorico();
            JOptionPane.showMessageDialog(this,
                    "Ordem Comercial gerada. O envio ao Fiscal e Financeiro é externo a este módulo.",
                    "Ordem gerada", JOptionPane.INFORMATION_MESSAGE);
        } catch (RuntimeException ex) {
            mostrarErro(ex.getMessage());
        }
    }

    private void encerrarOcorrencia() {
        try {
            exigirSolicitacaoSalva();
            controller.encerrarOcorrencia(devolucaoAtual);
            lblStatusAnalise.setText(devolucaoAtual.getStatusAnalise());
            lblStatusOrdem.setText(devolucaoAtual.getStatusOrdem());
            atualizarHistorico();
            atualizarContexto();
        } catch (RuntimeException ex) {
            mostrarErro(ex.getMessage());
        }
    }

    private void atualizarContexto() {
        if (devolucaoAtual == null) return;
        lblContextoCliente.setText("Cliente: " + devolucaoAtual.getNomeCliente());
        lblContextoPedido.setText("Pedido original: " + devolucaoAtual.getNumeroPedidoOriginal());
        lblContextoPrazo.setText("Prazo transcorrido: " + devolucaoAtual.getPrazoDecorridoEmDias() + " dia(s)");
        lblContextoValor.setText(String.format("Valor da ocorrência: R$ %.2f", devolucaoAtual.calcularValorTotal()));
        lblContextoAprovacao.setText("Aprovação: " + (devolucaoAtual.isAprovacaoNecessaria()
                ? (devolucaoAtual.isAprovada() ? "Aprovada" : "Pendente") : "Não necessária"));
    }

    private void atualizarHistorico() {
        if (devolucaoAtual == null) return;
        txtHistorico.setText(String.join("\n", devolucaoAtual.getHistorico()));
    }

    private void exigirSolicitacaoSalva() {
        if (devolucaoAtual == null) {
            throw new IllegalStateException("Salve e valide a solicitação antes de prosseguir.");
        }
    }

    private void limparFormulario() {
        txtCliente.setText("");
        txtPedidoOriginal.setText("");
        txtMotivo.setText("");
        txtObservacaoCliente.setText("");
        txtObservacaoInterna.setText("");
        txtAnexo.setText("");
        modeloItens.setRowCount(0);
        devolucaoAtual = null;
    }

    private GridBagConstraints restricoes() {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 6, 5, 6);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        return c;
    }

    private void adicionarCampo(JPanel painel, GridBagConstraints c, int linha, String rotulo, java.awt.Component campo) {
        c.gridx = 0;
        c.gridy = linha;
        c.weightx = 0.25;
        painel.add(new JLabel(rotulo), c);
        c.gridx = 1;
        c.weightx = 0.75;
        painel.add(campo, c);
    }

    private JPanel criarAreaTexto(String titulo, JTextArea area) {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(criarBorda(titulo));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        painel.add(new JScrollPane(area), BorderLayout.CENTER);
        return painel;
    }

    private JPanel criarLinha(String nome, JLabel valor) {
        JPanel linha = new JPanel(new BorderLayout());
        JLabel label = new JLabel(nome);
        label.setFont(new Font("SansSerif", Font.BOLD, 12));
        linha.add(label, BorderLayout.WEST);
        valor.setHorizontalAlignment(SwingConstants.RIGHT);
        linha.add(valor, BorderLayout.CENTER);
        return linha;
    }

    private TitledBorder criarBorda(String titulo) {
        return BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(210, 210, 210)), titulo);
    }

    private void adicionarContexto(JPanel painel, JLabel label) {
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(7, 0, 7, 0));
        painel.add(label);
    }

    private LocalDate converterData(String valor) {
        return LocalDate.parse(valor.trim(), FORMATO_DATA);
    }

    private double converterValor(String valor) {
        return Double.parseDouble(valor.trim().replace("R$", "").replace(".", "").replace(",", "."));
    }

    private void mostrarErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Validação da Devolução", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new GestaoDevolucoesView().setVisible(true));
    }
}
