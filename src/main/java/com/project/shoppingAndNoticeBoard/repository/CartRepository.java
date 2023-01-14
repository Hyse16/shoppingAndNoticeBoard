package com.project.shoppingAndNoticeBoard.repository;

import com.project.shoppingAndNoticeBoard.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
