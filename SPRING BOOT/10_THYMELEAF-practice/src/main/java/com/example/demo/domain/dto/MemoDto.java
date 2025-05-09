package com.example.demo.domain.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemoDto {

	public MemoDto(int id, String text) {
		this.id=id;
		this.text=text;
	}

	@Min(value = 10, message = "ID의 최소값은 10이상이어야 합니다")
	@Max(value = 65535, message = "ID의 최대숫자는 65535 이하여야 함니다")
	@NotNull(message = "ID는 필수항목입니다")
	private Integer id;
	@NotBlank(message = "메모를 입력하세요")
	private String text;
	@NotBlank(message = "이메일을 입력하세요")
	@Email(message = "example@example.com 에 맞게 입력해주세요")
	private String writer;
	@NotNull(message = "날짜 정보를 선택해주세요")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") // T는 오전,오후
	private LocalDateTime createAt;
//	@NotNull(message = "날짜를 입력하세요")
//	private LocalDate dateTest;
}
