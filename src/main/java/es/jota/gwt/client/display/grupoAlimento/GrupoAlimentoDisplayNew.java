package es.jota.gwt.client.display.grupoAlimento;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;

import es.jota.gwt.client.interfaces.INewActivity;

public interface GrupoAlimentoDisplayNew extends IsWidget {
	void setListener( GrupoAlimentoDisplayNew.Listener listener );
	public interface Listener extends INewActivity.Listener {
	}
	// menu
	HTMLPanel getMenu();
	void setListarUrl(String url);

	// cleanForm
	void setClean();
	
	// fillObject
	String getColor();
	String getNombre();
	String getDescripcion();
	
}