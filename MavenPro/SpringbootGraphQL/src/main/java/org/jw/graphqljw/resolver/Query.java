package org.jw.graphqljw.resolver;

import graphql.kickstart.tools.GraphQLQueryResolver;
import org.jw.graphqljw.model.Book;
import org.jw.graphqljw.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {
    @Autowired
    private BookRepository bookRepository;

    public Query() {
//        this.bookRepository = bookRepository;
    }

    public List<Book> books() {
        System.out.println("get all books.");
        return bookRepository.findAll();
    }
}
