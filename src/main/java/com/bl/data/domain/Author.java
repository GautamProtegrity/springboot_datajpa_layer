package com.bl.data.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference
    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    private List<Book> books;
}
