package training.rabbit.spring.consumer.listener;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SMSListener {

    @RabbitListener(queues = "QUEUE-Z")
    public void handleSMS(String message) {
        System.out.println("Incoming SMS : " + message);
    }

    @RabbitListener(queues = "QUEUE-EMAIL")
    public void handleEmail(String message) {
        System.out.println("Incoming EMAIL : " + message);
    }

}
