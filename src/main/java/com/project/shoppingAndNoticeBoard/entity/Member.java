package com.project.shoppingAndNoticeBoard.entity;

import com.project.shoppingAndNoticeBoard.Dto.MemberFormDto;
import com.project.shoppingAndNoticeBoard.constant.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "member")
@Getter
@ToString
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String address;

    @Enumerated(EnumType.STRING.STRING)
    private Role role;

}
