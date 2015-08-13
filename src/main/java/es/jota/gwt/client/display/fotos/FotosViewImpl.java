package es.jota.gwt.client.display.fotos;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

import es.jota.gwt.client.activity.fotos.FotosView;
import es.jota.gwt.client.uploader.ui.MyUploaderBase;
import es.jota.gwt.client.uploader.ui.MyUploaderBase.OnFinishUploadCallback;
import gwtupload.client.IUploader.UploadedInfo;
import gwtupload.client.PreloadedImage;
import gwtupload.client.PreloadedImage.OnLoadPreloadedImageHandler;

public class FotosViewImpl extends Composite implements FotosView {
	private static FotosViewImplUiBinder uiBinder = GWT.create(FotosViewImplUiBinder.class);
	interface FotosViewImplUiBinder extends UiBinder<Widget, FotosViewImpl>{}

	@UiField HTMLPanel uploadPanel;
	@UiField HTMLPanel imagesPanel;

	private Delegate listener;

	public FotosViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		MyUploaderBase myUploader = new MyUploaderBase( new MyUploaderForm() , 5 );
		uploadPanel.add(myUploader);
		myUploader.setOnFinishUploadCallback(new OnFinishUploadCallback() {
			
			@Override
			public void onFinish(UploadedInfo info, String fileUrl) {
				new PreloadedImage( fileUrl, showImage);


				imagesPanel.add(new Image(fileUrl));
/*				InputStream input;
				try {
					input = new URL(fileUrl).openStream();
	//				imagesPanel.add(new HTML(input.toString()));
				} catch (Exception e) {
					e.printStackTrace();
				}*/
				Anchor link = new Anchor("link");
				link.setHref(fileUrl);
				imagesPanel.add(link);
				
				// The server sends useful information to the client by default
				System.out.println("File name " + info.name);
				System.out.println("File content-type " + info.ctype);
				System.out.println("File size " + info.size);

				// You can send any customized message and parse it 
				System.out.println("Server message " + info.message);
			}
		});
	}


	// Attach an image to the pictures viewer
	private OnLoadPreloadedImageHandler showImage = new OnLoadPreloadedImageHandler() {
		public void onLoad(PreloadedImage image) {
			image.setWidth("75px");
			imagesPanel.add(image);
		}
	};

	@Override
	public void setDelegate(Delegate listener) {
		this.listener = listener;
	}

	@Override
	public void reset() {
	}
}