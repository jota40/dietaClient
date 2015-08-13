package es.jota.gwt.client.display.ingrediente;

import jota.server.dto.IngredienteDto;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;

import es.jota.gwt.client.cellTable.pager.MyPager.MyPagerView;

public interface IngredienteDisplayList extends IsWidget {
	void setListener( IngredienteDisplayList.Listener listener );
	public interface Listener {
		void reset();
		void refresh();
		void borrar( Long idIngrediente );
	}
	// menu
	HTMLPanel getMenu();
	void setNuevoUrl( String url );
	CellTable<IngredienteDto> getTabla();
	MyPagerView getPagerDisplay();
}