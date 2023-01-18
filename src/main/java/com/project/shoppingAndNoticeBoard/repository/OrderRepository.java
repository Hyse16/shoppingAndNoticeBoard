package com.project.shoppingAndNoticeBoard.repository;

import com.project.shoppingAndNoticeBoard.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
