package es.jota.gwt.client.activity.presupuesto;

import java.math.BigDecimal;
import java.util.HashMap;

import jota.server.dto.IngredienteDeRecetaDto;
import jota.server.dto.RecetaDto;

import com.google.gwt.user.client.ui.Widget;

import es.jota.gwt.client.miApp;
import es.jota.gwt.client.display.ingredienteDeReceta.IngredienteDeRecetaDisplayView;
import es.jota.gwt.client.display.presupuesto.PresupuestoDisplayNewView;
import es.jota.gwt.client.my.MyAbstractActivity;
import es.jota.gwt.client.place.presupuesto.PresupuestoPlaceNew;
import es.jota.gwt.client.widgets.display.TrWidget;

public class PresupuestoActivityNew extends MyAbstractActivity<PresupuestoPlaceNew> implements PresupuestoDisplayNewView.Listener {
	
	private PresupuestoDisplayNewView display;
	HashMap<Long, IngredienteDeRecetaDisplayView> idIngredienteIndexOnPanel = new HashMap<Long, IngredienteDeRecetaDisplayView>();
	
	public PresupuestoActivityNew() {
		display = miApp.INJECTOR.getPresupuestoDisplayNewView();
		display.setListener( this );
	}

	@Override
	protected void start() {
		miApp.INJECTOR.getTopPublicoView().setMenu( display.getMenu() );
		view();
	}

	public void view() {
		fillForm(  );
		containerWidget.setWidget(display.asWidget());
	}

	public void fillForm() {
		display.setNuevoPax( 1 );
	}

	@Override
	public void addReceta( RecetaDto recetaDto ) {
		if ( recetaDto != null ) {
			for ( IngredienteDeRecetaDto ingredienteDeRecetaDto : recetaDto.getIngredientesDeRecetaDto() ) {
				Long key = ingredienteDeRecetaDto.getIdIngrediente();
				IngredienteDeRecetaDisplayView ingredienteDeRecetaDisplayView = idIngredienteIndexOnPanel.get( key );
				if ( ingredienteDeRecetaDisplayView == null) {
					ingredienteDeRecetaDisplayView = miApp.INJECTOR.getIngredienteDeRecetaDisplayView();
					ingredienteDeRecetaDisplayView.set( new IngredienteDeRecetaDto( ingredienteDeRecetaDto.getIngredienteDto(), 1, ingredienteDeRecetaDto.getCantidadPorComensal(), null ) );
					idIngredienteIndexOnPanel.put( key, ingredienteDeRecetaDisplayView );
					display.getIngredientesDeReceta().add( (TrWidget) ingredienteDeRecetaDisplayView );
				} else {
					BigDecimal incremento = ingredienteDeRecetaDto.getCantidadPorComensal();
					ingredienteDeRecetaDisplayView.addCantidadPorComensal( incremento );
				}
			}
		}
	}

	@Override
	public void removeReceta( RecetaDto recetaDto ) {
		if ( recetaDto != null ) {
			for ( IngredienteDeRecetaDto ingredienteDeRecetaDto : recetaDto.getIngredientesDeRecetaDto() ) {
				Long key = ingredienteDeRecetaDto.getIdIngrediente();
				IngredienteDeRecetaDisplayView ingredienteDeRecetaDisplayView = idIngredienteIndexOnPanel.get( key );
				if ( ingredienteDeRecetaDisplayView != null) {
					BigDecimal decremento = ingredienteDeRecetaDto.getCantidadPorComensal();
					ingredienteDeRecetaDisplayView.addCantidadPorComensal( decremento.negate() );
					if ( BigDecimal.ZERO.compareTo( ingredienteDeRecetaDisplayView.get().getCantidadPorComensal() ) == 0 ) {
						idIngredienteIndexOnPanel.remove( key );
						display.getIngredientesDeReceta().remove( (TrWidget) ingredienteDeRecetaDisplayView );
					}
				}
			}
		}
	}

	@Override
	public void cambiarPax() {
		for ( Widget widget : (Iterable<Widget>)display.getIngredientesDeReceta() ) {
			((IngredienteDeRecetaDisplayView)widget).cambiarPax( display.getNuevoPax() );
		}
	}
}