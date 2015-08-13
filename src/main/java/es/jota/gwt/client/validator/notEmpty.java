package es.jota.gwt.client.validator;

public class notEmpty extends Validation<String> {

	public notEmpty( String value ) {
		super( value );
	}

	@Override
	public String validate() {
		String dev = null;
		if ( value == null || "".equals( value.trim() ) ) {
			dev = "No puede ser cadena vacia";
		}
		return dev;
	}
}