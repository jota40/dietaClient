package es.jota.gwt.client.display.unidadDeMedida;

import jota.server.dto.UnidadDeMedidaDto;

import com.google.gwt.cell.client.SafeHtmlCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

import es.jota.gwt.client.cellTable.pager.AccionesCell;
import es.jota.gwt.client.cellTable.pager.PagerPaginationDisplay;
import es.jota.gwt.client.place.unidadDeMedida.UnidadDeMedidaPlaceEdit;
import es.jota.gwt.client.place.unidadDeMedida.UnidadDeMedidaPlaceView;
import es.jota.gwt.client.utils.UtilClient;
import es.jota.gwt.client.utils.enums.AccionEnum;
import es.jota.gwt.client.utils.enums.IconoEnum;
import es.jota.gwt.client.widgets.display.MenuItem;

public class UnidadDeMedidaDisplayListImpl extends Composite implements UnidadDeMedidaDisplayList {

	private static UnidadDeMedidaDisplayListUiBinder uiBinder = GWT.create(UnidadDeMedidaDisplayListUiBinder.class);
	interface UnidadDeMedidaDisplayListUiBinder extends UiBinder<Widget, UnidadDeMedidaDisplayListImpl>{}

	@UiField HTMLPanel menu;
	@UiField MenuItem nuevo;
	@UiField Anchor reset;
	@UiField(provided=true) CellTable<UnidadDeMedidaDto> tabla;
	@UiField PagerPaginationDisplay paginator;

	private UnidadDeMedidaDisplayList.Listener listener;

	@Override
	public void setListener( UnidadDeMedidaDisplayList.Listener listener ) {
		this.listener = listener;
	}

	public UnidadDeMedidaDisplayListImpl() {
		tabla = new CellTable<UnidadDeMedidaDto>( 15 );
		initTableColumns();
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("reset")
	public void resetOnClick(ClickEvent event) {
		listener.reset();
	}

	@UiHandler("refresh")
	public void refreshOnClick(ClickEvent event) {
		listener.refresh();
	}

	// Menu
	@Override
	public HTMLPanel getMenu() {
		return menu;
	}

	@Override
	public void setNuevoUrl( String url ) {
		nuevo.setHref( url );
	}

	private void initTableColumns() {
		Column<UnidadDeMedidaDto, SafeHtml> nombreColumn = new Column<UnidadDeMedidaDto, SafeHtml>( new SafeHtmlCell()) {
			@Override
			public SafeHtml getValue( UnidadDeMedidaDto object) {
				Long id = object.getId();
				return UtilClient.TEMPLATES.mainLinkCell( UnidadDeMedidaPlaceEdit.getUrl( id ), IconoEnum.EDITAR.get(), UnidadDeMedidaPlaceView.getUrl( id ), object.getNombre() );
			}
		};

		Column<UnidadDeMedidaDto, SafeHtml> abreviacionColumn = new Column<UnidadDeMedidaDto, SafeHtml>( new SafeHtmlCell()) {
			@Override
			public SafeHtml getValue( UnidadDeMedidaDto object) {
				String cell = object.getAbreviacion();
				return new SafeHtmlBuilder().appendHtmlConstant( cell ).toSafeHtml(); 
			}
		};

		Column<UnidadDeMedidaDto, SafeHtml> descripcionColumn = new Column<UnidadDeMedidaDto, SafeHtml>( new SafeHtmlCell()) {
			@Override
			public SafeHtml getValue( UnidadDeMedidaDto object) {
				String cell = object.getDescripcion();
				return new SafeHtmlBuilder().appendEscapedLines( cell ).toSafeHtml(); 
			}
		};

		AccionesCell accionesCell = new AccionesCell(){
			@Override
			protected void doAction( String accionId, String accionValue ) {
				try {
					Long idUnidadDeMedida = Long.parseLong( accionValue );
					switch ( AccionEnum.getEnum( accionId ) ) {
						case BORRAR: listener.borrar( idUnidadDeMedida );
						break;
					}
				} catch (Exception e) {
				}
			}
		};
		Column<UnidadDeMedidaDto, SafeHtml> accionesColumn = new Column<UnidadDeMedidaDto, SafeHtml>( accionesCell ) {
			@Override
			public SafeHtml getValue( UnidadDeMedidaDto object ) {
				Long id = object.getId();
				SafeHtmlBuilder shb = new SafeHtmlBuilder();
				shb.append( UtilClient.TEMPLATES.accionDelegate( AccionEnum.BORRAR.getId().toString(), id.toString(), AccionEnum.BORRAR.getColor().getBtn(), AccionEnum.BORRAR.getIcono().get() ) );
				return shb.toSafeHtml();
			}
		};

		//		nombreColumn.setSortable(true);

		tabla.addColumn( nombreColumn, "Nombre");
		tabla.addColumn( abreviacionColumn, "Abreviacion");
		tabla.addColumn( descripcionColumn, "Descripcion");
		tabla.addColumn( accionesColumn, "Acciones");
	}
	
	@Override
	public CellTable<UnidadDeMedidaDto> getTabla() {
		return tabla;
	}

	@Override
	public PagerPaginationDisplay getPagerDisplay() {
		return paginator;
	}
}