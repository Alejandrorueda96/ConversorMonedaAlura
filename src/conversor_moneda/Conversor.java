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
		
		JLabel lblTitulo = new JLabel("CONVERSOR");
		lblTitulo.setBounds(166, 10, 58, 13);
		panelSeleccion.add(lblTitulo);
		
		JComboBox comboBoxSeleccion = new JComboBox();
		comboBoxSeleccion.setModel(new DefaultComboBoxModel(new String[] {"Conversor de Moneda", "Conversor de Longitud"}));
		
		//comboBoxSeleccion.setRenderer(new MiBoxRenderer());
		//comboBoxSeleccion.setEditor(new MiBoxEditor());
		//comboBoxSeleccion.setEditable(true);
		
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
		btnCancelarSeleccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancelarSeleccion.setBounds(220, 90, 85, 21);
		panelSeleccion.add(btnCancelarSeleccion);
		
		JPanel panelConversorMoneda = new JPanel();
		panelConversorMoneda.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(panelConversorMoneda, "panelConversorMoneda");
		panelConversorMoneda.setLayout(null);
		
		textFieldDinero = new JTextField();
		textFieldDinero.setBounds(10, 79, 96, 19);
		panelConversorMoneda.add(textFieldDinero);
		textFieldDinero.setColumns(10);
		
		JComboBox<String> comboBoxDe = new JComboBox();
		comboBoxDe.setBackground(new Color(255, 255, 255));
		comboBoxDe.setBounds(116, 78, 142, 21);
		panelConversorMoneda.add(comboBoxDe);
		
		JComboBox<String> comboBoxA = new JComboBox();
		comboBoxA.setBackground(new Color(255, 255, 255));
		comboBoxA.setBounds(363, 78, 142, 21);
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
		btnInvertirSeleccion.setBounds(268, 78, 85, 21);
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
		lblNewLabel.setBounds(10, 58, 57, 13);
		panelConversorMoneda.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Desde la moneda");
		lblNewLabel_1.setBounds(116, 56, 107, 13);
		panelConversorMoneda.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("A la moneda");
		lblNewLabel_2.setBounds(363, 56, 69, 13);
		panelConversorMoneda.add(lblNewLabel_2);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 0, 255));
		panel.setBounds(0, 0, 536, 46);
		panelConversorMoneda.add(panel);
		
		JLabel lblNewLabel_3 = new JLabel("Conversor de Moneda");
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("Myanmar Text", Font.BOLD, 18));
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		

		
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
		
		JButton btnConvertirLongitud = new JButton("Convertir");
		btnConvertirLongitud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				calcularLongitud(comboBoxLongitudDe, comboBoxLongitudA);
				
			}
		});
		btnConvertirLongitud.setBounds(159, 113, 85, 21);
		panelConversorLongitud.add(btnConvertirLongitud);
		
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
