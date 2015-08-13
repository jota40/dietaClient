package es.jota.gwt.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

import es.jota.gwt.client.gin.Injector;
import es.jota.gwt.client.gin.InjectorDisplay;
import es.jota.gwt.client.mappers.AppPlaceHistoryMapper;
import es.jota.gwt.client.mappers.LogsActivityMapper;
import es.jota.gwt.client.mappers.MainActivityMapper;
import es.jota.gwt.client.mappers.TopActivityMapper;
import es.jota.gwt.client.place.inicio.InicioPlace;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class miApp implements EntryPoint {
	static final Logger LOG = Logger.getLogger(miApp.class.getName());
	public static InjectorDisplay INJECTOR = GWT.create(InjectorDisplay.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		 GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {  
		     public void onUncaughtException(Throwable e) {  
		        LOG.log( Level.SEVERE, "miApp", e);  
		    }
		 });
     
		Callback<Void, Exception> injectionCallback = new Callback<Void, Exception>() {
			public void onFailure(Exception reason) {
				Window.alert("Script load failed.");
			}
			public void onSuccess(Void result) {
			//	Window.alert("Script load success.");
			}
		};
/*
		if ( !isjQueryLoaded() ) {
			ScriptInjector.fromUrl("https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js").setCallback( injectionCallback ).setWindow(ScriptInjector.TOP_WINDOW).inject();
			ScriptInjector.fromUrl("//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js").setCallback( injectionCallback ).setWindow(ScriptInjector.TOP_WINDOW).inject();
		}
*/
		SimplePanel main = new SimplePanel();
		SimplePanel top = new SimplePanel();
		SimplePanel logs = new SimplePanel();
		
		EventBus eventBus = Injector.INSTANCE.getEventBus();
		PlaceController placeController = Injector.INSTANCE.getPlaceController();

		// menu activity manager
		ActivityMapper topActivityMapper = new TopActivityMapper();
		ActivityManager topActivityManager = new ActivityManager(topActivityMapper, eventBus);
		topActivityManager.setDisplay( top );

		// main activity manager
		ActivityMapper mainActivityMapper = new MainActivityMapper();
		ActivityManager mainActivityManager = new ActivityManager(mainActivityMapper, eventBus);
		mainActivityManager.setDisplay(main);

		// logs activity manager
		ActivityMapper logsActivityMapper = new LogsActivityMapper();
		ActivityManager logsActivityManager = new ActivityManager(logsActivityMapper, eventBus);
		logsActivityManager.setDisplay(logs);

		// Start PlaceHistoryHandler with our PlaceHistoryMapper
		AppPlaceHistoryMapper historyMapper= GWT.create(AppPlaceHistoryMapper.class);
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
		historyHandler.register(placeController, eventBus, InicioPlace.instance());

		RootPanel.get("main").add(top);
		RootPanel.get("main").add(main);
		RootPanel.get("main").add(logs);
		//	Utils.JS.panelSlide(logs.getElement());
		// Goes to place represented on URL or default place
		historyHandler.handleCurrentHistory();
	}

	/**
	 * Check to see if jQuery is loaded already
	 *
	 * @return true is jQuery is loaded, false otherwise
	 */
	private native boolean isjQueryLoaded() /*-{
		return (typeof $wnd['jQuery'] !== 'undefined');
	}-*/;
}