package controller;

import java.util.ArrayList;
import java.util.List;
import model.Cliente;

public class ClienteController {

    public List<String> validarCliente(Cliente cliente) {
        List<String> erros = new ArrayList<>();

        if (cliente == null) {
            erros.add("Cliente não informado.");
            return erros;
        }

        if (!validarFormatoDocumento(cliente.getCpfCnpj())) {
            erros.add("CPF/CNPJ ausente ou em formato inválido (informe 11 ou 14 dígitos).");
        }

        if (cliente.getRazaoSocial() == null || cliente.getRazaoSocial().trim().isEmpty()) {
            erros.add("Razão social é obrigatória.");
        }

        if (cliente.getLimiteCredito() < 0) {
            erros.add("Limite de crédito não pode ser negativo.");
        }

        if (cliente.getCondicaoPagamento() == null || cliente.getCondicaoPagamento().trim().isEmpty()) {
            erros.add("Condição de pagamento é obrigatória.");
        }

        if (cliente.getTabelaPrecoVinculada() == null || cliente.getTabelaPrecoVinculada().trim().isEmpty()) {
            erros.add("É necessário vincular uma tabela de preço para ativar o cliente.");
        }

        return erros;
    }

    public boolean validarFormatoDocumento(String documento) {
        if (documento == null) return false;
        String somenteDigitos = documento.replaceAll("[^0-9]", "");
        return somenteDigitos.length() == 11 || somenteDigitos.length() == 14;
    }

    // Apenas SIMULA a regra fiscal, sem tocar no módulo Fiscal (proibido pelo enunciado)
    public void simularRegraFiscal(Cliente cliente) {
        boolean pendente = (cliente.getInscricaoEstadual() == null
                || cliente.getInscricaoEstadual().trim().isEmpty());
        cliente.setRegraFiscalPendente(pendente);
    }

    public String ativarCliente(Cliente cliente) {
        List<String> erros = validarCliente(cliente);
        simularRegraFiscal(cliente);

        if (!erros.isEmpty()) {
            cliente.setStatus("PENDENTE");
            return "PENDENTE";
        }
        if (cliente.isBloqueadoPorInadimplencia()) {
            cliente.setStatus("BLOQUEADO");
            return "BLOQUEADO";
        }
        cliente.setStatus("ATIVO");
        return "ATIVO";
    }
}
