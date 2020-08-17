package com.web.blog.controller.image;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import com.google.cloud.storage.BlobInfo;
import com.web.blog.service.GCSService;

@CrossOrigin(origins = { "http://localhost:3000" })
@RequiredArgsConstructor
@RestController
public class ImageController {

    private final GCSService gcsService;

    @PostMapping("/api/image")
    @ApiOperation(value = "이미지 업로드 하기")
    public Long save(MultipartHttpServletRequest request, HttpServletResponse response)
            throws IllegalStateException, IOException {

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        Iterator<String> itr = multipartHttpServletRequest.getFileNames();
        MultipartFile mfile = null;
        while(itr.hasNext()) {
            String filename = itr.next();
            System.out.println(filename);
            mfile = multipartHttpServletRequest.getFile(filename);
        };

        try {
            BlobInfo fileFromGCS = gcsService.uploadFileToGCS(mfile);
            System.out.println("출력------------------");
            System.out.println(fileFromGCS.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (long)0;
    }
}