package com.web.blog.service;

import java.util.List;
import java.util.stream.Collectors;

import com.web.blog.dao.blog.BlogRepository;
import com.web.blog.model.blog.Blog;
import com.web.blog.model.blog.BlogListResponseDto;
import com.web.blog.model.blog.BlogResponseDto;
import com.web.blog.model.blog.BlogSaveRequestDto;
import com.web.blog.model.blog.BlogUpdateRequestDto;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BlogService {
  private final BlogRepository blogRepository;

  @Transactional
  public Long save(BlogSaveRequestDto requestDto){
    return blogRepository.save(requestDto.toEntity()).getBid();
  }

  @Transactional
  public Long update(Long bid, BlogUpdateRequestDto requestDto){
    Blog blog = blogRepository.findByBid(bid)
                .orElseThrow(() -> new IllegalArgumentException("해당 블로그가 없습니다. bid=" + bid));
        blog.update(requestDto.getBlogname());
        return bid;
  }

  @Transactional
    public void delete (Long bid) {
      Blog blog = blogRepository.findByBid(bid)
                .orElseThrow(() -> new IllegalArgumentException("해당 블로그가 없습니다. bid=" + bid));

                blogRepository.delete(blog);
    }

    @Transactional(readOnly = true)
    public BlogResponseDto findByBid(Long bid) {
        Blog entity = blogRepository.findByBid(bid)
                .orElseThrow(() -> new IllegalArgumentException("해당 블로그가 없습니다. bid=" + bid));

        return new BlogResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<BlogListResponseDto> findAllDesc() {
        return blogRepository.findAllDesc().stream()
                .map(BlogListResponseDto::new)
                .collect(Collectors.toList());
    }
}