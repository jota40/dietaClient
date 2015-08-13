package es.jota.gwt.client.activity.menu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.place.shared.Place;

import es.jota.gwt.client.miApp;
import es.jota.gwt.client.display.menu.TopView;
import es.jota.gwt.client.gin.Injector;
import es.jota.gwt.client.gin.InjectorDisplay;
import es.jota.gwt.client.my.MyAbstractActivity;
import es.jota.gwt.client.my.MyAsyncCallback;
import es.jota.gwt.client.place.inicio.InicioPlace;
import es.jota.gwt.client.service.LoginGwtService;
import es.jota.gwt.shared.SecurityGwtUtils;

public class TopActivity extends MyAbstractActivity<Place> implements TopView.Delegate{

	SecurityGwtUtils securityGwtUtils = Injector.INSTANCE.getSecurityGwtUtils();
	private TopView display;

	public TopActivity() {
		display = miApp.INJECTOR.getTopPublicoView();
		display.setDelegate( this );
		loadSecurityGwtUtils();
	}

	@Override
	protected void start() {
		containerWidget.setWidget( display );
	}

	@Override
	public void login() {
		RequestBuilder rb = new RequestBuilder(RequestBuilder.POST, "/j_spring_security_check");
		rb.setHeader("Content-Type", "application/x-www-form-urlencoded");
		rb.setRequestData("j_username=" + URL.encode( display.getLogin() + "&j_password=" + URL.encode( display.getPass() ) ) );
		rb.setCallback(new RequestCallback() {
			@Override
			public void onResponseReceived( Request request, final Response response ) {
				if (response.getStatusCode() == 200) {
					goTo( InicioPlace.instance() );
					loadSecurityGwtUtils();
				} else {
					kickUser();
				}
			}

			@Override
			public void onError(Request request, Throwable exception) {
				kickUser();
			}
		});

		try {
			rb.send();
		} catch (RequestException re) {
			re.printStackTrace();
		}
	}

	@Override
	public void logout() {
		RequestBuilder rb = new RequestBuilder(RequestBuilder.POST, "/j_spring_security_logout");
		try {
			rb.sendRequest(null, new RequestCallback() {
				public void onResponseReceived(Request request, Response response) {
					kickUser();
				}
				public void onError(Request request, Throwable caught) {
					kickUser();
				}
			});
		} catch (RequestException re) {
		}
	}

	private void kickUser() {
		miApp.INJECTOR = GWT.create(InjectorDisplay.class);
		securityGwtUtils.clear();
		display.showLogin( true );
		goTo( InicioPlace.instance() );
	}

	private void loadSecurityGwtUtils() {
		LoginGwtService.Util.getInstance().loadSecurityGwtUtils( new MyAsyncCallback<SecurityGwtUtils>( "Login", "Identificando al usuario" ) {
			@Override
			public void handleOnSuccess( SecurityGwtUtils result ) {
				miApp.INJECTOR = GWT.create(InjectorDisplay.class);
				securityGwtUtils.update( result );
				display.setUserName( securityGwtUtils.getNombreUsuario() );
				display.showLogin( securityGwtUtils.getIdUsuario() == null );
			}
			@Override
			public void handleOnFailure(Throwable caught) {
				kickUser();
			}
		});
	}
}
