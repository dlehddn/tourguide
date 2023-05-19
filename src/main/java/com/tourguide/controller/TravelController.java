package com.tourguide.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tourguide.domain.dto.RecommendDto;
import com.tourguide.service.MemberService;
import com.tourguide.service.RecommendService;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@RestController
public class TravelController {
    @Autowired
    private MemberService memberService;
    @Autowired 
    private RecommendService recommendService;
    @GetMapping("/logincheck")
    public String getMno() {
        return memberService.getMno();
    }

    @GetMapping("/chat")
    public Resource getPage(){

        return new ClassPathResource("templates/member/chat.html");
    }

    @PostMapping("/setinfo")
    public int setinfo(RecommendDto recommendDto){
        return recommendService.setinfo(recommendDto);
    }

    @GetMapping("/getinfo")
    public String getinfo(String info_id){
        return recommendService.getinfo(info_id);
    }

    @GetMapping("/recommend")
    @ResponseBody
    public String recommend(@RequestParam("region") String region, @RequestParam("days") int days) {
        // ChatGPT API 호출 코드
        String mbti_type = memberService.getMmbti();
        String prompt = "내 MBTI는\n" + mbti_type + "이고\n" + region + "에서\n" + days + "일동안\n" + "여행할건데 "
                        + "나의 MBTI성향에 대해 간단하게 설명해주고, 나한테 어울리는 여행코스를 맛집도 포함해서 날짜별로 자세한 설명과 함께 추천해줘.";
        String response = callChatGPTAPI(prompt);
        String recommendation = response;
        return recommendation;
    }
    private String callChatGPTAPI(String prompt) {
        try {
            MediaType mediaType = MediaType.parse("application/json");
            OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS) // http 최대 요청시간 정의
            .build(); 

            String apikey = "api_key 입력";

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("model", "text-davinci-003");
            jsonObject.put("prompt", prompt);
            jsonObject.put("max_tokens",   1500);
            jsonObject.put("temperature", 0.7);
            jsonObject.put("top_p", 1);
            jsonObject.put("frequency_penalty", 0);
            jsonObject.put("presence_penalty", 0);
            jsonObject.put("stream", false);
            String json = jsonObject.toString();
            RequestBody body = RequestBody.create(json, mediaType);
            Request request = new Request.Builder()
                    .url("https://api.openai.com/v1/completions")
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "Bearer " + apikey)
                    .build();

            Response response = client.newCall(request).execute();
            
            
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response.body().byteStream());
            String response_text = rootNode.get("choices").get(0).get("text").asText();
            String response_final = response_text.replaceAll("\n", "<br/>");

            return response_final;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
