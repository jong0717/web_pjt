package com.web.blog.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.web.blog.dao.posts.PostsRepository;
import com.web.blog.model.posts.Posts;
import com.web.blog.model.posts.PostsListResponseDto;
import com.web.blog.model.posts.PostsResponseDto;
import com.web.blog.model.posts.PostsSaveRequestDto;
import com.web.blog.model.posts.PostsUpdateRequestDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostsService{
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getPno();
    }

    @Transactional
    public Long update(Long pno, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findByPno(pno)
                .orElseThrow(() -> new IllegalArgumentException("해당 글이 없습니다. pno=" + pno));

        posts.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getImg(), requestDto.getTag(), requestDto.getCreateDate());

        return pno;
    }

    @Transactional
    public void delete (Long pno) {
        Posts posts = postsRepository.findByPno(pno)
                .orElseThrow(() -> new IllegalArgumentException("해당 글이 없습니다. pno=" + pno));

        postsRepository.delete(posts);
    }

    @Transactional(readOnly = true)
    public PostsResponseDto findByPno(Long pno) {
        Posts entity = postsRepository.findByPno(pno)
                .orElseThrow(() -> new IllegalArgumentException("해당 글이 없습니다. pno=" + pno));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public Page<PostsListResponseDto> findAllDesc(int page, int size, Long bid) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "pno"));
        return postsRepository.findAllDesc(pageRequest, bid).map(PostsListResponseDto::new);
        // return postsRepository.findAllDesc().stream()
        //         .map(PostsListResponseDto::new)
        //         .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
	public String getUserNickname(Long pno) {
		return postsRepository.getUserNickname(pno);
    }
    
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findByTitleContaining(String title) {
        return postsRepository.findByTitleContaining(title).stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
	public List<String> getPostTag(Long pno){
        List<String> tagList = new LinkedList<>();
        String tag = postsRepository.getPostTag(pno);
        String[] tagArr = tag.split(":");

        for(int i=0; i<tagArr.length; i++)
            tagList.add(tagArr[i]);

        return tagList;
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findByTagContaining(String tagName) {
        return postsRepository.findByTagContaining(tagName).stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

}
