package com.tourguide.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionRequest;

@RestController
public class ChatgptAPIController {

    @GetMapping("/chat")
    public Resource getsignupPage(){

        return new ClassPathResource("templates/member/recommend.html");


    }

    @PostMapping("/postchat")
    public ResponseEntity<?> sendQuestion(@RequestBody String request) {
        
        OpenAiService service = new OpenAiService("   ");
        CompletionRequest completionRequest = CompletionRequest.builder()
            .prompt(request)
            .model("text-davinci-003") // 이 모델로 해줘야 제대로 대화가 된다. 하지만 한국어는 잘 안된다. 다른 모델을 써야할듯...
            .echo(false) // 이 기능은 내가 질문한 걸 똑같이 뱉어주고 나서 그 질문의 답을 그 뒤에 붙여서 보내기 때문에 질문을 반복할 필요가 없기 때문에 false
            .build();
        return ResponseEntity.ok(service.createCompletion(completionRequest).getChoices());
    }
}
