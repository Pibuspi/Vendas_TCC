package controller;

import java.time.LocalDate;

import model.Devolucao;
import model.TratamentoDevolucao;

/**
 * Controller: DevolucaoController.java
 * Responsável: Pietro
 * -----------------------------------------------------------------------------
 * Controla exclusivamente as decisões comerciais das devoluções: validação de
 * solicitação, análise da política, necessidade de aprovação e geração da Ordem
 * de Devolução Comercial. A emissão fiscal e o crédito financeiro são apenas
 * sinalizados para os módulos externos responsáveis, sem execução nesta classe.
 */
public class DevolucaoController {

    public void validarSolicitacao(Devolucao devolucao) {
        if (devolucao == null) {
            throw new IllegalArgumentException("A ocorrência de devolução é obrigatória.");
        }
        if (!devolucao.possuiPedidoOriginal()) {
            throw new IllegalStateException("Hard Stop: informe o pedido original da venda.");
        }
        if (!devolucao.possuiMotivoDefinido()) {
            throw new IllegalStateException("Hard Stop: informe o motivo da devolução ou crítica.");
        }
        if (!devolucao.possuiItens()) {
            throw new IllegalStateException("Hard Stop: inclua ao menos um item na devolução.");
        }
        if (!devolucao.estaDentroDoPrazo()) {
            throw new IllegalStateException("Hard Stop: solicitação fora do prazo comercial de "
                    + Devolucao.PRAZO_PADRAO_DIAS + " dias.");
        }
    }

    public void iniciarAnaliseComercial(Devolucao devolucao, String responsavel,
                                        String justificativa, String prioridade,
                                        TratamentoDevolucao tratamento) {
        validarSolicitacao(devolucao);
        if (responsavel == null || responsavel.isBlank()) {
            throw new IllegalArgumentException("Informe o responsável pela análise comercial.");
        }
        if (justificativa == null || justificativa.isBlank()) {
            throw new IllegalArgumentException("Informe a justificativa comercial.");
        }
        if (tratamento == null) {
            throw new IllegalArgumentException("Selecione o tratamento recomendado.");
        }

        devolucao.setResponsavelAnalise(responsavel.trim());
        devolucao.setJustificativaComercial(justificativa.trim());
        devolucao.setPrioridade(prioridade == null || prioridade.isBlank() ? "Normal" : prioridade);
        devolucao.setTratamentoRecomendado(tratamento);

        boolean excecao = tratamento == TratamentoDevolucao.ABATIMENTO
                || tratamento == TratamentoDevolucao.TROCA;
        devolucao.setAprovacaoNecessaria(excecao);
        devolucao.setStatusAnalise(excecao ? "AGUARDANDO APROVAÇÃO" : "APROVADA COMERCIALMENTE");
        devolucao.setAprovada(!excecao);
        devolucao.adicionarHistorico("Análise comercial registrada por " + responsavel + ".");
    }

    public void aprovarExcecao(Devolucao devolucao, String aprovador) {
        if (devolucao == null || !devolucao.isAprovacaoNecessaria()) {
            throw new IllegalStateException("Não há exceção comercial pendente de aprovação.");
        }
        if (aprovador == null || aprovador.isBlank()) {
            throw new IllegalArgumentException("Informe o aprovador da exceção.");
        }
        devolucao.setAprovada(true);
        devolucao.setStatusAnalise("APROVADA COMERCIALMENTE");
        devolucao.adicionarHistorico("Exceção aprovada por " + aprovador.trim() + ".");
    }

    public String gerarOrdemDevolucaoComercial(Devolucao devolucao) {
        validarSolicitacao(devolucao);
        if (!devolucao.isAprovada()) {
            throw new IllegalStateException("A Ordem Comercial só pode ser gerada após a aprovação.");
        }
        if (devolucao.getNumeroOrdemComercial() == null || devolucao.getNumeroOrdemComercial().isBlank()) {
            String numero = "ODC-" + devolucao.getNumeroOcorrencia() + "-"
                    + LocalDate.now().toString().replace("-", "");
            devolucao.setNumeroOrdemComercial(numero);
            devolucao.setDataGeracaoOrdem(LocalDate.now());
            devolucao.setStatusOrdem("GERADA - AGUARDANDO MÓDULOS EXTERNOS");
            devolucao.adicionarHistorico("Ordem de Devolução Comercial " + numero + " gerada.");
        }
        return devolucao.getNumeroOrdemComercial();
    }

    public void encerrarOcorrencia(Devolucao devolucao) {
        if (devolucao == null || devolucao.getNumeroOrdemComercial() == null) {
            throw new IllegalStateException("Gere a Ordem de Devolução Comercial antes de encerrar.");
        }
        devolucao.setStatusOrdem("ENCERRADA COMERCIALMENTE");
        devolucao.setStatusAnalise("ENCERRADA");
        devolucao.adicionarHistorico("Ocorrência encerrada comercialmente.");
    }
}
