package com.web.blog.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.web.blog.dao.heart.HeartDao;
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
    private final String errorMessage = "해당 글이 없습니다. pno=";
    private final PostsRepository postsRepository;
    private final HeartDao heartDao;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getPno();
    }

    @Transactional
    public Long update(Long pno, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findByPno(pno)
                .orElseThrow(() -> new IllegalArgumentException(errorMessage + pno));

        posts.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getImg(), requestDto.getTag(), requestDto.getCreateDate());

        return pno;
    }

    @Transactional
    public void delete (Long pno) {
        Posts posts = postsRepository.findByPno(pno)
                .orElseThrow(() -> new IllegalArgumentException(errorMessage + pno));

        postsRepository.delete(posts);
    }

    @Transactional(readOnly = true)
    public PostsResponseDto findByPno(Long pno, Long currentUid) {
        Posts entity = postsRepository.findByPno(pno)
                .orElseThrow(() -> new IllegalArgumentException(errorMessage + pno));

        return new PostsResponseDto(entity, heartDao.existsByPostPnoAndUserUid(entity.getPno(), currentUid));
    }

    @Transactional(readOnly = true)
    public Page<PostsListResponseDto> findAllDesc(int page, int size, Long bid, Long currentUid) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "pno"));
        return postsRepository.findAllDesc(pageRequest, bid)
                .map(post ->
                    new PostsListResponseDto(post, heartDao.existsByPostPnoAndUserUid(post.getPno(), currentUid))
                );
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
        String tag = postsRepository.getPostTag(pno);
        String[] tagArr = tag.split(":");

        return Arrays.asList(tagArr);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findByTagContaining(String tagName) {
        return postsRepository.findByTagContaining(tagName).stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteAllByUsersUid(Long uid) {
        int deleteRows = 1;
        while (deleteRows > 0) {
            deleteRows = postsRepository.deleteAllByUsersUidFirstN(uid, 50);
        }
    }
}
