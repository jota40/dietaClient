package es.jota.gwt.client.display.unidadDeMedida;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;

import es.jota.gwt.client.interfaces.IEditActivity;

public interface UnidadDeMedidaDisplayEdit extends IsWidget {
	void setListener( UnidadDeMedidaDisplayEdit.Listener listener );
	public interface Listener extends IEditActivity.Listener {
	}
	// menu
	HTMLPanel getMenu();
	void setListarUrl( String url );
	void setNuevoUrl( String url );
	void setVerUrl( String url);
	
	// fillForm
	void setAbreviacion( String abreviacion );
	void setNombre( String nombre );
	void setDescripcion( String descripcion );
	
	// fillObject
	String getAbreviacion();
	String getNombre();
	String getDescripcion();
	
	/////////////
}