package com.project.shoppingAndNoticeBoard.repository;

import com.project.shoppingAndNoticeBoard.Dto.ItemSearchDto;
import com.project.shoppingAndNoticeBoard.entity.Item;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

public interface ItemRepositoryCustom {
    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

}
