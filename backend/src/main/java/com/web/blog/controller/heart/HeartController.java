package com.web.blog.controller.heart;

import javax.validation.Valid;

import com.web.blog.dao.heart.HeartDao;
import com.web.blog.dao.posts.PostsRepository;
import com.web.blog.dao.user.UserDao;
import com.web.blog.model.heart.Heart;
import com.web.blog.model.heart.HeartRequest;
import com.web.blog.payload.response.MessageResponse;
import com.web.blog.security.jwt.JwtUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/heart")
public class HeartController {

    @Autowired
    HeartDao heartDao;

    @Autowired
    UserDao userDao;

    @Autowired
    PostsRepository postDao;

    @Autowired
    JwtUtils jwtutils;

    @PostMapping("/click")
    @ApiOperation(value = "좋아요 등록 <-> 해제")
    public Object clickHeart(@Valid @RequestBody HeartRequest request) {
        Long uid = jwtutils.getUidFromJwtToken(request.getAccessToken());

        if (Boolean.TRUE.equals(heartDao.existsByPostPnoAndUserUid(request.getPno(), uid))) {
            heartDao.deleteByPostPnoAndUserUid(request.getPno(), uid);
            postDao.minusHeart(request.getPno());

            return new ResponseEntity<>(new MessageResponse("hate"), HttpStatus.OK);
        }
        else {
            heartDao.save(Heart.builder()
                                .post(postDao.findByPno(request.getPno())
                                            .orElseThrow(() -> new IllegalArgumentException("게시판 조회 실패")))
                                .user(userDao.findById(uid)
                                            .orElseThrow(() -> new IllegalArgumentException("유저 조회 실패")))
                                .build());
            postDao.plusHeart(request.getPno());
            return new ResponseEntity<>(new MessageResponse("like"), HttpStatus.OK);
        }
    }

    @PostMapping("/check")
    @ApiOperation(value = "내가 이 게시글 하트를 클릭했는지 체크")
    public Object checkHeart(@Valid @RequestBody HeartRequest request) {
        Long uid = jwtutils.getUidFromJwtToken(request.getAccessToken());
        boolean result = Boolean.TRUE.equals(heartDao.existsByPostPnoAndUserUid(request.getPno(), uid));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
