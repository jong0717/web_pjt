package com.web.blog.model.posts;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class PostsResponseDto {
    private Long pno;
    private Long uid;
    private Long bid;
    private String title;
    private String content;
    private Long heart;
    private String img;
    private LocalDateTime createDate;

    public PostsResponseDto(Posts entity) {
        this.pno = entity.getPno();
        this.uid = entity.getUid();
        this.bid = entity.getBid();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.heart = entity.getHeart();
        this.img = entity.getImg();
        this.createDate = entity.getCreateDate();
    }
}
