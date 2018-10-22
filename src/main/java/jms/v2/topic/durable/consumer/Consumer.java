package jms.v2.topic.durable.consumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Consumer {

	public static void main(String[] args) throws NamingException {

		final InitialContext initialContext = new InitialContext();
		final TopicConnectionFactory connectionFactory = (TopicConnectionFactory) initialContext
				.lookup("jms/__defaultConnectionFactory");
		final Topic topic = (Topic) initialContext.lookup("jms/topic");

		ExecutorService executor = Executors.newFixedThreadPool(3);
		executor.submit(() -> {
			JMSContext context = connectionFactory.createContext();
			JMSConsumer consumer = context.createDurableConsumer(topic, "MyIDs");
			System.out.println("Consumer Name : TECHOGNITE: " + consumer.receiveBody(String.class));
		});

//		executor.submit(() -> {
//			JMSContext context = connectionFactory.createContext();
//			JMSConsumer consumer = context.createSharedDurableConsumer(topic, "TECHOGNITE");
//			System.out.println("Consumer Name : TECHOGNITE: " + consumer.receiveBody(String.class));
//		});
//
//		executor.submit(() -> {
//			JMSContext context = connectionFactory.createContext();
//			JMSConsumer consumer = context.createSharedDurableConsumer(topic, "COMPANY NAME");
//			System.out.println("Consumer Name : COMPANY NAME: " + consumer.receiveBody(String.class));
//		});
	}
}

