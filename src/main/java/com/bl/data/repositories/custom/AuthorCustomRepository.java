package com.bl.data.repositories.custom;

import com.bl.data.domain.dto.AuthorDTO;
import com.bl.data.domain.dto.SearchDTO;
import org.springframework.data.domain.Page;

public interface AuthorCustomRepository {

    Page<SearchDTO> getPagedAllAuthor();
}
