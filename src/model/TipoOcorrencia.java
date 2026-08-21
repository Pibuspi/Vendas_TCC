package model;

/**
 * Model: TipoOcorrencia.java
 * Responsável: Pietro
 * -----------------------------------------------------------------------------
 * Define os tipos de ocorrência que podem ser registrados na tela de Gestão de
 * Devoluções e Críticas de Vendas. A enumeração restringe a escolha do usuário
 * a opções comerciais válidas e evita classificações inconsistentes.
 */
public enum TipoOcorrencia {
    DEVOLUCAO,
    TROCA,
    CRITICA_DE_VENDA
}
