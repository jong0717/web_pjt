package com.web.blog.controller.blog;

import java.util.List;

import com.web.blog.service.BlogService;
import com.web.blog.service.GuestbookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import com.web.blog.model.blog.*;

@CrossOrigin(origins = { "http://localhost:3000" })
@RequiredArgsConstructor
@RestController
public class BlogController {
  
  private final BlogService blogService;

  @Autowired
  GuestbookService guestbookService;
  
  @PostMapping("/api/blog/insert")
  @ApiOperation(value = "블로그 개설하기")
  public Long save(@RequestBody BlogSaveRequestDto requestDto){
    return blogService.save(requestDto);
  }

  @PutMapping("api/blog/insert/{bid}")
  @ApiOperation(value = "블로그 이름 수정하기")
  public Long update(@PathVariable Long bid, @RequestBody BlogUpdateRequestDto requestDto){
      return blogService.update(bid, requestDto);
  }

  @DeleteMapping("api/blog/{bid}")
  @ApiOperation(value = "블로그 삭제하기")
  public Long delete(@PathVariable Long bid) {
    Blog blog = blogService.findBlog(bid);
    if (blog.getGuestbooks() != null)
      guestbookService.deleteAll(bid);
    blogService.delete(bid);
    return bid;
  }

  @GetMapping("api/blog/{bid}")
  @ApiOperation(value = "블로그 가져오기")
  public BlogResponseDto findByBid(@PathVariable Long bid){
      return blogService.findByBid(bid);
  }   

  @GetMapping("/api/blog/list")
  @ApiOperation(value = "전체 블로그 목록 불러오기")
  public List<BlogListResponseDto> findAll() {
      return blogService.findAllDesc();
  }

}