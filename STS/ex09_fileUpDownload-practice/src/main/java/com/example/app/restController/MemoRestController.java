package com.example.app.restController;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.domain.dto.MemoDto;
import com.example.app.domain.service.MemoServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/rest/memo")
public class MemoRestController {
	@Autowired
	private MemoServiceImpl memoService;

	// 메모확인전체
	@GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<MemoDto> getAll() {
		log.info("GET /memo/getAll");
		return memoService.getAllMemo();
	}

	// 메모확인(단건)
	@GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	// ResponseEntity는 MemoDto 뿐만 아니라 Http 응답상태, 상태코드, 부가 정보 등을 전달할 수 있다.
	// 즉 오류 이유를 좀 더 명확히 알 수 있다(에러가 발생하면 잘못된 요청인지, 요청은 맞는데 데이터가 존재하지 않는지 등)
	// @PathVariable 로 인해 id가 {id} 로 들어가게 된다.
	public ResponseEntity<MemoDto> get(@PathVariable int id) {
		log.info("GET /memo/get... " + id);
		MemoDto dto = memoService.getMemo(id);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	// 메모쓰기 - get
	// @RequestBody 있으면 안된다.
	@GetMapping("/add")
	public void add_get(MemoDto dto) throws SQLException {
		log.info("POST /rest/memo/post.." + dto);
		memoService.registrationMemo(dto);
	}

	// 메모쓰기 - post
	@PostMapping("/post") // http://localhost:8090/app/rest/memo/post
	// @RequestBody로 인해 Json이나 xml 등의 파일을 받아도 자동으로 MemoDto로 변환해서 저장한다.
	public void add(@RequestBody MemoDto dto) throws SQLException {
		log.info("POST /rest/memo/post.." + dto);
		memoService.registrationMemo(dto);
	}

	// 메모수정
	@PutMapping("/put/{id}/{text}")
	public void put(MemoDto dto) {
		log.info("PUT /memo/put.." + dto);
		memoService.modifyMemo(dto);
	}

	@PutMapping("/put2") // http://localhost:8090/app/memo/put2
	public void put2(@RequestBody MemoDto dto) {
		log.info("PUT /memo/put2.." + dto);
		memoService.modifyMemo(dto);
	}

	@PatchMapping("/patch/{id}/{text}")
	public void patch(MemoDto dto) {
		log.info("PATCH /memo/patch.." + dto);
		memoService.modifyMemo(dto);
	}

	// 메모삭제
	@DeleteMapping("/remove/{id}") // http://localhost:8090/app/memo/remove/{id}
	public void remove(@PathVariable int id) {
		log.info("DELETE /memo/remove.." + id);
		memoService.removeMemo(id);
	}
}
