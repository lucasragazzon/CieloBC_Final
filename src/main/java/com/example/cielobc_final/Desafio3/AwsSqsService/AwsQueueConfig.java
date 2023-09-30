package com.example.cielobc_final.Desafio3.AwsSqsService;

import com.example.cielobc_final.Desafio1.Model.Client;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.websocket.SendResult;
import lombok.Value;

import org.springframework.stereotype.Service;

@Service
public class AwsQueueConfig {
    @Value("${sqs.queue.name}")
    private String Aws_Queue;

    private final SqsTemplate sqsTemplate;

    private final ObjectMapper objectMapper;

    public AwsSqsQueueService(SqsTemplate sqsTemplate, ObjectMapper objectMapper){
        this.sqsTemplate = sqsTemplate;
        this.objectMapper = objectMapper;
    }

    public SendResult<String> publishToQueue(Client client) throw RuntimeException{
        try{
            var payload = objectMapper.writeValueAsString(client);
            return sqsTemplate.send((obj) ->obj.queue(Aws_Queue).payload(payload));
        } catch (JsonProcessinException e) {
            throw new RuntimeException(e);
        }
    }

    public Object getNextClientFromServiceQueue(){
        Optional<Message<Object>> message = sqsTemplate.receive((from) -> from.queue(Aws_Queue).maxNumberOfMessages(1), Object.class);

    return message.map(Message::getPayload).orElse(null);
    }
}
