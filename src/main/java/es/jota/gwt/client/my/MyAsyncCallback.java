package es.jota.gwt.client.my;

import java.util.logging.Level;
import java.util.logging.Logger;

import jota.server.exceptions.ServiceException;

import com.google.gwt.user.client.rpc.AsyncCallback;

import es.jota.gwt.client.miApp;
import es.jota.gwt.client.display.logs.AlertView;

public abstract class MyAsyncCallback<T> implements AsyncCallback<T> {
	static final Logger LOG = Logger.getLogger(MyAsyncCallback.class.getName());

	private AlertView alert;

	private String error;
	private String ok;

	public MyAsyncCallback( String titulo, String info ) {
		this( titulo, info, null, null );
	}

	public MyAsyncCallback( String titulo, String info, String error ) {
		this( titulo, info, error, null );
	}

	public MyAsyncCallback( String titulo, String info, String error, String ok ) {
		alert = miApp.INJECTOR.getLogsActivity().crearAlert( titulo, info );
		this.error = error;
		this.ok = ok;
	}

	@Override
	public void onFailure(Throwable caught){
		if ( caught instanceof ServiceException ) {
			ServiceException serviceException = ((ServiceException)caught);
			error = serviceException.getLastCause();
		}
		alert.turnToFail( error );
		handleOnFailure( caught );
	}

	public void handleOnFailure(Throwable caught) {
		LOG.log( Level.SEVERE, "MyAsyncCallBack", caught );
	}

	@Override
	public void onSuccess(T result){
		if ( ok == null ) {
			alert.close();
		} else {
			alert.turnToSuccess( ok );
		}
		handleOnSuccess( result );
	}

	public abstract void handleOnSuccess(T result);
}