package es.jota.gwt.client.place.presupuesto;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class PresupuestoPlaceNew extends PresupuestoPlace {
	private final static String NAME = "presupuestoNew";
	
	private PresupuestoPlaceNew() {
	}

	@Prefix(NAME)
	public static class Tokenizer implements PlaceTokenizer<PresupuestoPlaceNew> {
		@Override
		public String getToken( PresupuestoPlaceNew place ) {
			return "";
		}

		@Override
		public PresupuestoPlaceNew getPlace( String token ) {
			return instance();
		}
	}

	public static PresupuestoPlaceNew instance() {
		return new PresupuestoPlaceNew();
	}

	public static String getUrl( Long id ) {
		return "#" + NAME + ":" + id;
	}
}