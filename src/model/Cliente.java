package model;

/**
 * Classe Model: Cliente
 * -----------------------------------------------------------------------------
 * Descrição: Representa a entidade Cliente no sistema ERP. Contém os atributos
 * essenciais para cadastro, validação de CNPJ/CPF, controle de limite de crédito
 * e status financeiro (Hard Stop para inadimplência).
 * 
 * Implementar por: Equipe de Desenvolvimento (Módulo de Cadastro)
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

    // Construtores, Getters e Setters devem ser implementados aqui.
}
