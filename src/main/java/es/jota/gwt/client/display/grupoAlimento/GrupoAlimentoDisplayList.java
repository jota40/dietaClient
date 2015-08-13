package es.jota.gwt.client.display.grupoAlimento;

import jota.server.dto.GrupoAlimentoDto;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;

import es.jota.gwt.client.cellTable.pager.MyPager.MyPagerView;

public interface GrupoAlimentoDisplayList extends IsWidget {
	void setListener( GrupoAlimentoDisplayList.Listener listener );
	public interface Listener {
		void reset();
		void refresh();
		void borrar( Long idGrupoAlimento );
	}
	// menu
	HTMLPanel getMenu();
	void setNuevoUrl( String url );
	CellTable<GrupoAlimentoDto> getTabla();
	MyPagerView getPagerDisplay();
}