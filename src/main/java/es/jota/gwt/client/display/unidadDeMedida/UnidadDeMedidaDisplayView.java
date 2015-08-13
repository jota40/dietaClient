package es.jota.gwt.client.display.unidadDeMedida;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;

public interface UnidadDeMedidaDisplayView extends IsWidget {
	void setListener( UnidadDeMedidaDisplayView.Listener listener );
	public interface Listener {
	}
	// menu
	HTMLPanel getMenu();
	void setEditarUrl( String url);
	void setListarUrl( String url );
	void setNuevoUrl( String url );

	// fillForm
	void setAbreviacion( String abreviacion );
	void setNombre( String nombre );
	void setDescripcion( String descripcion );
}