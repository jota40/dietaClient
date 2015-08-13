package es.jota.gwt.client.uploader.interfaz;

import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.IsWidget;

import es.jota.gwt.client.uploader.ui.MyUploaderBase;

public interface IUploaderForm extends IsWidget {
	void setUploader(MyUploaderBase uploader);
	void set( FileUpload fileupload );
	FormPanel getFormPanel();
}
