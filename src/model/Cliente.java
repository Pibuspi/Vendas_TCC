package model;

/**
 * Model: Cliente.java
 * Responsável: Eúde
 * -----------------------------------------------------------------------------
 * Descrição detalhada: Representa a entidade de negócio Cliente no sistema ERP.
 * Contém os atributos fundamentais para a identificação, validação de CNPJ/CPF,
 * controle de limite de crédito e aplicação de Hard Stop em caso de inadimplência.
 * 
 * O que fazer em Model (Eúde):
 * - Criar atributos privados (idCliente, razaoSocial, cnpjCpf, limiteCredito, statusCredito).
 * - Implementar construtores, Getters e Setters.
 * - Adicionar métodos de validação de formatação de documentos.
 */

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private int idCliente;
    private String cpfCnpj;
    private String razaoSocial;
    private String nomeFantasia;
    private String inscricaoEstadual;
    private String inscricaoMunicipal;
    private String status;               // "ATIVO", "BLOQUEADO" ou "PENDENTE"
    private String canalVenda;           // ex: BALCAO, ATACADO, ECOMMERCE
    private String segmentacao;          // ex: VAREJO, ATACADO, INDUSTRIA
    private String vendedorResponsavel;
    private String condicaoPagamento;
    private String tabelaPrecoVinculada;
    private double limiteCredito;
    private double alcadaDesconto;
    private boolean bloqueadoPorInadimplencia;
    private boolean regraFiscalPendente;
    private List<Endereco> enderecos;

    public Cliente() {
        this.enderecos = new ArrayList<>();
        this.status = "PENDENTE";
    }

    public Cliente(String cpfCnpj, String razaoSocial, String nomeFantasia,
                    String condicaoPagamento, double limiteCredito) {
        this();
        this.cpfCnpj = cpfCnpj;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.condicaoPagamento = condicaoPagamento;
        this.limiteCredito = limiteCredito;
    }

    public void adicionarEndereco(Endereco endereco) {
        this.enderecos.add(endereco);
    }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    
    public String getCpfCnpj() { return cpfCnpj; }
    public void setCpfCnpj(String cpfCnpj) { this.cpfCnpj = cpfCnpj; }
    
    public String getRazaoSocial() { return razaoSocial; }
    public void setRazaoSocial(String razaoSocial) { this.razaoSocial = razaoSocial; }
    
    public String getNomeFantasia() { return nomeFantasia; }
    public void setNomeFantasia(String nomeFantasia) { this.nomeFantasia = nomeFantasia; }
    
    public String getInscricaoEstadual() { return inscricaoEstadual; }
    public void setInscricaoEstadual(String inscricaoEstadual) { this.inscricaoEstadual = inscricaoEstadual; }
    
    public String getInscricaoMunicipal() { return inscricaoMunicipal; }
    public void setInscricaoMunicipal(String inscricaoMunicipal) { this.inscricaoMunicipal = inscricaoMunicipal; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getCanalVenda() { return canalVenda; }
    public void setCanalVenda(String canalVenda) { this.canalVenda = canalVenda; }
    
    public String getSegmentacao() { return segmentacao; }
    public void setSegmentacao(String segmentacao) { this.segmentacao = segmentacao; }
    
    public String getVendedorResponsavel() { return vendedorResponsavel; }
    public void setVendedorResponsavel(String vendedorResponsavel) { this.vendedorResponsavel = vendedorResponsavel; }
    
    public String getCondicaoPagamento() { return condicaoPagamento; }
    public void setCondicaoPagamento(String condicaoPagamento) { this.condicaoPagamento = condicaoPagamento; }
    
    public String getTabelaPrecoVinculada() { return tabelaPrecoVinculada; }
    public void setTabelaPrecoVinculada(String tabelaPrecoVinculada) { this.tabelaPrecoVinculada = tabelaPrecoVinculada; }
    
    public double getLimiteCredito() { return limiteCredito; }
    public void setLimiteCredito(double limiteCredito) { this.limiteCredito = limiteCredito; }
    
    public double getAlcadaDesconto() { return alcadaDesconto; }
    public void setAlcadaDesconto(double alcadaDesconto) { this.alcadaDesconto = alcadaDesconto; }
    
    public boolean isBloqueadoPorInadimplencia() { return bloqueadoPorInadimplencia; }
    public void setBloqueadoPorInadimplencia(boolean bloqueadoPorInadimplencia) { this.bloqueadoPorInadimplencia = bloqueadoPorInadimplencia; }
    
    public boolean isRegraFiscalPendente() { return regraFiscalPendente; }
    public void setRegraFiscalPendente(boolean regraFiscalPendente) { this.regraFiscalPendente = regraFiscalPendente; }
    
    public List<Endereco> getEnderecos() { return enderecos; }
    public void setEnderecos(List<Endereco> enderecos) { this.enderecos = enderecos; }
}
