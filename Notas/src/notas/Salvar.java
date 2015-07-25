package notas;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Salvar extends JFrame {

	Estudiante per = new Estudiante();

	public void guardarDatos(String a, long b, double c, double d, double e)
			throws IOException {
		DataOutputStream archivo = null;
		DataInputStream leer = null;
		File confirmar = new File("c:\\Nueva_carpeta\\archivoaleer.txt");
		String nombre = a;
		long id = b;
		archivo = new DataOutputStream(new FileOutputStream("c:\\Nueva_carpeta\\archivoaleer.txt", true));
		leer = new DataInputStream(new FileInputStream("c:\\Nueva_carpeta\\archivoaleer.txt"));
		if(confirmar.exists()==true && confirmar.length()!=0){
			if (nombre.equals(leer.readUTF()) || id == leer.readLong()) {
				JOptionPane.showMessageDialog(null, "*** YA EXISTE UN REGISTRO PARA ESE NOMBRE O ID ***");
			} else {
				try {
					archivo.writeUTF(a);
					archivo.writeLong(b);
					archivo.writeDouble(c);
					archivo.writeDouble(d);
					archivo.writeDouble(e);
					archivo.close();
					JOptionPane.showMessageDialog(null, "Registro ok");
				} catch (FileNotFoundException fnfe) {
					JOptionPane.showMessageDialog(null, "ARCHIVO NO encontrado");
				} catch (IOException ioe) {
					JOptionPane.showMessageDialog(null, " Error");
				}
			}
		}else{
			try {
				archivo.writeUTF(a);
				archivo.writeLong(b);
				archivo.writeDouble(c);
				archivo.writeDouble(d);
				archivo.writeDouble(e);
				archivo.close();
				JOptionPane.showMessageDialog(null, "REGISTRO EXITOSO");
			} catch (FileNotFoundException fnfe) {
				JOptionPane.showMessageDialog(null, "ARCHIVO NO ENCONTRADO");
			} catch (IOException ioe) {
				JOptionPane.showMessageDialog(null, "ERROR AL ESCRIBIR");
			}
		}
	}
}
