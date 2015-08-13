package es.jota.gwt.client.activity.ingrediente;

import jota.server.dto.IngredienteDto;
import es.jota.gwt.client.miApp;
import es.jota.gwt.client.activity.IDoAfterUpdate;
import es.jota.gwt.client.display.ingrediente.IngredienteDisplayEdit;
import es.jota.gwt.client.my.MyAbstractActivity;
import es.jota.gwt.client.my.MyAsyncCallback;
import es.jota.gwt.client.place.ingrediente.IngredientePlaceEdit;
import es.jota.gwt.client.place.ingrediente.IngredientePlaceList;
import es.jota.gwt.client.place.ingrediente.IngredientePlaceNew;
import es.jota.gwt.client.place.ingrediente.IngredientePlaceView;
import es.jota.gwt.client.service.IngredienteGwtService;

public class IngredienteActivityEdit extends MyAbstractActivity<IngredientePlaceEdit> implements IngredienteDisplayEdit.Listener {

	private IngredienteDisplayEdit display;

	private Long idIngrediente;

	public IngredienteActivityEdit() {
		display = miApp.INJECTOR.getIngredienteDisplayEdit();
		display.setListener( this );

		display.setListarUrl( IngredientePlaceList.getUrl() );
		display.setNuevoUrl( IngredientePlaceNew.getUrl() );
	}

	@Override
	protected void start() {
		display.setVerUrl( IngredientePlaceView.getUrl( place.getId() ) );
		miApp.INJECTOR.getTopPublicoView().setMenu( display.getMenu() );
		edit();
	}

	private void edit() {
		IngredienteGwtService.Util.getInstance().getIngredienteDtoById( place.getId(), new MyAsyncCallback<IngredienteDto>( "Ingrediente", "Leyendo, id = " + place.getId() ) {
			@Override
			public void handleOnSuccess( IngredienteDto ingredienteDto ) {
				if ( ingredienteDto == null ) {
					goTo( IngredientePlaceNew.instance() );
				} else {
					idIngrediente = ingredienteDto.getId();
					fillForm( ingredienteDto );
					containerWidget.setWidget(display.asWidget());
				}
			}
		});
	}

	private void fillForm( IngredienteDto ingredienteDto ) {
		display.setNombre( ingredienteDto.getNombre() );
		display.setDescripcion( ingredienteDto.getDescripcion() );
		display.setPesoPorRacion( ingredienteDto.getPesoPorRacion() );
		display.setCoste( ingredienteDto.getCoste() );
		display.setUnidadDeMedida( ingredienteDto.getUnidadDeMedida() );
		display.setGrupoAlimento( ingredienteDto.getGrupoAlimento() );
	}

	@Override
	public void modificarYListar() {
		modificar( new IDoAfterUpdate() {
			@Override
			public void exec() {
				goTo( IngredientePlaceList.instance() );
			}
		});
	}

	@Override
	public void modificarYNuevo() {
		modificar( new IDoAfterUpdate() {
			@Override
			public void exec() {
				goTo( IngredientePlaceNew.instance() );
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
		if ( validate() ) {
			IngredienteGwtService.Util.getInstance().modificarIngrediente( idIngrediente, fillObject(), display.getIdUnidadDeMedida(), display.getIdGrupoAlimento(), new MyAsyncCallback<Void>( "Ingrediente", "Modificando, id = " + place.getId(), null, "Modificado" ) {
				@Override
				public void handleOnSuccess( Void ingredienteDto ) {
					doAfterUpdate.exec();
				}
			});
		}
	}

	private boolean validate() {
		boolean dev = display.validate();
		return dev;
	}

	private IngredienteDto fillObject() {
		IngredienteDto ingredienteDto = new IngredienteDto();
		ingredienteDto.setNombre( display.getNombre() );
		ingredienteDto.setDescripcion( display.getDescripcion() );
		ingredienteDto.setPesoPorRacion( display.getPesoPorRacion() );
		ingredienteDto.setCoste( display.getCoste() );
		return ingredienteDto;
	}
}