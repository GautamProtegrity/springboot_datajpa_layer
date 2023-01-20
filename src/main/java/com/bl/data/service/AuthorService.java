package com.bl.data.service;

import com.bl.data.domain.dto.AuthorDTO;
import com.bl.data.exception.AuthorCreationException;
import com.bl.data.exception.AuthorNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {

    public List<AuthorDTO> fetchAll();

    public AuthorDTO createAuthor(AuthorDTO request)throws AuthorCreationException;

    public AuthorDTO getAuthorById(Integer id)throws AuthorNotFoundException;

    public AuthorDTO updateAuthor(Integer id, AuthorDTO request)throws AuthorNotFoundException;

    public AuthorDTO deleteAuthor(Integer id)throws AuthorNotFoundException;
}
