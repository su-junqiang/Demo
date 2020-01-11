package com.test.demo.config.rabbitMQ;

import com.rabbitmq.client.ConnectionFactory;
import com.test.rabbitMQ.channel.RabbitChannelFactory;
import com.test.rabbitMQ.connection.RabbitConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
@Configuration
@PropertySource("classpath:application.properties")

public class RabbitConfig {

    @Value("${spring.rabbitmq.host}")
    private String[] host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String userName;

    @Value("${spring.rabbitmq.password}")
    private String passWord;

    @Bean
    public ConnectionFactory connectionFactoryDemo() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername(userName);
        connectionFactory.setPassword(passWord);
        connectionFactory.setVirtualHost("/");
        return connectionFactory;
    }

    @Bean
    public RabbitConnectionFactory rabbitConnectionFactoryDemo(){
        RabbitConnectionFactory rabbitConnectionFactory = new RabbitConnectionFactory();
        rabbitConnectionFactory.setConnectionFactory(connectionFactoryDemo());
        rabbitConnectionFactory.setHosts(host);
        return rabbitConnectionFactory;
    }

    @Bean
    public RabbitChannelFactory rabbitChannelFactoryDemo(){
        RabbitChannelFactory rabbitChannelFactory = new RabbitChannelFactory();
        rabbitChannelFactory.setConnectionFactory(rabbitConnectionFactoryDemo());

        return rabbitChannelFactory;
    }


}
