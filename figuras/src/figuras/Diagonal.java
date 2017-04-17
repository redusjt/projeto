package figuras;

public interface Diagonal {
	
	
	
	default double diagonal(double lado1, double lado2)
	{
		return Math.sqrt(Math.pow(2, lado1) + Math.pow(2, lado2));
	}
	
}
