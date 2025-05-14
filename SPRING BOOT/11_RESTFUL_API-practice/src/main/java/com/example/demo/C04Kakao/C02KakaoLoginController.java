package com.example.demo.C04Kakao;


import io.github.cdimascio.dotenv.Dotenv;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/kakao")
public class C02KakaoLoginController {

    String REDIRECT_URI = "http://192.168.16.22:8090/kakao/callback";
//    String REDIRECT_URI = "http://localhost:8090/kakao/callback";
    String CLIENT_ID = System.getenv("KAKAO_RESTAPI_KEY");;
    String RESPONSE_TYPE = "code";
    String LOGOUT_REDIRECT_URI = "http://192.168.16.22:8090/kakao/getCode";
//    String LOGOUT_REDIRECT_URI = "http://localhost:8090/kakao/getCode";

    public C02KakaoLoginController(Dotenv dotenv) {
        this.CLIENT_ID = dotenv.get("REACT_APP_KAKAO_RESTAPI_KEY");
    }


    // 인가 코드 받기
    @GetMapping("/getCode")
    public String getCode() {
        log.info("GET /kakao/getCode...");
        return "redirect:https://kauth.kakao.com/oauth/authorize"
                + "?client_id=" + CLIENT_ID
                + "&redirect_uri=" + REDIRECT_URI
                + "&response_type=" + RESPONSE_TYPE;
        // 이 이후 자동으로 callback으로 redirect 된다.
    }

    KakaoTokenResponse kakaoTokenResponse;
    KakaoProfileResponse kakaoProfileResponse;
    KakaoFriendsResponse kakaoFriendsResponse;


    // 토큰 받기
    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code) {
        log.info("GET /kakao/callback..." + code);
        // 요청 정보 확인
        String url = "https://kauth.kakao.com/oauth/token";

        //요청 헤더 설정
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        // 요청 바디 설정
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("grant_type","authorization_code");
        params.add("client_id",CLIENT_ID);
        params.add("redirect_uri",REDIRECT_URI);
        params.add("code",code);

        HttpEntity< MultiValueMap<String,String> > entity = new HttpEntity<>(params,header);

        // 요청 후 응답확인
        RestTemplate rt = new RestTemplate();
        ResponseEntity<KakaoTokenResponse> response = rt.exchange(url, HttpMethod.POST, entity, KakaoTokenResponse.class);
        System.out.println(response);
        this.kakaoTokenResponse = response.getBody();

        return "redirect:/kakao/main";
        // 가공 처리
    }

    // 사용자 정보 받기
    @GetMapping("/main")
    public void main(Model model) {
        log.info("GET /kakao/main..");

        // 정보 확인
        String url = "https://kapi.kakao.com/v2/user/me";

        // 요청 헤더
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        header.add("Authorization", "Bearer " + this.kakaoTokenResponse.getAccess_token());

        // 요청 바디

        // entity
        HttpEntity<String> entity = new HttpEntity<>(header);

        // 요청 -> 응답
        RestTemplate rt = new RestTemplate();
        ResponseEntity<KakaoProfileResponse> response = rt.exchange(url, HttpMethod.POST, entity, KakaoProfileResponse.class);
        System.out.println(response.getBody());
        this.kakaoProfileResponse = response.getBody();
        model.addAttribute("profile", this.kakaoProfileResponse);
    }

    // 로그아웃
    @GetMapping("/logout")
    @ResponseBody
    public void logout() {
        log.info("GET /kakao/logout...");
        // 정보
        String url = "https://kapi.kakao.com/v1/user/logout";

        // 요청헤더
        HttpHeaders header = new HttpHeaders();
        header.add("Authorization","Bearer "+this.kakaoTokenResponse.getAccess_token());

        // 요청바디

        // ENTITY
        HttpEntity entity = new HttpEntity<>(header);

        // 요청->응답
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response =rt.exchange(url,HttpMethod.POST,entity,String.class);
    }

    // 로그아웃(언링크, 리프레시 토큰도 만료처리)
    @GetMapping("/unlink")
    @ResponseBody
    public void unlink() {
        log.info("GET /kakao/unlink...");
        // 정보
        String url = "https://kapi.kakao.com/v1/user/unlink";

        // 요청헤더
        HttpHeaders header = new HttpHeaders();
        header.add("Authorization","Bearer "+this.kakaoTokenResponse.getAccess_token());

        // 요청 바디

        // ENTITY
        HttpEntity entity = new HttpEntity<>(header);

        // 요청 -> 응답
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response =rt.exchange(url,HttpMethod.POST,entity,String.class);
    }

    // 전체 로그아웃(다른 사이트에 로그인 되어있는 것도 죄다 로그아웃 된다.)
    @GetMapping("/logoutAll")
    public String logoutAll() {
        log.info("GET /kakao/logoutAll...");
        return "redirect:https://kauth.kakao.com/oauth/logout"
                + "?client_id=" + CLIENT_ID
                + "&logout_redirect_uri=" + LOGOUT_REDIRECT_URI;
    }

    @GetMapping("/getCodeMsg")
    public String getCodeMsg() {
        log.info("GET /kakao/getCodeMsg...");
        return "redirect:https://kauth.kakao.com/oauth/authorize"
                + "?client_id=" + CLIENT_ID
                + "&redirect_uri=" + REDIRECT_URI
                + "&response_type=" + RESPONSE_TYPE
                + "&scope=" + "talk_message";
    }

    // 카카오톡 메세지 (나에게 보내기)
    @GetMapping("/message/me/{message}")
    public void message_me(@PathVariable("message") String message) {
        // 정보
        String url = "https://kapi.kakao.com/v2/api/talk/memo/default/send";

        // 요청 헤더
        HttpHeaders header = new HttpHeaders();
        header.add("Authorization","Bearer " + this.kakaoTokenResponse.getAccess_token());
        header.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        // 요청 바디
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        JSONObject template_object = new JSONObject();
        template_object.put("object_type","text");
        template_object.put("text",message);
        template_object.put("link",new JSONObject());
        template_object.put("button_title","");

        params.add("template_object",template_object.toString());
        // 요청 엔티티
        HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity(params,header);

        // 요청 -> 응답
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(url,HttpMethod.POST, entity, String.class);
        System.out.println(response);
    }

    // 카카오 서비스 내 친구목록 동의 관련
    @GetMapping("/getCodeFriends")
    public String getCodeFriends() {
        log.info("GET /kakao/getCodeFriends...");
        return "redirect:https://kauth.kakao.com/oauth/authorize"
                + "?client_id=" + CLIENT_ID
                + "&redirect_uri=" + REDIRECT_URI
                + "&response_type=" + RESPONSE_TYPE
                + "&scope=" + "friends,talk_message";
    }

    // 친구 목록 가져오기
    @GetMapping("/friends")
    public void getFriends() {
        // 정보
        String url = "https://kapi.kakao.com/v1/api/talk/friends";

        // 헤더
        HttpHeaders header = new HttpHeaders();
        header.add("Authorization","Bearer " + this.kakaoTokenResponse.getAccess_token());

        // 본문
        HttpEntity entity = new HttpEntity(header);

        // 요청 -> 응답
        RestTemplate rt = new RestTemplate();
        ResponseEntity<KakaoFriendsResponse> response = rt.exchange(url,HttpMethod.GET, entity, KakaoFriendsResponse.class);
        System.out.println(response.getBody());

        this.kakaoFriendsResponse = response.getBody();
    }

    // 친구에게 메세지 보내기
    @GetMapping("/message/friends/{message}")
    @ResponseBody
    public void message_friends(@PathVariable("message") String message) {
        log.info("GET /kakao/message/friends..." + message);
        // 정보
        String url = "https://kapi.kakao.com/v1/api/talk/friends/message/default/send";

        // 요청 헤더
        HttpHeaders header = new HttpHeaders();
        header.add("Authorization","Bearer " + this.kakaoTokenResponse.getAccess_token());
        header.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        // 요청 바디
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
            // Receiver
        JSONArray uuids = new JSONArray();
        List<Element> list = kakaoFriendsResponse.getElements();
        for (int i = 0; i<list.size(); i++) {
            uuids.add(list.get(i).getUuid());
        }
            // Template
        JSONObject template_object = new JSONObject();
        template_object.put("object_type","text");
        template_object.put("text",message);
        template_object.put("link",new JSONObject());
        template_object.put("button_title","");

        params.add("template_object",template_object.toString());
        params.add("receiver_uuids", uuids.toString());

        // 요청 엔티티
        HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity(params,header);

        // 요청 -> 응답
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(url,HttpMethod.POST, entity, String.class);
        System.out.println(response);
    }

    // -----------------------
    // 카카오 토큰
    // -----------------------
    @Data
    private static class KakaoTokenResponse{
        public String access_token;
        public String token_type;
        public String refresh_token;
        public int expires_in;
        public String scope;
        public int refresh_token_expires_in;
    }

    // -----------------------
    // 카카오 프로필 response
    // -----------------------
    @Data
    private static class KakaoAccount{
        public boolean profile_nickname_needs_agreement;
        public boolean profile_image_needs_agreement;
        public Profile profile;
        public boolean has_email;
        public boolean email_needs_agreement;
        public boolean is_email_valid;
        public boolean is_email_verified;
        public String email;
    }

    @Data
    private static class Profile{
        public String nickname;
        public String thumbnail_image_url;
        public String profile_image_url;
        public boolean is_default_image;
        public boolean is_default_nickname;
    }

    @Data
    private static class Properties{
        public String nickname;
        public String profile_image;
        public String thumbnail_image;
    }

    @Data
    private static class KakaoProfileResponse{
        public long id;
        public Date connected_at;
        public Properties properties;
        public KakaoAccount kakao_account;
    }

    //------------------------
    //카카오 친구 메시지 보내기
    //------------------------
    @Data
    private static class Element{
        public String profile_nickname;
        public String profile_thumbnail_image;
        public boolean allowed_msg;
        public Object id;
        public String uuid;
        public boolean favorite;
    }
    @Data
    private static class KakaoFriendsResponse {
        public ArrayList<Element> elements;
        public int total_count;
        public Object after_url;
        public int favorite_count;
    }
}
