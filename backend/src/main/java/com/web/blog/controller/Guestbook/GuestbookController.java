package com.web.blog.controller.Guestbook;

import javax.validation.Valid;

import com.web.blog.model.guestbook.GuestbookRequest;
import com.web.blog.payload.response.MessageResponse;
import com.web.blog.security.jwt.JwtUtils;
import com.web.blog.service.GuestbookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/guestbook")
public class GuestbookController {

    private static final MessageResponse SUCCESS_RESPONSE = new MessageResponse("success");
    private static final MessageResponse FAILURE_RESPONSE = new MessageResponse("failure");

    @Autowired
    GuestbookService guestbookService;

    @Autowired
    JwtUtils jwtUtils;

    @GetMapping("/list")
    @ApiOperation(value = "현재 블로그의 방명록 불러오기")
    public Object getGuestbookList(@RequestParam(required = true) final Long blogId) {
        return new ResponseEntity<>(guestbookService.findByBlogBid(blogId), HttpStatus.OK);
    }

    @PostMapping("/insert")
    @ApiOperation(value = "현재 블로그에 방명록 등록하기")
    public Object save(@Valid @RequestBody GuestbookRequest request) {
        Object ret;
        try {
            guestbookService.save(request);
            ret = new ResponseEntity<>(SUCCESS_RESPONSE, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            ret = new ResponseEntity<>(FAILURE_RESPONSE, HttpStatus.BAD_REQUEST);
        }
        return ret;
    }

    @DeleteMapping("/delete/{gid}")
    @ApiOperation(value = "방명록 삭제하기 (글 쓴 사람/블로그 주인만)")
    public Object delete(@PathVariable Long gid) {
        Object ret;
        try {
            guestbookService.delete(gid);
            ret = new ResponseEntity<>(SUCCESS_RESPONSE, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            ret = new ResponseEntity<>(FAILURE_RESPONSE, HttpStatus.BAD_REQUEST);
        }
        return ret;
    }

    @PutMapping("/modify/{gid}")
    @ApiOperation(value = "방명록 수정하기 (글 쓴 사람만)")
    public Object modify(@PathVariable Long gid, @Valid @RequestBody GuestbookRequest request) {
        Object ret;
        try {
            guestbookService.update(gid, request);
            ret = new ResponseEntity<>(SUCCESS_RESPONSE, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            ret = new ResponseEntity<>(FAILURE_RESPONSE, HttpStatus.BAD_REQUEST);
        }
        return ret;
    }
}
