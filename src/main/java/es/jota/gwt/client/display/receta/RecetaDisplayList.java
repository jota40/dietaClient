package es.jota.gwt.client.display.receta;

import jota.server.dto.RecetaDto;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;

import es.jota.gwt.client.cellTable.pager.MyPager.MyPagerView;

public interface RecetaDisplayList extends IsWidget {
	void setListener( RecetaDisplayList.Listener listener );
	public interface Listener {
		void reset();
		void refresh();
		void borrar( Long idIngrediente );
	}
	// menu
	HTMLPanel getMenu();
	void setNuevoUrl( String url );

	CellTable<RecetaDto> getTabla();
	MyPagerView getPagerDisplay();
}