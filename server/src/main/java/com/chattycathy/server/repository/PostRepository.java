package com.chattycathy.server.repository;

import com.chattycathy.server.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Access to Post table in the database. Avoid direct access in business logic.
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
