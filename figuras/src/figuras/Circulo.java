package figuras;

public class Circulo extends Figura{
	
	public double raio = 0;
	
	public Circulo(double x)
	{
		raio = x;		
	}
	
	@Override
	public double area() {
		
		return 3.14 * Math.pow(2, raio);
	}
	
	public double perimetro()
	{
		return 3.14 * raio;
	}

	public String toString()
	{
		return "Círculo: \nÁrea: " + area() + " Perímetro: " + perimetro() + "\n";
	}
}
