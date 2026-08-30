package model;

public class pagamento{

    private String nome;
    private String cpf;
    private String ddd;
    private String telefone;
    private String cep;
    private String endereco;
    private String numero;
    private String uf;

    // 2. Construtor Vazio (Necessário para o DAO listar os dados)
    public pagamento() {
    }

    // 3. Construtor Completo (Para facilitar a criação do objeto pelo Controller)
    public pagamento(String nome, String cpf, String ddd, String telefone, String cep, String endereco, String numero, String uf) {
        this.nome = nome;
        this.cpf = cpf;
        this.ddd = ddd;
        this.telefone = telefone;
        this.cep = cep;
        this.endereco = endereco;
        this.numero = numero;
        this.uf = uf;

        
    }

    // 4. Getters e Setters (As "portas" de entrada e saída de cada informação)
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getDdd() { return ddd; }
    public void setDdd(String ddd) { this.ddd = ddd; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    
    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }
    
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    
    public String getUf() { return uf; }
    public void setUf(String uf) { this.uf = uf; }
   
};