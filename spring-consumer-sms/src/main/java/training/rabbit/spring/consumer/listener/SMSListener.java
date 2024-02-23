package training.rabbit.spring.consumer.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SMSListener {

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(name = "sms-queue", durable = "true", autoDelete = "false"),
                    exchange = @Exchange(name = "message-send", durable = "true", autoDelete = "false", type = ExchangeTypes.DIRECT),
                    key = "send-sms"
            )
    )
    public void handleSMS(String message) {
        System.out.println("Incoming SMS : " + message);
    }


}
