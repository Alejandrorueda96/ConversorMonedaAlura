package conversor_moneda;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Conversor {

	private JFrame frame;
	private JTextField textFieldDinero;
	boolean invertirConversionMoneda = false;
	String [] monedas = {"pesos Colombianos", "D�lar", "Euros", "Libras esterlinas", "Yen Japon�s", "Won sul-coreano"};
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
		comboBoxSeleccion.setModel(new DefaultComboBoxModel(new String[] {"Conversor de Moneda", "Conversor de Zona Horaria"}));
		comboBoxSeleccion.setBounds(132, 47, 130, 21);
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
		comboBoxDe.setBounds(36, 111, 116, 21);
		panelConversorMoneda.add(comboBoxDe);
		
		JComboBox<String> comboBoxA = new JComboBox();
		comboBoxA.setBounds(272, 111, 116, 21);
		panelConversorMoneda.add(comboBoxA);
			
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
		btnConvertirMoneda.setBounds(164, 155, 85, 21);
		panelConversorMoneda.add(btnConvertirMoneda);
		
		JPanel panelConversorHorario = new JPanel();
		frame.getContentPane().add(panelConversorHorario, "panelConversorHorario");
		
		JLabel lblTituloHorario = new JLabel("CONVERSOR ZONA HORARIA");
		panelConversorHorario.add(lblTituloHorario);
	}

}
