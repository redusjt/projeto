package figuras;

public class Losango extends Poligno{

	public Losango(double x, double y)
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
		
		return 4 * base;
	}
	
	public String toString()
	{
		return "Losango: \nÁrea: " + area() + " Perímetro: " + perimetro() + "\n";
	}

}
