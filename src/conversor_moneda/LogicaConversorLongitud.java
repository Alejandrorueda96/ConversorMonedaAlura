package conversor_moneda;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class LogicaConversorLongitud {
	
	private Conversor conversor;
	String [] longitud = {"mm", "cm", "dm", "m", "dam", "hm", "km"};
	String unidad;
	Double unidadDouble;
	
	
	// Constructor
	public LogicaConversorLongitud(Conversor parentConversor) {
		this.conversor = parentConversor;
	}

	public void inicializarLongitud(JComboBox unidadBase, JComboBox unidadAConvertir) {
		for(int i = 0; i <= longitud.length-1; i++) {
			unidadBase.addItem(longitud[i]);
			unidadAConvertir.addItem(longitud[i]);
		}
	}
	
	public void calcularLongitud(JComboBox unidadBase, JComboBox unidadAConvertir) {
		
		try {
			unidad = conversor.getTextFieldUnidad().getText();
			if(unidad.isEmpty()) {
				JOptionPane.showMessageDialog(null, "El campo cantidad no puede estar vacio","Error",JOptionPane.ERROR_MESSAGE);
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
						 + unidadDouble + " " + longitud[unidadAConvertir.getSelectedIndex()], "Conversion exitosa", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		catch(NumberFormatException exception) {
			JOptionPane.showMessageDialog(null, "Ingrese solo numeros","Error",JOptionPane.ERROR_MESSAGE);
		}

	}
}
