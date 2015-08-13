package es.jota.gwt.client.display.fotos;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Widget;

import es.jota.gwt.client.uploader.interfaz.IUploaderProgress;
import es.jota.gwt.client.uploader.ui.MyUploaderBaseForm;

public class MyUploaderForm extends MyUploaderBaseForm {

	private static MyUploaderFormUiBinder uiBinder = GWT.create(MyUploaderFormUiBinder.class);
	interface MyUploaderFormUiBinder extends UiBinder<Widget, MyUploaderForm> {}

	@UiField FormPanel formPanel;
	
	public MyUploaderForm() {
		initWidget(uiBinder.createAndBindUi(this));
		formPanel.getElement().setAttribute( "style", "width: 0px; visibility: hidden;" );
	}

	@UiHandler("uploadFake")
	void upLoadFakeOnClick(ClickEvent e) {
		openFileSelection();
	}

	@Override
	protected IUploaderProgress instanceUploaderProgress() {
		return new MyUploaderProgress();
	}

	@Override
	public FormPanel getFormPanel() {
		return formPanel;
	}
}