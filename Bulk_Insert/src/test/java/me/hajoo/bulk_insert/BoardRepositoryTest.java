package me.hajoo.bulk_insert;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    static List<Board> boards;

    @BeforeAll
    static void setup() {
        boards = new ArrayList<>();
        for (int i = 0; i < 60000; i++) {
            boards.add(new Board("title", "content"));
        }
    }

    @Test
    @DisplayName("6만 건 단일 save")
    public void add(){
        boards.forEach(board -> boardRepository.save(board));
    }

    @Test
    @DisplayName("6만 건 saveAll")
    public void addAll(){
        boardRepository.saveAll(boards);
    }

}