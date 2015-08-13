package es.jota.gwt.client.display.grupoAlimento;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

import es.jota.gwt.client.utils.UtilClient;
import es.jota.gwt.client.widgets.display.MenuItem;

public class GrupoAlimentoDisplayViewImpl extends Composite implements GrupoAlimentoDisplayView {
	
	private static GrupoAlimentoDisplayViewUiBinder uiBinder = GWT.create(GrupoAlimentoDisplayViewUiBinder.class);
	interface GrupoAlimentoDisplayViewUiBinder extends UiBinder<Widget, GrupoAlimentoDisplayViewImpl>{}

	@UiField HTMLPanel menu;
	@UiField MenuItem listar;
	@UiField MenuItem nuevo;
	@UiField MenuItem editar;
	
	@UiField ParagraphElement nombre;
	@UiField ParagraphElement color;
	@UiField ParagraphElement descripcion;

	private GrupoAlimentoDisplayView.Listener listener;

	@Override
	public void setListener(Listener listener) {
		this.listener = listener;
	}

	public GrupoAlimentoDisplayViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	// menu
	@Override
	public HTMLPanel getMenu() {
		return menu;
	}

	@Override
	public void setEditarUrl( String url ) {
		editar.setHref( url );
	}

	@Override
	public void setListarUrl( String url ) {
		listar.setHref( url );
	}

	@Override
	public void setNuevoUrl( String url ) {
		nuevo.setHref( url );
	}

	// fillForm
	@Override
	public void setNombre( String nombre ) {
		this.nombre.setInnerHTML( nombre );
	}

	@Override
	public void setColor( String color ) {
		this.color.setInnerHTML( color );
	}

	@Override
	public void setDescripcion( String descripcion ) {
		this.descripcion.setInnerSafeHtml( UtilClient.Cadena.string2SafeHtml( descripcion ) );
	}
}