package com.web.blog.model.blog;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.web.blog.model.guestbook.Guestbook;
import com.web.blog.model.user.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bid;

    private Long uid;
    
    @Column(length = 100, nullable = false)
    private String blogname;

    private Long template_num;

    private Long visitors_num;

    @ManyToOne
    @JoinColumn(name="uid", insertable = false ,updatable = false)
    private User user;

    @OneToMany(mappedBy = "blog")
    private Set<Guestbook> guestbooks;

    @Builder
    public Blog(Long bid, Long uid, String blogname, Long template_num, Long visitors_num){
        this.bid = bid;
        this.uid = uid;
        this.blogname = blogname;
        this.template_num = template_num;
        this.visitors_num = visitors_num;
    }

    public void update(String blogname) {
        this.blogname = blogname;
    }

    @Override
    public String toString() {
        return "[ bid: " + Long.toString(bid) + ", blogname: " + blogname + ", guestbooks size: " + guestbooks.size() + "]";
    }
}
