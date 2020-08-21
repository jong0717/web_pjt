package com.web.blog.service;

import java.util.List;
import java.util.stream.Collectors;

import com.web.blog.dao.blog.BlogRepository;
import com.web.blog.dao.guestbook.GuestbookDao;
import com.web.blog.dao.user.UserDao;
import com.web.blog.model.blog.Blog;
import com.web.blog.model.guestbook.Guestbook;
import com.web.blog.model.guestbook.GuestbookRequest;
import com.web.blog.model.guestbook.GuestbookResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GuestbookService {

    @Autowired
    UserDao userDao;

    @Autowired
    BlogRepository blogDao;

    @Autowired
    GuestbookDao guestbookDao;

    @Transactional(readOnly = true)
    public List<GuestbookResponse> findByBlogBid(Long blogId) {
        return guestbookDao.findAllByBlogBid(blogId).stream()
                            .map(guestbook ->
                                GuestbookResponse.builder()
                                    .gid(guestbook.getGid())
                                    .content(guestbook.getContent())
                                    .createDate(guestbook.getCreateDate())
                                    .writerNickname(userDao.findNicknameByUid(guestbook.getWriterUid())
                                                            .orElse("탈퇴한 회원"))
                                    .build()
                            )
                            .collect(Collectors.toList());
    }

    public Long save(GuestbookRequest request) {
        Blog host = blogDao.findById(request.getBlogId())
                        .orElseThrow(() -> new IllegalArgumentException("없는 블로그입니다."));
        return guestbookDao.save(new Guestbook(host, request)).getGid();
    }

    @Modifying
    @Transactional
    public void delete(Long gid) {
        guestbookDao.deleteByGid(gid);
    }

    @Modifying
    @Transactional
    public void update(Long gid, GuestbookRequest request) {
        guestbookDao.save(new Guestbook(gid, null, request));
    }

    @Transactional
    public void deleteAll(Long bid) {
        int deleteRows = 1;
        while (deleteRows > 0) {
            deleteRows = guestbookDao.deleteAllByBlogBidFirstN(bid, 50);
        }
    }
}
