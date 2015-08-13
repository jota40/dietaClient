package es.jota.gwt.client.place.fotos;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class FotoPlace extends Place {
	private final static String NAME = "foto";
	private final static String VIEW = "view";
	private final static String EDIT = "edit";
	private final static String FORBIDDEN = "forbidden";
	private String command;

	private FotoPlace( String command ) {
		this.command = command;
	}

	public String getCommand() {
		return command;
	}

	@Prefix(NAME)
	public static class Tokenizer implements PlaceTokenizer<FotoPlace> {
		@Override
		public String getToken(FotoPlace place) {
			return place.getCommand();
		}

		@Override
		public FotoPlace getPlace(String token) {
			if ( token.equalsIgnoreCase( VIEW ) ){
				return instanceView();
			}
			if ( token.equalsIgnoreCase( EDIT ) ){
				return instanceEdit();
			}
			return instanceForbidden();
		}
	}

	public boolean isView(){
		return VIEW.equals( command );
	}
	
	public boolean isEdit(){
		return EDIT.equals( command );
	}

	public boolean isForbidden(){
		return FORBIDDEN.equals( command );
	}
	public static FotoPlace instanceView() {
		return new FotoPlace( VIEW );
	}

	public static FotoPlace instanceEdit() {
		return new FotoPlace( EDIT );
	}

	public static FotoPlace instanceForbidden() {
		return new FotoPlace( FORBIDDEN );
	}

	public static String getUrlView() {
		return NAME + ":" + VIEW;
	}

	public static String getUrlEdit() {
		return NAME + ":" + EDIT;
	}

	public static String getUrlForbidden() {
		return NAME + ":" + FORBIDDEN;
	}
}