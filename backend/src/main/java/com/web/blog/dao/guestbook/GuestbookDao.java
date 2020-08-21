package com.web.blog.dao.guestbook;

import java.util.List;

import com.web.blog.model.guestbook.Guestbook;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestbookDao extends JpaRepository<Guestbook, Long> {

    List<Guestbook> findAllByBlogBid(Long bid);

    void deleteByGid(Long gid);

    @Modifying
    @Query(value = "DELETE FROM guestbooks WHERE blog_id = :bid LIMIT :count", nativeQuery = true)
    int deleteAllByBlogBidFirstN(@Param("bid") Long bid, @Param("count") int count);
}
