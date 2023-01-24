package com.project.shoppingAndNoticeBoard.Dto;

import com.project.shoppingAndNoticeBoard.entity.ItemImg;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemImgDto {

    private Long id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private String repImgYn;

    @Builder
    public ItemImgDto(String imgName, String oriImgName, String imgUrl, String repImgYn) {
        this.imgName = imgName;
        this.oriImgName = oriImgName;
        this.imgUrl = imgUrl;
        this.repImgYn = repImgYn;
    }

    public ItemImg toEntity(ItemImgDto dto) {
        ItemImg entity = ItemImg.builder()
                .imgName(dto.imgName)
                .oriImgName(dto.oriImgName)
                .imgUrl(dto.imgUrl)
                .repImgYn(dto.repImgYn)
                .build();

        return entity;
    }

    public ItemImgDto of(ItemImg entity) {
        ItemImgDto dto = ItemImgDto.builder()
                .imgName(entity.getImgName())
                .oriImgName(entity.getOriImgName())
                .imgUrl(entity.getImgUrl())
                .repImgYn(entity.getRepImgYn())
                .build();

        return dto;
    }

}
