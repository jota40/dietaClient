package es.jota.gwt.client.validator;

public class notNull  extends Validation<Object> {

	public notNull(Object value) {
		super(value);
	}

	@Override
	public String validate() {
		String dev = null;
		if ( value == null ) {
			dev = "No puede ser null";
		}
		return dev;
	}
}
