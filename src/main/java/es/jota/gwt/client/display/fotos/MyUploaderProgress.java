package es.jota.gwt.client.display.fotos;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;

import es.jota.gwt.client.uploader.ui.MyUploaderBaseProgress;
import gwtupload.client.IUploadStatus.Status;

public class MyUploaderProgress extends MyUploaderBaseProgress {

	private static MyUploaderProgressUiBinder uiBinder = GWT.create(MyUploaderProgressUiBinder.class);
	interface MyUploaderProgressUiBinder extends UiBinder<Widget, MyUploaderProgress> {}

//	@UiField ProgressBar progress;
	@UiField DivElement progressText;

	public MyUploaderProgress() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("cancel")
	void cancelOnClick(ClickEvent e) {
		cancel();
	}

	@Override
	public void updateStatus( Status status ) {
/*		switch (status) {
			case CHANGED:
			case QUEUED:
			case SUBMITING:
				progress.setColor( Color.INFO );
				progress.setPercent( 0 );
			break;
			case INPROGRESS:
				progress.setColor( Color.INFO );
				progress.addStyleName("active");
			break;
			case SUCCESS:
			case REPEATED: 
				progress.setColor( Color.SUCCESS );
				progress.setPercent( 100 );
				progress.removeStyleName("active");
			break;
			case CANCELING:
				progress.setColor( Color.DANGER );
			break;
			case INVALID:
			case CANCELED:
			case ERROR:
			case DELETED:
				progress.setColor( Color.DANGER );
				progress.setPercent( 100 );
				progress.removeStyleName("active");
			break;
		}
		*/
	}

	@Override
	public void updateText( String msg ) {
		progressText.setInnerHTML( msg );
	}

	@Override
	public void updatePercent( Integer percent ) {
	//	progress.setPercent(percent);
	}

	@Override
	public boolean validate() {
		return true;
	}
}