package notas;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Estudiante {
	
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
	private String nombreAlumno= new String("");
	private long IdAlumno;
	private double ParcialUno;
	private double ParcialDos;
	private double ExamenFinal;
	
	public String getNombreAlumno() {
		return nombreAlumno;
	}
	public void setNombreAlumno(String nombreAlumno) {
		this.nombreAlumno = nombreAlumno;
	}
	public long getIdAlumno() {
		return IdAlumno;
	}
	public void setIdAlumno(long idAlumno) {
		IdAlumno = idAlumno;
	}
	public double getParcialUno() {
		return ParcialUno;
	}
	public void setParcialUno(double parcialUno) {
		ParcialUno = parcialUno;
	}
	public double getParcialDos() {
		return ParcialDos;
	}
	public void setParcialDos(double parcialDos) {
		ParcialDos = parcialDos;
	}
	public double getExamenFinal() {
		return ExamenFinal;
	}
	public void setExamenFinal(double examenFinal) {
		ExamenFinal = examenFinal;
	}

}
