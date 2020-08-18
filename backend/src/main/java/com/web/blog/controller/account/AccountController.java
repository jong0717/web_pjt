package com.web.blog.controller.account;

import javax.validation.Valid;

import com.web.blog.dao.user.RoleDao;
import com.web.blog.dao.user.UserDao;
import com.web.blog.model.BasicResponse;
import com.web.blog.model.blog.Blog;
import com.web.blog.model.posts.Posts;
import com.web.blog.payload.request.UserRequest;
import com.web.blog.payload.response.UserResponse;
import com.web.blog.security.jwt.JwtUtils;
import com.web.blog.security.service.UserDetailsServiceImpl;
import com.web.blog.service.BlogService;
import com.web.blog.service.GuestbookService;
import com.web.blog.service.PostsService;
import com.web.blog.model.user.User;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.beans.factory.annotation.Autowired;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
@RequestMapping("/account")
public class AccountController {

    private final String errorMessage = "유저 정보 얻기 실패";

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;

    @Autowired
    UserDetailsServiceImpl userService;

    @Autowired
    BlogService blogService;

    @Autowired
    PostsService postService;

    @Autowired
    GuestbookService guestbookService;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/userinfo")
    @ApiOperation(value = "유저 정보 얻기")
    public Object getUserInfo(@RequestParam(required = true) final String accessToken) {
        User user = userDao.getUserByUid(jwtUtils.getUidFromJwtToken(accessToken))
                            .orElseThrow(() -> new IllegalArgumentException(errorMessage));

        return new ResponseEntity<>(new UserResponse(user), HttpStatus.OK);
    }

    @PutMapping("/modify")
    @ApiOperation(value = "수정하기")
    public Object modify(@Valid @RequestBody UserRequest request) {
        final BasicResponse result = new BasicResponse();
        User user = userDao.getUserByUid(jwtUtils.getUidFromJwtToken(request.getAccessToken()))
                            .orElseThrow(() -> new IllegalArgumentException(errorMessage));

        try {
            user.setNickname(request.getNickname());
            user.setPassword(encoder.encode(request.getPassword()));
            user.setIntroduce(request.getIntroduce());
            user.setImageUrl(request.getImageUrl());

            userDao.save(user);

            result.status = true;
            result.data = "success";

            Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(user.getEmail(), request.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            result.accessToken = jwtUtils.generateJwtToken(authentication);

        } catch (Exception e) {
            result.status = false;
            result.data = "fail";
            result.message = "회원 정보 수정 실패";

            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/withdrawal")
    @ApiOperation(value = "탈퇴하기")
    public Object withdrawal(@Valid @RequestBody String accessToken) {
        final BasicResponse result = new BasicResponse();

        try {
            User user = userDao.getUserByUid(jwtUtils.getUidFromJwtToken(accessToken))
                                .orElseThrow(() -> new IllegalArgumentException(errorMessage));

            postService.deleteAllByUsersUid(user.getUid());

            if (user.getBlogs() != null) {
                for (Blog b : user.getBlogs()) {
                    guestbookService.deleteAll(b.getBid());
                    blogService.delete(b.getBid());
                }
            }

            userDao.deleteByUid(jwtUtils.getUidFromJwtToken(accessToken));

            result.status = true;
            result.data = "success";
            result.message = "회원 탈퇴 성공";
        } catch (Exception e) {
            e.printStackTrace();

            result.status = false;
            result.data = "fail";
            result.message = "회원 탈퇴 실패";

            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
