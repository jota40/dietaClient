package es.jota.gwt.client.activity.unidadDeMedida;

import jota.server.dto.UnidadDeMedidaDto;
import es.jota.gwt.client.miApp;
import es.jota.gwt.client.activity.IDoAfterInsert;
import es.jota.gwt.client.display.unidadDeMedida.UnidadDeMedidaDisplayNew;
import es.jota.gwt.client.my.MyAbstractActivity;
import es.jota.gwt.client.my.MyAsyncCallback;
import es.jota.gwt.client.place.unidadDeMedida.UnidadDeMedidaPlaceEdit;
import es.jota.gwt.client.place.unidadDeMedida.UnidadDeMedidaPlaceList;
import es.jota.gwt.client.place.unidadDeMedida.UnidadDeMedidaPlaceNew;
import es.jota.gwt.client.service.UnidadDeMedidaGwtService;

public class UnidadDeMedidaActivityNew extends MyAbstractActivity<UnidadDeMedidaPlaceNew> implements UnidadDeMedidaDisplayNew.Listener {
	
	private UnidadDeMedidaDisplayNew display;

	public UnidadDeMedidaActivityNew() {
		display = miApp.INJECTOR.getUnidadDeMedidaDisplayNew();
		display.setListener( this );

		display.setListarUrl( UnidadDeMedidaPlaceList.getUrl() );
	}

	@Override
	protected void start() {
		miApp.INJECTOR.getTopPublicoView().setMenu( display.getMenu() );
		display.setClean();
		containerWidget.setWidget(display.asWidget());
	}

	@Override
	public void guardarYListar() {
		guardar( new IDoAfterInsert<Long>() {
			@Override
			public void exec( Long idUnidadDeMedida ) {
				goTo( UnidadDeMedidaPlaceList.instance() );
			}
		});
	}

	@Override
	public void guardarYNuevo() {
		guardar( new IDoAfterInsert<Long>() {
			@Override
			public void exec( Long idReceta ) {
				start();
			}
		});
	}

	@Override
	public void guardar() {
		guardar( new IDoAfterInsert<Long>() {
			@Override
			public void exec( Long idUnidadDeMedida ) {
				goTo( UnidadDeMedidaPlaceEdit.instance( idUnidadDeMedida ) );
			}
		});
	}

	private void guardar( final IDoAfterInsert<Long> doAfterInsert ) {
		UnidadDeMedidaGwtService.Util.getInstance().insertarUnidadDeMedida( fillObject(), new MyAsyncCallback<Long>( "Unidad de medida", "Insertando", null, "Insertado" ) {
			@Override
			public void handleOnSuccess( Long idUnidadDeMedida ) {
				doAfterInsert.exec( idUnidadDeMedida );
			}
		});
	}

	private UnidadDeMedidaDto fillObject() {
		UnidadDeMedidaDto unidadDeMedidaDto = new UnidadDeMedidaDto();
		unidadDeMedidaDto.setAbreviacion( display.getAbreviacion() );
		unidadDeMedidaDto.setNombre( display.getNombre() );
		unidadDeMedidaDto.setDescripcion( display.getDescripcion() );
		return unidadDeMedidaDto;
	}

	@Override
	public void limpiar() {
		display.setClean();
	}
}