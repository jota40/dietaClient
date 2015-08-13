package es.jota.gwt.client.activity.ingrediente;

import jota.server.dto.IngredienteDto;
import es.jota.gwt.client.miApp;
import es.jota.gwt.client.activity.IDoAfterInsert;
import es.jota.gwt.client.display.ingrediente.IngredienteDisplayNew;
import es.jota.gwt.client.my.MyAbstractActivity;
import es.jota.gwt.client.my.MyAsyncCallback;
import es.jota.gwt.client.place.ingrediente.IngredientePlaceEdit;
import es.jota.gwt.client.place.ingrediente.IngredientePlaceList;
import es.jota.gwt.client.place.ingrediente.IngredientePlaceNew;
import es.jota.gwt.client.service.IngredienteGwtService;

public class IngredienteActivityNew extends MyAbstractActivity<IngredientePlaceNew> implements IngredienteDisplayNew.Listener {
	
	private IngredienteDisplayNew display;

	public IngredienteActivityNew() {
		display = miApp.INJECTOR.getIngredienteDisplayNew();
		display.setListener( this );

		display.setListarUrl( IngredientePlaceList.getUrl() );
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
			public void exec( Long idIngrediente ) {
				goTo( IngredientePlaceList.instance() );
			}
		});
	}

	@Override
	public void guardarYNuevo() {
		guardar( new IDoAfterInsert<Long>() {
			@Override
			public void exec( Long idIngrediente ) {
				start();
			}
		});
	}

	@Override
	public void guardar() {
		guardar( new IDoAfterInsert<Long>() {
			@Override
			public void exec( Long idIngrediente ) {
				goTo( IngredientePlaceEdit.instance( idIngrediente ) );
			}
		});
	}


	private void guardar( final IDoAfterInsert<Long> doAfterInsert ) {
		if ( validate() ) {
			IngredienteGwtService.Util.getInstance().insertarIngrediente( fillObject(), display.getIdUnidadDeMedida(), display.getIdGrupoAlimento(), new MyAsyncCallback<Long>( "Ingrediente", "Insertando", null, "Insertado" ) {
				@Override
				public void handleOnSuccess( Long idIngrediente ) {
					doAfterInsert.exec( idIngrediente );
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

	@Override
	public void limpiar() {
		display.setClean();
	}
}