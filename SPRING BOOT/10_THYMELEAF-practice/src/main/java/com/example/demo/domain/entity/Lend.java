package com.example.demo.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lend")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(fetch = FetchType.EAGER) // 조회 할 때마다 데이터를 다 가져오는 방식. 기본값
    @ManyToOne(fetch = FetchType.LAZY) // 필요할때만 데이터를 가져오는 방식. 이게 좀 더 효율적
    @JoinColumn(name = "bookcode"
            , foreignKey = @ForeignKey(name = "FK_LEND_BOOK"
                , foreignKeyDefinition = "FOREIGN KEY(bookcode) REFERENCES book(bookcode) ON DELETE CASCADE ON UPDATE CASCADE"))
    private Book book;

//    @ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username"
            , foreignKey = @ForeignKey(name = "FK_LEND_USER"
            , foreignKeyDefinition = "FOREIGN KEY(username) REFERENCES user(username) ON DELETE CASCADE ON UPDATE CASCADE"))
    private User user;
}
