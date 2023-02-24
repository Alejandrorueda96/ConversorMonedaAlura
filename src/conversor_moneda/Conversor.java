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
		
		try {
			unidad = textFieldUnidad.getText();
			if(unidad.isEmpty()) {
				JOptionPane.showMessageDialog(null, "El campo cantidad no puede estar vacio");
			}
			else {
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
				JOptionPane.showMessageDialog(null, unidad + " " + longitud[unidadBase.getSelectedIndex()] + " son "
						 + unidadDouble + " " + longitud[unidadAConvertir.getSelectedIndex()]);
			}
		}
		catch(NumberFormatException exception) {
			JOptionPane.showMessageDialog(null, "Ingrese solo numeros");
		}

	}
	//Personalizar comboBox
	class MiBoxRenderer extends JLabel implements ListCellRenderer {
	    
	    public MiBoxRenderer() {
	        setOpaque(true);
	        setFont(new Font("Arial", Font.PLAIN | Font.PLAIN, 14));
	        setBackground(new Color(255, 255, 255));
	        setForeground(Color.BLACK);
	    }

	    @Override
	    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
	            boolean cellHasFocus) {
	        setText(value.toString());
	        if(index == 0){
	            this.setForeground(Color.BLACK);
	            this.setBackground(Color.white);
	            if (isSelected) {
	                this.setBackground(new Color(204, 204, 204));
	            }
	        }
	        if(index == 1){
	            this.setForeground(Color.BLACK);
	            this.setBackground(Color.white);
	            if (isSelected) {
	                this.setBackground(new Color(204, 204, 204));
	            }
	        }
 
	        return this;
	    }
	    
	}

	class MiBoxEditor extends BasicComboBoxEditor {
	    private JLabel label = new JLabel();
	    private JPanel panel = new JPanel();
	    private Object selectedItem;
	     
	    public MiBoxEditor() {
	         
	        label.setOpaque(false);
	        label.setFont(new Font("Arial", Font.PLAIN, 14));
	        label.setForeground(Color.BLACK);
	         
	        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 2));
	        panel.add(label);
	        panel.setBackground(Color.WHITE);
	
	    }
	     
	    public Component getEditorComponent() {
	        return this.panel;
	    }
	     
	    public Object getItem() {
	        return this.selectedItem.toString();
	    }
	     
	    public void setItem(Object item) {
	        this.selectedItem = item;
	        label.setText(item.toString());
	    }
	}
	
	
	
	
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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
		comboBoxSeleccion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBoxSeleccion.setModel(new DefaultComboBoxModel(new String[] {"Conversor de Moneda", "Conversor de Longitud"}));
		
		//comboBoxSeleccion.setRenderer(new MiBoxRenderer());
		//comboBoxSeleccion.setEditor(new MiBoxEditor());
		//comboBoxSeleccion.setEditable(true);
		
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
		
		invertir(comboBoxDe, comboBoxA);
		
		JButton btnInvertirSeleccion = new JButton("Invertir");
		btnInvertirSeleccion.setForeground(new Color(255, 255, 255));
		btnInvertirSeleccion.setBackground(new Color(51, 0, 255));
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
				
				try {
					dinero = textFieldDinero.getText();
					
					if(dinero.isEmpty())
						JOptionPane.showMessageDialog(null,"El campo cantidad no puede estar vacio!");
					else
					{
						dineroDouble = Double.parseDouble(dinero);
						
						if(invertirConversionMoneda == false) {
							resultadoConversion = dineroDouble / conversion[comboBoxA.getSelectedIndex() + 1];
							dineroConvertido = String.format("%.2f",resultadoConversion);
						}
						else {
							resultadoConversion = dineroDouble * conversion[comboBoxDe.getSelectedIndex() + 1];
							dineroConvertido = String.format("%.2f",resultadoConversion);
						}
						
						if(invertirConversionMoneda == false) {
							JOptionPane.showMessageDialog(null, dinero + " " + simboloMoneda[comboBoxDe.getSelectedIndex()] + " son "
									 + dineroConvertido + " " + simboloMoneda[comboBoxA.getSelectedIndex()+1]);
						}
						else {
							JOptionPane.showMessageDialog(null, dinero + " " + simboloMoneda[comboBoxDe.getSelectedIndex()+1] + " son "
									 + dineroConvertido + " " + simboloMoneda[comboBoxA.getSelectedIndex()]);
						}
						
					}

				}
				
				catch(NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "Ingrese solo numeros");
				}

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
		
		inicializarLongitud(comboBoxLongitudDe, comboBoxLongitudA);
		
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
				
				calcularLongitud(comboBoxLongitudDe, comboBoxLongitudA);
				
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
