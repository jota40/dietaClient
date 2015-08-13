package es.jota.gwt.client.display.unidadDeMedida;

import jota.server.dto.UnidadDeMedidaDto;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;

import es.jota.gwt.client.cellTable.pager.MyPager.MyPagerView;

public interface UnidadDeMedidaDisplayList extends IsWidget {
	void setListener( UnidadDeMedidaDisplayList.Listener listener );
	public interface Listener {
		void reset();
		void refresh();
		void borrar( Long idUnidadDeMedida );
	}
	// menu
	HTMLPanel getMenu();
	void setNuevoUrl( String url );
	CellTable<UnidadDeMedidaDto> getTabla();
	MyPagerView getPagerDisplay();
}