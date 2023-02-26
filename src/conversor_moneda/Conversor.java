package conversor_moneda;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import javax.swing.plaf.basic.BasicComboPopup;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Conversor {

	private JFrame frame;
	private JTextField textFieldDinero;
	private JTextField textFieldUnidad;
	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JTextField getTextFieldDinero() {
		return textFieldDinero;
	}

	public void setTextFieldDinero(JTextField textFieldDinero) {
		this.textFieldDinero = textFieldDinero;
	}

	public JTextField getTextFieldUnidad() {
		return textFieldUnidad;
	}

	public void setTextFieldUnidad(JTextField textFieldUnidad) {
		this.textFieldUnidad = textFieldUnidad;
	}

	LogicaConversor logicaConversor;
	LogicaConversorLongitud logicaConversorLongitud;
	
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
		//logicaConversor = new LogicaConversor(this);	
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		logicaConversor = new LogicaConversor(this);
		logicaConversorLongitud = new LogicaConversorLongitud(this);

		frame = new JFrame();
		frame.setBounds(100, 100, 550, 211);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CardLayout myLayout = new CardLayout();
		frame.getContentPane().setLayout(myLayout);
		
		JPanel panelSeleccion = new JPanel();
		panelSeleccion.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(panelSeleccion, "panelSeleccion");
		panelSeleccion.setLayout(null);
		
		JComboBox comboBoxSeleccion = new JComboBox();
		comboBoxSeleccion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBoxSeleccion.setModel(new DefaultComboBoxModel(new String[] {"Conversor de Moneda", "Conversor de Longitud"}));
				
		comboBoxSeleccion.setBounds(169, 56, 208, 37);
		panelSeleccion.add(comboBoxSeleccion);
		
		
		JButton btnAceptarSeleccion = new JButton("Aceptar");
		btnAceptarSeleccion.setForeground(new Color(255, 255, 255));
		btnAceptarSeleccion.setBackground(new Color(51, 0, 255));
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
		btnAceptarSeleccion.setBounds(169, 116, 85, 21);
		panelSeleccion.add(btnAceptarSeleccion);
		
		JButton btnCancelarSeleccion = new JButton("Cancelar");
		btnCancelarSeleccion.setForeground(new Color(255, 255, 255));
		btnCancelarSeleccion.setBackground(new Color(51, 0, 255));
		btnCancelarSeleccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancelarSeleccion.setBounds(292, 116, 85, 21);
		panelSeleccion.add(btnCancelarSeleccion);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(51, 0, 255));
		panel_2.setBounds(0, 0, 536, 46);
		panelSeleccion.add(panel_2);
		
		JLabel lblTitulo = new JLabel("CONVERSOR ALURA");
		lblTitulo.setForeground(new Color(255, 255, 255));
		lblTitulo.setFont(new Font("Myanmar Text", Font.BOLD, 18));
		panel_2.add(lblTitulo);
		
		JPanel panelConversorMoneda = new JPanel();
		panelConversorMoneda.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(panelConversorMoneda, "panelConversorMoneda");
		panelConversorMoneda.setLayout(null);
		
		textFieldDinero = new JTextField();
		textFieldDinero.setBounds(20, 80, 96, 19);
		panelConversorMoneda.add(textFieldDinero);
		textFieldDinero.setColumns(10);
		
		JComboBox<String> comboBoxDe = new JComboBox();
		comboBoxDe.setBackground(new Color(255, 255, 255));
		comboBoxDe.setBounds(126, 79, 142, 21);
		panelConversorMoneda.add(comboBoxDe);
		
		JComboBox<String> comboBoxA = new JComboBox();
		comboBoxA.setBackground(new Color(255, 255, 255));
		comboBoxA.setBounds(373, 79, 142, 21);
		panelConversorMoneda.add(comboBoxA);
		
		logicaConversor.invertir(comboBoxDe, comboBoxA);
		
		JButton btnInvertirSeleccion = new JButton("Invertir");
		btnInvertirSeleccion.setForeground(new Color(255, 255, 255));
		btnInvertirSeleccion.setBackground(new Color(51, 0, 255));
		btnInvertirSeleccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				logicaConversor.invertir(comboBoxDe, comboBoxA);
			}
		});
		btnInvertirSeleccion.setBounds(278, 79, 85, 21);
		panelConversorMoneda.add(btnInvertirSeleccion);
		
		JButton btnRegresar = new JButton("Atras");
		btnRegresar.setForeground(new Color(255, 255, 255));
		btnRegresar.setBackground(new Color(51, 0, 255));
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myLayout.show(frame.getContentPane(), "panelSeleccion" );
			}
		});
		btnRegresar.setBounds(0, 153, 85, 21);
		panelConversorMoneda.add(btnRegresar);
		
		JButton btnConvertirMoneda = new JButton("convertir");
		btnConvertirMoneda.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnConvertirMoneda.setForeground(new Color(255, 255, 255));
		btnConvertirMoneda.setBackground(new Color(51, 0, 255));
		btnConvertirMoneda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				logicaConversor.llenarComboBox(comboBoxDe, comboBoxA);

			}
		});
		btnConvertirMoneda.setBounds(206, 110, 120, 35);
		panelConversorMoneda.add(btnConvertirMoneda);
		
		JLabel lblNewLabel = new JLabel("Importe");
		lblNewLabel.setFont(new Font("Myanmar Text", Font.BOLD, 12));
		lblNewLabel.setBounds(20, 59, 57, 13);
		panelConversorMoneda.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Desde la moneda");
		lblNewLabel_1.setBounds(126, 57, 107, 13);
		panelConversorMoneda.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("A la moneda");
		lblNewLabel_2.setBounds(373, 57, 69, 13);
		panelConversorMoneda.add(lblNewLabel_2);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 0, 255));
		panel.setBounds(0, 0, 536, 46);
		panelConversorMoneda.add(panel);
		
		JLabel lblNewLabel_3 = new JLabel("CONVERSOR DE MONEDA");
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("Myanmar Text", Font.BOLD, 18));
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
			
		JPanel panelConversorLongitud = new JPanel();
		panelConversorLongitud.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(panelConversorLongitud, "panelConversorHorario");
		panelConversorLongitud.setLayout(null);
			
		JComboBox comboBoxLongitudDe = new JComboBox();
		comboBoxLongitudDe.setBounds(238, 74, 63, 27);
		panelConversorLongitud.add(comboBoxLongitudDe);
		
		JComboBox comboBoxLongitudA = new JComboBox();
		comboBoxLongitudA.setBounds(332, 74, 63, 27);
		panelConversorLongitud.add(comboBoxLongitudA);
		
		logicaConversorLongitud.inicializarLongitud(comboBoxLongitudDe, comboBoxLongitudA);
		
		textFieldUnidad = new JTextField();
		textFieldUnidad.setBounds(117, 74, 96, 27);
		panelConversorLongitud.add(textFieldUnidad);
		textFieldUnidad.setColumns(10);
		
		JButton btnConvertirLongitud = new JButton("Convertir");
		btnConvertirLongitud.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnConvertirLongitud.setForeground(new Color(255, 255, 255));
		btnConvertirLongitud.setBackground(new Color(51, 0, 255));
		btnConvertirLongitud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				logicaConversorLongitud.calcularLongitud(comboBoxLongitudDe, comboBoxLongitudA);
				
			}
		});
		btnConvertirLongitud.setBounds(185, 111, 137, 36);
		panelConversorLongitud.add(btnConvertirLongitud);
		
		JButton btnRegresarLongitud = new JButton("Regresar");
		btnRegresarLongitud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myLayout.show(frame.getContentPane(), "panelSeleccion" );
			}
		});
		btnRegresarLongitud.setBounds(0, 242, 85, 21);
		panelConversorLongitud.add(btnRegresarLongitud);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(51, 0, 255));
		panel_1.setBounds(0, 0, 536, 46);
		panelConversorLongitud.add(panel_1);
		
		JLabel lblTituloHorario = new JLabel("CONVERSOR LONGITUD");
		lblTituloHorario.setForeground(new Color(255, 255, 255));
		panel_1.add(lblTituloHorario);
		lblTituloHorario.setFont(new Font("Myanmar Text", Font.BOLD, 18));
		
		JLabel lblNewLabel_4 = new JLabel("Cantidad");
		lblNewLabel_4.setBounds(117, 52, 63, 13);
		panelConversorLongitud.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Convertir de");
		lblNewLabel_5.setBounds(237, 52, 85, 13);
		panelConversorLongitud.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Convertir a");
		lblNewLabel_6.setBounds(332, 52, 77, 13);
		panelConversorLongitud.add(lblNewLabel_6);
		
		JButton btnNewButton = new JButton("Atras");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myLayout.show(frame.getContentPane(), "panelSeleccion" );
			}
		});
		btnNewButton.setBackground(new Color(51, 0, 255));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBounds(0, 153, 85, 21);
		panelConversorLongitud.add(btnNewButton);
	}
	
	
}
