package es.jota.gwt.client.display.grupoAlimento;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;

public interface GrupoAlimentoDisplayView extends IsWidget {
	void setListener( GrupoAlimentoDisplayView.Listener listener );
	public interface Listener {
	}
	// menu
	HTMLPanel getMenu();
	void setEditarUrl( String url);
	void setListarUrl( String url );
	void setNuevoUrl( String url );

	// fillForm
	void setColor( String abreviacion );
	void setNombre( String nombre );
	void setDescripcion( String descripcion );
}