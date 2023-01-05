package com.project.shoppingAndNoticeBoard.Dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberFormDto {

    private String name;

    private String email;

    private String password;

    private String address;
}
