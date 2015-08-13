package es.jota.gwt.client.display.unidadDeMedida;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;

import es.jota.gwt.client.interfaces.INewActivity;

public interface UnidadDeMedidaDisplayNew extends IsWidget {
	void setListener( UnidadDeMedidaDisplayNew.Listener listener );
	public interface Listener extends INewActivity.Listener {
	}
	// menu
	HTMLPanel getMenu();
	void setListarUrl(String url);

	// cleanForm
	void setClean();
	
	// fillObject
	String getAbreviacion();
	String getNombre();
	String getDescripcion();
	
}