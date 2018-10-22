package jms.v2.queue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Consumer {
	public static void main(String[] args) throws NamingException, JMSException {
		InitialContext initialContext = new InitialContext();
		Queue queue = (Queue) initialContext.lookup("jms/P2PQueue");
		ConnectionFactory connectionFactory = (QueueConnectionFactory) initialContext
				.lookup("jms/__defaultConnectionFactory");
//		JMSContext context = connectionFactory.createContext();
//		JMSConsumer consumer = context.createConsumer(queue);
//		System.out.println(consumer.receiveBody(String.class));
		
		// AT A time only one thread will receive the Message
		ExecutorService executor = Executors.newFixedThreadPool(3);
		executor.submit(() -> {
			JMSContext context = connectionFactory.createContext();
			JMSConsumer consumer = context.createConsumer(queue);
			System.out.println(Thread.currentThread() + consumer.receiveBody(String.class));
		});
		executor.submit(() -> {
			JMSContext context = connectionFactory.createContext();
			JMSConsumer consumer = context.createConsumer(queue);
			System.out.println(Thread.currentThread() +consumer.receiveBody(String.class));
		});
		executor.submit(() -> {
			JMSContext context = connectionFactory.createContext();
			JMSConsumer consumer = context.createConsumer(queue);
			System.out.println(Thread.currentThread() +consumer.receiveBody(String.class));
		});
	}
}


	