package com.example.demo.C01OpenData;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/openData")
public class OpenData02Controller {

    String url = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst";
    String serviceKey = "HuCVWyilOTqxMrP/q3s2wk0DZgsf/KeEnFylSDMmws0IyE8E5dSlPrZkXLWGh8M2G98YRViid4NmtXjVJuER4w==";
    String pageNo = "1";
    String numOfRows = "10";
    String dataType = "JSON";
    String base_date = "20250512";
    String base_time = "0900";
    String nx = "60";
    String ny = "127";

    @GetMapping("/forecast")
    public String forecast(Model model) {
        log.info("GET /openData/forecast...");

        // 서버정보
        String fullUrl = url + "?serviceKey=" + serviceKey
                + "&pageNo=" + pageNo
                + "&numOfRows=" + numOfRows
                + "&base_date=" + base_date
                + "&base_time=" + base_time
                + "&dataType=" + dataType
                + "&nx=" + nx
                + "&ny=" + ny;

        // 요청 헤더
        log.info("요청 URL: " + fullUrl);

        // 요청 바디

        // 요청 -> 응답
        RestTemplate rt = new RestTemplate();
        ResponseEntity<Root> response = rt.exchange(fullUrl, HttpMethod.GET, null, Root.class);
        System.out.println(response);

        // 데이터 가공
        Root root = response.getBody();
        Response rs = root.getResponse();
        Body body = rs.getBody();
        Items items = body.getItems();
        List<Item> list = items.getItem();
        list.stream().forEach(System.out::println);

        if (list.size() == 0) {
            model.addAttribute("isOk",false);
        } else {
            model.addAttribute("isOk",true);
        }
        log.info("isOk : " + model.getAttribute("isOk"));
        model.addAttribute("list",list);

        return "openData/forecast";

    }

    //------------------------------------------
    @Data
    private static class Body {
        public String dataType;
        public Items items;
        public int pageNo;
        public int numOfRows;
        public int totalCount;
    }

    @Data
    private static class Header {
        public String resultCode;
        public String resultMsg;
    }

    @Data
    private static class Item {
        public String baseDate;
        public String baseTime;
        public String category;
        public int nx;
        public int ny;
        public String obsrValue;
    }

    @Data
    private static class Items {
        public ArrayList<Item> item;
    }

    @Data
    private static class Response {
        public Header header;
        public Body body;
    }

    @Data
    private static class Root {
        public Response response;
    }
    //------------------------------------------
}
