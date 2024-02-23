package training.rabbit.spring.consumer.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DeadLetter {


    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(name = "dead-letter-queue", durable = "true", autoDelete = "false"),
                    exchange = @Exchange(name = "dead-letter-ex", durable = "true", autoDelete = "false", type = ExchangeTypes.FANOUT)
            )
    )
    public void handleDeadLetter(Message message) {
        System.out.println("Incoming DL : " + message);
    }

}
