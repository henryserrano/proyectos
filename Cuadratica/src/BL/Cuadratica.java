package BL;

public class Cuadratica {
	
	//Atributos
	private double coeficienteA;
	private double coeficienteB;
	private double coeficienteC;
	
	//Constructor
	public Cuadratica(){
	}

	//Metodos GET y SET
	public double getCoeficienteA() {
		return coeficienteA;
	}

	public void setCoeficienteA(double coeficienteA) {
		this.coeficienteA = coeficienteA;
	}

	public double getCoeficienteB() {
		return coeficienteB;
	}

	public void setCoeficienteB(double coeficienteB) {
		this.coeficienteB = coeficienteB;
	}

	public double getCoeficienteC() {
		return coeficienteC;
	}

	public void setCoeficienteC(double coeficienteC) {
		this.coeficienteC = coeficienteC;
	}
	
	
	//Metodos
	 
	 public boolean evaluarImaginario(){ // Resulatdos Imagianarios True
		 double evaluar =  Math.pow(coeficienteB, 2) - 4*coeficienteA*coeficienteC;
		 if(evaluar < 0)
			 return true;
		 else
			 return false;
	 }
	 
	 public double evaluarLineal(){ //Funcion Lineal True
		return -coeficienteC/coeficienteB;
	 }
	 
	 public double resultadoReal1(){
		return (-coeficienteB + Math.sqrt(Math.pow(coeficienteB, 2) - 4*coeficienteA*coeficienteC))/(2*coeficienteA);
			 
	 }
	 
	 public double resultadoReal2(){
			 return (-coeficienteB - Math.sqrt(Math.pow(coeficienteB, 2) - 4*coeficienteA*coeficienteC))/(2*coeficienteA);
			 
	 }
		
	 public double resultadoImaginario1(){
		 return -coeficienteB/(2*coeficienteA);
	 }
	 public double resultadoImaginario2(){
		 double evaluar =  Math.pow(coeficienteB, 2) - 4*coeficienteA*coeficienteC;
		 evaluar = evaluar *-1;
		 return Math.sqrt(evaluar)/(2*coeficienteA);
	 }

}
