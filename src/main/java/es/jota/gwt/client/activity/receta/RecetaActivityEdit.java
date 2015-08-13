package es.jota.gwt.client.activity.receta;

import java.util.ArrayList;
import java.util.List;

import jota.server.dto.IngredienteDeRecetaDto;
import jota.server.dto.RecetaDto;

import com.google.gwt.user.client.ui.Widget;

import es.jota.gwt.client.miApp;
import es.jota.gwt.client.activity.IDoAfterUpdate;
import es.jota.gwt.client.display.ingredienteDeReceta.IngredienteDeRecetaDisplayEdit;
import es.jota.gwt.client.display.receta.RecetaDisplayEdit;
import es.jota.gwt.client.my.MyAbstractActivity;
import es.jota.gwt.client.my.MyAsyncCallback;
import es.jota.gwt.client.place.receta.RecetaPlaceEdit;
import es.jota.gwt.client.place.receta.RecetaPlaceList;
import es.jota.gwt.client.place.receta.RecetaPlaceNew;
import es.jota.gwt.client.place.receta.RecetaPlaceView;
import es.jota.gwt.client.service.RecetaGwtService;
import es.jota.gwt.client.widgets.display.TrWidget;

public class RecetaActivityEdit extends MyAbstractActivity<RecetaPlaceEdit> implements RecetaDisplayEdit.Listener, IngredienteDeRecetaDisplayEdit.Listener {

	private RecetaDisplayEdit display;

	private Long idReceta;

	public RecetaActivityEdit() {
		display = miApp.INJECTOR.getRecetaDisplayEdit();
		display.setListener( this );
		display.setListarUrl( RecetaPlaceList.getUrl() );
		display.setNuevoUrl( RecetaPlaceNew.getUrl() );
	}

	@Override
	protected void start() {
		display.setVerUrl( RecetaPlaceView.getUrl( place.getId() ) );
		miApp.INJECTOR.getTopPublicoView().setMenu( display.getMenu() );
		edit();
	}

	private void edit() {
		RecetaGwtService.Util.getInstance().getRecetaDtoById( place.getId(), new MyAsyncCallback<RecetaDto>( "Receta", "Leyendo, id = " + place.getId() ) {
			@Override
			public void handleOnSuccess( RecetaDto recetaDto ) {
				if ( recetaDto == null ) {
					goTo( RecetaPlaceNew.instance() );
				} else {
					idReceta = recetaDto.getId();
					fillForm( recetaDto );
					containerWidget.setWidget(display.asWidget());
				}
			}
		});
	}

	private void fillForm( RecetaDto recetaDto ) {
		display.setNombre( recetaDto.getNombre() );
		display.setDescripcion( recetaDto.getDescripcion() );
		display.setPax( recetaDto.getPax() );
		
		display.getIngredientesDeReceta().clear();
		if ( recetaDto.getIngredientesDeRecetaDto() != null ) {
			for ( IngredienteDeRecetaDto ingredienteDeRecetaDto : recetaDto.getIngredientesDeRecetaDto()) {
				IngredienteDeRecetaDisplayEdit ingredienteDeRecetaDisplayEdit = miApp.INJECTOR.getIngredienteDeRecetaDisplayEdit().setListener( this );
				ingredienteDeRecetaDisplayEdit.set( ingredienteDeRecetaDto );
				display.getIngredientesDeReceta().add( (TrWidget) ingredienteDeRecetaDisplayEdit );
			}
		}
	}

	@Override
	public void modificarYListar() {
		modificar( new IDoAfterUpdate() {
			@Override
			public void exec() {
				goTo( RecetaPlaceList.instance() );
			}
		});
	}

	@Override
	public void modificarYNuevo() {
		modificar( new IDoAfterUpdate() {
			@Override
			public void exec() {
				goTo( RecetaPlaceNew.instance() );
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
			RecetaGwtService.Util.getInstance().modificarReceta( idReceta, fillReceta(), fillIngredientesDeReceta(), new MyAsyncCallback<Void>( "Receta", "Modificando, id = " + place.getId(), null, "Modificada" ) {
				@Override
				public void handleOnSuccess( Void recetaDto ) {
					doAfterUpdate.exec();
				}
			});
		}
	}

	private boolean validate() {
		boolean dev = display.validate();
		return dev;
	}

	private RecetaDto fillReceta() {
		RecetaDto recetaDto = new RecetaDto();
		recetaDto.setNombre( display.getNombre() );
		recetaDto.setDescripcion( display.getDescripcion() );
		recetaDto.setPax( display.getPax() == null ? 0 : display.getPax().intValue() );
		return recetaDto;
	}

	private List<IngredienteDeRecetaDto> fillIngredientesDeReceta() {
		List<IngredienteDeRecetaDto> ingredientesDeRecetaDto = new ArrayList<IngredienteDeRecetaDto>();
		for ( Widget widget : display.getIngredientesDeReceta() ) {
			ingredientesDeRecetaDto.add( ((IngredienteDeRecetaDisplayEdit) widget).get() );
		}
		return ingredientesDeRecetaDto;
	}

	@Override
	public void addIngredientesDeReceta() {
		display.getIngredientesDeReceta().add( (TrWidget) miApp.INJECTOR.getIngredienteDeRecetaDisplayEdit().setListener( this ) );
	}
}