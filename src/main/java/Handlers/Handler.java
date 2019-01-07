package Handlers;

import java.util.List;

public interface Handler<T> {

    T get(int id);

    List<T> get();

    void add(T object);

    void add (List<T> objects);

    void delete(int id);

    void delete(List<T> objects);

    Object update();

}
