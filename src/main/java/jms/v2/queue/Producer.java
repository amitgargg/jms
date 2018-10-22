package jms.v2.queue;

import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Producer {
	public static void main(String[] args) throws NamingException, JMSException {
		InitialContext initialContext = new InitialContext();
		Queue queue = (Queue) initialContext.lookup("jms/P2PQueue");
		ConnectionFactory connectionFactory = (QueueConnectionFactory) initialContext
				.lookup("jms/__defaultConnectionFactory");
		JMSContext context = connectionFactory.createContext();
		JMSProducer producer = context.createProducer();
		
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		producer.setTimeToLive(100000l);
		
		TextMessage message = context.createTextMessage("HELLOOOOOOO");
		producer.send(queue, message);
		System.err.println("Message Produced");
	}
}
