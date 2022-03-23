package org.jw.graphqljw.repositories;

import org.jw.graphqljw.model.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorRepository {
    public Author findById(int authorId) {
        return new Author(1, "Job Buthish");
    }
}
