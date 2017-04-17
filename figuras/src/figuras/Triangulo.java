package figuras;

public class Triangulo extends Poligno{

	public Triangulo(double x, double y)
	{
		base = x;
		altura = y;
	}
	@Override
	public double area() {
		
		return (base * altura) / 2;
	}

	@Override
	public double perimetro() {
		
		return base + altura  * 2;
	}
	
	public String toString()
	{
		return "Tri�ngulo: \n�rea: " + area() + " Per�metro: " + perimetro() + "\n";
	}

}
