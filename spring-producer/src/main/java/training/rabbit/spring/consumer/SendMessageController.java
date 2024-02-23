package training.rabbit.spring.consumer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/send/message")
public class SendMessageController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/sms")
    public void sendSms(@RequestParam String message) {
        rabbitTemplate.convertAndSend("message-send",
                                      "send-sms",
                                      message);
    }

    @PostMapping("/email")
    public void sendEmail(@RequestParam String message) {
        rabbitTemplate.convertAndSend("message-send",
                                      "send-email",
                                      message);
    }

    @PostMapping("/topic/sms")
    public String sendTopicSms(@RequestParam String message,
                               @RequestParam String key) {
        rabbitTemplate.convertAndSend("topic-message-send",
                                      key,
                                      message);
        return "OK";
    }

    @PostMapping("/topic/email")
    public String sendTopicEmail(@RequestParam String message,
                                 @RequestParam String key) {
        rabbitTemplate.convertAndSend("topic-message-send",
                                      key,
                                      message);
        return "OK";
    }

}
