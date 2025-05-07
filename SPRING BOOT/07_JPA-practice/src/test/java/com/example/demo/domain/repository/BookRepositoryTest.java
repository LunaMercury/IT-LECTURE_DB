package com.example.demo.domain.repository;

import com.example.demo.domain.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    public void t1() throws Exception{
        // CREATE
        Book book = Book.builder()
                        .bookCode(1111L)
                        .bookName("리눅스")
                        .publisher("한빛미디어")
                        .isbn("1111-1111")
                        .build();

//        bookRepository.save(book);

        // UPDATE
        book.setBookName("Java의 정석");
        book.setPublisher("이지퍼블리싱");
        book.setIsbn("2222-2222");
        bookRepository.save(book);

        // DELETE
        bookRepository.deleteById(1111L);
    }

// 함수명명규칙 테스트
    @Test
    public void t2() {
//        Optional<Book> bookOptional = bookRepository.findById(1111L);
//        if (bookOptional.isPresent())
//            System.out.println(bookOptional.get());

//        List<Book> list = bookRepository.findByBookName("이것이리눅스다");
//        List<Book> list = bookRepository.findByPublisher("한빛미디어");
//        list.stream().forEach(System.out::println);
//        Book book = bookRepository.findByBookNameAndIsbn("이것이리눅스다","4444");
//        System.out.println(book);

//        List<Book> list = bookRepository.findByBookNameContains("리눅스");
//        list.stream().forEach(System.out::println);

//        int count = bookRepository.countByBookName("이것이리눅스다");
//        int count = bookRepository.countByBookNameContains("C");
//        System.out.println(count);

//        bookRepository.deleteByBookName("");
    }
}