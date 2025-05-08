package com.example.demo.domain.mapper;

import com.example.demo.domain.dto.MemoDto;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
@MapperScan
class MemoMapperTest {

    @Autowired
    private MemoMapper memoMapper;

    @Test
    public void t1() {
        MemoDto memoDto = new MemoDto(444, "a", "a", LocalDateTime.now());
        memoMapper.insert(memoDto);
        System.out.println(memoDto);
    }

    @Test
    public void t2() {
        MemoDto memoDto = new MemoDto(555, "b", "b", LocalDateTime.now());
        memoMapper.insertXml(memoDto); //xml에서 id와 text만 받으므로 writer와 date는 못받는다.
        System.out.println(memoDto);
    }
}