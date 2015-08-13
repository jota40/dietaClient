package es.jota.gwt.client.activity.unidadDeMedida;

import jota.server.dto.UnidadDeMedidaDto;
import es.jota.gwt.client.miApp;
import es.jota.gwt.client.activity.IDoAfterUpdate;
import es.jota.gwt.client.display.unidadDeMedida.UnidadDeMedidaDisplayEdit;
import es.jota.gwt.client.my.MyAbstractActivity;
import es.jota.gwt.client.my.MyAsyncCallback;
import es.jota.gwt.client.place.unidadDeMedida.UnidadDeMedidaPlaceEdit;
import es.jota.gwt.client.place.unidadDeMedida.UnidadDeMedidaPlaceList;
import es.jota.gwt.client.place.unidadDeMedida.UnidadDeMedidaPlaceNew;
import es.jota.gwt.client.place.unidadDeMedida.UnidadDeMedidaPlaceView;
import es.jota.gwt.client.service.UnidadDeMedidaGwtService;

public class UnidadDeMedidaActivityEdit extends MyAbstractActivity<UnidadDeMedidaPlaceEdit> implements UnidadDeMedidaDisplayEdit.Listener {

	private UnidadDeMedidaDisplayEdit display;

	private Long idUnidadDeMedida;

	public UnidadDeMedidaActivityEdit() {
		display = miApp.INJECTOR.getUnidadDeMedidaDisplayEdit();
		display.setListener( this );

		display.setListarUrl( UnidadDeMedidaPlaceList.getUrl() );
		display.setNuevoUrl( UnidadDeMedidaPlaceNew.getUrl() );
	}

	@Override
	protected void start() {
		display.setVerUrl( UnidadDeMedidaPlaceView.getUrl( place.getId() ) );
		miApp.INJECTOR.getTopPublicoView().setMenu( display.getMenu() );
		edit();
	}

	public void edit() {
		UnidadDeMedidaGwtService.Util.getInstance().getUnidadDeMedidaDtoById( place.getId(), new MyAsyncCallback<UnidadDeMedidaDto>( "Unidad de medida", "Leyendo, id = " + place.getId() ) {
			@Override
			public void handleOnSuccess( UnidadDeMedidaDto unidadDeMedidaDto ) {
				if ( unidadDeMedidaDto == null ) {
					goTo( UnidadDeMedidaPlaceNew.instance() );
				} else {
					idUnidadDeMedida = unidadDeMedidaDto.getId();
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

	@Override
	public void modificarYListar() {
		modificar( new IDoAfterUpdate() {
			@Override
			public void exec() {
				goTo( UnidadDeMedidaPlaceList.instance() );
			}
		});
	}

	@Override
	public void modificarYNuevo() {
		modificar( new IDoAfterUpdate() {
			@Override
			public void exec() {
				goTo( UnidadDeMedidaPlaceNew.instance() );
			}
		});
	}

	@Override
	public void modificar() {
		modificar( new IDoAfterUpdate() {
			@Override
			public void exec() {
				edit();
			}
		});
	}

	private void modificar( final IDoAfterUpdate doAfterUpdate ) {
		UnidadDeMedidaGwtService.Util.getInstance().modificarUnidadDeMedida( idUnidadDeMedida, fillObject(), new MyAsyncCallback<Void>( "Unidad de medida", "Modificando, id = " + place.getId(), null, "Modificada" ) {
			@Override
			public void handleOnSuccess( Void unidadDeMedidaDto ) {
				doAfterUpdate.exec();
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
}