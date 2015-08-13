package es.jota.gwt.client.activity.grupoAlimento;

import jota.server.dto.GrupoAlimentoDto;
import es.jota.gwt.client.miApp;
import es.jota.gwt.client.activity.IDoAfterUpdate;
import es.jota.gwt.client.display.grupoAlimento.GrupoAlimentoDisplayEdit;
import es.jota.gwt.client.my.MyAbstractActivity;
import es.jota.gwt.client.my.MyAsyncCallback;
import es.jota.gwt.client.place.grupoAlimento.GrupoAlimentoPlaceEdit;
import es.jota.gwt.client.place.grupoAlimento.GrupoAlimentoPlaceList;
import es.jota.gwt.client.place.grupoAlimento.GrupoAlimentoPlaceNew;
import es.jota.gwt.client.place.grupoAlimento.GrupoAlimentoPlaceView;
import es.jota.gwt.client.service.GrupoAlimentoGwtService;

public class GrupoAlimentoActivityEdit extends MyAbstractActivity<GrupoAlimentoPlaceEdit> implements GrupoAlimentoDisplayEdit.Listener {

	private GrupoAlimentoDisplayEdit display;

	private Long idGrupoAlimento;

	public GrupoAlimentoActivityEdit() {
		display = miApp.INJECTOR.getGrupoAlimentoDisplayEdit();
		display.setListener( this );

		display.setListarUrl( GrupoAlimentoPlaceList.getUrl() );
		display.setNuevoUrl( GrupoAlimentoPlaceNew.getUrl() );
	}

	@Override
	protected void start() {
		display.setVerUrl( GrupoAlimentoPlaceView.getUrl( place.getId() ) );
		miApp.INJECTOR.getTopPublicoView().setMenu( display.getMenu() );
		edit();
	}

	public void edit() {
		GrupoAlimentoGwtService.Util.getInstance().getGrupoAlimentoDtoById( place.getId(), new MyAsyncCallback<GrupoAlimentoDto>( "Grupo alimenticio", "Leyendo, id = " + place.getId() ) {
			@Override
			public void handleOnSuccess( GrupoAlimentoDto grupoAlimentoDto ) {
				if ( grupoAlimentoDto == null ) {
					goTo( GrupoAlimentoPlaceNew.instance() );
				} else {
					idGrupoAlimento = grupoAlimentoDto.getId();
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

	@Override
	public void modificarYListar() {
		modificar( new IDoAfterUpdate() {
			@Override
			public void exec() {
				goTo( GrupoAlimentoPlaceList.instance() );
			}
		});
	}

	@Override
	public void modificarYNuevo() {
		modificar( new IDoAfterUpdate() {
			@Override
			public void exec() {
				goTo( GrupoAlimentoPlaceNew.instance() );
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
		GrupoAlimentoGwtService.Util.getInstance().modificarGrupoAlimento( idGrupoAlimento, fillObject(), new MyAsyncCallback<Void>( "Grupo alimenticio", "Modificando, id = " + place.getId(), null, "Modificada" ) {
			@Override
			public void handleOnSuccess( Void grupoAlimentoDto ) {
				doAfterUpdate.exec();
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
}