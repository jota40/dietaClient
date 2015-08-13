package es.jota.gwt.client.display.grupoAlimento;

import jota.server.dto.GrupoAlimentoDto;

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
import es.jota.gwt.client.place.grupoAlimento.GrupoAlimentoPlaceEdit;
import es.jota.gwt.client.place.grupoAlimento.GrupoAlimentoPlaceView;
import es.jota.gwt.client.utils.UtilClient;
import es.jota.gwt.client.utils.enums.AccionEnum;
import es.jota.gwt.client.utils.enums.IconoEnum;
import es.jota.gwt.client.widgets.display.MenuItem;

public class GrupoAlimentoDisplayListImpl extends Composite implements GrupoAlimentoDisplayList {

	private static GrupoAlimentoDisplayListUiBinder uiBinder = GWT.create(GrupoAlimentoDisplayListUiBinder.class);
	interface GrupoAlimentoDisplayListUiBinder extends UiBinder<Widget, GrupoAlimentoDisplayListImpl>{}

	@UiField HTMLPanel menu;
	@UiField MenuItem nuevo;
	@UiField Anchor reset;
	@UiField(provided=true) CellTable<GrupoAlimentoDto> tabla;
	@UiField PagerPaginationDisplay paginator;

	private GrupoAlimentoDisplayList.Listener listener;

	@Override
	public void setListener( GrupoAlimentoDisplayList.Listener listener ) {
		this.listener = listener;
	}

	public GrupoAlimentoDisplayListImpl() {
		tabla = new CellTable<GrupoAlimentoDto>( 15 );
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
		Column<GrupoAlimentoDto, SafeHtml> nombreColumn = new Column<GrupoAlimentoDto, SafeHtml>( new SafeHtmlCell()) {
			@Override
			public SafeHtml getValue( GrupoAlimentoDto object) {
				Long id = object.getId();
				return UtilClient.TEMPLATES.mainLinkCell( GrupoAlimentoPlaceEdit.getUrl( id ), IconoEnum.EDITAR.get(), GrupoAlimentoPlaceView.getUrl( id ), object.getNombre() );
			}
		};

		Column<GrupoAlimentoDto, SafeHtml> colorColumn = new Column<GrupoAlimentoDto, SafeHtml>( new SafeHtmlCell()) {
			@Override
			public SafeHtml getValue( GrupoAlimentoDto object) {
				String cell = object.getColor();
				return new SafeHtmlBuilder().appendHtmlConstant( cell ).toSafeHtml(); 
			}
		};

		Column<GrupoAlimentoDto, SafeHtml> descripcionColumn = new Column<GrupoAlimentoDto, SafeHtml>( new SafeHtmlCell()) {
			@Override
			public SafeHtml getValue( GrupoAlimentoDto object) {
				String cell = object.getDescripcion();
				return new SafeHtmlBuilder().appendEscapedLines( cell ).toSafeHtml(); 
			}
		};

		AccionesCell accionesCell = new AccionesCell(){
			@Override
			protected void doAction( String accionId, String accionValue ) {
				try {
					Long idGrupoAlimento = Long.parseLong( accionValue );
					switch ( AccionEnum.getEnum( accionId ) ) {
						case BORRAR: listener.borrar( idGrupoAlimento );
						break;
					}
				} catch (Exception e) {
				}
			}
		};
		Column<GrupoAlimentoDto, SafeHtml> accionesColumn = new Column<GrupoAlimentoDto, SafeHtml>( accionesCell ) {
			@Override
			public SafeHtml getValue( GrupoAlimentoDto object ) {
				Long id = object.getId();
				SafeHtmlBuilder shb = new SafeHtmlBuilder();
				shb.append( UtilClient.TEMPLATES.accionDelegate( AccionEnum.BORRAR.getId().toString(), id.toString(), AccionEnum.BORRAR.getColor().getBtn(), AccionEnum.BORRAR.getIcono().get() ) );
				return shb.toSafeHtml();
			}
		};

		//		nombreColumn.setSortable(true);

		tabla.addColumn( nombreColumn, "Nombre");
		tabla.addColumn( colorColumn, "Color");
		tabla.addColumn( descripcionColumn, "Descripcion");
		tabla.addColumn( accionesColumn, "Acciones");
	}
	
	@Override
	public CellTable<GrupoAlimentoDto> getTabla() {
		return tabla;
	}

	@Override
	public PagerPaginationDisplay getPagerDisplay() {
		return paginator;
	}
}