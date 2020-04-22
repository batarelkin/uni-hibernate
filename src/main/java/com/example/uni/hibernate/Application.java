package com.example.uni.hibernate;

import com.example.uni.hibernate.entity.AuthorEntity;
import com.example.uni.hibernate.entity.BookEntity;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Query;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Application {
    public static void main(String[] args) {
        try {
            new Application().run();
        } finally {
            HibernateUtils.shutdown();
        }
    }

    public void run() {
//        exampleSelect();
//        exampleCreate();
//        cleanUpAfterCreate();
        advancedCreateExample();
    }

    public void exampleSelect () {
        System.out.println("************************ exampleSelect ****************************");
        HibernateUtils.executeWithSession(session -> {
            BookEntity book = (BookEntity) session.get(BookEntity.class, 3L);
            log.info("{}", book);
            book.getAuthors().forEach(author -> {
                log.info("Author {}", author);
            });
        });
        System.out.println("************************ exampleSelect End ************************");
    }

    public void exampleCreate () {
        System.out.println("************************ exampleCreate ****************************");
        HibernateUtils.executeInTransaction(session -> {
            AuthorEntity author1 = new AuthorEntity();
            author1.setName("Author 1");
            author1.setDescription("Author 1 description");
            session.save(author1);

            AuthorEntity author2 = new AuthorEntity();
            author2.setName("Author 2");
            author2.setDescription("Author 2 description");
            session.save(author2);
        });
        System.out.println("************************ exampleCreate End ************************");
    }

    public void cleanUpAfterCreate () {
        System.out.println("********************* cleanUpAfterCreate ****************************");
//        HibernateUtils.executeWithSession(session -> {
        HibernateUtils.executeInTransaction(session -> {
            Query query = session.createQuery("FROM AuthorEntity where name LIKE CONCAT(:name,'%')");
            query.setParameter("name", "Author");
            query.list().forEach(a -> {
                System.out.println(a);
                session.delete(a);
            });
            session.flush();
        });
        System.out.println("********************* cleanUpAfterCreate End ************************");
    }

    public void advancedCreateExample () {
        System.out.println("************************ advancedCreateExample ****************************");
        HibernateUtils.executeInTransaction(session -> {
            AuthorEntity author1 = new AuthorEntity();
            author1.setName("!Author 1");
            author1.setDescription("!Author 1 description");
//            session.save(author1);

            AuthorEntity author2 = new AuthorEntity();
            author2.setName("!Author 2");
            author2.setDescription("!Author 2 description");
//            session.save(author1);

//            session.flush();

            List<AuthorEntity> authors = new ArrayList<>();
            authors.add(author1);
            authors.add(author2);

            BookEntity book = BookEntity.builder()
                    .title("Book1")
                    .description("Book1 description")
                    .ISBN("AAAAAAA")
                    .year(2018)
                    .price(123)
                    .authors(authors)
                    .build();
            session.save(book);

//            book.getAuthors().add(author1);
//            book.getAuthors().add(author2);
//
//            session.save(book);

            System.out.println(book.getAuthors());

        });
        System.out.println("************************ advancedCreateExample End ************************");
    }
}
