package com.tourguide.controller;

import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@RestController
public class TravelController {

    @GetMapping("/chatgpt")
    public Resource getPage(){

        return new ClassPathResource("templates/member/chat.html");
    }

    @GetMapping("/recommend")
    @ResponseBody
    public String recommend(@RequestParam("region") String region, @RequestParam("days") int days) {
        // ChatGPT API 호출 코드
        String prompt = region + "에서\n" + days + "일동안\n" + "여행할건데\n관광지랑\n맛집을\n날짜별로\n소개해줘";
        String response = callChatGPTAPI(prompt);

        // ChatGPT API 결과값 처리 코드
        String recommendation = response;

        // region + "에서\n" + days + "일동안\n" + "여행할건데\n관광지랑\n맛집을\n일차별로\n소개해줘"

        return recommendation;
    }

    private String callChatGPTAPI(String prompt) {
        try {
            MediaType mediaType = MediaType.parse("application/json");
            OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build();

            String model = "text-davinci-003";
            String maxTokens = "150";
            String temperature = "0.7";
            String topP = "1";
            String frequencyPenalty = "0";
            String presencePenalty = "0";
            String apikey = "API_KEY 토큰 입력";
            
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("model", "text-davinci-003");
            jsonObject.put("prompt", prompt);
            jsonObject.put("max_tokens",   1000);
            jsonObject.put("temperature", 0.7);
            jsonObject.put("top_p", 1);
            jsonObject.put("frequency_penalty", 0);
            jsonObject.put("presence_penalty", 0);
            jsonObject.put("stream", false );
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

            return response_text;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
