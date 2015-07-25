package notas;

import java.io.IOException;

public class CalculoNota {
	
	public double calcularFinal(double a, double b, double c){
		double notaFin=0;
		double nota1=(a);
		double nota2=(b);
		double nota3=(c);
		notaFin=(double) Math
				.round((((nota1 * 30) / 100) + ((nota2 * 30) / 100) + ((nota3 * 40) / 100)) * 10) / 10;
		return notaFin;
	}
	
	public double calcularPromedio(double a, double b){
		double promedio=0;
		promedio=(double)Math.round((a/b)*10)/10;
		return promedio;
	}

}
