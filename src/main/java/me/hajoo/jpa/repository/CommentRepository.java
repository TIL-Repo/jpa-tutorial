package me.hajoo.jpa.repository;


import me.hajoo.jpa.domain.Comment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.NamedEntityGraph;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // @Query("select c from Comment c join fetch c.board")
    // @EntityGraph(attributePaths = {"board"}, type = EntityGraph.EntityGraphType.LOAD)
    List<Comment> findAll();
}
