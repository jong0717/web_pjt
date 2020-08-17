package com.web.blog.model.heart;

import javax.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class HeartRequest {

    @NotBlank
    private String accessToken;

    // @NotBlank
    private Long pno;
}
