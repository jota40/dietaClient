package es.jota.gwt.client.activity.grupoAlimento;

import jota.server.dto.GrupoAlimentoDto;
import es.jota.gwt.client.miApp;
import es.jota.gwt.client.display.grupoAlimento.GrupoAlimentoDisplayView;
import es.jota.gwt.client.my.MyAbstractActivity;
import es.jota.gwt.client.my.MyAsyncCallback;
import es.jota.gwt.client.place.grupoAlimento.GrupoAlimentoPlaceEdit;
import es.jota.gwt.client.place.grupoAlimento.GrupoAlimentoPlaceList;
import es.jota.gwt.client.place.grupoAlimento.GrupoAlimentoPlaceNew;
import es.jota.gwt.client.place.grupoAlimento.GrupoAlimentoPlaceView;
import es.jota.gwt.client.service.GrupoAlimentoGwtService;

public class GrupoAlimentoActivityView extends MyAbstractActivity<GrupoAlimentoPlaceView> implements GrupoAlimentoDisplayView.Listener {
	
	private GrupoAlimentoDisplayView display;

	public GrupoAlimentoActivityView() {
		display = miApp.INJECTOR.getGrupoAlimentoDisplayView();
		display.setListener( this );

		display.setListarUrl( GrupoAlimentoPlaceList.getUrl() );
		display.setNuevoUrl( GrupoAlimentoPlaceNew.getUrl() );
	}

	@Override
	protected void start() {
		display.setEditarUrl( GrupoAlimentoPlaceEdit.getUrl( place.getId() ) );
		miApp.INJECTOR.getTopPublicoView().setMenu( display.getMenu() );
		view();
	}

	public void view() {
		GrupoAlimentoGwtService.Util.getInstance().getGrupoAlimentoDtoById( place.getId(), new MyAsyncCallback<GrupoAlimentoDto>( "Grupo alimenticio ", "Leyendo id = " + place.getId() ) {
			@Override
			public void handleOnSuccess( GrupoAlimentoDto grupoAlimentoDto ) {
				if ( grupoAlimentoDto == null ) {
					goTo( GrupoAlimentoPlaceNew.instance() );
				} else {
					fillForm( grupoAlimentoDto );
					containerWidget.setWidget(display.asWidget());
				}
			}
		});
	}

	public void fillForm( GrupoAlimentoDto grupoAlimentoDto ) {
		display.setColor( grupoAlimentoDto.getColor() );
		display.setNombre( grupoAlimentoDto.getNombre() );
		display.setDescripcion( grupoAlimentoDto.getDescripcion() );
	}
}