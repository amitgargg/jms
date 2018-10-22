package jms.v2.topic.reply.to.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.sun.messaging.bridge.api.MessageTransformer.JMSMessageType;

public class Consumer {

	public static void main(String[] args) throws NamingException {

		final InitialContext initialContext = new InitialContext();
		final TopicConnectionFactory connectionFactory = (TopicConnectionFactory) initialContext
				.lookup("jms/__defaultConnectionFactory");
		final Topic topic = (Topic) initialContext.lookup("jms/topic");

		ExecutorService executor = Executors.newFixedThreadPool(3);
		executor.submit(() -> {
			try {
				JMSContext context = connectionFactory.createContext();
				JMSConsumer consumer = context.createConsumer(topic);
				Message message = consumer.receive();
				String Message = message.getBody(String.class);
				System.out.println(Message);

				// Get Reply To Queue and Send Message
				Destination destination = message.getJMSReplyTo();
				JMSProducer producer = context.createProducer();
				producer.send(destination, "Thanks " + Message);

			} catch (JMSException e) {
				e.printStackTrace();
			}
		});
	}
}
