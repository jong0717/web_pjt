package com.web.blog.model.guestbook;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class GuestbookRequest {

    @NotNull
    private Long blogId;

    @NotNull
    private Long writerUid;

    @NotNull
    private String content;

    @NotNull
    private LocalDateTime createTime;
}
