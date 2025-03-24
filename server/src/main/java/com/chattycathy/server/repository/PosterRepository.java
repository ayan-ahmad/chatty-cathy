package com.chattycathy.server.repository;

import com.chattycathy.server.model.Poster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Access to Poster table in the database. Avoid direct access in business logic.
 */
@Repository
public interface PosterRepository extends JpaRepository<Poster, Long> {}
