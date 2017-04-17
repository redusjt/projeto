package figuras;

public class Esfera extends Circulo implements Volumes
{

	public Esfera(double x) {
		super(x);		
	}

	@Override
	public double volume() {
		
		return 4 * 3.14 * Math.pow(3, raio);
	}
	
	public String toString()
	{
		return "Esfera: Volume: " + volume() + "\n";
	}

}
