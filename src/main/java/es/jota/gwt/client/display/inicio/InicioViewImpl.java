package es.jota.gwt.client.display.inicio;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class InicioViewImpl extends Composite implements InicioView {
	
	private static InicioViewImplUiBinder uiBinder = GWT.create(InicioViewImplUiBinder.class);
	interface InicioViewImplUiBinder extends UiBinder<Widget, InicioViewImpl>{}

	private InicioView.Listener listener;

	public InicioViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setListener(Listener listener) {
		this.listener = listener;
	}
}