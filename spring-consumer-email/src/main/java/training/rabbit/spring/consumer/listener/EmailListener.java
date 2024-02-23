package training.rabbit.spring.consumer.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EmailListener {

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(name = "email-queue", durable = "true", autoDelete = "false",declare = "true"),
                    exchange = @Exchange(name = "message-send", durable = "true", autoDelete = "false", type = ExchangeTypes.DIRECT),
                    key = "send-email"
            )
    )
    public void handleEmail(String message) {
        System.out.println("Incoming EMAIL : " + message);
    }

}
