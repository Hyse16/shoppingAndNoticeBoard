package com.project.shoppingAndNoticeBoard.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ItemDto {

    private Long id;

    private String itemNm;

    private Integer price;

    private String ItemDetail;

    private String sellStatCd;

    private LocalDateTime regTime;

    private LocalDateTime updateTime;
}