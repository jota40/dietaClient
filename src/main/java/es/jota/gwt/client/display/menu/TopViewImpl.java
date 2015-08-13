package es.jota.gwt.client.display.menu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

import es.jota.gwt.client.utils.UtilClient;

public class TopViewImpl extends Composite implements TopView {

	private static TopViewUiBinder uiBinder = GWT.create(TopViewUiBinder.class);
	interface TopViewUiBinder extends UiBinder<Widget, TopViewImpl> {}

	@UiField HTMLPanel main;
	@UiField InputElement userName;
	@UiField InputElement pass;
	@UiField Element userNameP;

	@UiField HTMLPanel acciones;

	private Delegate delegate;
	
	public TopViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		acciones.getElement().setId("acciones");
		acciones.getElement().setAttribute( "role" , "menu" );
		acciones.getElement().setAttribute( "style" , "margin: 0px; position: absolute; top: 0px; right: 0px; height: 0px; width: 45px; display: none;" );
	}

	@UiHandler("login")
	public void clickOnLogin( ClickEvent event ) {
		delegate.login();
	}

	@UiHandler("logout")
	public void clickOnLogout( ClickEvent event ) {
		delegate.logout();
	}

	@Override
	public void setDelegate(Delegate delegate) {
		this.delegate = delegate;
	}

	@Override
	public String getPass() {
		return pass.getValue();
	}

	@Override
	public String getLogin() {
		return userName.getValue();
	}

	@Override
	public void setUserName( String msg ) {
		this.userNameP.setInnerHTML( msg );
	}

	public void setMenu( HTMLPanel panel ) {
		acciones.clear();
		acciones.add( panel );
/*		int widgets = panel.getWidgetCount();
		int correccion = 0;
		int incremento = 0;
		switch (widgets) {
			case 1: incremento = 45;
					correccion = 1;
			break;
			case 2: incremento = 30;
					correccion = 1;
			break;
			default: incremento = 90 / ( widgets - 1 );
			break;
		}
		for ( int i = 0 ; i < widgets ; i++ ) {
			HTMLPanel wrap = new HTMLPanel("");
			wrap.setStyleName("myMenuItem");
			wrap.getElement().setAttribute( "style" , "transform: rotate(" + ( 180 - ( ( i + correccion ) * incremento ) ) + "deg); padding-left: 30px;" );
			wrap.add( panel.getWidget( 0 ) );
			acciones.add( wrap );
		}*/
	}

	@Override
	public void showLogin( boolean show ) {
		UtilClient.JS.show( "login", show );
		UtilClient.JS.show( "logout", !show );
	}
}