package es.jota.gwt.client.activity.receta;

import java.util.ArrayList;
import java.util.List;

import jota.server.dto.IngredienteDeRecetaDto;
import jota.server.dto.RecetaDto;

import com.google.gwt.user.client.ui.Widget;

import es.jota.gwt.client.miApp;
import es.jota.gwt.client.activity.IDoAfterInsert;
import es.jota.gwt.client.display.ingredienteDeReceta.IngredienteDeRecetaDisplayEdit;
import es.jota.gwt.client.display.receta.RecetaDisplayNew;
import es.jota.gwt.client.my.MyAbstractActivity;
import es.jota.gwt.client.my.MyAsyncCallback;
import es.jota.gwt.client.place.receta.RecetaPlaceEdit;
import es.jota.gwt.client.place.receta.RecetaPlaceList;
import es.jota.gwt.client.place.receta.RecetaPlaceNew;
import es.jota.gwt.client.service.RecetaGwtService;
import es.jota.gwt.client.widgets.display.TrWidget;

public class RecetaActivityNew extends MyAbstractActivity<RecetaPlaceNew> implements RecetaDisplayNew.Listener, IngredienteDeRecetaDisplayEdit.Listener {
	
	private RecetaDisplayNew display;

	public RecetaActivityNew() {
		display = miApp.INJECTOR.getRecetaDisplayNew();
		display.setListener( this );

		display.setListarUrl( RecetaPlaceList.getUrl() );
	}

	@Override
	protected void start() {
		miApp.INJECTOR.getTopPublicoView().setMenu( display.getMenu() );
		limpiar();
		containerWidget.setWidget(display.asWidget());
	}

	@Override
	public void limpiar() {
		display.clear();
		display.getIngredientesDeReceta().clear();
	}

	@Override
	public void guardarYListar() {
		guardar( new IDoAfterInsert<Long>() {
			@Override
			public void exec( Long idReceta ) {
				goTo( RecetaPlaceList.instance() );
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
			public void exec( Long idReceta ) {
				goTo( RecetaPlaceEdit.instance( idReceta ) );
			}
		});
	}

	private void guardar( final IDoAfterInsert<Long> doAfterInsert ) {
		if ( validate() ) {
			RecetaGwtService.Util.getInstance().insertarReceta( fillRceta(), fillIngredientesDeReceta(), new MyAsyncCallback<Long>( "Receta", "Insertando", null, "Insertado" ) {
				@Override
				public void handleOnSuccess( Long idReceta ) {
					doAfterInsert.exec( idReceta );
				}
			});
		}
	}

	private boolean validate() {
		boolean dev = display.validate();
		return dev;
	}

	private RecetaDto fillRceta() {
		RecetaDto recetaDto = new RecetaDto();
		recetaDto.setNombre( display.getNombre() );
		recetaDto.setDescripcion( display.getDescripcion() );
		recetaDto.setPax( display.getPax() == null ? 0 : display.getPax().intValue() );
		return recetaDto;
	}

	private List<IngredienteDeRecetaDto> fillIngredientesDeReceta() {
		List<IngredienteDeRecetaDto> ingredientesDeRecetaDto = new ArrayList<IngredienteDeRecetaDto>();
		for ( Widget widget : display.getIngredientesDeReceta() ) {
			IngredienteDeRecetaDisplayEdit ingredienteDeRecetaDisplay = (IngredienteDeRecetaDisplayEdit) widget;
			ingredientesDeRecetaDto.add( ingredienteDeRecetaDisplay.get() );
		}
		return ingredientesDeRecetaDto;
	}

	@Override
	public void addIngredientesDeReceta() {
		display.getIngredientesDeReceta().add( (TrWidget) miApp.INJECTOR.getIngredienteDeRecetaDisplayEdit().setListener( this ) );
	}
}