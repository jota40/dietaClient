package es.jota.gwt.client.display.receta;

import java.math.BigDecimal;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;

import es.jota.gwt.client.interfaces.INewActivity;
import es.jota.gwt.client.widgets.display.TBodyPanel;

public interface RecetaDisplayNew extends IsWidget {
	void setListener( RecetaDisplayNew.Listener listener );
	public interface Listener extends INewActivity.Listener {
		void addIngredientesDeReceta();
	}

	// menu
	HTMLPanel getMenu();
	void setListarUrl( String url );

	// cleanForm
	void clear();
	
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