package es.jota.gwt.client.display.receta;

import java.math.BigDecimal;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import es.jota.gwt.client.utils.UtilClient;
import es.jota.gwt.client.widgets.display.MenuItem;
import es.jota.gwt.client.widgets.display.TBodyPanel;

public class RecetaDisplayViewImpl extends Composite implements RecetaDisplayView {
	
	private static RecetaDisplayViewUiBinder uiBinder = GWT.create(RecetaDisplayViewUiBinder.class);
	interface RecetaDisplayViewUiBinder extends UiBinder<Widget, RecetaDisplayViewImpl>{}

	@UiField HTMLPanel menu;
	@UiField MenuItem listar;
	@UiField MenuItem nuevo;
	@UiField MenuItem editar;
	
	@UiField ParagraphElement nombre;
	@UiField ParagraphElement descripcion;
	@UiField ParagraphElement pax;

	@UiField TextBox nuevoPax;
	@UiField TBodyPanel ingredientesDeReceta;

	private RecetaDisplayView.Listener listener;

	@Override
	public void setListener(Listener listener) {
		this.listener = listener;
	}

	public RecetaDisplayViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		nuevoPax.getElement().setAttribute("placeholder", "Raciones");
	}

	@UiHandler("nuevoPax")
	public void nuevoPaxOnValueChange(ValueChangeEvent<String> event) {
		listener.cambiarPax();
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
	public void setPax( Integer pax ) {
		this.pax.setInnerHTML( UtilClient.Numeros.number2String( pax ) );
		this.nuevoPax.setText( UtilClient.Numeros.number2String( pax ) );
	}

	@Override
	public TBodyPanel getIngredientesDeReceta() {
		return ingredientesDeReceta;
	}

	@Override
	public BigDecimal getNuevoPax() {
		return UtilClient.Numeros.string2BigDecimal( nuevoPax.getValue() );
	}
}