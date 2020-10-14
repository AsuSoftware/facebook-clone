package com.asusoftware.facebookClone.repositories;

import com.asusoftware.facebookClone.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<User, Long> {
}
