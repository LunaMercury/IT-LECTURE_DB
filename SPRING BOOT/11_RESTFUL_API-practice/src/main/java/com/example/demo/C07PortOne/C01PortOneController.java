package com.example.demo.C07PortOne;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@Slf4j
@RequestMapping("/portOne")
public class C01PortOneController {

    String PORTONE_RESTAPI_KEY;
    String PORTONE_RESTAPI_SECRET;
    PortOneTokenResponse portOneTokenResponse;


    public C01PortOneController(Dotenv dotenv) {
        this.PORTONE_RESTAPI_KEY = dotenv.get("REACT_APP_PORTONE_RESTAPI_KEY");
        this.PORTONE_RESTAPI_SECRET = dotenv.get("REACT_APP_PORTONE_RESTAPI_SECRET");
        System.out.println("REST 키 : " + PORTONE_RESTAPI_KEY);
        System.out.println("REST 시크릿 키 : " + PORTONE_RESTAPI_SECRET);
    }


    @GetMapping("/index")
    public void login() {
        log.info("GET /portOne/index...");
    }

    // 토큰받기
    @GetMapping("/getToken")
    public void getToken() {
        log.info("GET /portOne/getToken...");
        // 요청 정보 확인
        String url = "https://api.iamport.kr/users/getToken";

        //요청 헤더 설정
        HttpHeaders header = new HttpHeaders();
//        header.add("Content-Type", "application/json");

        // 요청 바디 설정
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("imp_key",PORTONE_RESTAPI_KEY);
        params.add("imp_secret",PORTONE_RESTAPI_SECRET);

        HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<>(params,header);

        // 요청 후 응답확인
        RestTemplate rt = new RestTemplate();
        ResponseEntity<PortOneTokenResponse> response = rt.exchange(url, HttpMethod.POST, entity, PortOneTokenResponse.class);
        System.out.println("getToken 의 리스폰스 : " + response);
        this.portOneTokenResponse = response.getBody();
        System.out.println("포트1 토큰 리스폰스 : " + portOneTokenResponse);
    }

    // 결제 조회 (다건조회)
    @GetMapping("/getPayments")
    @ResponseBody
    public void payment() {
        log.info("GET /portOne/getPayments...");

        // 요청 정보 확인
        String url = "https://api.iamport.kr/payments?imp_uid[]=imp값 넣기&merchant_uid[]=[]";
//        String url = "https://api.iamport.kr/payments";

        //요청 헤더 설정
        HttpHeaders header = new HttpHeaders();
        header.add("Authorization", "Bearer " + this.portOneTokenResponse.getResponse().getAccess_token());
        header.add("Content-Type", "application/json");


        HttpEntity<String> entity = new HttpEntity<>(header);

        // 요청 후 응답확인
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("payment 의 리스폰스 : " + response);
    }

    // 결제 취소
    @GetMapping("/payment/cancel")
    @ResponseBody
    public void payment_cancel() {
        log.info("GET /portOne/payment/cancel...");

        // 요청 정보 확인
        String url = "https://api.iamport.kr/payments/cancel";

        //요청 헤더 설정
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type","application/json");
        header.add("Authorization","Bearer " + this.portOneTokenResponse.getResponse().getAccess_token());

        // 요청 바디 설정
        JSONObject params = new JSONObject();
        params.put("imp_uid","imp_값넣기");
        params.put("merchant_uid","merchant_값넣기");
        System.out.println("결제취소 파라미터 목록" + params);

        HttpEntity<String> entity = new HttpEntity<>(params.toString(),header);

        // 요청 후 응답확인
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(url, HttpMethod.POST, entity, String.class);
        System.out.println("payment 의 리스폰스바디 : " + response.getBody());
    }

    // 인증 조회
    @GetMapping("/certifications/{imp_uid}")
    @ResponseBody
    public void certifications(@PathVariable("imp_uid")String imp_uid) {
        log.info("GET /portOne/certifications...");

        // 요청 정보 확인
        String url = "https://api.iamport.kr/certifications/" + imp_uid;

        //요청 헤더 설정
        HttpHeaders header = new HttpHeaders();
        header.add("Authorization", "Bearer " + this.portOneTokenResponse.getResponse().getAccess_token());
        header.add("Content-Type", "application/json");

         HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<>(header);

        // 요청 후 응답확인
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("certifications 의 리스폰스 : " + response);
    }


    @Data
    public static class TokenData {
        public String access_token;
        public int now;
        public int expired_at;
    }

    @Data
    private static class PortOneTokenResponse{
        public int code;
        public Object message;
        public TokenData response; // Response에서 TokenData로 바꾼다
    }
}
