package com.example.uni.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import java.io.File;
import java.util.function.Consumer;

public class HibernateUtils {
    private static final SessionFactory SESSION_FACTORY;

    static {
        SESSION_FACTORY = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    /**
     * Исполняет переданный код внутри Hibernate сессии без создания транзакции.
     *
     * @param inSession callback для выполнения в рамках Hibernate сессии
     */
    public static void executeWithSession(Consumer<Session> inSession) {
        Session session = SESSION_FACTORY.openSession();
        try {
            inSession.accept(session);
        } finally {
            session.close();
        }
    }

    /**
     * Исполняет переданный код внутри Hibernate транзакции.
     * После выполнения закрыват транзакцию и сессию.
     *
     * @param inTransaction callback для выполнения в транзакции
     */
    public static void executeInTransaction(Consumer<Session> inTransaction) {
        executeWithSession(session -> {
            Transaction transaction = session.beginTransaction();
            try {
                inTransaction.accept(session);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                transaction.rollback();
                throw e;
            }
        });
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
