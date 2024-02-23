package training.rabbit.spring.consumer.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SMSListener {
    private int counter = 0;
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(name = "sms-queue", durable = "true", autoDelete = "false"
                            ,arguments={
//                            @Argument(name = "x-expires",value = "2000"),
                            @Argument(name = "x-dead-letter-exchange",value = "dead-letter-ex")
                    }),
                    exchange = @Exchange(name = "message-send", durable = "true", autoDelete = "false", type = ExchangeTypes.DIRECT),
                    key = "send-sms"
            )
    )
    public void handleSMS(String message) {
        counter++;
        System.out.println("Incoming SMS : " + message + " counter : " + counter);
        if (counter % 3 == 0) {
            throw new IllegalStateException("Counter "
                                            + counter);
        }
    }

//    @RabbitListener(
//            bindings = @QueueBinding(
//                    value = @Queue(name = "sms-queue", durable = "true", autoDelete = "false"),
//                    exchange = @Exchange(name = "message-send", durable = "true", autoDelete = "false", type = ExchangeTypes.DIRECT),
//                    key = "send-sms"
//            )
//    )
//    public void handleSMS(String message,
//                          Channel channelParam,
//                          @Header(AmqpHeaders.DELIVERY_TAG)  long seq) throws IOException {
//        counter++;
//        System.out.println("Incoming SMS : " + message + " counter : " + counter);
//        if (counter % 3 == 0) {
//            channelParam.basicNack(seq,true,false);
//        } else {
//            channelParam.basicAck(seq,true);
//        }
//    }


}
