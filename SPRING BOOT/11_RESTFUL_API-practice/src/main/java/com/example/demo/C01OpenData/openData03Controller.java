package com.example.demo.C01OpenData;

import com.example.demo.C01OpenData.bus.BUSResult;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@Slf4j
@RequestMapping("/openData")
public class openData03Controller {

    @GetMapping("/bus/realtime")
    public void bus_realtime(Dotenv dotenv) throws UnsupportedEncodingException {
        String url = "https://apis.data.go.kr/6270000/dbmsapi01/getRealtime";
        String serviceKey = dotenv.get("REACT_APP_KR_OPENDATA_API_KEY_DECODING");
        String bsId = "7001001600";
        String routeNo = "649";



        URI uri = UriComponentsBuilder
                .fromHttpUrl(url)
                .queryParam("serviceKey", URLEncoder.encode(serviceKey, StandardCharsets.UTF_8))
                .queryParam("bsId", bsId)
                .queryParam("routeNo", routeNo)
                .build(true)
                .toUri();

//        String fullUrl = url + "?serviceKey=" + serviceKey
//                + "&bsId=" + bsId
//                + "&routeNo=" + routeNo;

        // 요청헤더

        // 요청 바디

        // 요청 후 응답값 받기
        RestTemplate rt = new RestTemplate();
        ResponseEntity<BUSResult> response = rt.exchange(uri, HttpMethod.GET, null, BUSResult.class);
        System.out.println(response);

        // 가공처리
    }
}
