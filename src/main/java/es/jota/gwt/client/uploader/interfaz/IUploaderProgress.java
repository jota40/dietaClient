package es.jota.gwt.client.uploader.interfaz;

import es.jota.gwt.client.uploader.ui.MyUploaderBase;
import gwtupload.client.IUploadStatus.Status;

import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.IsWidget;

public interface IUploaderProgress extends IsWidget {
	void updatePercent( Integer percent );
	void updateStatus( Status status );
	void updateText( String msg );
	void setUploader(MyUploaderBase uploader);
	String getBaseName();
	FileUpload getFileUpload();
	String getFileName();
	String getName();
	boolean validate();
	void setFileUpload( FileUpload fileUpload );
}
