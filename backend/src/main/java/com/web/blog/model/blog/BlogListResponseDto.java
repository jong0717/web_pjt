package com.web.blog.model.blog;

import lombok.Getter;

@Getter
public class BlogListResponseDto {
    private Long bid;
    private Long uid;
    private String blogname;
    private Long template_num;
    private Long visitors_num;

    public BlogListResponseDto(Blog entity){
        this.bid = entity.getBid();
        this.uid = entity.getUid();
        this.blogname = entity.getBlogname();
        this.template_num = entity.getTemplate_num();
        this.visitors_num = entity.getVisitors_num();
    }
}