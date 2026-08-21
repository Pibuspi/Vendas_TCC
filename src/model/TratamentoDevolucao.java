package model;

/**
 * Model: TratamentoDevolucao.java
 * Responsável: Pietro
 * -----------------------------------------------------------------------------
 * Define o encaminhamento decidido na etapa de Análise Comercial. A decisão é
 * comercial; a execução de nota fiscal e crédito permanece para os módulos
 * externos Fiscal e Financeiro.
 */
public enum TratamentoDevolucao {
    REPOSICAO,
    TROCA,
    DEVOLUCAO_INTEGRAL,
    ABATIMENTO
}
