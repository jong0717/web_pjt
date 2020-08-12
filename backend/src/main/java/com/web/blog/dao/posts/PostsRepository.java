package com.web.blog.dao.posts;

import com.web.blog.model.posts.Posts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.pno DESC")
    // List<Posts> findAllDesc();
    Page<Posts> findAllDesc(Pageable pageable);

    //@Query("SELECT p.pno, p.uid, p.title, p.content, p.heart, p.createDate, u.nickname FROM Posts p, User u WHERE p.pno = :pno and p.uid = u.uid")
    Optional<Posts> findByPno(@Param("pno") Long pno);

    @Query("SELECT u.nickname FROM Posts p, User u WHERE p.pno = :pno and p.uid = u.uid")
    String getUserNickname(@Param("pno") Long pno);

    List<Posts> findByTitleContaining(@Param("title") String title);

    @Modifying
    @Transactional
    @Query(value = "UPDATE posts SET heart = heart+1 WHERE pno = :pno", nativeQuery = true)
    void plusHeart(@Param("pno") Long pno);

    @Modifying
    @Transactional
    @Query(value = "UPDATE posts SET heart = heart-1 WHERE pno = :pno", nativeQuery = true)
    void minusHeart(@Param("pno") Long pno);
}
