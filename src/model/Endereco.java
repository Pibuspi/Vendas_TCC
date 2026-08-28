package model;

/**
 * Model: Endereco.java
 * Responsável: Eúde
 * -----------------------------------------------------------------------------
 * Descrição detalhada: Representa um endereço vinculado a um Cliente (cobrança
 * ou entrega). Foi criado como classe separada — e não como campos soltos dentro
 * de Cliente.java — porque um mesmo cliente pode ter mais de um endereço (ex: um de cobrança 
 * e outro de entrega). Se os campos estivessem direto em Cliente, 
 * só seria possível guardar UM endereço por cliente.
 *
 * Como funciona:
 * - Cada objeto Endereco guarda um "tipo" (COBRANCA ou ENTREGA) + os dados
 *   tradicionais de endereço (logradouro, número, bairro, cidade, estado, CEP).
 * - A classe Cliente mantém uma List<Endereco>, permitindo adicionar quantos
 *   endereços forem necessários através do método adicionarEndereco().
 * - O método toString() foi sobrescrito para gerar um texto legível (usado na
 *   JList da tela de cadastro), sem precisar montar a formatação na View.
 *
 * Não é necessário salvar no banco, este objeto vive apenas em
 * memória, dentro da List<Endereco> do Cliente correspondente.
 */

public class Endereco {
    private String tipo; // "COBRANCA" ou "ENTREGA"
    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

    public Endereco() {
    }

    public Endereco(String tipo, String logradouro, String numero, String bairro,
                     String cidade, String estado, String cep) {
        this.tipo = tipo;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getLogradouro() { return logradouro; }
    public void setLogradouro(String logradouro) { this.logradouro = logradouro; }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }
    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    @Override
    public String toString() {
        return tipo + ": " + logradouro + ", " + numero + " - " + bairro
                + " - " + cidade + "/" + estado + " CEP " + cep;
    }
}
