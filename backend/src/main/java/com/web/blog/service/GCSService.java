package com.web.blog.service;

import java.io.*;
import java.util.*;

import com.google.cloud.storage.Acl;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.web.blog.model.upload.UploadReqDto;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GCSService {

    private final Storage storage;

    @SuppressWarnings("deprecation")
    public BlobInfo uploadFileToGCS(MultipartFile file) throws IOException {

        BlobInfo blobInfo =storage.create(
                BlobInfo.newBuilder("getblog", file.getOriginalFilename())
                        .setAcl(new ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllAuthenticatedUsers(), Acl.Role.READER))))
                        .build(),
                        
                file.getInputStream());

        return blobInfo;
    }
    
}