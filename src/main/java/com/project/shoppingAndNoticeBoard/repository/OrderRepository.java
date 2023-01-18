package com.project.shoppingAndNoticeBoard.repository;

import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
