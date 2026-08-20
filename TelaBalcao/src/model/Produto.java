package model;

public class Produto {

    private int codigo;
    private String produto;
    private int quantidade;
    private int vendas;
    private float preco;

    // 2. Construtor Vazio (Necessário para o DAO listar os dados)
    public Produto() {
    }

    // 3. Construtor Completo (Para facilitar a criação do objeto pelo Controller)
    public Produto(int codigo, String produto, int quantidade, int vendas, float preco) {
        this.codigo = codigo;
        this.produto = produto;
        this.quantidade = quantidade;
        this.vendas = vendas;
        this.preco = preco;
    }

    // 4. Getters e Setters (As "portas" de entrada e saída de cada informação)
    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }

    public String getProduto() { return produto; }
    public void setProduto(String produto) { this.produto = produto; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public int getVendas() { return vendas; }
    public void setVendas(int vendas) { this.vendas = vendas; }

    
    public float getPreco() { return preco; }
    public void setPreco(float preco) { this.preco = preco; }
   
};