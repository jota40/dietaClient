package es.jota.gwt.client.display.presupuesto;

import java.math.BigDecimal;

import jota.server.dto.RecetaDto;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import es.jota.gwt.client.utils.UtilClient;
import es.jota.gwt.client.widgets.custom.SuggestRecetas;
import es.jota.gwt.client.widgets.display.Suggest;
import es.jota.gwt.client.widgets.display.TBodyPanel;
import es.jota.gwt.client.widgets.presenter.events.ValueAddEvent;
import es.jota.gwt.client.widgets.presenter.events.ValueAddHandler;
import es.jota.gwt.client.widgets.presenter.events.ValueRemoveEvent;
import es.jota.gwt.client.widgets.presenter.events.ValueRemoveHandler;

public class PresupuestoDisplayNewViewImpl extends Composite implements PresupuestoDisplayNewView {
	
	private static PresupuestoDisplayNewViewUiBinder uiBinder = GWT.create(PresupuestoDisplayNewViewUiBinder.class);
	interface PresupuestoDisplayNewViewUiBinder extends UiBinder<Widget, PresupuestoDisplayNewViewImpl>{}

	@UiField HTMLPanel menu;
	@UiField Suggest receta;

	@UiField TextBox nuevoPax;
	@UiField TBodyPanel ingredientesDeReceta;

	private PresupuestoDisplayNewView.Listener listener;
	private SuggestRecetas suggestReceta;

	@Override
	public void setListener(Listener listener) {
		this.listener = listener;
	}

	public PresupuestoDisplayNewViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		nuevoPax.getElement().setAttribute("placeholder", "Raciones");
		suggestReceta = new SuggestRecetas( receta );
		suggestReceta.addValueAddHandler( new ValueAddHandler<RecetaDto>() {
			@Override
			public void onValueAdd(ValueAddEvent<RecetaDto> event) {
				listener.addReceta( event.getValue() );
			}
		});
		suggestReceta.addValueRemoveHandler( new ValueRemoveHandler<RecetaDto>() {
			@Override
			public void onValueRemove(ValueRemoveEvent<RecetaDto> event) {
				listener.removeReceta( event.getValue() );
			}
		});
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

	// fillForm
	@Override
	public void setNuevoPax( Integer nuevoPax ) {
		this.nuevoPax.setValue( UtilClient.Numeros.number2String( nuevoPax ) );
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