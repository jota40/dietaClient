package es.jota.gwt.client.display.unidadDeMedida;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

import es.jota.gwt.client.utils.UtilClient;
import es.jota.gwt.client.widgets.display.MenuItem;

public class UnidadDeMedidaDisplayViewImpl extends Composite implements UnidadDeMedidaDisplayView {
	
	private static UnidadDeMedidaDisplayViewUiBinder uiBinder = GWT.create(UnidadDeMedidaDisplayViewUiBinder.class);
	interface UnidadDeMedidaDisplayViewUiBinder extends UiBinder<Widget, UnidadDeMedidaDisplayViewImpl>{}

	@UiField HTMLPanel menu;
	@UiField MenuItem listar;
	@UiField MenuItem nuevo;
	@UiField MenuItem editar;
	
	@UiField ParagraphElement abreviacion;
	@UiField ParagraphElement nombre;
	@UiField ParagraphElement descripcion;

	private UnidadDeMedidaDisplayView.Listener listener;

	@Override
	public void setListener(Listener listener) {
		this.listener = listener;
	}

	public UnidadDeMedidaDisplayViewImpl() {
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
	public void setAbreviacion( String abreviacion ) {
		this.abreviacion.setInnerHTML( abreviacion );
	}

	@Override
	public void setNombre( String nombre ) {
		this.nombre.setInnerHTML( nombre );
	}

	@Override
	public void setDescripcion( String descripcion ) {
		this.descripcion.setInnerSafeHtml( UtilClient.Cadena.string2SafeHtml( descripcion ) );
	}
}