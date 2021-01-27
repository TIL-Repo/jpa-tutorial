package me.hajoo.jpa.domain;

import me.hajoo.jpa.repository.BoardRepository;
import me.hajoo.jpa.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class BoardTest{

        @Autowired
        private BoardRepository boardRepository;
        @Autowired
        private CommentRepository commentRepository;

        /**
         * 연관 관계 주인이 아닌 대상에 데이터를 삽입하려고 해도 소용이 없다.
         * 연관 관계 주인에 설정해주어야 하며 컬렉션에 넣는 이유는 ORM을 쓰는 이유이다
         * @throws Exception
         */
        @Test
        public void 연관관계_주인() throws Exception {
            //given
            Board board = new Board();
            board.setContent("게시글 내용");
            boardRepository.save(board);

            Comment comment = new Comment();
            // comment.setBoard(board); 방법2
            comment.setContent("덧글 내용");
            //when
            board.addComment(comment);
            commentRepository.save(comment);
        }

        /**
         * 다대일, 전체를 읽으려고 할 경우 연관관계에 있는 것까지 가져와 N+1 문제가 발생
         * 해결방법, 글로벌패치전략을 LAZY로 변경한다, 페치조인을 이용한다.
         * 일대다, 양방향 일 경우 연관관계에 있는 컬렉션 내의 내용을 읽으려고 할때 기본 글로벌페치전략이 LAZY라 에러 발생
         * 해결방법, 페치조인 이용한다.
         * @throws Exception
         */
        @Test
        public void 연관관계_findAll() throws Exception {
            //given
            Board board = new Board();
            board.setContent("게시글 내용");
            boardRepository.save(board);

            Board board2 = new Board();
            board2.setContent("게시글 내용2");
            boardRepository.save(board2);

            Board board3 = new Board();
            board3.setContent("게시글 내용3");
            boardRepository.save(board3);

            Comment comment = new Comment();
            comment.setContent("덧글 내용");

            Comment comment2 = new Comment();
            comment2.setContent("덧글 내용2");

            Comment comment3 = new Comment();
            comment3.setContent("덧글 내용3");

            board.addComment(comment);
            board2.addComment(comment2);
            board3.addComment(comment3);

            commentRepository.save(comment);
            commentRepository.save(comment2);
            commentRepository.save(comment3);
            //when
            System.out.println("############################");
            // List<Board> boards = boardRepository.findAll();
            // boards.get(0).getComments().get(0).getContent();
            // oneToMany는 Default가 LAZY라 찾을수 없다 해결법은 EAGER로 바꾸거나 영속성 컨텍스트에 존재해야함
            System.out.println("############################");
            List<Comment> comments = commentRepository.findAll();
            for (Comment c: comments
                 ) {
                c.getBoard().getContent(); // Entity에서 글로벌페치전략을 LAZY로 하면 마찬가지로 lazyinitialization 에러
            }
            //then
        }

}