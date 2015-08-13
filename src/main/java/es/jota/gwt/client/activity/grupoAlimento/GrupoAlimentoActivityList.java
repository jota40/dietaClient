package es.jota.gwt.client.activity.grupoAlimento;

import java.util.List;

import jota.server.dto.FiltroServer;
import jota.server.dto.GrupoAlimentoDto;

import com.google.gwt.user.client.rpc.AsyncCallback;

import es.jota.gwt.client.miApp;
import es.jota.gwt.client.cellTable.pager.MyPagerPaginator;
import es.jota.gwt.client.display.grupoAlimento.GrupoAlimentoDisplayList;
import es.jota.gwt.client.my.MyAbstractActivity;
import es.jota.gwt.client.my.MyAsyncCallback;
import es.jota.gwt.client.place.grupoAlimento.GrupoAlimentoPlaceList;
import es.jota.gwt.client.place.grupoAlimento.GrupoAlimentoPlaceNew;
import es.jota.gwt.client.service.GrupoAlimentoGwtService;

public class GrupoAlimentoActivityList extends MyAbstractActivity<GrupoAlimentoPlaceList> implements GrupoAlimentoDisplayList.Listener {
	
	private GrupoAlimentoDisplayList display;
	private MyPagerPaginator<GrupoAlimentoDto> pager;
	private boolean canRefresh;

	public GrupoAlimentoActivityList() {
		canRefresh = false;
		display = miApp.INJECTOR.getGrupoAlimentoDisplayList();
		display.setListener( this );

		display.setNuevoUrl( GrupoAlimentoPlaceNew.getUrl() );

		pager = new MyPagerPaginator<GrupoAlimentoDto>( display.getTabla(), display.getPagerDisplay() ) {
			@Override
			public void loadAsyncData( int start, int length, AsyncCallback<List<GrupoAlimentoDto>> asyncCallback ) {
				GrupoAlimentoGwtService.Util.getInstance().findGruposAlimentoByFiltro( start, length, new FiltroServer(), asyncCallback );
			}
		};
	}

	@Override
	protected void start() {
		miApp.INJECTOR.getTopPublicoView().setMenu( display.getMenu() );
		if ( canRefresh ) {
			refresh();
		}
		canRefresh = true;
		containerWidget.setWidget(display.asWidget());
		
	}

	@Override
	public void reset() {
		pager.reset();
	}

	@Override
	public void refresh() {
		pager.refresh();
	}

	@Override
	public void borrar( Long idGrupoAlimento ) {
		GrupoAlimentoGwtService.Util.getInstance().borrarGrupoAlimentoById( idGrupoAlimento, new MyAsyncCallback<Void>( "Grupo alimenticio", "Borrando, id = " + idGrupoAlimento ) {
			@Override
			public void handleOnSuccess(Void result) {
				pager.increaseRowCount( -1 );
			}
		});
	}
}