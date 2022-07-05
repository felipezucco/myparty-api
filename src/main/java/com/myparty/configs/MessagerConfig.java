package com.myparty.configs;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.myparty.model.notification.Notification;

@Configuration
public class MessagerConfig {

	public static final String EXCHANGE_NAME = "amq.direct";
	public static final String QUEUE_NAME = "estoque";
	
	private AmqpAdmin amqpAdmin;

	public MessagerConfig(AmqpAdmin amqpAdmin) {
		this.amqpAdmin = amqpAdmin;
	}

	private static Queue queue(String queueName) {
		return new Queue(queueName, true, false, false);
	}

	private static DirectExchange exchange() {
		return new DirectExchange(EXCHANGE_NAME);
	}

	private static Binding binding(Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(queue.getName());
	}

	public void addQueue(String queueName) {
		Queue queue = queue(queueName);
		DirectExchange exchange = exchange();
		Binding binding = binding(queue, exchange);
		
		amqpAdmin.declareQueue(queue);
		amqpAdmin.declareExchange(exchange);
		amqpAdmin.declareBinding(binding);
	}

}
