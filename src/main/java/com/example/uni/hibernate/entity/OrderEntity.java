package com.example.uni.hibernate.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(optional = false)
    private UserEntity user;

    @Column(nullable = false)
    private LocalDateTime created;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderEntryEntity> books;
}
