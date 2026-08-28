package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import model.Cliente;
import model.Endereco;
import controller.ClienteController;

/**
 * View: CadastroClienteView
 * Responsável: Eúde
 * -----------------------------------------------------------------------------
 * Tela principal do Cadastro de Clientes e Regras de Venda.
 * Estrutura: um JFrame com JTabbedPane de 3 abas (Dados Cadastrais &
 * Segmentação, Regras Comerciais, Configuração Fiscal por Estabelecimento) e
 * um rodapé fixo com botão de validação e indicador visual de status.
 */
public class CadastroClienteView extends JFrame {

    private static final long serialVersionUID = 1L;

    // Controller que faz toda a lógica de validação — a View NUNCA valida
    // regra de negócio sozinha, só coleta dados da tela e delega ao Controller.
    private ClienteController clienteController = new ClienteController();

    // Objeto "de trabalho": vai sendo preenchido conforme o usuário mexe na tela.
    private Cliente clienteAtual = new Cliente();

    // ---- Campos da Aba 1: Dados Cadastrais & Segmentação ----
    private JTextField txtCpfCnpj, txtRazaoSocial, txtNomeFantasia, txtIe, txtIm, txtVendedor;
    private JComboBox<String> cbCanal, cbSegmentacao;
    // Campos do bloco de endereço (dentro da aba 1)
    private JTextField txtLogradouro, txtNumero, txtBairro, txtCidade, txtEstado, txtCep;
    private JComboBox<String> cbTipoEndereco;
    // Modelo + lista visual que mostram os endereços já adicionados
    private DefaultListModel<String> modeloEnderecos = new DefaultListModel<>();
    private JList<String> listaEnderecos;

    // ---- Campos da Aba 2: Regras Comerciais ----
    private JComboBox<String> cbCondicaoPagamento;
    private JTextField txtLimiteCredito, txtAlcadaDesconto, txtTabelaPreco;
    private JCheckBox chkBloqueadoInadimplencia;

    // ---- Campos da Aba 3: Configuração Fiscal ----
    private JLabel lblRegraFiscal;
    private JLabel lblMensagemBloqueio;

    // ---- Rodapé (fora das abas, sempre visível) ----
    private JLabel lblStatus;

    /**
     * Construtor: monta a janela inteira. É chamado uma única vez, quando a
     * tela é criada (veja o main() lá embaixo).
     */
    public CadastroClienteView() {
        setTitle("Cadastro de Clientes e Regras de Venda");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // fecha só esta janela, não a JVM inteira
        setLocationRelativeTo(null); // centraliza a janela na tela

        // JTabbedPane é o componente que cria as "abas" clicáveis no topo
        JTabbedPane abas = new JTabbedPane();
        abas.addTab("Dados Cadastrais & Segmentação", criarAbaDadosCadastrais());
        abas.addTab("Regras Comerciais", criarAbaRegrasComerciais());
        abas.addTab("Configuração Fiscal por Estabelecimento", criarAbaFiscal());

        // BorderLayout: abas ocupam o centro, rodapé fica fixo embaixo
        add(abas, BorderLayout.CENTER);
        add(criarRodape(), BorderLayout.SOUTH);
    }

    /**
     * Monta o conteúdo da Aba 1. Retorna um JPanel pronto para ser inserido
     * na JTabbedPane. Dividida em duas partes: dados do cliente (grid 2
     * colunas) + bloco de endereço com sua própria lista.
     */
    private JPanel criarAbaDadosCadastrais() {
        // GridLayout(0, 2, ...): 0 linhas = "quantas forem necessárias",
        // 2 colunas (rótulo | campo), espaçamento de 8px entre células
        JPanel topo = new JPanel(new GridLayout(0, 2, 8, 8));
        topo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // margem interna

        txtCpfCnpj = new JTextField();
        txtRazaoSocial = new JTextField();
        txtNomeFantasia = new JTextField();
        txtIe = new JTextField();
        txtIm = new JTextField();
        txtVendedor = new JTextField();
        // JComboBox = caixa de seleção (dropdown); os valores aqui são os
        // únicos que o usuário pode escolher, evitando erro de digitação
        cbCanal = new JComboBox<>(new String[]{"BALCAO", "ATACADO", "ECOMMERCE", "REPRESENTANTE"});
        cbSegmentacao = new JComboBox<>(new String[]{"VAREJO", "ATACADO", "INDUSTRIA"});

        // Cada par add() é "rótulo" + "campo" — o GridLayout posiciona
        // automaticamente em sequência, linha por linha
        topo.add(new JLabel("CPF/CNPJ:")); topo.add(txtCpfCnpj);
        topo.add(new JLabel("Razão Social:")); topo.add(txtRazaoSocial);
        topo.add(new JLabel("Nome Fantasia:")); topo.add(txtNomeFantasia);
        topo.add(new JLabel("Inscrição Estadual:")); topo.add(txtIe);
        topo.add(new JLabel("Inscrição Municipal:")); topo.add(txtIm);
        topo.add(new JLabel("Canal de Venda:")); topo.add(cbCanal);
        topo.add(new JLabel("Segmentação:")); topo.add(cbSegmentacao);
        topo.add(new JLabel("Vendedor Responsável:")); topo.add(txtVendedor);

        // --- Bloco de endereço: formulário + botão + lista dos já adicionados ---
        JPanel camposEndereco = new JPanel(new GridLayout(0, 2, 5, 5));
        cbTipoEndereco = new JComboBox<>(new String[]{"COBRANCA", "ENTREGA"});
        txtLogradouro = new JTextField();
        txtNumero = new JTextField();
        txtBairro = new JTextField();
        txtCidade = new JTextField();
        txtEstado = new JTextField();
        txtCep = new JTextField();
        camposEndereco.add(new JLabel("Tipo:")); camposEndereco.add(cbTipoEndereco);
        camposEndereco.add(new JLabel("Logradouro:")); camposEndereco.add(txtLogradouro);
        camposEndereco.add(new JLabel("Número:")); camposEndereco.add(txtNumero);
        camposEndereco.add(new JLabel("Bairro:")); camposEndereco.add(txtBairro);
        camposEndereco.add(new JLabel("Cidade:")); camposEndereco.add(txtCidade);
        camposEndereco.add(new JLabel("Estado (UF):")); camposEndereco.add(txtEstado);
        camposEndereco.add(new JLabel("CEP:")); camposEndereco.add(txtCep);

        JButton btnAdicionarEndereco = new JButton("Adicionar Endereço");
        listaEnderecos = new JList<>(modeloEnderecos); // JList "escuta" o modeloEnderecos
        // Expressão lambda: "quando o botão for clicado, chame adicionarEndereco()"
        btnAdicionarEndereco.addActionListener(e -> adicionarEndereco());

        JPanel painelEndereco = new JPanel(new BorderLayout(5, 5));
        painelEndereco.setBorder(BorderFactory.createTitledBorder("Endereços do Cliente"));
        painelEndereco.add(camposEndereco, BorderLayout.NORTH);   // formulário em cima
        painelEndereco.add(btnAdicionarEndereco, BorderLayout.CENTER); // botão no meio
        // JScrollPane dá barra de rolagem caso a lista de endereços cresça muito
        painelEndereco.add(new JScrollPane(listaEnderecos), BorderLayout.SOUTH);

        // Painel final da aba: dados do cliente em cima, endereços embaixo
        JPanel completo = new JPanel(new BorderLayout());
        completo.add(topo, BorderLayout.NORTH);
        completo.add(painelEndereco, BorderLayout.CENTER);
        return completo;
    }

    /**
     * Monta a Aba 2 — campos comerciais que o Controller usa para decidir
     * se o cliente pode ser ativado (condição de pagamento, limite, etc).
     */
    private JPanel criarAbaRegrasComerciais() {
        JPanel painel = new JPanel(new GridLayout(0, 2, 8, 8));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        cbCondicaoPagamento = new JComboBox<>(new String[]{"A_VISTA", "30D", "30_60D", "30_60_90D"});
        txtLimiteCredito = new JTextField();
        txtAlcadaDesconto = new JTextField();
        txtTabelaPreco = new JTextField();
        chkBloqueadoInadimplencia = new JCheckBox("Bloquear cliente por inadimplência");

        painel.add(new JLabel("Condição de Pagamento:")); painel.add(cbCondicaoPagamento);
        painel.add(new JLabel("Limite de Crédito (R$):")); painel.add(txtLimiteCredito);
        painel.add(new JLabel("Alçada de Desconto (%):")); painel.add(txtAlcadaDesconto);
        painel.add(new JLabel("Tabela de Preço Vinculada:")); painel.add(txtTabelaPreco);
        painel.add(new JLabel("Inadimplência:")); painel.add(chkBloqueadoInadimplencia);

        return painel;
    }

    /**
     * Monta a Aba 3 — exige apenas DEMONSTRAR se a regra fiscal está
     * configurada ou pendente. Não existe nenhuma chamada a serviço fiscal
     * real aqui, conforme proibido pelo enunciado do TCC.
     */
    private JPanel criarAbaFiscal() {
        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Texto inicial neutro; será atualizado depois que o usuário clicar
        // em "Validar / Ativar Cliente" (veja validarEAtivar())
        lblRegraFiscal = new JLabel("Regra fiscal: aguardando validação.", SwingConstants.CENTER);
        lblRegraFiscal.setFont(lblRegraFiscal.getFont().deriveFont(Font.BOLD, 14f));

        lblMensagemBloqueio = new JLabel(" ", SwingConstants.CENTER);

        // JTextArea só para deixar escrito, de forma bem clara, que esta aba
        // não emite documento fiscal — importante para o critério do TCC
        JTextArea aviso = new JTextArea(
                "Esta aba apenas demonstra se a regra fiscal do estabelecimento esta\n" +
                "configurada ou pendente para este cliente. Nenhum documento fiscal e\n" +
                "emitido aqui - a emissao real e responsabilidade do modulo Fiscal,\n" +
                "fora do escopo desta parte do TCC."
        );
        aviso.setEditable(false); // usuário não pode digitar em cima
        aviso.setOpaque(false);   // fundo transparente, parece um texto comum
        aviso.setLineWrap(true);       // quebra linha automaticamente
        aviso.setWrapStyleWord(true);  // quebra por palavra inteira, não no meio

        painel.add(lblRegraFiscal, BorderLayout.NORTH);
        painel.add(aviso, BorderLayout.CENTER);
        painel.add(lblMensagemBloqueio, BorderLayout.SOUTH);
        return painel;
    }

    /**
     * Monta o rodapé: botão de ação + label colorida de status. Fica fora
     * das abas, então está sempre visível não importa em qual aba o usuário
     * esteja navegando.
     */
    private JPanel criarRodape() {
        JPanel rodape = new JPanel(new BorderLayout());
        JButton btnValidar = new JButton("Validar / Ativar Cliente");
        btnValidar.addActionListener(e -> validarEAtivar());

        lblStatus = new JLabel("Status: PENDENTE", SwingConstants.CENTER);
        lblStatus.setOpaque(true); // precisa ser true para o setBackground funcionar visualmente
        lblStatus.setBackground(Color.LIGHT_GRAY);
        lblStatus.setFont(lblStatus.getFont().deriveFont(Font.BOLD));

        rodape.add(btnValidar, BorderLayout.WEST);
        rodape.add(lblStatus, BorderLayout.CENTER);
        return rodape;
    }

    /**
     * Chamado quando o botão "Adicionar Endereço" é clicado. Faz uma
     * validação mínima (não deixa endereço totalmente vazio), cria o objeto
     * Endereco e o guarda tanto no Cliente (dado real) quanto na JList
     * (representação visual).
     */
    private void adicionarEndereco() {
        String logradouro = txtLogradouro.getText().trim();
        String cidade = txtCidade.getText().trim();
        if (logradouro.isEmpty() || cidade.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Informe ao menos logradouro e cidade para adicionar o endereço.",
                    "Endereço incompleto", JOptionPane.WARNING_MESSAGE);
            return; // interrompe aqui — não adiciona endereço incompleto
        }
        Endereco endereco = new Endereco(
                (String) cbTipoEndereco.getSelectedItem(), // cast necessário: getSelectedItem() retorna Object
                logradouro, txtNumero.getText().trim(), txtBairro.getText().trim(),
                cidade, txtEstado.getText().trim(), txtCep.getText().trim());
        clienteAtual.adicionarEndereco(endereco);           // guarda no objeto real
        modeloEnderecos.addElement(endereco.toString());    // guarda a versão em texto pra JList mostrar
    }

    /**
     * Lê tudo o que está digitado/selecionado na tela e "despeja" dentro do
     * objeto clienteAtual. É chamado sempre antes de validar, para garantir
     * que o Controller receba os dados mais recentes da tela.
     */
    private void preencherClienteComFormulario() {
        clienteAtual.setCpfCnpj(txtCpfCnpj.getText().trim());
        clienteAtual.setRazaoSocial(txtRazaoSocial.getText().trim());
        clienteAtual.setNomeFantasia(txtNomeFantasia.getText().trim());
        clienteAtual.setInscricaoEstadual(txtIe.getText().trim());
        clienteAtual.setInscricaoMunicipal(txtIm.getText().trim());
        clienteAtual.setCanalVenda((String) cbCanal.getSelectedItem());
        clienteAtual.setSegmentacao((String) cbSegmentacao.getSelectedItem());
        clienteAtual.setVendedorResponsavel(txtVendedor.getText().trim());
        clienteAtual.setCondicaoPagamento((String) cbCondicaoPagamento.getSelectedItem());
        clienteAtual.setTabelaPrecoVinculada(txtTabelaPreco.getText().trim());
        clienteAtual.setBloqueadoPorInadimplencia(chkBloqueadoInadimplencia.isSelected());

        // try/catch protege contra o usuário digitar texto onde deveria ser número.
        // Se der erro de conversão, força limiteCredito = -1, que o Controller
        // já rejeita como "negativo" — assim o erro vira uma mensagem clara
        // pro usuário, em vez de travar a aplicação (Exception não tratada).
        try {
            String limite = txtLimiteCredito.getText().trim().replace(",", "."); // aceita vírgula BR
            clienteAtual.setLimiteCredito(limite.isEmpty() ? -1 : Double.parseDouble(limite));
        } catch (NumberFormatException ex) {
            clienteAtual.setLimiteCredito(-1);
        }

        try {
            String alcada = txtAlcadaDesconto.getText().trim().replace(",", ".");
            clienteAtual.setAlcadaDesconto(alcada.isEmpty() ? 0 : Double.parseDouble(alcada));
        } catch (NumberFormatException ex) {
            clienteAtual.setAlcadaDesconto(0);
        }
    }

    /**
     * Ação principal do botão "Validar / Ativar Cliente". Fluxo:
     * 1) atualiza o objeto Cliente com o que está na tela;
     * 2) pede ao Controller a lista de erros de validação;
     * 3) se houver erro, mostra tudo em um diálogo e para por aí;
     * 4) se estiver tudo certo, pede ao Controller para decidir o status
     *    final (ATIVO/BLOQUEADO/PENDENTE) e atualiza a interface.
     */
    private void validarEAtivar() {
        preencherClienteComFormulario();
        List<String> erros = clienteController.validarCliente(clienteAtual);

        if (!erros.isEmpty()) {
            // Monta uma mensagem com "- erro1\n- erro2\n..." para o usuário
            // ver de uma vez só tudo que falta corrigir
            StringBuilder msg = new StringBuilder("Corrija os itens abaixo:\n");
            for (String erro : erros) msg.append("- ").append(erro).append("\n");
            JOptionPane.showMessageDialog(this, msg.toString(), "Cadastro incompleto", JOptionPane.ERROR_MESSAGE);
            atualizarStatusVisual("PENDENTE");
            return; // não avança pra ativação se tem erro
        }

        // Delega ao Controller a decisão final de status (regra de negócio
        // fica lá, não aqui na View)
        String statusFinal = clienteController.ativarCliente(clienteAtual);
        atualizarStatusVisual(statusFinal);

        // Atualiza o texto da Aba 3 conforme o resultado da simulação fiscal
        if (clienteAtual.isRegraFiscalPendente()) {
            lblRegraFiscal.setText("Regra fiscal: PENDENTE de configuração (falta Inscrição Estadual).");
            lblMensagemBloqueio.setText("Preencha a IE na aba de Dados Cadastrais para liberar a regra fiscal.");
        } else {
            lblRegraFiscal.setText("Regra fiscal: CONFIGURADA para este estabelecimento.");
            lblMensagemBloqueio.setText(" ");
        }

        JOptionPane.showMessageDialog(this, "Cliente processado com status: " + statusFinal,
                "Resultado", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Troca o texto e a cor de fundo do label de status no rodapé, dando
     * feedback visual imediato (verde/vermelho/amarelo) — é o requisito do
     * PDF de "mostrar visualmente os estados de ativo, bloqueado e regra
     * pendente".
     */
    private void atualizarStatusVisual(String status) {
        lblStatus.setText("Status: " + status);
        if ("ATIVO".equals(status)) {
            lblStatus.setBackground(new Color(198, 239, 206)); // verde claro
        } else if ("BLOQUEADO".equals(status)) {
            lblStatus.setBackground(new Color(255, 199, 206)); // vermelho claro
        } else {
            lblStatus.setBackground(new Color(255, 235, 156)); // amarelo (pendente)
        }
    }

    /**
     * Ponto de entrada para testar esta tela isoladamente (Run As > Java
     * Application). SwingUtilities.invokeLater garante que a interface seja
     * criada na "thread de eventos" do Swing — boa prática obrigatória para
     * qualquer aplicação Swing, evita bugs esquisitos de renderização.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CadastroClienteView().setVisible(true));
    }
}
