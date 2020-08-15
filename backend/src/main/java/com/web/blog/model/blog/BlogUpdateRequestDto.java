package com.web.blog.model.blog;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BlogUpdateRequestDto {
    private String blogname;

    @Builder
    public BlogUpdateRequestDto(String blogname){
        this.blogname = blogname;
    }
}