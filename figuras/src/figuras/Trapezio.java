package figuras;

public class Trapezio extends Poligno{

	public Trapezio(double x, double y)
	{
		base = x;
		altura = y;
	}
	
	@Override
	public double area() {
		
		return ((base+base) * altura) / 2;
	}

	@Override
	public double perimetro() {
		
		return 0;
	}

	public String toString()
	{
		return "Trap�zio: \n�rea: " + area() + " Per�metro: " + perimetro() + "\n";
	}
}
