package com.ss.lms.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "book")
public class Book {

    private int id;

    private String isbnNumber;

    private String title;

    private int publishYear;

    private int pages;

    private int edition;


}
