package es.jota.gwt.client.display.grupoAlimento;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;

import es.jota.gwt.client.interfaces.IEditActivity;

public interface GrupoAlimentoDisplayEdit extends IsWidget {
	void setListener( GrupoAlimentoDisplayEdit.Listener listener );
	public interface Listener extends IEditActivity.Listener {
	}
	// menu
	HTMLPanel getMenu();
	void setListarUrl( String url );
	void setNuevoUrl( String url );
	void setVerUrl( String url);
	
	// fillForm
	void setColor( String abreviacion );
	void setNombre( String nombre );
	void setDescripcion( String descripcion );
	
	// fillObject
	String getColor();
	String getNombre();
	String getDescripcion();
	
	/////////////
}