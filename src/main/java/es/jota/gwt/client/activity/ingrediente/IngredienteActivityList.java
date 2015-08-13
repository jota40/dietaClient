package es.jota.gwt.client.activity.ingrediente;

import java.util.List;

import jota.server.dto.FiltroServer;
import jota.server.dto.IngredienteDto;

import com.google.gwt.user.client.rpc.AsyncCallback;

import es.jota.gwt.client.miApp;
import es.jota.gwt.client.cellTable.pager.MyPagerScroller;
import es.jota.gwt.client.display.ingrediente.IngredienteDisplayList;
import es.jota.gwt.client.my.MyAbstractActivity;
import es.jota.gwt.client.my.MyAsyncCallback;
import es.jota.gwt.client.place.ingrediente.IngredientePlaceList;
import es.jota.gwt.client.place.ingrediente.IngredientePlaceNew;
import es.jota.gwt.client.service.IngredienteGwtService;

public class IngredienteActivityList extends MyAbstractActivity<IngredientePlaceList> implements IngredienteDisplayList.Listener {
	
	private IngredienteDisplayList display;
	private MyPagerScroller<IngredienteDto> pager;
	private boolean canRefresh;

	public IngredienteActivityList() {
		canRefresh = false;
		display = miApp.INJECTOR.getIngredienteDisplayList();
		display.setListener( this );

		display.setNuevoUrl( IngredientePlaceNew.getUrl() );

		pager = new MyPagerScroller<IngredienteDto>( display.getTabla(), display.getPagerDisplay() ) {
			@Override
			public void loadAsyncData( int start, int length, AsyncCallback<List<IngredienteDto>> asyncCallback ) {
				IngredienteGwtService.Util.getInstance().findIngredientesByFiltro( start, length, new FiltroServer(), asyncCallback );
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
	public void borrar( Long idIngrediente ) {
		IngredienteGwtService.Util.getInstance().borrarIngredienteById( idIngrediente, new MyAsyncCallback<Void>( "Ingrediente", "Borrando, id = " + idIngrediente ) {
			@Override
			public void handleOnSuccess(Void result) {
				refresh();
			}
		});
	}
}