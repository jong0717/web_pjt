package com.web.blog.model.heart;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.web.blog.model.posts.Posts;
import com.web.blog.model.user.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "post_hearts")
public class Heart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hid;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonIgnoreProperties({"img", "create_date"})
    private Posts post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({ "password", "email_verified", "image_url", "provider", "introduce" })
    private User user;

    @Builder
    public Heart(Posts post, User user) {
        this.post = post;
        this.user = user;
    }
}
