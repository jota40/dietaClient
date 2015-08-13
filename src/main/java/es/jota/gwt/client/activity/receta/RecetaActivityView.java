package es.jota.gwt.client.activity.receta;

import jota.server.dto.IngredienteDeRecetaDto;
import jota.server.dto.RecetaDto;

import com.google.gwt.user.client.ui.Widget;

import es.jota.gwt.client.miApp;
import es.jota.gwt.client.display.ingredienteDeReceta.IngredienteDeRecetaDisplayView;
import es.jota.gwt.client.display.receta.RecetaDisplayView;
import es.jota.gwt.client.my.MyAbstractActivity;
import es.jota.gwt.client.my.MyAsyncCallback;
import es.jota.gwt.client.place.receta.RecetaPlaceEdit;
import es.jota.gwt.client.place.receta.RecetaPlaceList;
import es.jota.gwt.client.place.receta.RecetaPlaceNew;
import es.jota.gwt.client.place.receta.RecetaPlaceView;
import es.jota.gwt.client.service.RecetaGwtService;
import es.jota.gwt.client.widgets.display.TrWidget;

public class RecetaActivityView extends MyAbstractActivity<RecetaPlaceView> implements RecetaDisplayView.Listener {
	
	private RecetaDisplayView display;

	public RecetaActivityView() {
		display = miApp.INJECTOR.getRecetaDisplayView();
		display.setListener( this );

		display.setListarUrl( RecetaPlaceList.getUrl() );
		display.setNuevoUrl( RecetaPlaceNew.getUrl() );
	}

	@Override
	protected void start() {
		display.setEditarUrl( RecetaPlaceEdit.getUrl( place.getId() ) );
		miApp.INJECTOR.getTopPublicoView().setMenu( display.getMenu() );
		view();
	}

	public void view() {
		RecetaGwtService.Util.getInstance().getRecetaDtoById( place.getId(), new MyAsyncCallback<RecetaDto>( "Receta", "Leyendo, id = " + place.getId() ) {
			@Override
			public void handleOnSuccess( RecetaDto recetaDto ) {
				if ( recetaDto == null ) {
					goTo( RecetaPlaceNew.instance() );
				} else {
					fillForm( recetaDto );
					containerWidget.setWidget(display.asWidget());
				}
			}
		});
	}

	public void fillForm( RecetaDto recetaDto ) {
		display.setNombre( recetaDto.getNombre() );
		display.setDescripcion( recetaDto.getDescripcion() );
		display.setPax( recetaDto.getPax() );
		
		display.getIngredientesDeReceta().clear();
		if ( recetaDto.getIngredientesDeRecetaDto() != null ) {
			for ( IngredienteDeRecetaDto ingredienteDeRecetaDto : recetaDto.getIngredientesDeRecetaDto()) {
				IngredienteDeRecetaDisplayView ingredienteDeRecetaDisplayView = miApp.INJECTOR.getIngredienteDeRecetaDisplayView();
				ingredienteDeRecetaDisplayView.set( ingredienteDeRecetaDto );
				display.getIngredientesDeReceta().add( (TrWidget) ingredienteDeRecetaDisplayView );
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