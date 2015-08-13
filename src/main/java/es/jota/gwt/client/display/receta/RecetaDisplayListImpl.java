package es.jota.gwt.client.display.receta;

import jota.server.dto.RecetaDto;

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
import es.jota.gwt.client.cellTable.pager.MyPager.MyPagerView;
import es.jota.gwt.client.place.receta.RecetaPlaceEdit;
import es.jota.gwt.client.place.receta.RecetaPlaceView;
import es.jota.gwt.client.utils.UtilClient;
import es.jota.gwt.client.utils.enums.AccionEnum;
import es.jota.gwt.client.utils.enums.IconoEnum;
import es.jota.gwt.client.widgets.display.MenuItem;

public class RecetaDisplayListImpl extends Composite implements RecetaDisplayList {

	private static RecetaDisplayListUiBinder uiBinder = GWT.create(RecetaDisplayListUiBinder.class);
	interface RecetaDisplayListUiBinder extends UiBinder<Widget, RecetaDisplayListImpl>{}

	@UiField HTMLPanel menu;
	@UiField MenuItem nuevo;
	@UiField Anchor reset;
	@UiField(provided=true) CellTable<RecetaDto> tabla;
	@UiField MyPagerView paginator;

	private RecetaDisplayList.Listener listener;

	@Override
	public void setListener( RecetaDisplayList.Listener listener ) {
		this.listener = listener;
	}

	public RecetaDisplayListImpl() {
		tabla = new CellTable<RecetaDto>( 15 );
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
		Column<RecetaDto, SafeHtml> nombreColumn = new Column<RecetaDto, SafeHtml>( new SafeHtmlCell()) {
			@Override
			public SafeHtml getValue( RecetaDto object) {
				Long id = object.getId();
				return UtilClient.TEMPLATES.mainLinkCell( RecetaPlaceEdit.getUrl( id ), IconoEnum.EDITAR.get(), RecetaPlaceView.getUrl( id ), object.getNombre() );
			}
		};

		Column<RecetaDto, SafeHtml> descripcionColumn = new Column<RecetaDto, SafeHtml>( new SafeHtmlCell()) {
			@Override
			public SafeHtml getValue( RecetaDto object) {
				String cell = object.getDescripcion();
				return new SafeHtmlBuilder().appendEscapedLines( cell ).toSafeHtml(); 
			}
		};

		Column<RecetaDto, SafeHtml> paxColumn = new Column<RecetaDto, SafeHtml>( new SafeHtmlCell()) {
			@Override
			public SafeHtml getValue( RecetaDto object) {
				String cell = UtilClient.Numeros.number2String( object.getPax() );
				return new SafeHtmlBuilder().appendHtmlConstant( cell ).toSafeHtml(); 
			}
		};

		AccionesCell accionesCell = new AccionesCell(){
			@Override
			protected void doAction( String accionId, String accionValue) {
				try {
					Long idReceta = Long.parseLong( accionValue );
					switch ( AccionEnum.getEnum( accionId ) ) {
						case BORRAR: listener.borrar( idReceta );
						break;
					}
				} catch (Exception e) {
				}
			}
		};
		Column<RecetaDto, SafeHtml> accionesColumn = new Column<RecetaDto, SafeHtml>( accionesCell ) {
			@Override
			public SafeHtml getValue( RecetaDto object ) {
				Long id = object.getId();
				SafeHtmlBuilder shb = new SafeHtmlBuilder();
				shb.append( UtilClient.TEMPLATES.accionDelegate( AccionEnum.BORRAR.getId().toString(), id.toString(), AccionEnum.BORRAR.getColor().getBtn(), AccionEnum.BORRAR.getIcono().get() ) );
//				shb.append( Utils.TEMPLATES.accionLink( RecetaPlaceEdit.getUrl( id ), ColorEnum.AZUL.getBtn(), IconoEnum.EDITAR.get() ) );
//				shb.append( Utils.TEMPLATES.accionLink( RecetaPlaceView.getUrl( id ), ColorEnum.VERDE.getBtn(), IconoEnum.VER.get() ) );
				return shb.toSafeHtml();
			}
		};

//		nombreColumn.setSortable(true);

		paxColumn.setCellStyleNames( "text-right" );
		
		tabla.addColumn( nombreColumn, "nombre");
		tabla.addColumn( descripcionColumn, "Descripcion");
		tabla.addColumn( paxColumn, "Número de comensales");
		tabla.addColumn( accionesColumn, "Acciones");
	}
	
	@Override
	public CellTable<RecetaDto> getTabla() {
		return tabla;
	}

	@Override
	public MyPagerView getPagerDisplay() {
		return paginator;
	}
}