package jms.v1.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Producer {
	public static void main(String[] args) throws NamingException, JMSException {
		InitialContext context = new InitialContext();
		Queue queue = (Queue) context.lookup("jms/P2PQueue");
		ConnectionFactory connectionFactory = (QueueConnectionFactory) 
				context.lookup("jms/__defaultConnectionFactory");
		Connection connection = connectionFactory.createConnection();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		connection.start();
		MessageProducer producer = session.createProducer(queue);
		TextMessage textMessage = session.createTextMessage("HELLO JMS");
		producer.send(textMessage);
		System.err.println("Message Produced");
	}
}
