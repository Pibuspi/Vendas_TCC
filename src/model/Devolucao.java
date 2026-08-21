package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Model: Devolucao.java
 * Responsável: Pietro
 * -----------------------------------------------------------------------------
 * Representa a ocorrência de Gestão de Devoluções e Críticas de Vendas (Return
 * Orders) no escopo comercial. Centraliza a solicitação, a análise comercial e a
 * Ordem de Devolução Comercial que será posteriormente consumida pelos módulos
 * Fiscal e Financeiro.
 *
 * Limite de escopo: esta classe NÃO emite nota fiscal de entrada, NÃO credita o
 * cliente e NÃO atualiza banco de dados externo. Essas operações pertencem aos
 * módulos Fiscal, Financeiro e Banco de Dados/Integração.
 */
public class Devolucao {
    public static final int PRAZO_PADRAO_DIAS = 30;

    private String numeroOcorrencia;
    private String numeroPedidoOriginal;
    private String nomeCliente;
    private LocalDate dataVenda;
    private LocalDate dataSolicitacao;
    private TipoOcorrencia tipoOcorrencia;
    private String motivoPrincipal;
    private String observacaoCliente;
    private String observacaoInterna;
    private final List<String> anexos = new ArrayList<>();
    private final List<ItemDevolucao> itens = new ArrayList<>();

    private String responsavelAnalise;
    private String justificativaComercial;
    private String prioridade;
    private String statusAnalise;
    private TratamentoDevolucao tratamentoRecomendado;
    private boolean aprovacaoNecessaria;
    private boolean aprovada;

    private String numeroOrdemComercial;
    private String statusOrdem;
    private LocalDate dataGeracaoOrdem;
    private final List<String> historico = new ArrayList<>();

    public Devolucao(String numeroOcorrencia, String numeroPedidoOriginal, String nomeCliente,
                     LocalDate dataVenda, LocalDate dataSolicitacao, TipoOcorrencia tipoOcorrencia,
                     String motivoPrincipal) {
        this.numeroOcorrencia = numeroOcorrencia;
        this.numeroPedidoOriginal = numeroPedidoOriginal;
        this.nomeCliente = nomeCliente;
        this.dataVenda = dataVenda;
        this.dataSolicitacao = dataSolicitacao == null ? LocalDate.now() : dataSolicitacao;
        this.tipoOcorrencia = tipoOcorrencia;
        this.motivoPrincipal = motivoPrincipal;
        this.prioridade = "Normal";
        this.statusAnalise = "SOLICITADA";
        this.statusOrdem = "NÃO GERADA";
        adicionarHistorico("Solicitação criada.");
    }

    public boolean possuiPedidoOriginal() {
        return numeroPedidoOriginal != null && !numeroPedidoOriginal.isBlank();
    }

    public boolean possuiMotivoDefinido() {
        return motivoPrincipal != null && !motivoPrincipal.isBlank();
    }

    public boolean possuiItens() {
        return !itens.isEmpty();
    }

    public boolean estaDentroDoPrazo() {
        if (dataVenda == null || dataSolicitacao == null) {
            return false;
        }
        return !dataSolicitacao.isBefore(dataVenda)
                && !dataSolicitacao.isAfter(dataVenda.plusDays(PRAZO_PADRAO_DIAS));
    }

    public int getPrazoDecorridoEmDias() {
        if (dataVenda == null || dataSolicitacao == null) {
            return 0;
        }
        return (int) java.time.temporal.ChronoUnit.DAYS.between(dataVenda, dataSolicitacao);
    }

    public double calcularValorTotal() {
        return itens.stream().mapToDouble(ItemDevolucao::calcularTotalItem).sum();
    }

    public void adicionarItem(ItemDevolucao item) {
        if (item == null) {
            throw new IllegalArgumentException("O item da devolução não pode ser nulo.");
        }
        itens.add(item);
    }

    public void adicionarAnexo(String nomeArquivo) {
        if (nomeArquivo != null && !nomeArquivo.isBlank()) {
            anexos.add(nomeArquivo.trim());
        }
    }

    public void adicionarHistorico(String evento) {
        if (evento != null && !evento.isBlank()) {
            historico.add(LocalDate.now() + " - " + evento);
        }
    }

    public String getNumeroOcorrencia() { return numeroOcorrencia; }
    public String getNumeroPedidoOriginal() { return numeroPedidoOriginal; }
    public String getNomeCliente() { return nomeCliente; }
    public LocalDate getDataVenda() { return dataVenda; }
    public LocalDate getDataSolicitacao() { return dataSolicitacao; }
    public TipoOcorrencia getTipoOcorrencia() { return tipoOcorrencia; }
    public String getMotivoPrincipal() { return motivoPrincipal; }
    public String getObservacaoCliente() { return observacaoCliente; }
    public void setObservacaoCliente(String observacaoCliente) { this.observacaoCliente = observacaoCliente; }
    public String getObservacaoInterna() { return observacaoInterna; }
    public void setObservacaoInterna(String observacaoInterna) { this.observacaoInterna = observacaoInterna; }
    public List<String> getAnexos() { return Collections.unmodifiableList(anexos); }
    public List<ItemDevolucao> getItens() { return Collections.unmodifiableList(itens); }
    public String getResponsavelAnalise() { return responsavelAnalise; }
    public void setResponsavelAnalise(String responsavelAnalise) { this.responsavelAnalise = responsavelAnalise; }
    public String getJustificativaComercial() { return justificativaComercial; }
    public void setJustificativaComercial(String justificativaComercial) { this.justificativaComercial = justificativaComercial; }
    public String getPrioridade() { return prioridade; }
    public void setPrioridade(String prioridade) { this.prioridade = prioridade; }
    public String getStatusAnalise() { return statusAnalise; }
    public void setStatusAnalise(String statusAnalise) { this.statusAnalise = statusAnalise; }
    public TratamentoDevolucao getTratamentoRecomendado() { return tratamentoRecomendado; }
    public void setTratamentoRecomendado(TratamentoDevolucao tratamentoRecomendado) { this.tratamentoRecomendado = tratamentoRecomendado; }
    public boolean isAprovacaoNecessaria() { return aprovacaoNecessaria; }
    public void setAprovacaoNecessaria(boolean aprovacaoNecessaria) { this.aprovacaoNecessaria = aprovacaoNecessaria; }
    public boolean isAprovada() { return aprovada; }
    public void setAprovada(boolean aprovada) { this.aprovada = aprovada; }
    public String getNumeroOrdemComercial() { return numeroOrdemComercial; }
    public void setNumeroOrdemComercial(String numeroOrdemComercial) { this.numeroOrdemComercial = numeroOrdemComercial; }
    public String getStatusOrdem() { return statusOrdem; }
    public void setStatusOrdem(String statusOrdem) { this.statusOrdem = statusOrdem; }
    public LocalDate getDataGeracaoOrdem() { return dataGeracaoOrdem; }
    public void setDataGeracaoOrdem(LocalDate dataGeracaoOrdem) { this.dataGeracaoOrdem = dataGeracaoOrdem; }
    public List<String> getHistorico() { return Collections.unmodifiableList(historico); }
}
