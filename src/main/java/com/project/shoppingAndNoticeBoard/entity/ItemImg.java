package com.project.shoppingAndNoticeBoard.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "item_img")
@Getter@ToString
@NoArgsConstructor
public class ItemImg {

    @Id
    @GeneratedValue
    @Column(name = "item_img_id")
    private Long id;

    private String imgName;

    private String oriImgName;


    private String imgUrl;

    private String repImgYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public void updateItemImg(String oriImgName, String imgName, String imgUrl) {
        this.oriImgName = oriImgName;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
    }

    @Builder
    public ItemImg(String imgName, String oriImgName, String imgUrl, String repImgYn, Item item) {
        this.imgName = imgName;
        this.oriImgName = oriImgName;
        this.imgUrl = imgUrl;
        this.repImgYn = repImgYn;
        this.item = item;
    }
}

