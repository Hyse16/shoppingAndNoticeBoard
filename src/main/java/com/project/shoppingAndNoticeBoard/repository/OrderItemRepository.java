package com.project.shoppingAndNoticeBoard.repository;

import com.project.shoppingAndNoticeBoard.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
