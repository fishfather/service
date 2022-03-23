package org.jw.graphqljw.repositories;

import org.jw.graphqljw.model.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookRepository {

    public List<Book> findAll() {
        List<Book> list = new ArrayList<>();
        list.add(new Book(1, "Java" , 1));
        list.add(new Book(2, "Data Base" , 1));
        list.add(new Book(3, "JavaScript" , 1));
        return list;
    }


}
