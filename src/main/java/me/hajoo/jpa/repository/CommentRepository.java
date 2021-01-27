package me.hajoo.jpa.repository;


import me.hajoo.jpa.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
