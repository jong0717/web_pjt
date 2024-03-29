package com.web.blog.controller.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.google.cloud.storage.BlobInfo;
import com.web.blog.model.posts.PostsListResponseDto;
import com.web.blog.model.posts.PostsResponseDto;
import com.web.blog.model.posts.PostsSaveRequestDto;
import com.web.blog.model.posts.PostsUpdateRequestDto;
import com.web.blog.model.upload.UploadReqDto;
import com.web.blog.service.GCSService;
import com.web.blog.service.PostsService;

@CrossOrigin(origins = { "http://localhost:3000" })
@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;
    private final GCSService gcsService;

    @PostMapping("/api/post/insert")
    @ApiOperation(value = "게시글 등록하기")
    public Long save(@RequestParam("files") MultipartFile files, @RequestParam("bid") Long bid, @RequestParam("uid") Long uid,
            @RequestParam("title") String title, @RequestParam("content") String content,
            @RequestParam("heart") Long heart, @RequestParam("tag") String tag) {

        PostsSaveRequestDto requestDto;
        try {
            BlobInfo fileFromGCS = gcsService.uploadFileToGCS(files);
        } catch (IOException e) {
            e.printStackTrace();
        }
        requestDto = new PostsSaveRequestDto(null, uid, bid, title, content, heart, files.getOriginalFilename(), tag, null);
        
        return postsService.save(requestDto);
    }


    @PutMapping("api/post/modify/{pno}")
    @ApiOperation(value = "게시글 수정하기")
    public Long update(@PathVariable Long pno, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(pno, requestDto);
    }

    @DeleteMapping("api/post/{pno}")
    @ApiOperation(value = "게시글 삭제하기")
    public Long delete(@PathVariable Long pno) {
        postsService.delete(pno);
        return pno;
    }

    @GetMapping("api/post/{pno}")
    @ApiOperation(value = "게시글 가져오기")
    public PostsResponseDto findByPno(@PathVariable Long pno){
        return postsService.findByPno(pno);
    }   

    @GetMapping("/api/post/list/{bid}")
    @ApiOperation(value = "전체 게시글 불러오기")
    public Page<PostsListResponseDto> findAll(@RequestParam(required = true) final int page,
                                                @RequestParam(required = true) final int size,
                                                @PathVariable Long bid) {
        return postsService.findAllDesc(page, size, bid);
    }

    @GetMapping("api/post/detail/{pno}")
    @ApiOperation(value = "닉네임 가져오기")
    public String getUserNickname(@PathVariable Long pno){
        return postsService.getUserNickname(pno);
    }

    @GetMapping("/api/post/search/{title}")
    @ApiOperation(value = "검색 게시글 불러오기")
    public List<PostsListResponseDto> findByTitleContaining(@PathVariable String title) {
        return postsService.findByTitleContaining(title);
    }

    @GetMapping("api/post/tag/{pno}")
    @ApiOperation(value = "게시글에 해당하는 태그 가져오기")
    public List<String> getPostTag(@PathVariable Long pno){
        return postsService.getPostTag(pno);
    }

    @GetMapping("api/tag/search/{tagName}")
    @ApiOperation(value = "태그 포함하는 게시글 불러오기")
    public List<PostsListResponseDto> findByTagContaining(@PathVariable String tagName){
        return postsService.findByTagContaining(tagName);
    }

}
