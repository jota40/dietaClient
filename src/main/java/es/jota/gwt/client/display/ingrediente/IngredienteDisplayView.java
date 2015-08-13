package es.jota.gwt.client.display.ingrediente;

import java.math.BigDecimal;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;

public interface IngredienteDisplayView extends IsWidget {
	void setListener( IngredienteDisplayView.Listener listener );
	public interface Listener {
	}
	// menu
	HTMLPanel getMenu();
	void setEditarUrl( String url);
	void setListarUrl( String url );
	void setNuevoUrl( String url );

	// fillForm
	void setNombre( String nombre );
	void setDescripcion( String descripcion );
	void setPesoPorRacion( BigDecimal pesoPorRacion );
	void setCoste( BigDecimal coste );
	void setUnidadDeMedida( String unidadDeMedida );
	void setGrupoAlimento( String grupoAlimento );
}