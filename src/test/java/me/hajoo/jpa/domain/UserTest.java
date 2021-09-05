package me.hajoo.jpa.domain;

import me.hajoo.jpa.repository.UserRepository;
import org.junit.jupiter.api.RepeatedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
public class UserTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EntityManager entityManager;

    @RepeatedTest(5)
    public void 유저생성() throws Exception {
        //given
        long start = System.currentTimeMillis();
        //when
        for (int i = 0; i < 50000; i++) {
            userRepository.save(new User("columnA"));
        }
        //then
        System.out.println((System.currentTimeMillis() - start) / 1000 + "s");
    }

    @RepeatedTest(5)
    public void 유저수정() throws Exception {
        //given
        User user = userRepository.save(new User("columnA"));
        long start = System.currentTimeMillis();
        //when
        for (int i = 0; i < 50000; i++) {
            user.changeColumnA("columnA" + i);
            entityManager.flush();
        }
        //then
        System.out.println((System.currentTimeMillis() - start) / 1000 + "s");
    }


}
