package com.example.demo.domain.repository;

import com.example.demo.domain.entity.Book;
import com.example.demo.domain.entity.Lend;
import com.example.demo.domain.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class LendRepositoryTest {
    @Autowired
    private LendRepository lendRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void t1() throws Exception {

        // 저장되어 있는 도서코드를 가지는 Book
        Book book = bookRepository.findById(1111L).get();
        // 저장되어 있는 유저정보를 가지는 User
        User user = userRepository.findById("user1").get();

        Lend lend = new Lend();
        lend.setBook(book);
        lend.setUser(user);
        lendRepository.save(lend);
    }

    @Test
    public void t2() throws Exception {
        Lend lend = lendRepository.findById(1L).get();
        Book book = bookRepository.findById(4444L).get();
        lend.setBook(book);
        lendRepository.save(lend);

    }

    @Test
    public void t3() {
        lendRepository.deleteById(1L);
    }

    @Test
    public void t4() {
        List<Lend> list = lendRepository.findLendsByUser("user1");
        list.stream().forEach(System.out::println);
    }

    @Test
    public void t5() {
        System.out.println("----------FETCH 방식 테스트 시작----------");
        Optional<Lend> lendOptional = lendRepository.findById(2L);
        System.out.println("----------findById(2L)------------");
        Lend lend = lendOptional.get();
        System.out.println("------------getUser()------------");
        User user = lend.getUser(); // LAZY 사용 시 해당 시점에서 쿼리 발생
        System.out.println("----------FETCH 방식 테스트 종료----------");
        
    }
}