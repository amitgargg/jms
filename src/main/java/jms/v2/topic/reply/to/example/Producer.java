package jms.v2.topic.reply.to.example;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Producer {

	public static void main(String[] args) throws NamingException {
		InitialContext initialContext = new InitialContext();
		TopicConnectionFactory connectionFactory = (TopicConnectionFactory) 
				initialContext.lookup("jms/__defaultConnectionFactory");
		Topic topic = (Topic) initialContext.lookup("jms/topic");
		JMSContext context = connectionFactory.createContext();
		
		// Create Message
		JMSProducer producer = context.createProducer();
		TextMessage message = context.createTextMessage("HELLO TOPIC");
		
		// Create Temporary Queue
		Queue queue = context.createTemporaryQueue();
		producer.setJMSReplyTo(queue);
		
		producer.send(topic, message);
		
		// Read From Temporary Queue
		JMSConsumer consumer = context.createConsumer(queue);
		System.out.println(consumer.receiveBody(String.class));
	}
}


