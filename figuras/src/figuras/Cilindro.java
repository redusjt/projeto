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
		return "Círculo: \nÁrea: " + area() + " Perímetro: " + perimetro() + "\nCilindro: Volume " + volume() + "\n";
	}

}
