package es.jota.gwt.client.display.logs;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.ButtonElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class AlertViewImpl extends Composite implements AlertView {

	private static AlertViewUiBinder uiBinder = GWT.create(AlertViewUiBinder.class);
	interface AlertViewUiBinder extends UiBinder<Widget, AlertViewImpl> {}
	
	@UiField HTMLPanel alert;
	@UiField ButtonElement close;
	@UiField Element titulo;
	@UiField Element texto;
	@UiField Element resultado;

	public AlertViewImpl( String titulo, String texto ) {
		initWidget(uiBinder.createAndBindUi(this));
		alert.getElement().setAttribute( "role" , "alert" );
		this.titulo.setInnerHTML( titulo );
		this.texto.setInnerHTML( texto );
	}

	@Override
	public void turnToSuccess( String texto ) {
		turnTo( alert.getElement(), "alert-success" );
		this.resultado.setInnerHTML( texto );
		this.close.removeClassName("hidden");
		alertViewAutoClose( this.alert.getElement(), 10000 );
	}
	
	@Override
	public void turnToFail( String texto ) {
		turnTo( alert.getElement(), "alert-danger" );
		this.resultado.setInnerHTML( texto );
		this.close.removeClassName("hidden");
	}
	
	@Override
	public void close() {
		alertViewAutoClose( this.alert.getElement(), 200 );
	}

	private native void turnTo( Element element, String className ) /*-{
		$wnd.$(element).switchClass( "alert-warning", className, 200 )
	}-*/;

	private native void alertViewAutoClose( Element element, int duration ) /*-{
		$wnd.$(element).fadeTo( duration, 0, function () {element.firstChild.click()} )
	}-*/;
}
