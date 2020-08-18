package com.web.blog.model.guestbook;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GuestbookResponse {

    private Long gid;
    private String writerNickname;
    private String content;
    private LocalDateTime createDate;

    @Builder
    public GuestbookResponse(Long gid, String writerNickname, String content, LocalDateTime createDate) {
        this.gid = gid;
        this.writerNickname = writerNickname;
        this.content = content;
        this.createDate = createDate;
    }
}
