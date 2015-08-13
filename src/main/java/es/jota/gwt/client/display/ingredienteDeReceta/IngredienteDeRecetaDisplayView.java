package es.jota.gwt.client.display.ingredienteDeReceta;

import java.math.BigDecimal;

import jota.server.dto.IngredienteDeRecetaDto;

public interface IngredienteDeRecetaDisplayView {
	IngredienteDeRecetaDisplayView setListener( IngredienteDeRecetaDisplayView.Listener listener );
	public interface Listener {
	}
	IngredienteDeRecetaDto get();
	void set( IngredienteDeRecetaDto ingredienteDeRecetaDto );
	void cambiarPax( BigDecimal pax );
	void addCantidadPorComensal( BigDecimal incremento );
}