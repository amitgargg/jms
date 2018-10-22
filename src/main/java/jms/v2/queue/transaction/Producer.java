package jms.v2.queue.transaction;

import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.jms.TopicConnectionFactory;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Producer {

	public static void main(String[] args) throws NamingException {
		InitialContext initialContext = new InitialContext();
		TopicConnectionFactory connectionFactory = (TopicConnectionFactory) initialContext
				.lookup("jms/__defaultConnectionFactory");
		Queue queue = (Queue) initialContext.lookup("jms/P2PQueue");
		JMSContext context = connectionFactory.createContext(JMSContext.SESSION_TRANSACTED);
		JMSProducer producer = context.createProducer();
		TextMessage message = context.createTextMessage("HELLO Queue1");
		producer.send(queue, message);
		System.out.println("Message Sent");
//		context.rollback();
		context.commit();
	}
}


