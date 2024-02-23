package training.rabbit.spring.consumer.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SMSTopicListener {

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(name = "sms-national-queue", durable = "true", autoDelete = "false"),
                    exchange = @Exchange(name = "topic-message-send", durable = "true", autoDelete = "false", type = ExchangeTypes.TOPIC),
                    key = "sms.national.#"
            )
    )
    public void handleNationalSMS(String message) {
        System.out.println("Incoming National SMS : " + message);
    }

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(name = "sms-international-queue", durable = "true", autoDelete = "false"),
                    exchange = @Exchange(name = "topic-message-send", durable = "true", autoDelete = "false", type = ExchangeTypes.TOPIC),
                    key = "sms.international.#"
            )
    )
    public void handleInternationalSMS(String message) {
        System.out.println("Incoming International SMS : " + message);
    }

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(name = "all-sms-topic-queue", durable = "true", autoDelete = "false"),
                    exchange = @Exchange(name = "topic-message-send", durable = "true", autoDelete = "false", type = ExchangeTypes.TOPIC),
                    key = "sms.#"
            )
    )
    public void handleAllSMS(String message) {
        System.out.println("Incoming All SMS : " + message);
    }


}
