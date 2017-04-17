package figuras;

public class Cubo extends Quadrado implements Volumes{

	public double largura = 0;
	
	public Cubo(double x, double y, double w) {
		super(x, y);		
		largura = w;
	}

	@Override
	public double volume() {
		
		return base * altura * largura;
	}

	public String toString()
	{
		return "Quadrado: \nÁrea: " + area() + " Perímetro: " + perimetro() + " Diagonal: "+ diagonal(base, altura)+ "\nCubo: Volume:" + volume() + "\n";
	}
}
