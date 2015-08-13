package es.jota.gwt.client.activity.ingrediente;

import jota.server.dto.IngredienteDto;
import es.jota.gwt.client.miApp;
import es.jota.gwt.client.display.ingrediente.IngredienteDisplayView;
import es.jota.gwt.client.my.MyAbstractActivity;
import es.jota.gwt.client.my.MyAsyncCallback;
import es.jota.gwt.client.place.ingrediente.IngredientePlaceEdit;
import es.jota.gwt.client.place.ingrediente.IngredientePlaceList;
import es.jota.gwt.client.place.ingrediente.IngredientePlaceNew;
import es.jota.gwt.client.place.ingrediente.IngredientePlaceView;
import es.jota.gwt.client.service.IngredienteGwtService;

public class IngredienteActivityView extends MyAbstractActivity<IngredientePlaceView> implements IngredienteDisplayView.Listener {
	
	private IngredienteDisplayView display;

	public IngredienteActivityView() {
		display = miApp.INJECTOR.getIngredienteDisplayView();
		display.setListener( this );

		display.setListarUrl( IngredientePlaceList.getUrl() );
		display.setNuevoUrl( IngredientePlaceNew.getUrl() );
	}

	@Override
	protected void start() {
		display.setEditarUrl( IngredientePlaceEdit.getUrl( place.getId() ) );
		miApp.INJECTOR.getTopPublicoView().setMenu( display.getMenu() );
		view();
	}

	public void view() {
		IngredienteGwtService.Util.getInstance().getIngredienteDtoById( place.getId(), new MyAsyncCallback<IngredienteDto>( "Ingrediente", "Leyendo, id = " + place.getId() ) {
			@Override
			public void handleOnSuccess( IngredienteDto ingredienteDto ) {
				if ( ingredienteDto == null ) {
					goTo( IngredientePlaceNew.instance() );
				} else {
					fillForm( ingredienteDto );
					containerWidget.setWidget(display.asWidget());
				}
			}
		});
	}

	public void fillForm( IngredienteDto ingredienteDto ) {
		display.setNombre( ingredienteDto.getNombre() );
		display.setDescripcion( ingredienteDto.getDescripcion() );
		display.setPesoPorRacion( ingredienteDto.getPesoPorRacion() );
		display.setCoste( ingredienteDto.getCoste() );
		display.setUnidadDeMedida( ingredienteDto.getUnidadDeMedida() == null ? "" : ingredienteDto.getUnidadDeMedida().getNombre() );
		display.setGrupoAlimento( ingredienteDto.getGrupoAlimento() == null ? "" : ingredienteDto.getGrupoAlimento().getNombre() );
	}
}