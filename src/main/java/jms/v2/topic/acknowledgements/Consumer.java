package jms.v2.topic.acknowledgements;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Consumer {

	public static void main(String[] args) throws NamingException, JMSException {

		final InitialContext initialContext = new InitialContext();
		final TopicConnectionFactory connectionFactory = (TopicConnectionFactory) initialContext
				.lookup("jms/__defaultConnectionFactory");
		final Topic topic = (Topic) initialContext.lookup("jms/topic");
		JMSContext context = connectionFactory.createContext(JMSContext.CLIENT_ACKNOWLEDGE);
		JMSConsumer consumer = context.createSharedDurableConsumer(topic, "SAHRED");
		Message message = consumer.receive();
		System.out.println("Consumer Name : TECHOGNITE: " + message.getBody(String.class));
		message.acknowledge();
	}
}


