package es.jota.gwt.client.activity.unidadDeMedida;

import java.util.List;

import jota.server.dto.FiltroServer;
import jota.server.dto.UnidadDeMedidaDto;

import com.google.gwt.user.client.rpc.AsyncCallback;

import es.jota.gwt.client.miApp;
import es.jota.gwt.client.cellTable.pager.MyPagerPaginator;
import es.jota.gwt.client.display.unidadDeMedida.UnidadDeMedidaDisplayList;
import es.jota.gwt.client.my.MyAbstractActivity;
import es.jota.gwt.client.my.MyAsyncCallback;
import es.jota.gwt.client.place.unidadDeMedida.UnidadDeMedidaPlaceList;
import es.jota.gwt.client.place.unidadDeMedida.UnidadDeMedidaPlaceNew;
import es.jota.gwt.client.service.UnidadDeMedidaGwtService;

public class UnidadDeMedidaActivityList extends MyAbstractActivity<UnidadDeMedidaPlaceList> implements UnidadDeMedidaDisplayList.Listener {
	
	private UnidadDeMedidaDisplayList display;
	private MyPagerPaginator<UnidadDeMedidaDto> pager;
	private boolean canRefresh;

	public UnidadDeMedidaActivityList() {
		canRefresh = false;
		display = miApp.INJECTOR.getUnidadDeMedidaDisplayList();
		display.setListener( this );

		display.setNuevoUrl( UnidadDeMedidaPlaceNew.getUrl() );

		pager = new MyPagerPaginator<UnidadDeMedidaDto>( display.getTabla(), display.getPagerDisplay() ) {
			@Override
			public void loadAsyncData( int start, int length, AsyncCallback<List<UnidadDeMedidaDto>> asyncCallback ) {
				UnidadDeMedidaGwtService.Util.getInstance().findUnidadesDeMedidaByFiltro( start, length, new FiltroServer(), asyncCallback );
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
	public void borrar( Long idUnidadDeMedida ) {
		UnidadDeMedidaGwtService.Util.getInstance().borrarUnidadDeMedidaById( idUnidadDeMedida, new MyAsyncCallback<Void>( "Unidad de medida", "Borrando, id = " + idUnidadDeMedida ) {
			@Override
			public void handleOnSuccess(Void result) {
				pager.increaseRowCount( -1 );
			}
		});
	}
}