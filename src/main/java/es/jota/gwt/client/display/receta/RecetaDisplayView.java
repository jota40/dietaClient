package es.jota.gwt.client.display.receta;

import java.math.BigDecimal;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;

import es.jota.gwt.client.widgets.display.TBodyPanel;

public interface RecetaDisplayView extends IsWidget {
	void setListener( RecetaDisplayView.Listener listener );
	public interface Listener {
		void cambiarPax();
	}
	// menu
	HTMLPanel getMenu();
	void setEditarUrl( String url);
	void setListarUrl( String url );
	void setNuevoUrl( String url );

	// fillReceta
	void setNombre( String nombre );
	void setDescripcion( String descripcion );
	void setPax( Integer pesoPorRacion );
	// fillIngredientesDeReceta
	TBodyPanel getIngredientesDeReceta();

	BigDecimal getNuevoPax();
}