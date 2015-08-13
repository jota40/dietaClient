package es.jota.gwt.client.activity.receta;

import java.util.List;

import jota.server.dto.FiltroServer;
import jota.server.dto.RecetaDto;

import com.google.gwt.user.client.rpc.AsyncCallback;

import es.jota.gwt.client.miApp;
import es.jota.gwt.client.cellTable.pager.MyPagerScroller;
import es.jota.gwt.client.display.receta.RecetaDisplayList;
import es.jota.gwt.client.my.MyAbstractActivity;
import es.jota.gwt.client.my.MyAsyncCallback;
import es.jota.gwt.client.place.receta.RecetaPlaceList;
import es.jota.gwt.client.place.receta.RecetaPlaceNew;
import es.jota.gwt.client.service.RecetaGwtService;

public class RecetaActivityList extends MyAbstractActivity<RecetaPlaceList> implements RecetaDisplayList.Listener {
	
	private RecetaDisplayList display;
	private MyPagerScroller<RecetaDto> pager;
	private boolean canRefresh;

	public RecetaActivityList() {
		canRefresh = false;
		display = miApp.INJECTOR.getRecetaDisplayList();
		display.setListener( this );

		display.setNuevoUrl( RecetaPlaceNew.getUrl() );

		pager = new MyPagerScroller<RecetaDto>( display.getTabla(), display.getPagerDisplay() ) {
			@Override
			public void loadAsyncData( int start, int length, AsyncCallback<List<RecetaDto>> asyncCallback ) {
				RecetaGwtService.Util.getInstance().findRecetasByFiltro( start, length, new FiltroServer(), asyncCallback );
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
	public void borrar( Long idReceta ) {
		RecetaGwtService.Util.getInstance().borrarRecetaById( idReceta, new MyAsyncCallback<Void>( "Receta", "Borrando, id = " + idReceta ) {
			@Override
			public void handleOnSuccess(Void result) {
				refresh();
			}
		});
	}
}