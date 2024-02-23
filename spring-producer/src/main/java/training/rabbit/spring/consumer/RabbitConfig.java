package training.rabbit.spring.consumer;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactoryParam) {
        RabbitTemplate rabbitTemplateLoc = new RabbitTemplate(connectionFactoryParam);
        rabbitTemplateLoc.setMandatory(true);
        rabbitTemplateLoc.setReturnsCallback(rm -> System.out.println("RM : " + rm.toString()));
        rabbitTemplateLoc.setConfirmCallback((cr, t, s) -> System.out.println("Cor :"
                                                                              + cr
                                                                              + " conf : "
                                                                              + t
                                                                              + " str : "
                                                                              + s));
        rabbitTemplateLoc.setChannelTransacted(true);
        return rabbitTemplateLoc;
    }

}
