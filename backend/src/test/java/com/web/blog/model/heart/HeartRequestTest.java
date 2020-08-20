package com.web.blog.model.heart;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class HeartRequestTest {

    @Test
    public void getAccessToken() {
        final HeartRequest heartRequest = new HeartRequest("accessToken", 1234L);
        final String accessToken = heartRequest.getAccessToken();

        assertEquals("accessToken", accessToken);
    }

    @Test
    public void getPno() {
        final HeartRequest heartRequest = new HeartRequest("accessToken", 1234L);
        Long pno = heartRequest.getPno();

        assertEquals(1234L, pno);
    }
}
