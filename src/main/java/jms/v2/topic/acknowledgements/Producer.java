package jms.v2.topic.acknowledgements;

import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Producer {

	public static void main(String[] args) throws NamingException {
		InitialContext initialContext = new InitialContext();
		TopicConnectionFactory connectionFactory = (TopicConnectionFactory) initialContext
				.lookup("jms/__defaultConnectionFactory");
		Topic topic = (Topic) initialContext.lookup("jms/topic");
		JMSContext context = connectionFactory.createContext();
		JMSProducer producer = context.createProducer();
		TextMessage message = context.createTextMessage("HELLO TOPIC");
		producer.send(topic, message);
		System.out.println("Message Sent");
	}
}
