package controller;

import model.Devolucao;

/**
 * Controller: DevolucaoController.java
 * Responsável: Pietro
 * -----------------------------------------------------------------------------
 * Descrição detalhada: Controla as regras de negócio para devoluções de mercadorias
 * e críticas de vendas, processando estornos e gerando notas de crédito.
 * 
 * O que fazer em Controller (Pietro):
 * - Validar prazos e condições para devolução.
 * - Acionar `DevolucaoDAO` para persistir o estorno e atualizar o estoque.
 */
public class DevolucaoController {
    public void registrarDevolucao(Devolucao devolucao) {}
}
