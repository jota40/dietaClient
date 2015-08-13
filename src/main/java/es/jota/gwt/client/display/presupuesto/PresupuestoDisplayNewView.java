package es.jota.gwt.client.display.presupuesto;

import java.math.BigDecimal;

import jota.server.dto.RecetaDto;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;

import es.jota.gwt.client.widgets.display.TBodyPanel;

public interface PresupuestoDisplayNewView extends IsWidget {
	void setListener( PresupuestoDisplayNewView.Listener listener );
	public interface Listener {
		void addReceta( RecetaDto recetaDto );
		void removeReceta( RecetaDto recetaDto );
		void cambiarPax();
	}
	// menu
	HTMLPanel getMenu();

	// fillForm
	void setNuevoPax( Integer nuevoPax );

	// fillIngredientesDeReceta
	TBodyPanel getIngredientesDeReceta();

	BigDecimal getNuevoPax();
}