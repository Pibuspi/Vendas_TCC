package controller;

import model.RegraPreco;
import model.ResultadoPrecificacao;

public class PricingEngineController {

    public ResultadoPrecificacao validarMargemLucro(RegraPreco regra, double precoPraticado, double descontoAplicado) {
        if (precoPraticado <= 0) {
            return new ResultadoPrecificacao(0.0, "BLOQ", "Preço de venda inválido.");
        }

        // Cálculo da margem real
        double margemReal = ((precoPraticado - regra.getCusto()) / precoPraticado) * 100;

        // Validação de margem abaixo do mínimo ou negativa
        if (margemReal < regra.getMargemMinima() || precoPraticado < regra.getPrecoMinimo()) {
            return new ResultadoPrecificacao(margemReal, "HARD_STOP", "HARD STOP: Margem ou preço abaixo do mínimo permitido.");
        }

        // Regras de Alçada por faixa de desconto[cite: 1]
        if (descontoAplicado <= 5.0) {
            return new ResultadoPrecificacao(margemReal, "LIBERADO", "Desconto dentro da faixa permitida. Liberação automática[cite: 1].");
        } else if (descontoAplicado > 5.0 && descontoAplicado <= 10.0) {
            return new ResultadoPrecificacao(margemReal, "PENDENTE", "Desconto entre 5% e 10%. Necessária aprovação do Gerente[cite: 1].");
        } else {
            return new ResultadoPrecificacao(margemReal, "HARD_STOP", "HARD STOP: Desconto acima de 10% exige supervisor[cite: 1].");
        }
    }
}