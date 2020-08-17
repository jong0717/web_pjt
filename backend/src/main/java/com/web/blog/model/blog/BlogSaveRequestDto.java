package com.web.blog.model.blog;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BlogSaveRequestDto {
    private Long bid;
    private Long uid;
    private String blogname;
    private Long template_num;
    private Long visitors_num;

    @Builder
    public BlogSaveRequestDto(Long bid, Long uid, String blogname, Long template_num, Long visitors_num){
        this.bid = bid;
        this.uid = uid;
        this.blogname = blogname;
        this.template_num = template_num;
        this.visitors_num = visitors_num;
    }

    public Blog toEntity(){
        return Blog.builder()
                .bid(bid)
                .uid(uid)
                .blogname(blogname)
                .template_num(template_num)
                .visitors_num(visitors_num)
                .build();
    }
}