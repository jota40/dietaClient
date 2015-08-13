package es.jota.gwt.client.display.receta;

import java.math.BigDecimal;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;

import es.jota.gwt.client.interfaces.IEditActivity;
import es.jota.gwt.client.widgets.display.TBodyPanel;

public interface RecetaDisplayEdit extends IsWidget {
	void setListener( RecetaDisplayEdit.Listener listener );
	public interface Listener extends IEditActivity.Listener {
		void addIngredientesDeReceta();
	}
	// menu
	HTMLPanel getMenu();
	void setListarUrl( String url );
	void setNuevoUrl( String url );
	void setVerUrl( String url);
	
	// fillForm
	void setNombre( String nombre );
	void setDescripcion( String descripcion );
	void setPax( Integer pax );
	
	// fillReceta
	String getNombre();
	String getDescripcion();
	BigDecimal getPax();
	// fillIngredientesDeReceta
	TBodyPanel getIngredientesDeReceta();

	// Validate
	boolean validate();
	/////////////
}