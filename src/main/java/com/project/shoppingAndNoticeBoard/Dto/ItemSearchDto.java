package com.project.shoppingAndNoticeBoard.Dto;

import com.project.shoppingAndNoticeBoard.constant.ItemSellStatus;
import lombok.Data;

@Data
public class ItemSearchDto {

    private ItemSellStatus searchSellStatus;

    private String searchDataType;

    private String searchBy;

    private String searchQuery = "";
}
