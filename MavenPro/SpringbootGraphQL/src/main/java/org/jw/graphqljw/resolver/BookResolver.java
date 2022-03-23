package org.jw.graphqljw.resolver;

import graphql.kickstart.tools.GraphQLResolver;
import org.jw.graphqljw.model.Author;
import org.jw.graphqljw.model.Book;
import org.jw.graphqljw.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class BookResolver implements GraphQLResolver<Book> {

    private AuthorRepository authorRepository;

    @Autowired
    public BookResolver(AuthorRepository authorRepository) {
        System.out.println("************ bookresolver initiallized. " + authorRepository);
        this.authorRepository = authorRepository;
    }

    public Author author(Book book) {
        System.out.println("find author with book "+ book.getName());
        return authorRepository.findById(book.getAuthorId());
    }
}
