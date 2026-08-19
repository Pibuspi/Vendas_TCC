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
public class Cliente {
    private int idCliente;
    private String razaoSocial;
    private String cnpjCpf;
    private String ieRg;
    private String endereco;
    private String cidade;
    private String estado;
    private double limiteCredito;
    private String statusCredito; // "LIBERADO" ou "BLOQUEADO"
}
