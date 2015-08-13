package es.jota.gwt.client.display.grupoAlimento;

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

public class GrupoAlimentoDisplayNewImpl extends Composite implements GrupoAlimentoDisplayNew {
	
	private static GrupoAlimentoDisplayNewUiBinder uiBinder = GWT.create(GrupoAlimentoDisplayNewUiBinder.class);
	interface GrupoAlimentoDisplayNewUiBinder extends UiBinder<Widget, GrupoAlimentoDisplayNewImpl>{}

	@UiField HTMLPanel menu;
	@UiField MenuItem listar;

	@UiField InputElement nombre;
	@UiField InputElement color;
	@UiField TextAreaElement descripcion;

	private GrupoAlimentoDisplayNew.Listener listener;

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
	public void setListener( GrupoAlimentoDisplayNew.Listener listener ) {
		this.listener = listener;
	}

	public GrupoAlimentoDisplayNewImpl() {
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
		this.nombre.setValue( "" );
		this.color.setValue( "" );
		this.descripcion.setValue( "" );
	}

	//fillObject
	@Override
	public String getNombre() {
		return nombre.getValue();
	}

	@Override
	public String getColor() {
		return color.getValue();
	}

	@Override
	public String getDescripcion() {
		return descripcion.getValue();
	}
}