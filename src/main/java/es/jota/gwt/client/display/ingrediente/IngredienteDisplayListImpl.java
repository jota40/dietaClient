package es.jota.gwt.client.display.ingrediente;

import jota.server.dto.IngredienteDto;

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
import es.jota.gwt.client.place.ingrediente.IngredientePlaceEdit;
import es.jota.gwt.client.place.ingrediente.IngredientePlaceView;
import es.jota.gwt.client.utils.UtilClient;
import es.jota.gwt.client.utils.enums.AccionEnum;
import es.jota.gwt.client.utils.enums.IconoEnum;
import es.jota.gwt.client.widgets.display.MenuItem;

public class IngredienteDisplayListImpl extends Composite implements IngredienteDisplayList {

	private static IngredienteDisplayListUiBinder uiBinder = GWT.create(IngredienteDisplayListUiBinder.class);
	interface IngredienteDisplayListUiBinder extends UiBinder<Widget, IngredienteDisplayListImpl>{}

	@UiField HTMLPanel menu;
	@UiField MenuItem nuevo;
	@UiField Anchor reset;
	@UiField(provided=true) CellTable<IngredienteDto> tabla;
	@UiField MyPagerView paginator;

	private IngredienteDisplayList.Listener listener;

	@Override
	public void setListener( IngredienteDisplayList.Listener listener ) {
		this.listener = listener;
	}

	public IngredienteDisplayListImpl() {
		tabla = new CellTable<IngredienteDto>( 15 );
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
		Column<IngredienteDto, SafeHtml> nombreColumn = new Column<IngredienteDto, SafeHtml>( new SafeHtmlCell()) {
			@Override
			public SafeHtml getValue( IngredienteDto object) {
				Long id = object.getId();
				return UtilClient.TEMPLATES.mainLinkCell( IngredientePlaceEdit.getUrl( id ), IconoEnum.EDITAR.get(), IngredientePlaceView.getUrl( id ), object.getNombre() );
			}
		};

		Column<IngredienteDto, SafeHtml> descripcionColumn = new Column<IngredienteDto, SafeHtml>( new SafeHtmlCell()) {
			@Override
			public SafeHtml getValue( IngredienteDto object) {
				String cell = object.getDescripcion();
				return new SafeHtmlBuilder().appendEscapedLines( cell ).toSafeHtml(); 
			}
		};

		Column<IngredienteDto, SafeHtml> pesoPorRacionColumn = new Column<IngredienteDto, SafeHtml>( new SafeHtmlCell()) {
			@Override
			public SafeHtml getValue( IngredienteDto object) {
				String cell = UtilClient.Numeros.number2String( object.getPesoPorRacion() );
				return new SafeHtmlBuilder().appendHtmlConstant( cell ).toSafeHtml(); 
			}
		};

		Column<IngredienteDto, SafeHtml> costeColumn = new Column<IngredienteDto, SafeHtml>( new SafeHtmlCell()) {
			@Override
			public SafeHtml getValue( IngredienteDto object) {
				String cell = UtilClient.Numeros.number2String( object.getCoste(), "€" );
				return new SafeHtmlBuilder().appendHtmlConstant( cell ).toSafeHtml(); 
			}
		};

		Column<IngredienteDto, SafeHtml> unidadDeMedidaColumn = new Column<IngredienteDto, SafeHtml>( new SafeHtmlCell()) {
			@Override
			public SafeHtml getValue( IngredienteDto object) {
				String cell = object.getUnidadDeMedida() == null ? "" : object.getUnidadDeMedida().getNombre();
				return new SafeHtmlBuilder().appendHtmlConstant( cell ).toSafeHtml(); 
			}
		};
		
		Column<IngredienteDto, SafeHtml> grupoAlimentoColumn = new Column<IngredienteDto, SafeHtml>( new SafeHtmlCell()) {
			@Override
			public SafeHtml getValue( IngredienteDto object) {
				String cell = object.getGrupoAlimento() == null ? "" : object.getGrupoAlimento().getNombre();
				return new SafeHtmlBuilder().appendHtmlConstant( cell ).toSafeHtml(); 
			}
		};

		AccionesCell accionesCell = new AccionesCell(){
			@Override
			protected void doAction( String accionId, String accionValue ) {
				try {
					Long idIngrediente = Long.parseLong( accionValue );
					switch ( AccionEnum.getEnum( accionId ) ) {
						case BORRAR: listener.borrar( idIngrediente );
						break;
					}
				} catch (Exception e) {
				}
			}
		};
		Column<IngredienteDto, SafeHtml> accionesColumn = new Column<IngredienteDto, SafeHtml>( accionesCell ) {
			@Override
			public SafeHtml getValue( IngredienteDto object ) {
				Long id = object.getId();
				SafeHtmlBuilder shb = new SafeHtmlBuilder();
				shb.append( UtilClient.TEMPLATES.accionDelegate( AccionEnum.BORRAR.getId().toString(), id.toString(), AccionEnum.BORRAR.getColor().getBtn(), AccionEnum.BORRAR.getIcono().get() ) );
//				shb.append( Utils.TEMPLATES.accionLink( IngredientePlaceEdit.getUrl( id ), ColorEnum.AZUL.getBtn(), IconoEnum.EDITAR.get() ) );
//				shb.append( Utils.TEMPLATES.accionLink( IngredientePlaceView.getUrl( id ), ColorEnum.VERDE.getBtn(), IconoEnum.VER.get() ) );
				return shb.toSafeHtml();
			}
		};

//		nombreColumn.setSortable(true);
		
		pesoPorRacionColumn.setCellStyleNames("text-right");
		costeColumn.setCellStyleNames("text-right");

		tabla.addColumn( nombreColumn, "nombre");
		tabla.addColumn( descripcionColumn, "Descripcion");
		tabla.addColumn( pesoPorRacionColumn, "Peso por racion");
		tabla.addColumn( costeColumn, "Coste");
		tabla.addColumn( unidadDeMedidaColumn, "Unidad de medida");
		tabla.addColumn( grupoAlimentoColumn, "Grupo alimenticio ");
		tabla.addColumn( accionesColumn, "Acciones");
	}
	
	@Override
	public CellTable<IngredienteDto> getTabla() {
		return tabla;
	}

	@Override
	public MyPagerView getPagerDisplay() {
		return paginator;
	}
}