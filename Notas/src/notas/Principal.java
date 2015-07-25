package notas;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.print.DocFlavor.URL;
import javax.swing.AbstractButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import java.awt.Font;

import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

import java.awt.Image;
import java.awt.Canvas;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	private JPanel contentPane;
	private JTextField NomAlum;
	private JTextField IDAlumno;
	private JTextField Parcial1;
	private JTextField Parcial2;
	private JTextField ExamenFinal;
	private JTextArea areaTexto;

	Estudiante per = new Estudiante();
	CalculoNota cal = new CalculoNota();
	Salvar grd = new Salvar();
	Buscar bus = new Buscar();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Principal() throws IOException {
		setResizable(false);
		setTitle("Programa C\u00E1lculo de Notas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 649, 533);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblInfoProg = new JLabel(
				"Digite la informaci\u00F3n solicitada.");

		JLabel lblNomAlum = new JLabel("Alumno:");
		lblNomAlum.setIgnoreRepaint(true);
		Image icon = Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/imagenes/estudiante.png"));
		setIconImage(icon);
		setVisible(true);

		NomAlum = new JTextField();
		NomAlum.setColumns(10);

		JLabel lblIdAlum = new JLabel("ID o Código Alumno:");
		lblIdAlum.setHorizontalAlignment(SwingConstants.RIGHT);

		IDAlumno = new JTextField();
		IDAlumno.setColumns(10);

		JLabel lblParcial1 = new JLabel("Parcial 1:");

		Parcial1 = new JTextField();
		Parcial1.setColumns(10);

		JLabel lblParcialIi = new JLabel("Parcial 2:");

		Parcial2 = new JTextField();
		Parcial2.setColumns(10);

		JLabel lblExamenFinal = new JLabel("Examen Final:");

		ExamenFinal = new JTextField();
		ExamenFinal.setColumns(10);

		JButton IngresaDato = new JButton("Ingresar Datos");
		IngresaDato.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/guardar.png")));
		IngresaDato.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				int error = 0;
				per.setNombreAlumno(NomAlum.getText());
				try {
					if (Long.parseLong(IDAlumno.getText()) > 0) {
						per.setIdAlumno(Long.parseLong(IDAlumno.getText()));
					} else {
						JOptionPane
								.showMessageDialog(null,
										"El valor de COD no es válido. Inténtelo.");
						throw new NumberFormatException();
					}
					if (Double.parseDouble(Parcial1.getText()) >= 0
							&& Double.parseDouble(Parcial1.getText()) <= 5) {
						per.setParcialUno(Double.parseDouble(Parcial1.getText()));
					} else {
						JOptionPane.showMessageDialog(null,
								"Entre 0.0 y 5.0"
										+ "\nInténtelo.");
						throw new NumberFormatException();
					}
					if (Double.parseDouble(Parcial2.getText()) >= 0
							&& Double.parseDouble(Parcial2.getText()) <= 5) {
						per.setParcialDos(Double.parseDouble(Parcial2.getText()));
					} else {
						JOptionPane.showMessageDialog(null,
								" Debe estar entre 0.0 y 5.0"
										+ "\nInténtelo de nuevo.");
						throw new NumberFormatException();
					}
					if (Double.parseDouble(ExamenFinal.getText()) >= 0
							&& Double.parseDouble(ExamenFinal.getText()) <= 5) {
						per.setExamenFinal(Double.parseDouble(ExamenFinal
								.getText()));
					} else {
						JOptionPane.showMessageDialog(null,
								"Debe estar entre 0 y 5.0"
										+ "\nInténtelo otra vez.");
						throw new NumberFormatException();
					}
				} catch (NumberFormatException nfe) {
					JOptionPane
							.showMessageDialog(null,
									"Ingrese numeros. Intente otra vez.");
					error = 1;
				}
				if (error == 0) {
					try {
						grd.guardarDatos(per.getNombreAlumno(),
								per.getIdAlumno(), per.getParcialUno(),
								per.getParcialDos(), per.getExamenFinal());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
		});

		JSeparator separador1 = new JSeparator();

		JButton LimpiarCampos = new JButton("Limpiar Campos");
		LimpiarCampos.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/limpiar.png")));
		LimpiarCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		LimpiarCampos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				NomAlum.setText("");
				IDAlumno.setText("");
				Parcial1.setText("");
				Parcial2.setText("");
				ExamenFinal.setText("");
				areaTexto.setText("");
			}
		});
		JButton btnVerifDatos = new JButton("Verificar Datos ");
		btnVerifDatos.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnVerifDatos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				areaTexto.setText("");
				DataInputStream leer = null;
				String nombre = "";
				long id;
				double par1, par2, efinal;
				double notaFin = 0;
				double notaPromedio = 0;
				int conteo = 0;
				try {
					leer = new DataInputStream(new FileInputStream(
							"c:\\Nueva_carpeta\\archivoaleer.txt"));
					while (true) {
						nombre = leer.readUTF();
						per.setNombreAlumno(nombre);
						id = leer.readLong();
						per.setIdAlumno(id);
						par1 = leer.readDouble();
						per.setParcialUno(par1);
						par2 = leer.readDouble();
						per.setParcialDos(par2);
						efinal = leer.readDouble();
						per.setExamenFinal(efinal);
						notaFin = (cal.calcularFinal(per.getParcialUno(),
								per.getParcialDos(), per.getExamenFinal()));
						areaTexto.append("Nombre: "
								+ per.getNombreAlumno()
								+ ", COD: "
								+ per.getIdAlumno()
								+ ", Nota 1: "
								+ per.getParcialUno()
								+ ", Nota 2: "
								+ per.getParcialDos()
								+ ""
								+ "\nExamen Final: "
								+ per.getExamenFinal()
								+ "\nNOTA FINAL DEL ALUMNO: "
								+ notaFin
								+ "\n*****************************************\n");
						notaPromedio += notaFin;
						conteo++;
					}
				} catch (FileNotFoundException fnfe) { /* Archivo no encontrado */
				} catch (IOException ioe) { /* Error al escribir */
				}

				JOptionPane.showMessageDialog(null, "NOTA PROMEDIO DEL CURSO: "
						+ cal.calcularPromedio(notaPromedio, conteo));

			}

		});

		JScrollPane scrollPane = new JScrollPane();

		JButton botonBuscarNombre = new JButton("Buscar Nombre");
		botonBuscarNombre.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				DataInputStream buscar = null;
				String nombre = "";
				long id;
				double par1, par2, efinal;
				double notaFin = 0;
				int temporal = 0;
				String cadena = JOptionPane.showInputDialog(this,
						"Introduzca NOMBRE a buscar");
				areaTexto.setText("");
				try {

					buscar = new DataInputStream(new FileInputStream(
							"c:\\Nueva_carpeta\\archivoaleer.txt"));

					while (true) {
						nombre = buscar.readUTF();
						id = buscar.readLong();
						par1 = buscar.readDouble();
						par2 = buscar.readDouble();
						efinal = buscar.readDouble();
						notaFin = (cal.calcularFinal(par1, par2, efinal));
						if (nombre.contains(cadena)) {
							areaTexto
									.append("ALUMNOS :"
											+ "\nNombre: "
											+ nombre
											+ ", COD: "
											+ id
											+ ", Nota 1: "
											+ par1
											+ ", Nota 2: "
											+ par2
											+ ""
											+ "\nExamen Final: "
											+ efinal
											+ "\nNOTA FINAL ALUMNO: "
											+ notaFin
											+ "\n____________________________________\n");
							temporal = temporal + 1;
						}
					}

				} catch (FileNotFoundException fnfe) { /* Archivo no encontrado */
				} catch (IOException ioe) { /* Error al escribir */
				}

				if (temporal == 0) {
					JOptionPane
							.showMessageDialog(null, "El registro no existe");
					areaTexto.setText("El registro no existe ");
				}

			}
		});
		botonBuscarNombre.setFont(new Font("Tahoma", Font.PLAIN, 10));

		JButton botonBuscarId = new JButton("Busca Código");
		botonBuscarId.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String cadena = JOptionPane.showInputDialog(this,
						"Cod a buscar...");
				try {
					areaTexto.setText("");
					areaTexto.append(bus.busqId(Long.parseLong(cadena)));
				} catch (IOException f) {
					f.printStackTrace();
				}
			}
		});
		botonBuscarId.setFont(new Font("Tahoma", Font.PLAIN, 10));

		JButton botonFiltrarMayor = new JButton("Filtrar Mayores");
		botonFiltrarMayor.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				String nombre = "";
				long id;
				double par1, par2, efinal;
				double notaFin = 0;
				try {
					double cadena = Double.parseDouble(JOptionPane
							.showInputDialog(this,
									"NOTA para filtrar las mayores"));
					if (cadena < 0 || cadena > 5) {
						JOptionPane.showMessageDialog(null,
								"FUERA DE RANGO ***");
					} else {
						areaTexto.setText("");
						areaTexto.setText("LOS ALUMNOS CON NOTA MAYOR A "
								+ cadena + " SON: \n");
						DataInputStream filtrar = null;
						try {
							filtrar = new DataInputStream(new FileInputStream(
									"c:\\Nueva_carpeta\\archivoaleer.txt"));
							while (true) {
								nombre = filtrar.readUTF();
								id = filtrar.readLong();
								par1 = filtrar.readDouble();
								par2 = filtrar.readDouble();
								efinal = filtrar.readDouble();
								notaFin = cal.calcularFinal(par1, par2, efinal);
								if (par1 > cadena || par2 > cadena
										|| efinal > cadena) {
									areaTexto
											.append("Nombre: "
													+ nombre
													+ ", ID: "
													+ id
													+ ", Nota 1: "
													+ par1
													+ ", Nota 2: "
													+ par2
													+ ""
													+ "\nExamen Final: "
													+ efinal
													+ "\nNOTA FINAL ALUMNO: "
													+ notaFin
													+ "\n______________________________________\n");
								} else {
									JOptionPane
											.showMessageDialog(null,
													"*** NO EXISTEN NOTAS MAYORES A LA INGRESADA ***");
								}
							}
						} catch (FileNotFoundException fnfe) { /*
																 * Archivo no
																 * encontrado
																 */
						} catch (IOException ioe) { /* Error al escribir */
						}

					}
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null,
							"*** INGRESE SOLO VALORES NUMÉRICOS ***");
				}
			}
		});
		botonFiltrarMayor.setFont(new Font("Tahoma", Font.PLAIN, 10));

		JButton botonFiltrarMenor = new JButton("Filtrar Menores");
		botonFiltrarMenor.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				String nombre = "";
				long id;
				double par1, par2, efinal;
				double notaFin = 0;
				int temporal = 0;
				try {
					double cadena = Double.parseDouble(JOptionPane
							.showInputDialog(this,
									"Introduzca NOTA para filtrar las menores..."));
					if (cadena < 0 || cadena > 5) {
						JOptionPane.showMessageDialog(null,
								"*** VALOR PARA FILTRAR FUERA DE RANGO ***");
					} else {
						areaTexto.setText("");
						areaTexto.setText("LOS ALUMNOS CON NOTA MENOR A "
								+ cadena + " SON: \n");
						DataInputStream filtrar = null;
						try {
							filtrar = new DataInputStream(new FileInputStream(
									"c:\\Nueva_carpeta\\archivoaleer.txt"));
							while (true) {
								nombre = filtrar.readUTF();
								id = filtrar.readLong();
								par1 = filtrar.readDouble();
								par2 = filtrar.readDouble();
								efinal = filtrar.readDouble();
								notaFin = cal.calcularFinal(par1, par2, efinal);
								if (par1 < cadena || par2 < cadena
										|| efinal < cadena) {
									areaTexto
											.append("Nombre: "
													+ nombre
													+ ", ID: "
													+ id
													+ ", Nota I: "
													+ par1
													+ ", Nota II: "
													+ par2
													+ ""
													+ "\nExamen Final: "
													+ efinal
													+ "\nNOTA FINAL DEL ALUMNO: "
													+ notaFin
													+ "\n__________________________________\n");
									temporal = temporal + 1;
								}
							}
						} catch (FileNotFoundException fnfe) { /*
																 * Archivo no
																 * encontrado
																 */
						} catch (IOException ioe) { /* Error al escribir */
						}

						if (temporal == 0) {
							JOptionPane
									.showMessageDialog(null,
											"*** NO existen notas menores ");
						}

					}
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null,
							"Valores numeros");
				}
			}
		});
		botonFiltrarMenor.setFont(new Font("Tahoma", Font.PLAIN, 10));

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblInfoProg)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblNomAlum)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(NomAlum, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(lblIdAlum, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
											.addGap(4)
											.addComponent(IDAlumno, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.RELATED, 89, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblParcial1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(Parcial1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblParcialIi, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(Parcial2, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblExamenFinal)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(ExamenFinal, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(IngresaDato, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
										.addComponent(LimpiarCampos, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE))
									.addGap(20)))
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnVerifDatos)
							.addGap(2)
							.addComponent(botonBuscarNombre)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(botonBuscarId)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(botonFiltrarMayor, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(botonFiltrarMenor, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addComponent(separador1, GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblInfoProg)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNomAlum)
						.addComponent(NomAlum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblIdAlum)
						.addComponent(IDAlumno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(IngresaDato, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
							.addComponent(LimpiarCampos)
							.addGap(11))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblParcial1)
								.addComponent(Parcial1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblParcialIi)
								.addComponent(Parcial2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblExamenFinal)
								.addComponent(ExamenFinal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(27)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separador1, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnVerifDatos)
						.addComponent(botonBuscarNombre, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(botonBuscarId, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(botonFiltrarMayor, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(botonFiltrarMenor, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 292, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(21, Short.MAX_VALUE))
		);
		areaTexto = new JTextArea();
		scrollPane.setViewportView(areaTexto);
		areaTexto.setWrapStyleWord(true);
		areaTexto.setFont(new Font("Monospaced", Font.PLAIN, 11));
		contentPane.setLayout(gl_contentPane);
	}
}
