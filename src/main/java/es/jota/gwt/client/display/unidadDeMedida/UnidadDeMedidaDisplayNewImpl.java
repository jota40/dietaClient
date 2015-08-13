package es.jota.gwt.client.display.unidadDeMedida;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.TextAreaElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

import es.jota.gwt.client.widgets.display.MenuItem;

public class UnidadDeMedidaDisplayNewImpl extends Composite implements UnidadDeMedidaDisplayNew {
	
	private static UnidadDeMedidaDisplayNewUiBinder uiBinder = GWT.create(UnidadDeMedidaDisplayNewUiBinder.class);
	interface UnidadDeMedidaDisplayNewUiBinder extends UiBinder<Widget, UnidadDeMedidaDisplayNewImpl>{}

	@UiField HTMLPanel menu;
	@UiField MenuItem listar;

	@UiField InputElement abreviacion;
	@UiField InputElement nombre;
	@UiField TextAreaElement descripcion;

	private UnidadDeMedidaDisplayNew.Listener listener;

	@UiHandler("guardarYListar")
	public void guardarYListarOnClick(ClickEvent event) {
		listener.guardarYListar();
	}

	@UiHandler("guardarYNuevo")
	public void guardarYNuevoOnClick(ClickEvent event) {
		listener.guardarYNuevo();
	}

	@UiHandler("guardar")
	public void guardarOnClick(ClickEvent event) {
		listener.guardar();
	}

	@UiHandler("limpiar")
	public void limpiarOnClick(ClickEvent event) {
		listener.limpiar();
	}

	@Override
	public void setListener( UnidadDeMedidaDisplayNew.Listener listener ) {
		this.listener = listener;
	}

	public UnidadDeMedidaDisplayNewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	// Menu
	@Override
	public HTMLPanel getMenu() {
		return menu;
	}

	@Override
	public void setListarUrl( String url ) {
		listar.setHref( url );
	}

	// fillForm
	@Override
	public void setClean() {
		this.abreviacion.setValue( "" );
		this.nombre.setValue( "" );
		this.descripcion.setValue( "" );
	}

	//fillObject
	@Override
	public String getAbreviacion() {
		return abreviacion.getValue();
	}

	@Override
	public String getNombre() {
		return nombre.getValue();
	}

	@Override
	public String getDescripcion() {
		return descripcion.getValue();
	}
}