package model;

public class RegraPreco {
    private String sku;
    private double custo;
    private double precoMinimo;
    private double margemMinima;
    private double descontoMaximo;

    public RegraPreco(String sku, double custo, double precoMinimo, double margemMinima, double descontoMaximo) {
        this.sku = sku;
        this.custo = custo;
        this.precoMinimo = precoMinimo;
        this.margemMinima = margemMinima;
        this.descontoMaximo = descontoMaximo;
    }

    // Getters e Setters
    public String getsku() { return sku; }
    public void setsku(String sku) { this.sku = sku; }
    
    public double getCusto() { return custo; }
    public void setCusto(double custo) { this.custo = custo; }

    public double getPrecoMinimo() { return precoMinimo; }
    public void setPrecoMinimo(double precoMinimo) { this.precoMinimo = precoMinimo; }

    public double getMargemMinima() { return margemMinima; }
    public void setMargemMinima(double margemMinima) { this.margemMinima = margemMinima; }

    public double getDescontoMaximo() { return descontoMaximo; }
    public void setDescontoMaximo(double descontoMaximo) { this.descontoMaximo = descontoMaximo; }
}