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

public class GrupoAlimentoDisplayEditImpl extends Composite implements GrupoAlimentoDisplayEdit {
	
	private static GrupoAlimentoDisplayEditUiBinder uiBinder = GWT.create(GrupoAlimentoDisplayEditUiBinder.class);
	interface GrupoAlimentoDisplayEditUiBinder extends UiBinder<Widget, GrupoAlimentoDisplayEditImpl>{}

	@UiField HTMLPanel menu;
	@UiField MenuItem listar;
	@UiField MenuItem nuevo;
	@UiField MenuItem ver;
	
	@UiField InputElement nombre;
	@UiField InputElement color;
	@UiField TextAreaElement descripcion;
	
	private GrupoAlimentoDisplayEdit.Listener listener;

	@UiHandler("modificarYListar")
	public void modificarYListarOnClick(ClickEvent event) {
		listener.modificarYListar();
	}

	@UiHandler("modificarYNuevo")
	public void modificarYNuevoOnClick(ClickEvent event) {
		listener.modificarYNuevo();
	}

	@UiHandler("modificar")
	public void modificarOnClick(ClickEvent event) {
		listener.modificar();
	}

	@Override
	public void setListener(Listener listener) {
		this.listener = listener;
	}

	public GrupoAlimentoDisplayEditImpl() {
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

	@Override
	public void setNuevoUrl( String url ) {
		nuevo.setHref( url );
	}

	@Override
	public void setVerUrl( String url ) {
		ver.setHref( url );
	}

	// fillForm
	@Override
	public void setNombre( String nombre ) {
		this.nombre.setValue( nombre );
	}

	@Override
	public void setColor( String color ) {
		this.color.setValue( color );
	}

	@Override
	public void setDescripcion( String descripcion ) {
		this.descripcion.setValue( descripcion );
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