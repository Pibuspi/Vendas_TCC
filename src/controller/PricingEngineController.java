package controller;

import model.PedidoVenda;

/**
 * Classe Controller: PricingEngineController
 * -----------------------------------------------------------------------------
 * Descrição: Implementa o motor de preços (*Pricing Engine*). Valida em tempo
 * real se a margem de lucro do pedido está acima do mínimo permitido. Caso
 * contrário, aciona as alçadas comerciais ou aplica *Hard Stop*.
 * 
 * Implementar por: Equipe de Lógica Comercial e Regras de Negócio
 */
public class PricingEngineController {

    public boolean validarMargemLucro(PedidoVenda pedido) {
        // Lógica de cálculo de custo vs preço praticado.
        return true;
    }

    public void aplicarAlcadaComercial(PedidoVenda pedido) {
        // Encaminha para aprovação de gerência caso o desconto seja excessivo.
    }
}
