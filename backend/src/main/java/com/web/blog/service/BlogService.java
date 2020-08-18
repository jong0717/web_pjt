package com.web.blog.service;

import java.util.List;
import java.util.stream.Collectors;

import com.web.blog.dao.blog.BlogRepository;
import com.web.blog.model.blog.Blog;
import com.web.blog.model.blog.BlogListResponseDto;
import com.web.blog.model.blog.BlogResponseDto;
import com.web.blog.model.blog.BlogSaveRequestDto;
import com.web.blog.model.blog.BlogUpdateRequestDto;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BlogService {
    private final String errorMessage = "해당 블로그가 없습니다. bid=";
    private final BlogRepository blogRepository;

    @Transactional
    public Long save(BlogSaveRequestDto requestDto){
        return blogRepository.save(requestDto.toEntity()).getBid();
    }

    @Transactional
    public Long update(Long bid, BlogUpdateRequestDto requestDto){
    Blog blog = blogRepository.findById(bid)
                .orElseThrow(() -> new IllegalArgumentException(errorMessage + bid));
        blog.update(requestDto.getBlogname());
        return bid;
    }

    @Transactional
    public void delete (Long bid) {
        blogRepository.deleteByBid(bid);
    }

    @Transactional(readOnly = true)
    public BlogResponseDto findByBid(Long bid) {
        Blog entity = blogRepository.findByBid(bid)
                .orElseThrow(() -> new IllegalArgumentException(errorMessage + bid));

        return new BlogResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<BlogListResponseDto> findAllDesc() {
        return blogRepository.findAllDesc().stream()
                .map(BlogListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Blog findBlog(Long bid) {
        return blogRepository.findById(bid)
                    .orElseThrow(() -> new IllegalArgumentException(errorMessage + bid));
    }
}
