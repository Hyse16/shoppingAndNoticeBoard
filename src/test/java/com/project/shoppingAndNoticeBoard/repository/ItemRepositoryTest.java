package com.project.shoppingAndNoticeBoard.repository;

import com.project.shoppingAndNoticeBoard.constant.ItemSellStatus;
import com.project.shoppingAndNoticeBoard.entity.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    @DisplayName("상품 저장 테스트")
    public void createItemTest() {
        Item item = Item.builder()
                .itemNm("테스트상품")
                .itemDetail("상품 상세 설명")
                .price(10000)
                .itemSellStatus(ItemSellStatus.SELL)
                .stockNumber(100)
                .build();

        Item savedItem = itemRepository.save(item);
        System.out.println(savedItem.toString());

    }
}