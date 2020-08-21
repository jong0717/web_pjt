package com.web.blog.model.guestbook;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.web.blog.model.blog.Blog;

import org.springframework.data.annotation.CreatedDate;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "guestbooks")
public class Guestbook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gid;

    @ManyToOne
    @JoinColumn(name = "blog_id", updatable = false)
    private Blog blog;

    @Column(updatable = false)
    private Long writerUid;

    private String content;

    // @Column(insertable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createDate;

    public Guestbook(Blog blog, GuestbookRequest request) {
        this.blog = blog;
        this.writerUid = request.getWriterUid();
        this.content = request.getContent();
        this.createDate = request.getCreateTime();
    }

    public Guestbook(Long gid, Blog blog, GuestbookRequest request) {
        this.gid = gid;
        this.blog = blog;
        this.writerUid = request.getWriterUid();
        this.content = request.getContent();
        this.createDate = request.getCreateTime();
    }
}
