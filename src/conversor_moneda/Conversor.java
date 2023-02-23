package conversor_moneda;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;

public class Conversor {

	private JFrame frame;
	private JTextField textFieldDinero;
	boolean invertirConversionMoneda = false;
	String dinero;
	String dineroConvertido;
	double dineroDouble;
	double resultadoConversion;
	Double [] conversion = {1.0, 4899.84, 5197.0, 5903.51, 36.3184, 3.76006};
	String [] monedas = {"pesos Colombianos", "Dólar", "Euros", "Libras esterlinas", "Yen Japonés", "Won sul-coreano"};
	String [] simboloMoneda = {"COP", "USD", "EUR", "GBP", "JPY", "KRW"};
	String [] longitud = {"mm", "cm", "dm", "m", "dam", "hm", "km"};
	String unidad;
	Double unidadDouble;
	private JTextField textFieldUnidad;
	//LocalDateTime localDate = LocalDateTime.now();
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Conversor window = new Conversor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Conversor() {
		initialize();
	}

	public void invertir(JComboBox comboBoxDe, JComboBox comboBoxA) {
		comboBoxA.removeAllItems();
		comboBoxDe.removeAllItems();
		if(invertirConversionMoneda == false) {
			comboBoxDe.addItem(monedas[0]);
			for(int i = 1; i <= 5; i++) {
				comboBoxA.addItem(monedas[i]);
			}
		}
		else {
			comboBoxA.addItem(monedas[0]);
			for(int i = 1; i <= 5; i++) {
				comboBoxDe.addItem(monedas[i]);
			}
		}
	}
	
	public void inicializarLongitud(JComboBox unidadBase, JComboBox unidadAConvertir) {
		for(int i = 0; i <= longitud.length-1; i++) {
			unidadBase.addItem(longitud[i]);
			unidadAConvertir.addItem(longitud[i]);
		}
	}
	
	public void calcularLongitud(JComboBox unidadBase, JComboBox unidadAConvertir) {
		unidad = textFieldUnidad.getText();
		unidadDouble = Double.parseDouble(unidad);
		
		int i = unidadBase.getSelectedIndex();
		int j = unidadAConvertir.getSelectedIndex();
		
		if(i<j) {
			for(; i < j; i++) {
				unidadDouble /= 10;
			}
		}
		else if(i>j) {
			for(; i > j; i--) {
				unidadDouble *= 10;
			}
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CardLayout myLayout = new CardLayout();
		frame.getContentPane().setLayout(myLayout);
		
		JPanel panelSeleccion = new JPanel();
		frame.getContentPane().add(panelSeleccion, "panelSeleccion");
		panelSeleccion.setLayout(null);
		
		JLabel lblTitulo = new JLabel("CONVERSOR");
		lblTitulo.setBounds(171, 10, 65, 13);
		panelSeleccion.add(lblTitulo);
		
		JComboBox comboBoxSeleccion = new JComboBox();
		comboBoxSeleccion.setModel(new DefaultComboBoxModel(new String[] {"Conversor de Moneda", "Conversor de Longitud"}));
		comboBoxSeleccion.setBounds(101, 48, 199, 21);
		panelSeleccion.add(comboBoxSeleccion);
		
		
		JButton btnAceptarSeleccion = new JButton("Aceptar");
		btnAceptarSeleccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int indexCombo = comboBoxSeleccion.getSelectedIndex();
				if(indexCombo == 0) {
					myLayout.show(frame.getContentPane(), "panelConversorMoneda" );
				}
				else if(indexCombo == 1) {
					myLayout.show(frame.getContentPane(), "panelConversorHorario" );
				}
				
			}
		});
		btnAceptarSeleccion.setBounds(101, 90, 85, 21);
		panelSeleccion.add(btnAceptarSeleccion);
		
		JButton btnCancelarSeleccion = new JButton("Cancelar");
		btnCancelarSeleccion.setBounds(220, 90, 85, 21);
		panelSeleccion.add(btnCancelarSeleccion);
		
		JPanel panelConversorMoneda = new JPanel();
		frame.getContentPane().add(panelConversorMoneda, "panelConversorMoneda");
		panelConversorMoneda.setLayout(null);
		
		textFieldDinero = new JTextField();
		textFieldDinero.setBounds(164, 56, 96, 19);
		panelConversorMoneda.add(textFieldDinero);
		textFieldDinero.setColumns(10);
		
		JComboBox<String> comboBoxDe = new JComboBox();
		comboBoxDe.setBounds(25, 111, 127, 21);
		panelConversorMoneda.add(comboBoxDe);
		
		JComboBox<String> comboBoxA = new JComboBox();
		comboBoxA.setBounds(272, 111, 116, 21);
		panelConversorMoneda.add(comboBoxA);
		
		invertir(comboBoxDe, comboBoxA);
		
		JButton btnInvertirSeleccion = new JButton("Invertir");
		btnInvertirSeleccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(invertirConversionMoneda == false) {
					invertirConversionMoneda = true;
				}
				else {
					invertirConversionMoneda = false;
				}
				invertir(comboBoxDe, comboBoxA);
			}
		});
		btnInvertirSeleccion.setBounds(164, 111, 85, 21);
		panelConversorMoneda.add(btnInvertirSeleccion);
		


		
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myLayout.show(frame.getContentPane(), "panelSeleccion" );
			}
		});
		btnRegresar.setBounds(0, 242, 85, 21);
		panelConversorMoneda.add(btnRegresar);
		
		JButton btnConvertirMoneda = new JButton("Convertir");
		btnConvertirMoneda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dinero = textFieldDinero.getText();
				dineroDouble = Double.parseDouble(dinero);
				
				if(invertirConversionMoneda == false) {
					resultadoConversion = dineroDouble / conversion[comboBoxA.getSelectedIndex() + 1];
					dineroConvertido = String.format("%.2f",resultadoConversion);
				}
				else {
					resultadoConversion = dineroDouble * conversion[comboBoxDe.getSelectedIndex() + 1];
					dineroConvertido = String.format("%.2f",resultadoConversion);
				}
				//lblNewLabel.setText(dineroConvertido);
				if(invertirConversionMoneda == false) {
					JOptionPane.showMessageDialog(null, dinero + " " + simboloMoneda[comboBoxDe.getSelectedIndex()] + " son "
							 + dineroConvertido + " " + simboloMoneda[comboBoxA.getSelectedIndex()+1]);
				}
				else {
					JOptionPane.showMessageDialog(null, dinero + " " + simboloMoneda[comboBoxDe.getSelectedIndex()+1] + " son "
							 + dineroConvertido + " " + simboloMoneda[comboBoxA.getSelectedIndex()]);
				}
				

			}
		});
		btnConvertirMoneda.setBounds(164, 155, 96, 21);
		panelConversorMoneda.add(btnConvertirMoneda);
		
		JLabel lblNewLabel = new JLabel("Cantidad");
		lblNewLabel.setBounds(188, 40, 45, 13);
		panelConversorMoneda.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Desde la moneda");
		lblNewLabel_1.setBounds(51, 88, 91, 13);
		panelConversorMoneda.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("A la moneda");
		lblNewLabel_2.setBounds(294, 88, 69, 13);
		panelConversorMoneda.add(lblNewLabel_2);
		

		
		JPanel panelConversorLongitud = new JPanel();
		frame.getContentPane().add(panelConversorLongitud, "panelConversorHorario");
		panelConversorLongitud.setLayout(null);
		
		JLabel lblTituloHorario = new JLabel("CONVERSOR LONGITUD");
		lblTituloHorario.setBounds(120, 10, 182, 13);
		panelConversorLongitud.add(lblTituloHorario);
		
		
		JComboBox comboBoxLongitudDe = new JComboBox();
		comboBoxLongitudDe.setBounds(105, 71, 58, 21);
		panelConversorLongitud.add(comboBoxLongitudDe);
		
		JComboBox comboBoxLongitudA = new JComboBox();
		comboBoxLongitudA.setBounds(238, 71, 47, 21);
		panelConversorLongitud.add(comboBoxLongitudA);
		
		inicializarLongitud(comboBoxLongitudDe, comboBoxLongitudA);
		
		textFieldUnidad = new JTextField();
		textFieldUnidad.setBounds(164, 33, 96, 19);
		panelConversorLongitud.add(textFieldUnidad);
		textFieldUnidad.setColumns(10);
		
		JButton btnNewButton = new JButton("mostrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calcularLongitud(comboBoxLongitudDe, comboBoxLongitudA);
				JOptionPane.showMessageDialog(null, unidad + " " + longitud[comboBoxLongitudDe.getSelectedIndex()] + " son "
						 + unidadDouble + " " + longitud[comboBoxLongitudA.getSelectedIndex()]);
			}
		});
		btnNewButton.setBounds(159, 113, 85, 21);
		panelConversorLongitud.add(btnNewButton);
		
		JButton btnRegresarLongitud = new JButton("Regresar");
		btnRegresarLongitud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myLayout.show(frame.getContentPane(), "panelSeleccion" );
			}
		});
		btnRegresarLongitud.setBounds(0, 242, 85, 21);
		panelConversorLongitud.add(btnRegresarLongitud);
	}
}
