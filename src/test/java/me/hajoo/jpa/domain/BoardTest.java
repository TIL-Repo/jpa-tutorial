package me.hajoo.jpa.domain;

import me.hajoo.jpa.repository.BoardRepository;
import me.hajoo.jpa.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@Rollback(value = false)
class BoardTest {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void mappedby() throws Exception {
        //given
        Board board = new Board();
        board.setContent("게시글 내용");
        boardRepository.save(board);

        Comment comment = new Comment();
        // comment.setBoard(board); 방법2
        comment.setContent("덧글 내용");

        board.addComment(comment);

        commentRepository.save(comment);
        //then
        boardRepository.findAll();

    }

}