package com.web.blog.dao.heart;

import javax.transaction.Transactional;

import com.web.blog.model.heart.Heart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface HeartDao extends JpaRepository<Heart, Long> {

    Boolean existsByPostPnoAndUserUid(Long postId, Long userId);

    @Modifying
    @Transactional
    void deleteByPostPnoAndUserUid(Long postId, Long userId);
}
