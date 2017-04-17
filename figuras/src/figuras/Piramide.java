package figuras;

public class Piramide extends Triangulo implements Volumes{

	
	public Piramide(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double volume() {
		
		return (area() * altura) / 3;
	}
	
	public String toString()
	{
		return "Tri�ngulo: \n�rea: " + area() + " Per�metro: " + perimetro() + "\nPir�mide: Volume: " + volume() + "\n";
	}

}
