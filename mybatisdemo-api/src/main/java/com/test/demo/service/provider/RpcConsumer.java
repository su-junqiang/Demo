package com.test.demo.service.provider;
import com.test.demo.service.BookService;
import com.test.demo.service.TokenService;
import com.test.demo.service.UserService;
import com.test.rabbitMQ.ExchangeType;
import com.test.rabbitMQ.channel.RabbitChannelFactory;
import com.test.rabbitMQ.remoting.RabbitInvokerProxyFactoryBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@PropertySource("classpath:application.properties")
@Configuration
public class RpcConsumer {

    @Autowired
    private RabbitChannelFactory rabbitChannelFactory;
    @Value("${rpc.prefix.key}")
    private String prefixKey;
    @Value("${rpc.exchange}")
    private String exchangeName;


    @Bean
    public RabbitInvokerProxyFactoryBean userService() {
        return consumerFactory(UserService.class);
    }

    @Bean
    public RabbitInvokerProxyFactoryBean bookService() {
        return consumerFactory(BookService.class);
    }

    @Bean
    public RabbitInvokerProxyFactoryBean tokenService() {
        return consumerFactory(TokenService.class);
    }

    public RabbitInvokerProxyFactoryBean consumerFactory(Class serviceInterface) {
        String domain = getDomain(serviceInterface);

        RabbitInvokerProxyFactoryBean rabbitInvokerProxyFactoryBean = new RabbitInvokerProxyFactoryBean();
        rabbitInvokerProxyFactoryBean.setChannelFactory(rabbitChannelFactory);
        rabbitInvokerProxyFactoryBean.setExchangeType(ExchangeType.TOPIC);
        rabbitInvokerProxyFactoryBean.setMandatory(true);
        rabbitInvokerProxyFactoryBean.setImmediate(false);
        rabbitInvokerProxyFactoryBean.setTimeoutMs(10000);
        rabbitInvokerProxyFactoryBean.setPoolSize(1);

        rabbitInvokerProxyFactoryBean.setServiceInterface(serviceInterface);
        rabbitInvokerProxyFactoryBean.setRoutingKey(prefixKey + domain);
        rabbitInvokerProxyFactoryBean.setExchange(exchangeName);

        return rabbitInvokerProxyFactoryBean;
    }

    public String getDomain(Class serviceInterface) {
        String[] aa = serviceInterface.getName().split("\\.");
        String last = aa[aa.length - 1];
        String temp = last.substring(0, last.length() - 7);

        return temp.substring(0, 1).toLowerCase() + temp.substring(1);
    }
}
