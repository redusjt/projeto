package figuras;

public class Quadrado extends Poligno implements Diagonal{
	
	public Quadrado	(double x, double y)
	{
		base = x;
		altura = y;
	}
	
	@Override
	public double area() {
		
		return base * altura;
	}

	@Override
	public double perimetro() {
		
		return base * 2 + altura * 2;
	}
	
	public String toString()
	{
		return "Quadrado: \nÁrea: " + area() + " Perímetro: " + perimetro() + " Diagonal: "+ diagonal(base, altura)+ "\n";
	}

	

}
