package es.jota.gwt.client.display.ingredienteDeReceta;

import jota.server.dto.IngredienteDeRecetaDto;

public interface IngredienteDeRecetaDisplayEdit {
	IngredienteDeRecetaDisplayEdit setListener( IngredienteDeRecetaDisplayEdit.Listener listener );
	public interface Listener {
	}
	IngredienteDeRecetaDto get();
	void set( IngredienteDeRecetaDto ingredienteDeRecetaDto );
}