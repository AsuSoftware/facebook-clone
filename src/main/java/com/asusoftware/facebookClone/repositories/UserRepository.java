package com.asusoftware.facebookClone.repositories;

import com.asusoftware.facebookClone.models.Post;
import com.asusoftware.facebookClone.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT p FROM User p WHERE p.email = :email AND p.password = :password")
    Optional<User> findByEmailAndPassword(@Param(value = "email") String email, @Param(value = "password") String password);

    // return user with current ad id
    @Query("SELECT p FROM Ad p WHERE p.id = :id")
    Optional<Post> findPostById(@Param(value = "id") Long id);

    // return all ads
    @Query("SELECT p.ads FROM User p")
    Optional<Post> getAllPosts();
}
