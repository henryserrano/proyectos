package notas;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Buscar extends JFrame {
	
	DataInputStream buscar = null;
	String nombre="";
	long id;
	double par1, par2, efinal, notaFin;
	int temporal=0;
	CalculoNota cal = new CalculoNota();
	
	public String busqId(long a) throws IOException{
		long entrada=a;
		String devolver="";
		try{
			
			buscar = new DataInputStream(new FileInputStream("c:\\Nueva_carpeta\\archivoaleer.txt"));
			
			while(true){
				nombre = buscar.readUTF();
				id = buscar.readLong();
				par1=buscar.readDouble();
				par2=buscar.readDouble();
				efinal=buscar.readDouble();
				notaFin=(cal.calcularFinal(par1, par2, efinal));
				if(entrada==id){
					devolver="ALUMNOS ENCONTRADOS:"
							+ "\nNombre: "+nombre+", ID: "+id+", Nota I: "+par1+", Nota II: "+par2+""
									+ "\nExamen Final: "+efinal+"\nNOTA FINAL DEL ALUMNO: "+notaFin+"\n*****************************************\n";
					temporal=temporal+1;
				}
			}
			
		}catch(FileNotFoundException fnfe) { /* Archivo no encontrado */ }
		catch (IOException ioe) { /* Error al escribir */ }
		
		if(temporal==0){
			JOptionPane.showMessageDialog(null, "EL REGISTRO NO Esta");
			devolver=" EL REGISTRO NO Esta";
		  }
		
		return devolver;
	}

}
