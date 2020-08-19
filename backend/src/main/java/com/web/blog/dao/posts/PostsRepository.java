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

    @Query("SELECT p FROM Posts p, Blog b where p.bid = b.bid and p.bid = :bid ORDER BY p.pno DESC")
    Page<Posts> findAllDesc(Pageable pageable, @Param("bid") Long bid);

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

    @Query("SELECT p.tag FROM Posts p WHERE p.pno = :pno")
    String getPostTag(@Param("pno") Long pno);

    @Query("SELECT p FROM Posts p where p.tag like '%' || :tagName || '%'")
    List<Posts> findByTagContaining(@Param("tagName") String tagName);

    @Modifying
    @Query(value = "DELETE FROM posts WHERE uid = :uid LIMIT :count", nativeQuery = true)
    int deleteAllByUsersUidFirstN(@Param("uid") Long uid, @Param("count") int count);
}
