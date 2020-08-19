package com.web.blog;

import javax.transaction.Transactional;

import com.web.blog.service.GuestbookService;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

@SpringBootTest(
    properties = {
        "email = test@test.com",
        "nickname = test"
    },
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@Transactional
@AutoConfigureMockMvc
class WebBlogApplicationTests {

    @Value("${email}")
    private String email;

    @Value("${nickname}")
    private String nickname;

    @Autowired
    MockMvc mvc;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private GuestbookService guestbookService;

    @Autowired
    private WebApplicationContext ctx;
}
