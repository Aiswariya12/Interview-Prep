package com.interviewprep.repository;

import com.interviewprep.entity.UserBadge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserBadgeRepository extends JpaRepository<UserBadge, Long> {
    List<UserBadge> findByUserIdOrderByEarnedAtDesc(Long userId);
    Boolean existsByUserIdAndBadgeKey(Long userId, String badgeKey);
    Optional<UserBadge> findByUserIdAndBadgeKey(Long userId, String badgeKey);
}
