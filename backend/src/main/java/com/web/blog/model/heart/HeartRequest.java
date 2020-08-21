package com.web.blog.model.heart;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HeartRequest {

    @NotBlank
    private String accessToken;

    // @NotBlank
    private Long pno;
}
