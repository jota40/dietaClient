package es.jota.gwt.client.cellTable.pager;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import es.jota.gwt.client.cellTable.pager.MyPager.MyPagerView;

public class PagerScrollerDisplay extends Composite implements MyPagerView {
	
	private static PagerScrollerDisplayUiBinder uiBinder = GWT.create(PagerScrollerDisplayUiBinder.class);
	interface PagerScrollerDisplayUiBinder extends UiBinder<Widget, PagerScrollerDisplay>{}

	@UiField Anchor mas;
	private MyPager listener;

	@UiHandler("mas")
	public void primeroYListarOnClick(ClickEvent event) {
		listener.nextPage();
	}

	@Override
	public void setListener( MyPager listener ) {
		this.listener = listener;
	}

	public PagerScrollerDisplay() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	private void setEnabled( Anchor link, boolean enabled ) {
		if ( enabled ) {
			link.getElement().getParentElement().removeClassName( "disabled" );
		} else {
			link.getElement().getParentElement().addClassName( "disabled" );
		}
	}

	@Override
	public void onRangeOrRowCountChanged() {
		setEnabled( mas, listener.hasNextPage() );
	}
}