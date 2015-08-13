package es.jota.gwt.client.activity.grupoAlimento;

import jota.server.dto.GrupoAlimentoDto;
import es.jota.gwt.client.miApp;
import es.jota.gwt.client.activity.IDoAfterInsert;
import es.jota.gwt.client.display.grupoAlimento.GrupoAlimentoDisplayNew;
import es.jota.gwt.client.my.MyAbstractActivity;
import es.jota.gwt.client.my.MyAsyncCallback;
import es.jota.gwt.client.place.grupoAlimento.GrupoAlimentoPlaceEdit;
import es.jota.gwt.client.place.grupoAlimento.GrupoAlimentoPlaceList;
import es.jota.gwt.client.place.grupoAlimento.GrupoAlimentoPlaceNew;
import es.jota.gwt.client.service.GrupoAlimentoGwtService;

public class GrupoAlimentoActivityNew extends MyAbstractActivity<GrupoAlimentoPlaceNew> implements GrupoAlimentoDisplayNew.Listener {
	
	private GrupoAlimentoDisplayNew display;

	public GrupoAlimentoActivityNew() {
		display = miApp.INJECTOR.getGrupoAlimentoDisplayNew();
		display.setListener( this );

		display.setListarUrl( GrupoAlimentoPlaceList.getUrl() );
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
			public void exec( Long idGrupoAlimento ) {
				goTo( GrupoAlimentoPlaceList.instance() );
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
			public void exec( Long idGrupoAlimento ) {
				goTo( GrupoAlimentoPlaceEdit.instance( idGrupoAlimento ) );
			}
		});
	}

	private void guardar( final IDoAfterInsert<Long> doAfterInsert ) {
		GrupoAlimentoGwtService.Util.getInstance().insertarGrupoAlimento( fillObject(), new MyAsyncCallback<Long>( "Grupo alimenticio", "Insertando", null, "Insertado" ) {
			@Override
			public void handleOnSuccess( Long idGrupoAlimento ) {
				doAfterInsert.exec( idGrupoAlimento );
			}
		});
	}

	private GrupoAlimentoDto fillObject() {
		GrupoAlimentoDto grupoAlimentoDto = new GrupoAlimentoDto();
		grupoAlimentoDto.setColor( display.getColor() );
		grupoAlimentoDto.setNombre( display.getNombre() );
		grupoAlimentoDto.setDescripcion( display.getDescripcion() );
		return grupoAlimentoDto;
	}

	@Override
	public void limpiar() {
		display.setClean();
	}
}