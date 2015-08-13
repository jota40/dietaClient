package es.jota.gwt.client.activity;

public interface IDoAfterInsert<T> {
	void exec( T id );
}