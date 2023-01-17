package com.project.shoppingAndNoticeBoard.entity;

import com.project.shoppingAndNoticeBoard.constant.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.jpa.repository.EntityGraph;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private LocalDateTime regTime;

    private LocalDateTime updateTime;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @Builder
    public Order(Member member, LocalDateTime orderDate, OrderStatus orderStatus, LocalDateTime regTime, LocalDateTime updateTime) {

        this.member = member;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.regTime = regTime;
        this.updateTime = updateTime;
    }
}
