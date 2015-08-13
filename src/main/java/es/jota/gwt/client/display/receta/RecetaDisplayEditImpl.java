package es.jota.gwt.client.display.receta;

import java.math.BigDecimal;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.TextAreaElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

import es.jota.gwt.client.utils.UtilClient;
import es.jota.gwt.client.validator.Bootstrap3Action;
import es.jota.gwt.client.validator.Validator;
import es.jota.gwt.client.validator.isDecimal;
import es.jota.gwt.client.validator.notEmpty;
import es.jota.gwt.client.widgets.display.MenuItem;
import es.jota.gwt.client.widgets.display.TBodyPanel;

public class RecetaDisplayEditImpl extends Composite implements RecetaDisplayEdit {
	
	private static RecetaDisplayEditUiBinder uiBinder = GWT.create(RecetaDisplayEditUiBinder.class);
	interface RecetaDisplayEditUiBinder extends UiBinder<Widget, RecetaDisplayEditImpl>{}

	@UiField HTMLPanel menu;
	@UiField MenuItem listar;
	@UiField MenuItem nuevo;
	@UiField MenuItem ver;
	
	@UiField Element nombreFormGroup;
	@UiField InputElement nombre;
	@UiField Element nombreError;

	@UiField TextAreaElement descripcion;
	
	@UiField Element paxFormGroup;
	@UiField InputElement pax;
	@UiField Element paxError;

	@UiField TBodyPanel ingredientesDeReceta;

	private RecetaDisplayEdit.Listener listener;

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

	@UiHandler("addIngredientesDeReceta")
	public void addIngredientesDeRecetaOnClick(ClickEvent event) {
		listener.addIngredientesDeReceta();
	}

	@Override
	public void setListener(Listener listener) {
		this.listener = listener;
	}

	public RecetaDisplayEditImpl() {
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
	public void setDescripcion( String descripcion ) {
		this.descripcion.setValue( descripcion );
	}

	@Override
	public void setPax( Integer pax ) {
		this.pax.setValue( UtilClient.Numeros.number2String( pax ) );
	}

	//fillObject
	@Override
	public String getNombre() {
		return nombre.getValue();
	}

	@Override
	public String getDescripcion() {
		return descripcion.getValue();
	}

	@Override
	public BigDecimal getPax() {
		return UtilClient.Numeros.string2BigDecimal( pax.getValue() );
	}

	@Override
	public TBodyPanel getIngredientesDeReceta() {
		return ingredientesDeReceta;
	}

	//validate
	@Override
	public boolean validate() {
		boolean dev = true;
		dev &= Validator.validate( new Bootstrap3Action( nombreFormGroup, nombreError ), new notEmpty( nombre.getValue() ) );
		dev &= Validator.validate( new Bootstrap3Action( paxFormGroup, paxError ), new notEmpty( pax.getValue() ), new isDecimal( pax.getValue() ) );
		return dev;
	}
}