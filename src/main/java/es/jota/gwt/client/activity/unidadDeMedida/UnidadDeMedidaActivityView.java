package es.jota.gwt.client.activity.unidadDeMedida;

import jota.server.dto.UnidadDeMedidaDto;
import es.jota.gwt.client.miApp;
import es.jota.gwt.client.display.unidadDeMedida.UnidadDeMedidaDisplayView;
import es.jota.gwt.client.my.MyAbstractActivity;
import es.jota.gwt.client.my.MyAsyncCallback;
import es.jota.gwt.client.place.unidadDeMedida.UnidadDeMedidaPlaceEdit;
import es.jota.gwt.client.place.unidadDeMedida.UnidadDeMedidaPlaceList;
import es.jota.gwt.client.place.unidadDeMedida.UnidadDeMedidaPlaceNew;
import es.jota.gwt.client.place.unidadDeMedida.UnidadDeMedidaPlaceView;
import es.jota.gwt.client.service.UnidadDeMedidaGwtService;

public class UnidadDeMedidaActivityView extends MyAbstractActivity<UnidadDeMedidaPlaceView> implements UnidadDeMedidaDisplayView.Listener {
	
	private UnidadDeMedidaDisplayView display;

	public UnidadDeMedidaActivityView() {
		display = miApp.INJECTOR.getUnidadDeMedidaDisplayView();
		display.setListener( this );

		display.setListarUrl( UnidadDeMedidaPlaceList.getUrl() );
		display.setNuevoUrl( UnidadDeMedidaPlaceNew.getUrl() );
	}

	@Override
	protected void start() {
		display.setEditarUrl( UnidadDeMedidaPlaceEdit.getUrl( place.getId() ) );
		miApp.INJECTOR.getTopPublicoView().setMenu( display.getMenu() );
		view();
	}

	public void view() {
		UnidadDeMedidaGwtService.Util.getInstance().getUnidadDeMedidaDtoById( place.getId(), new MyAsyncCallback<UnidadDeMedidaDto>( "Unidad de medida ", "Leyendo id = " + place.getId() ) {
			@Override
			public void handleOnSuccess( UnidadDeMedidaDto unidadDeMedidaDto ) {
				if ( unidadDeMedidaDto == null ) {
					goTo( UnidadDeMedidaPlaceNew.instance() );
				} else {
					fillForm( unidadDeMedidaDto );
					containerWidget.setWidget(display.asWidget());
				}
			}
		});
	}

	public void fillForm( UnidadDeMedidaDto unidadDeMedidaDto ) {
		display.setAbreviacion( unidadDeMedidaDto.getAbreviacion() );
		display.setNombre( unidadDeMedidaDto.getNombre() );
		display.setDescripcion( unidadDeMedidaDto.getDescripcion() );
	}
}