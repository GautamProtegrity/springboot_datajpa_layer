package com.bl.data.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="BOOK")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_generator")
    @SequenceGenerator(name = "book_generator", sequenceName = "book_seq", allocationSize = 1)
    @Column(name="BOOK_CODE")
    private Integer bookCode;
    @Column(name="BOOK_NAME")
    private String bookName;
    @Column(name="PUBLISHER")
    private String publisher;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "AUTHOR_ID")
    private Author author;
}
