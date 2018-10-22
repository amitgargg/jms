package jms.v2.topic;

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
		final TopicConnectionFactory connectionFactory = (TopicConnectionFactory) 
				initialContext.lookup("jms/__defaultConnectionFactory");
		final Topic topic = (Topic) initialContext.lookup("jms/topic");
		
		// All registered topic will receive the Message
		ExecutorService executor = Executors.newFixedThreadPool(3);
		executor.submit(() -> {
			JMSContext context = connectionFactory.createContext();
			JMSConsumer consumer = context.createConsumer(topic);
			System.out.println(consumer.receiveBody(String.class));
		});
		executor.submit(() -> {
			JMSContext context = connectionFactory.createContext();
			JMSConsumer consumer = context.createConsumer(topic);
			System.out.println(consumer.receiveBody(String.class));
		});
		executor.submit(() -> {
			JMSContext context = connectionFactory.createContext();
			JMSConsumer consumer = context.createConsumer(topic);
			System.out.println(consumer.receiveBody(String.class));
		});
	}
}





