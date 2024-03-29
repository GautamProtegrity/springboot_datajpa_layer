package com.bl.data.service.impl;

import com.bl.data.common.utils.CommonUtil;
import com.bl.data.domain.Author;
import com.bl.data.domain.dto.AuthorDTO;
import com.bl.data.domain.dto.SearchDTO;
import com.bl.data.exception.AuthorCreationException;
import com.bl.data.exception.AuthorNotFoundException;
import com.bl.data.repositories.AuthorRepository;
import com.bl.data.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    CommonUtil commonUtil;

    @Override
    public List<AuthorDTO> fetchAll() {
        return commonUtil.mapAuthor(authorRepository.findAll());
    }

    @Override
    public AuthorDTO createAuthor(AuthorDTO request) {
        AuthorDTO authorDTO = null;
        try{
            Author author = new Author();
            author.setAuthorCode(request.getAuthorCode());
            author.setAuthorName(request.getAuthorName());
            author.setAuthorDetails(request.getAuthorDetails());
            author.setIntroLayout(request.getAuthorIntro());
            authorRepository.saveAndFlush(author);
            authorDTO = commonUtil.mapAuthor(List.of(author)).get(0);
        }catch(Exception ex){
            throw AuthorCreationException.builder()
                    .message(ex.getLocalizedMessage())
                    .dateTime(LocalDateTime.now())
                    .build();
        }

        return authorDTO;
    }

    @Override
    public AuthorDTO getAuthorById(Integer id) {
        AuthorDTO authorDTO = null;
        try{
            Author author = authorRepository.getReferenceById(id);
            authorDTO = commonUtil.mapAuthor(List.of(author)).get(0);
        }catch(Exception ex){
            throw AuthorNotFoundException.builder()
                    .message(ex.getLocalizedMessage())
                    .dateTime(LocalDateTime.now())
                    .build();
        }
        return authorDTO;
    }

    @Override
    public AuthorDTO updateAuthor(Integer id, AuthorDTO request) {
        AuthorDTO authorDTO = null;
        try{
            Author author =  authorRepository.getReferenceById(id);
            author.setAuthorName(request.getAuthorName());
            author.setAuthorDetails(request.getAuthorDetails());
            authorRepository.saveAndFlush(author);
            authorDTO = commonUtil.mapAuthor(List.of(author)).get(0);
        }catch(Exception ex){
            throw AuthorNotFoundException.builder()
                    .message(ex.getLocalizedMessage())
                    .dateTime(LocalDateTime.now())
                    .build();
        }
        return authorDTO;
    }

    @Override
    public AuthorDTO deleteAuthor(Integer id) {
        AuthorDTO authorDTO = null;
        try{
            Author author = authorRepository.findById(id).get();
            authorRepository.delete(author);
            authorDTO = commonUtil.mapAuthor(List.of(author)).get(0);
        }catch(Exception ex){
            throw AuthorNotFoundException.builder()
                    .message(ex.getLocalizedMessage())
                    .dateTime(LocalDateTime.now())
                    .build();
        }
        return authorDTO;
    }

    @Override
    public Page<SearchDTO> searchAuthorBookDetails() {
        return authorRepository.getPagedAllAuthor();
    }

}
