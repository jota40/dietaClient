package es.jota.gwt.client.display.ingrediente;

import java.math.BigDecimal;

import jota.server.dto.GrupoAlimentoDto;
import jota.server.dto.UnidadDeMedidaDto;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;

import es.jota.gwt.client.interfaces.IEditActivity;

public interface IngredienteDisplayEdit extends IsWidget {
	void setListener( IngredienteDisplayEdit.Listener listener );
	public interface Listener extends IEditActivity.Listener {
	}
	// menu
	HTMLPanel getMenu();
	void setListarUrl( String url );
	void setNuevoUrl( String url );
	void setVerUrl( String url);
	
	// fillForm
	void setNombre( String nombre );
	void setDescripcion( String descripcion );
	void setPesoPorRacion( BigDecimal pesoPorRacion );
	void setCoste( BigDecimal coste );
	void setUnidadDeMedida( UnidadDeMedidaDto unidadDeMedida );
	void setGrupoAlimento( GrupoAlimentoDto grupoAlimento );
	
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