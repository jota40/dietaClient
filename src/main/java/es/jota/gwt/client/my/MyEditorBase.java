package es.jota.gwt.client.my;

public abstract interface MyEditorBase<T> {
	public interface Delegate<T> {
		void addEditor( T object );
		void removeEditor( MyEditorBase<T> editor );
	}
	T get();
	void set(T t);
}
