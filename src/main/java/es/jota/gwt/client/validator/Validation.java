package es.jota.gwt.client.validator;

public abstract class Validation<T> {
	protected T value;

	public Validation( T value ) {
		this.value = value;
	}

	public abstract String validate();
}