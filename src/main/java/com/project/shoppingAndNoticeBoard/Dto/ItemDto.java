package com.project.shoppingAndNoticeBoard.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class ItemDto {

    private Long id;

    private String itemNm;

    private Integer price;

    private String itemDetail;

    private String sellStatCd;


    @Builder
    public ItemDto(String itemNm, Integer price, String itemDetail, String sellStatCd) {
        this.itemNm = itemNm;
        this.price = price;
        this.sellStatCd = sellStatCd;
        this.itemDetail = itemDetail;
    }
}
