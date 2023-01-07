package com.project.shoppingAndNoticeBoard.repository;

import com.project.shoppingAndNoticeBoard.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);
}
