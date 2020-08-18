package com.web.blog.dao.blog;

import java.util.List;
import java.util.Optional;

import com.web.blog.model.blog.Blog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BlogRepository extends JpaRepository<Blog, Long>{

    @Query("SELECT b FROM Blog b ORDER BY b.bid DESC")
    List<Blog> findAllDesc();

    // @Query("SELECT b.bid, b.uid, b.blogname, b.template_num, b.visitors_num FROM Blog b, User u WHERE b.bid = :bid and b.uid = u.uid")
    Optional<Blog> findByBid(@Param("bid") Long bid);

    void deleteByBid(Long bid);
}
