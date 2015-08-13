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

public class RecetaDisplayNewImpl extends Composite implements RecetaDisplayNew {
	
	private static RecetaDisplayNewUiBinder uiBinder = GWT.create(RecetaDisplayNewUiBinder.class);
	interface RecetaDisplayNewUiBinder extends UiBinder<Widget, RecetaDisplayNewImpl>{}

	@UiField HTMLPanel menu;
	@UiField MenuItem listar;

	@UiField Element nombreFormGroup;
	@UiField InputElement nombre;
	@UiField Element nombreError;

	@UiField TextAreaElement descripcion;
	
	@UiField Element paxFormGroup;
	@UiField InputElement pax;
	@UiField Element paxError;
	
	@UiField TBodyPanel ingredientesDeReceta;

	private RecetaDisplayNew.Listener listener;

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

	@UiHandler("addIngredientesDeReceta")
	public void addIngredientesDeRecetaOnClick(ClickEvent event) {
		listener.addIngredientesDeReceta();
	}

	@Override
	public void setListener( RecetaDisplayNew.Listener listener ) {
		this.listener = listener;
	}

	public RecetaDisplayNewImpl() {
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
	public void clear() {
		this.nombre.setValue( "" );
		this.descripcion.setValue( "" );
		this.pax.setValue( "" );
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

	@Override
	public boolean validate() {
		boolean dev = true;
		dev &= Validator.validate( new Bootstrap3Action( nombreFormGroup, nombreError ), new notEmpty( nombre.getValue() ) );
		dev &= Validator.validate( new Bootstrap3Action( paxFormGroup, paxError ), new notEmpty( pax.getValue() ), new isDecimal( pax.getValue() ) );
		return dev;
	}
}