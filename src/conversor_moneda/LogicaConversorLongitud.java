package conversor_moneda;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class LogicaConversorLongitud {
	
	private Conversor conversor;
	String [] longitud = {"mm", "cm", "dm", "m", "dam", "hm", "km"};
	String [] masa = {"mg", "cg", "dg", "g", "dag", "hg", "kg"};
	String [] capacidad = {"mL", "cL", "dL", "L", "daL", "hL", "kL"};
	String unidad;
	Double unidadDouble;
	
	
	public String[] getLongitud() {
		return longitud;
	}

	public void setLongitud(String[] longitud) {
		this.longitud = longitud;
	}

	// Constructor
	public LogicaConversorLongitud(Conversor parentConversor) {
		this.conversor = parentConversor;
	}

	public void inicializarLongitud(String[] simbolos) {
		conversor.getComboBoxLongitudDe().removeAllItems();
		conversor.getComboBoxLongitudA().removeAllItems();
		
		for(int i = 0; i <= simbolos.length-1; i++) {
			conversor.getComboBoxLongitudDe().addItem(simbolos[i]);
			conversor.getComboBoxLongitudA().addItem(simbolos[i]);
			
		}
	}
	
	public void inicializarComboBox() {
		if(conversor.getIndexActual() == 1) {
			conversor.getLblTituloMedida().setText("CONVERSOR DE LONGITUD");
			inicializarLongitud(longitud);
		}
		else if(conversor.getIndexActual() == 2) {
			conversor.getLblTituloMedida().setText("CONVERSOR DE MASA");
			inicializarLongitud(masa);
		}
		else if(conversor.getIndexActual() == 3) {
			conversor.getLblTituloMedida().setText("CONVERSOR DE CAPACIDAD");
			inicializarLongitud(capacidad);
		}
	}
	
	public void calcularLongitud(String[] simbolos) {
		
		try {
			unidad = conversor.getTextFieldUnidad().getText();
			if(unidad.isEmpty()) {
				JOptionPane.showMessageDialog(null, "El campo cantidad no puede estar vacio","Error",JOptionPane.ERROR_MESSAGE);
			}
			else {
				unidadDouble = Double.parseDouble(unidad);
				if(unidadDouble < 0) {
					JOptionPane.showMessageDialog(null, "No ingrese valores negativos","Error",JOptionPane.ERROR_MESSAGE);
				}
				else {
					int i = conversor.getComboBoxLongitudDe().getSelectedIndex();
					int j = conversor.getComboBoxLongitudA().getSelectedIndex();
					
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
					JOptionPane.showMessageDialog(null, unidad + " " + simbolos[conversor.getComboBoxLongitudDe().getSelectedIndex()] + " son "
							 + unidadDouble + " " + simbolos[conversor.getComboBoxLongitudA().getSelectedIndex()], "Conversion exitosa", JOptionPane.INFORMATION_MESSAGE);
				}

			}
		}
		catch(NumberFormatException exception) {
			JOptionPane.showMessageDialog(null, "Ingrese solo numeros","Error",JOptionPane.ERROR_MESSAGE);
		}

	}
	
	public void conversionUnidades() {
		if(conversor.getIndexActual() == 1) {
			calcularLongitud(longitud);
		}
		else if(conversor.getIndexActual() == 2)
		{
			calcularLongitud(masa);
		}
		else if(conversor.getIndexActual() == 3)
		{
			calcularLongitud(capacidad);
		}
	}
}
