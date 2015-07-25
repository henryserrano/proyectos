package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.lang.model.element.VariableElement;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import BL.Cuadratica;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.Toolkit;

import javax.swing.ImageIcon;



import java.awt.Color;

public class Principal extends JFrame {
	Cuadratica variable = new Cuadratica();
	int error = 0;

	private JPanel contentPane;
	private JTextField A;
	private JTextField B;
	private JTextField C;
	private JLabel lblEcuacionCuadratica;
	private JLabel lblNewLabel_1;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public Principal() {
			//imagen para cambiar icono aplicacion por defecto
		Image icon = Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/imagenes/x.png"));
		setIconImage(icon);
		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 477, 223);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("X^2   +");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(112, 78, 46, 14);
		contentPane.add(lblNewLabel);

		A = new JTextField("A");
		A.setBounds(16, 75, 86, 20);
		contentPane.add(A);
		A.setColumns(10);

		JLabel lblX = new JLabel("X     +");
		lblX.setForeground(Color.RED);
		lblX.setBounds(264, 78, 46, 14);
		contentPane.add(lblX);

		B = new JTextField("B");
		B.setColumns(10);
		B.setBounds(168, 75, 86, 20);
		contentPane.add(B);

		C = new JTextField("C");
		C.setColumns(10);
		C.setBounds(320, 75, 86, 20);
		contentPane.add(C);
		
		JButton LimpiarCampos = new JButton("Limpiar");
		LimpiarCampos.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/limpiar.png")));
		LimpiarCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		LimpiarCampos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				A.setText("");
				B.setText("");
				C.setText("");
				
			}
		});
		LimpiarCampos.setBounds(360, 160, 110, 23);
		contentPane.add(LimpiarCampos);

		JButton calcular = new JButton("Ver Resultado");
		calcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					variable.setCoeficienteA(Double.parseDouble(A.getText()));
					variable.setCoeficienteB(Double.parseDouble(B.getText()));
					variable.setCoeficienteC(Double.parseDouble(C.getText()));
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null,
							"Error, ingrese solo números.");
					error = 1;
				}

				if (error == 0) {
					if (variable.getCoeficienteA() == 0
							&& variable.getCoeficienteB() == 0
							&& variable.getCoeficienteC() == 0) {
						JOptionPane.showMessageDialog(null,
								"Error, esto no es una ecuación."); // Error
					} else {
						if (variable.getCoeficienteA() == 0
								&& variable.getCoeficienteB() == 0
								&& variable.getCoeficienteC() != 0) {
							JOptionPane.showMessageDialog(null,
									"Error, es una indeterminacion.");
						} else {
							if (variable.getCoeficienteA() == 0
									&& variable.getCoeficienteB() != 0) {
								if (variable.getCoeficienteC() == 0)
									JOptionPane.showMessageDialog(null,
											"La funcion es lineal y el resultado es: "
													+ variable.evaluarLineal()
													* -1);
								else
									JOptionPane.showMessageDialog(null,
											"La funcion es lineal y el resultado es: "
													+ variable.evaluarLineal());
							} else {
								if (variable.evaluarImaginario() == true) {
									if (variable.getCoeficienteB() == 0) {
										// imprimir las i para el caso que sea
										// imaginario
										JOptionPane
												.showMessageDialog(
														null,
														"X1 = "
																+ variable
																		.resultadoImaginario2()
																+ "i");
										JOptionPane
												.showMessageDialog(
														null,
														"X2 = "
																+ " - "
																+ variable
																		.resultadoImaginario2()
																+ "i");
									} else {
										JOptionPane
												.showMessageDialog(
														null,
														"X1 = "
																+ variable
																		.resultadoImaginario1()
																+ " + "
																+ variable
																		.resultadoImaginario2()
																+ "i");
										JOptionPane
												.showMessageDialog(
														null,
														"X2 = "
																+ variable
																		.resultadoImaginario1()
																+ " - "
																+ variable
																		.resultadoImaginario2()
																+ "i");
									}
								} else {
									JOptionPane.showMessageDialog(null, "X1 = "
											+ variable.resultadoReal1());
									JOptionPane.showMessageDialog(null, "X2 = "
											+ variable.resultadoReal2());
								}
							}
						}
					}
				}
			}
		});
		calcular.setBounds(168, 140, 126, 23);
		contentPane.add(calcular);

		lblEcuacionCuadratica = new JLabel("ECUACIONES DE SEGUNDO GRADO");
		lblEcuacionCuadratica.setIcon(new ImageIcon(Principal.class
				.getResource("/imagenes/tablero.png")));
		lblEcuacionCuadratica.setBounds(62, 0, 361, 48);
		contentPane.add(lblEcuacionCuadratica);

		lblNewLabel_1 = new JLabel("=    0");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(416, 78, 67, 14);
		contentPane.add(lblNewLabel_1);

		
	
	
	}
}
