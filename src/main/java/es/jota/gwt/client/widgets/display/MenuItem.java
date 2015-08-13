package es.jota.gwt.client.widgets.display;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MenuItem extends Composite implements HasClickHandlers {
	private static MenuItemUiBinder uiBinder = GWT.create(MenuItemUiBinder.class);
	interface MenuItemUiBinder extends UiBinder<Widget, MenuItem>{}

	@UiField Anchor link;

	public MenuItem() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public void setText( String text ) {
		this.link.setText( text );
	}

	public void setColor( String color ) {
		this.link.addStyleName( color );
	}

	public void setGrados( String grados ) {
		this.getElement().setAttribute( "style", "transform: rotate(" + grados + "deg);" );
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return link.addClickHandler(handler);
	}

	public void setHref(String url) {
		link.setHref( url );
	}
}