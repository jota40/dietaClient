package es.jota.gwt.client.display.logs;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class LogsViewImpl extends Composite implements LogsView {

	private static LogsViewImplUiBinder uiBinder = GWT.create(LogsViewImplUiBinder.class);
	interface LogsViewImplUiBinder extends UiBinder<Widget, LogsViewImpl> {}
	
	@UiField HTMLPanel panel;
	
	private Presenter presenter;

	public LogsViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		panel.getElement().setAttribute("style", "top:70px; left: 10px; padding: 0;");
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
	
	@Override
	public HTMLPanel getPanel() {
		return panel;
	}

	@Override
	public void showBackground() {
		panel.getElement().setAttribute("style", "top:70px; left: 10px; padding: 10px 5px;background-color: rgba(200, 200, 200, 0.99);");
	}

	@Override
	public void hideBackground() {
		animateBackground( panel.getElement(), "rgba(200, 200, 200, 0)", 10000 );
	}

	public native void animateBackground( Element element, String color, int duration ) /*-{
		$wnd.$(element).animate({ backgroundColor: color }, 9000 )
	}-*/;
}