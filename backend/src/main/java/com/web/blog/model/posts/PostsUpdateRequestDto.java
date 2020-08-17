package com.web.blog.model.posts;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private String title;
    private String content;
    private String img;
    private String tag;
    private LocalDateTime createDate;
    
    @Builder
    public PostsUpdateRequestDto(String title, String content, String img, String tag, LocalDateTime createDate) {
        this.title = title;
        this.content = content;
        this.img = img;
        this.tag = tag;
        this.createDate = createDate;
    }
}
