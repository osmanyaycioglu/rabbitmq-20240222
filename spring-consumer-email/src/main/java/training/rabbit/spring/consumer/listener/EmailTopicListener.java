package training.rabbit.spring.consumer.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EmailTopicListener {


    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(name = "all-email-topic-queue", durable = "true", autoDelete = "false"),
                    exchange = @Exchange(name = "topic-message-send", durable = "true", autoDelete = "false", type = ExchangeTypes.TOPIC),
                    key = "email.#"
            )
    )
    public void handleAllSMS(String message) {
        System.out.println("Incoming All Email : " + message);
    }

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(name = "adv-email-topic-queue", durable = "true", autoDelete = "false"),
                    exchange = @Exchange(name = "topic-message-send", durable = "true", autoDelete = "false", type = ExchangeTypes.TOPIC),
                    key = "email.adv.#"
            )
    )
    public void handleAdvSMS(String message) {
        System.out.println("Incoming Adv Email : " + message);
    }

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(name = "sys-email-topic-queue", durable = "true", autoDelete = "false"),
                    exchange = @Exchange(name = "topic-message-send", durable = "true", autoDelete = "false", type = ExchangeTypes.TOPIC),
                    key = "email.sys.#"
            )
    )
    public void handleSysSMS(String message) {
        System.out.println("Incoming Sys Email : " + message);
    }

}
