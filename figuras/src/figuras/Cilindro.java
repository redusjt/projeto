package figuras;

public class Cilindro extends Circulo implements Volumes{

	
	public double altura = 0;
	public Cilindro(double x, double y) {
		super(x);		
		altura = y;
	}

	
	
	@Override
	public double volume() {
		
		return 3.14 * raio*raio * altura;
	}
	
	public String toString()
	{
		return "C�rculo: \n�rea: " + area() + " Per�metro: " + perimetro() + "\nCilindro: Volume " + volume() + "\n";
	}

}
