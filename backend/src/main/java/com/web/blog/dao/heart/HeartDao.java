package com.web.blog.dao.heart;

import com.web.blog.model.heart.Heart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface HeartDao extends JpaRepository<Heart, Long> {

    Boolean existsByPostPnoAndUserUid(Long postId, Long userId);

    @Modifying
    @Transactional
    void deleteByPostPnoAndUserUid(Long postId, Long userId);
}
