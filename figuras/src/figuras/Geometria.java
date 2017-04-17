package figuras;

import java.util.ArrayList;

public class Geometria {
	public static void main(String[] args)
	{
	
	ArrayList<Figura> figuras = new ArrayList<>();	
	
	figuras.add(new Cilindro(2, 5));
	figuras.add(new Esfera(2));
	figuras.add(new Piramide(3, 5));
	figuras.add(new Cubo(5, 5, 5));
	figuras.add(new Retangulo(2, 1));
	figuras.add(new Losango(3, 1));
	figuras.add(new Trapezio(3, 1));
	
	for(Figura fig:figuras)
	{
		fig.area();
		fig.perimetro();
		System.out.println(fig.toString());
	}
	
	
	
	
	}
}
