package com.example.uni.hibernate.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"id", "title"})
@Builder
public class BookEntity {
    
    /**
     * Идентификатор книги
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Название книги
     */
    @Column(nullable = false, length = 255)
    private String title;

    /**
     * Список авторов
     */
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AuthorEntity> authors;

    /**
     * Год издания
     */
    private int year;

    /**
     * Международный стандартный книжный номер
     */
    @Column(nullable = false, length = 17)
    private String ISBN;

    /**
     * Цена
     */
    private int price;

    /**
     * Описание книги
     */
    @Column(nullable = false, length = 2048)
    private String description;
}
