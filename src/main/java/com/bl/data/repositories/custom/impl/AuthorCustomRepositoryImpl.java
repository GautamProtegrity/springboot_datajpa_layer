package com.bl.data.repositories.custom.impl;

import com.bl.data.domain.Author;
import com.bl.data.domain.Book;
import com.bl.data.domain.dto.AuthorDTO;
import com.bl.data.domain.dto.SearchDTO;
import com.bl.data.repositories.custom.AuthorCustomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public class AuthorCustomRepositoryImpl implements AuthorCustomRepository {
    @PersistenceContext
    EntityManager entityManager;
    @Override
    public Page<SearchDTO> getPagedAllAuthor() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<SearchDTO> criteriaQuery = cb.createQuery(SearchDTO.class);
        Root<Author> rootQuery = criteriaQuery.from(Author.class);
        Join<Book, Author> bookAuthorJoin = rootQuery.join("books", JoinType.LEFT);
        criteriaQuery.select(cb.construct(SearchDTO.class,
                rootQuery.get("authorName"),
                rootQuery.get("authorDetails"),
                bookAuthorJoin.get("bookName"),
                bookAuthorJoin.get("publisher")
                ));
        List<SearchDTO> result = entityManager.createQuery(criteriaQuery).getResultList();
        return new PageImpl<>(result, PageRequest.of(0,10), result.size());
    }
}
