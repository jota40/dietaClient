package es.jota.gwt.client.display.ingrediente;

import java.math.BigDecimal;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

import es.jota.gwt.client.utils.UtilClient;
import es.jota.gwt.client.widgets.display.MenuItem;

public class IngredienteDisplayViewImpl extends Composite implements IngredienteDisplayView {
	
	private static IngredienteDisplayViewUiBinder uiBinder = GWT.create(IngredienteDisplayViewUiBinder.class);
	interface IngredienteDisplayViewUiBinder extends UiBinder<Widget, IngredienteDisplayViewImpl>{}

	@UiField HTMLPanel menu;
	@UiField MenuItem listar;
	@UiField MenuItem nuevo;
	@UiField MenuItem editar;
	
	@UiField ParagraphElement nombre;
	@UiField ParagraphElement descripcion;
	@UiField ParagraphElement pesoPorRacion;
	@UiField ParagraphElement coste;
	@UiField ParagraphElement unidadDeMedida;
	@UiField ParagraphElement grupoAlimento;

	private IngredienteDisplayView.Listener listener;

	@Override
	public void setListener(Listener listener) {
		this.listener = listener;
	}

	public IngredienteDisplayViewImpl() {
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
	public void setDescripcion( String descripcion ) {
		this.descripcion.setInnerSafeHtml( UtilClient.Cadena.string2SafeHtml( descripcion ) );
	}

	@Override
	public void setPesoPorRacion( BigDecimal pesoPorRacion ) {
		this.pesoPorRacion.setInnerHTML( UtilClient.Numeros.number2String( pesoPorRacion ) );
	}

	@Override
	public void setCoste( BigDecimal coste ) {
		this.coste.setInnerHTML( UtilClient.Numeros.number2String( coste ) );
	}

	@Override
	public void setUnidadDeMedida( String unidadDeMedida ) {
		this.unidadDeMedida.setInnerHTML( unidadDeMedida );
	}
	
	@Override
	public void setGrupoAlimento( String grupoAlimento ) {
		this.grupoAlimento.setInnerHTML( grupoAlimento );
	}
}