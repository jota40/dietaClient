package es.jota.gwt.client.display.ingrediente;

import java.math.BigDecimal;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;

import es.jota.gwt.client.interfaces.INewActivity;

public interface IngredienteDisplayNew extends IsWidget {
	void setListener( IngredienteDisplayNew.Listener listener );
	public interface Listener extends INewActivity.Listener {
	}
	// menu
	HTMLPanel getMenu();
	void setListarUrl(String url);

	// cleanForm
	void setClean();
	
	// fillObject
	String getNombre();
	String getDescripcion();
	BigDecimal getPesoPorRacion();
	BigDecimal getCoste();
	Long getIdUnidadDeMedida();
	Long getIdGrupoAlimento();

	// Validate
	boolean validate();
	/////////////
}