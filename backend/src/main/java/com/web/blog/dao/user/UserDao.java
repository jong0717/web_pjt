package com.web.blog.dao.user;

import java.util.Optional;

import com.web.blog.model.user.User;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    @Transactional(readOnly = true)
    User getUserByEmail(String email);

    @Transactional(readOnly = true)
    User getUserByUid(Long uid);

    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);

    // @Query("DELETE FROM user WHERE user.email = :email")
    void deleteUserByEmail(String email);

    @OnDelete(action = OnDeleteAction.CASCADE)
    // @Query("DELETE FROM user WHERE user.uid = :uid")
    void deleteById(Long uid);
}
