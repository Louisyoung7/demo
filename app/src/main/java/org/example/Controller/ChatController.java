package org.example.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.example.dto.Movie;
import org.springframework.ai.converter.BeanOutputConverter;
@RestController
public class ChatController {
    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder builder,ChatMemory chatMemory){
        this.chatClient=builder.defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build()).build();
    }

    @GetMapping("/chat")
    public String chat(@RequestParam(defaultValue ="你好")String message){
        return chatClient.prompt().system("你是一个耐心的万能老师，用通俗易懂的话回答，多举生活总的例子").user(message).call().content();
    }

    @GetMapping(value="/chat/stream",produces=MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter stream(@RequestParam(defaultValue="你好") String message){
        SseEmitter emitter=new SseEmitter(0L);
        chatClient.prompt().system("你是一个耐心的万能老师，用通俗易懂的话回答，多举生活中的例子").user(message).stream().content().subscribe(chunk->{
            try{
                emitter.send(chunk);
            }catch (Exception e){
                emitter.completeWithError(e);
            }
        },
                emitter::completeWithError,
                emitter::complete
        );
        return emitter;
    }

    @GetMapping("/chat/movie")
    public Movie movie(@RequestParam(defaultValue="推荐一部科幻电影")String topic){
        BeanOutputConverter<Movie> converter=new BeanOutputConverter<>(Movie.class);

        String content=chatClient.prompt().system("你只输出JSON,不要输出代码块外的任何解释").user("介绍一部电影，主题："+topic+"\n"+converter.getFormat()).call().content();

        return converter.convert(content);
    }
}
