package com.bl.data.repositories;

import com.bl.data.domain.Author;
import com.bl.data.repositories.custom.AuthorCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>, AuthorCustomRepository {
}
