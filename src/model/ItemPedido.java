package model;

/**
 * Model: ItemPedido.java
 * Responsável: Rafael
 * -----------------------------------------------------------------------------
 * Descrição detalhada: Representa cada linha de produto adicionada durante a digitação
 * do pedido. Armazena a quantidade, preço praticado, desconto e subtotal.
 * 
 * O que fazer em Model (Rafael):
 * - Vincular a entidade `Produto`.
 * - Criar lógica de cálculo de subtotal baseada na quantidade e desconto aplicado.
 */
public class ItemPedido {
    private int idItem;
    private Produto produto;
    private int quantidade;
    private double precoPraticado;
    private double descontoAplicado;
    private double subtotal;
}

    // Construtor 
    public ItemPedido(int idItem, Produto produto, int quantidade, 
    double precoPraticado, double descontoAplicado) { 
  
        this.idItem = idItem; 
        this.produto = produto; 
        this.quantidade = quantidade; 
        this.precoPraticado = precoPraticado; 
        this.descontoAplicado = descontoAplicado; 
        calcularSubtotal(); 
    } 
 
    // Calcula subtotal
    public void calcularSubtotal() { 
        double valorBruto = quantidade * precoPraticado; 
        double valorDesconto = valorBruto * (descontoAplicado / 100); 
        this.subtotal = valorBruto - valorDesconto; 
    } 
 
    // Getters e Setters 
 
    public int getIdItem() { 
    return idItem; 
    } 
    public void setIdItem(int idItem) { 
    this.idItem = idItem; 
    } 
    public Produto getProduto() { 
    return produto; 
    } 
    public void setProduto(Produto produto) { 
    this.produto = produto; 
    } 
    public int getQuantidade() { 
    return quantidade; 
    } 
    public void setQuantidade(int quantidade) { 
    this.quantidade = quantidade; 
    calcularSubtotal(); 
    } 
    public double getPrecoPraticado() { 
    return precoPraticado; 
    } 
    public void setPrecoPraticado(double precoPraticado) { 
    this.precoPraticado = precoPraticado; 
    calcularSubtotal(); 
    } 
    public double getDescontoAplicado() { 
    return descontoAplicado; 
    } 
    public void setDescontoAplicado(double descontoAplicado) { 
    this.descontoAplicado = descontoAplicado; 
    calcularSubtotal(); 
    } 
    public double getSubtotal() { 
    return subtotal; 
    } 
}
