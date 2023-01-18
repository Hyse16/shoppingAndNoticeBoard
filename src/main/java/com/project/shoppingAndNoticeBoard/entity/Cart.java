package com.project.shoppingAndNoticeBoard.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "cart")
@ToString
@Getter
@NoArgsConstructor
public class Cart {

    @Id
    @Column(name = "cart_id")
    @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Cart(Member member) {
        this.member = member;
    }
}
