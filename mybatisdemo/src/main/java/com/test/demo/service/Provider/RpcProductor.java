package com.test.demo.service.Provider;

import com.test.demo.service.TokenService;
import com.test.demo.service.UserService;
import com.test.rabbitMQ.ExchangeType;
import com.test.rabbitMQ.channel.RabbitChannelFactory;
import com.test.rabbitMQ.remoting.RabbitInvokerServiceExporter;
import com.test.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RpcProductor {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private RabbitChannelFactory rabbitChannelFactory;
    //QUEUE的前缀名
    @Value("${rpc.prefix.queue}")
    private String prefixQueue;
    //KEY的前缀名
    @Value("${rpc.prefix.key}")
    private String prefixKey;
    //EXCHANGE 的前缀名
    @Value("${rpc.exchange}")
    private String exchangeName;

    @Bean
    public RabbitInvokerServiceExporter userService() {
        return productorFactorty(UserService.class);
    }

    @Bean
    public RabbitInvokerServiceExporter bookService() {
        return productorFactorty(BookService.class);
    }

    @Bean
    public RabbitInvokerServiceExporter tokenService() {
        return productorFactorty(TokenService.class);
    }

    public RabbitInvokerServiceExporter productorFactorty(Class serviceInterface) {
        String domain = getDomain(serviceInterface);

        RabbitInvokerServiceExporter rabbitInvokerServiceExporter = new RabbitInvokerServiceExporter();
        rabbitInvokerServiceExporter.setChannelFactory(rabbitChannelFactory);
        rabbitInvokerServiceExporter.setPoolsize(5);
        rabbitInvokerServiceExporter.setExchangeType(ExchangeType.TOPIC);

        rabbitInvokerServiceExporter.setServiceInterface(serviceInterface);
        rabbitInvokerServiceExporter.setService(applicationContext.getBean(serviceInterface));
        rabbitInvokerServiceExporter.setExchange(exchangeName);
        rabbitInvokerServiceExporter.setRoutingKey(prefixKey + domain);
        rabbitInvokerServiceExporter.setQueueName(prefixQueue + domain);

        return rabbitInvokerServiceExporter;
    }

    //字符串截取
    public String getDomain(Class serviceInterface) {
        String[] aa = serviceInterface.getName().split("\\.");
        String last = aa[aa.length - 1];
        String temp = last.substring(0, last.length() - 7);

        return temp.substring(0, 1).toLowerCase() + temp.substring(1);
    }
}
