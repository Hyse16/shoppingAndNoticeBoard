package com.project.shoppingAndNoticeBoard.repository;

import com.project.shoppingAndNoticeBoard.Dto.ItemDto;
import com.project.shoppingAndNoticeBoard.constant.ItemSellStatus;
import com.project.shoppingAndNoticeBoard.entity.Item;
import com.project.shoppingAndNoticeBoard.entity.QItem;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest
class ItemRepositoryTest {

    @PersistenceContext
    EntityManager em;

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



    public void createItemList() {
        for (int i = 1; i <= 10; i++) {
            Item item = Item.builder()
                    .itemNm("테스트상품" + i)
                    .price(10000 + i)
                    .itemDetail("테스트 상품 상세설명" + i)
                    .stockNumber(100)
                    .itemSellStatus(ItemSellStatus.SELL)
                    .regTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .build();
            Item saveItem = itemRepository.save(item);

        }
    }

    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByItemNmTest() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemNm("테스트 상품1");
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("상품명, 상세설명 Or 테스트")
    public void findByItemNmOrItemDetailTest() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트상품1", "테스트 상품 상세설명5");
        for (Item item : itemList) {
            System.out.println(item.toString());

        }
    }

    @Test
    @DisplayName("가격 LessThan 테스트")
    public void findByPriceLessThanTest() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByPriceLessThan(10005);
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("가격 내림차순 테스트")
    public void findByPriceLessThanOrderByPriceDesc() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10005);
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("@Query를 이용한 상품 테스트")
    public void findByItemDetailTest() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemDetail("테스트 상품 상세설명");
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("nativeQuery 속성 조회 테스트")
    public void findByItemDetailByNative() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemDetailByNative("테스트 상품 상세설명");
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("QueryDsl 조회 테스트")
    public void queryDslTest() {

        this.createItemList();
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QItem item = QItem.item;
        JPAQuery<Item> query = queryFactory.selectFrom(item)
                .where(item.itemSellStatus.eq(ItemSellStatus.SELL))
                .where(item.itemDetail.like("%" + "테스트 상품 상세설명" + "%"))
                .orderBy(item.price.desc());

        List<Item> itemList = query.fetch();

        for (Item item1 : itemList) {
            System.out.println(item1.toString());
        }
    }

    public void createItemList2(){
        for(int i=1;i<=5;i++) {
            Item item = Item.builder()
                    .itemNm("테스트 상품" + i)
                    .price(10000+i)
                    .itemDetail("테스트 상품 상세설명" + i)
                    .itemSellStatus(ItemSellStatus.SELL)
                    .stockNumber(100)
                    .regTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .build();
            itemRepository.save(item);

        }

        for(int i=6;i<=10;i++) {
            Item item = Item.builder()
                    .itemNm("테스트 상품" + i)
                    .price(10000+i)
                    .itemDetail("테스트 상품 상세설명" + i)
                    .itemSellStatus(ItemSellStatus.SELL)
                    .stockNumber(100)
                    .regTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .build();
            itemRepository.save(item);
        }
    }


    @Test
    @DisplayName("상품 Querydsl 조회 테스트 2")
    public void queryDslTest2(){

        this.createItemList2();

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QItem item = QItem.item;
        String itemDetail = "테스트 상품 상세설명";
        int price = 10003;
        String itemSellStat = "SELL";

        booleanBuilder.and(item.itemDetail.like("%" + itemDetail + "%"));
        booleanBuilder.and(item.price.gt(price));
        System.out.println(ItemSellStatus.SELL);
        if(StringUtils.equals(itemSellStat, ItemSellStatus.SELL)){
            booleanBuilder.and(item.itemSellStatus.eq(ItemSellStatus.SELL));
        }

        Pageable pageable = PageRequest.of(0, 7);
        Page<Item> itemPagingResult = itemRepository.findAll(booleanBuilder, pageable);
        System.out.println("total elements : " + itemPagingResult. getTotalElements ());

        List<Item> resultItemList = itemPagingResult.getContent();
        for(Item resultItem: resultItemList){
            System.out.println(resultItem.toString());
        }
    }

}