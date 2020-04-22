package com.example.uni.hibernate.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class OrderEntryEntity {

    @EmbeddedId
    private OrderEntryId id = new OrderEntryId();

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "orderId"/*, insertable = false, updatable = false, nullable = false*/)
    private OrderEntity order;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "bookId"/*, insertable = false, updatable = false, nullable = false*/)
    private BookEntity book;

    private int price;

    private int amount;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    public static class OrderEntryId implements Serializable {
        private static final long serialVersionUID = -7220952260230685065L;
        private long orderId;
        private long bookId;
    }

}
