package es.jota.gwt.client.display.menu;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;

public interface TopView extends IsWidget {
	void setDelegate( Delegate delegate );
	public interface Delegate {
		void logout();
		void login();
		
	}
	
	String getPass();
	String getLogin();
	void setUserName( String msg );
	void showLogin( boolean show );
	void setMenu( HTMLPanel menu );
}
