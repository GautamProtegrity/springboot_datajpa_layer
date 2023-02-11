package com.bl.data.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "AUTHOR")
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_generator")
    @SequenceGenerator(name = "author_generator", sequenceName = "author_seq", allocationSize = 1)
    @Column(name="AUTHOR_CODE")
    private Integer authorCode;
    @Column(name="AUTHOR_NAME")
    private String authorName;
    @Column(name="AUTHOR_DETAILS")
    private String authorDetails;
    @Column(name="SCRIPT_INTRO_LAYOUT", columnDefinition = "TEXT")
    private String introLayout;
    @OneToMany(mappedBy = "author", fetch=FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Book> books;
}
