package jms.v2.queue.acknowledgements;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class Consumer {

	public static void main(String[] args) throws NamingException {
		InitialContext initialContext = new InitialContext();
		QueueConnectionFactory connectionFactory = (QueueConnectionFactory) 
				initialContext.lookup("jms/__defaultConnectionFactory");
		final Queue queue = (Queue) initialContext.lookup("jms/P2PQueue");
		JMSContext context = connectionFactory.createContext(JMSContext.DUPS_OK_ACKNOWLEDGE);
		JMSConsumer consumer = context.createConsumer(queue);
		System.out.println(consumer.receiveBody(String.class));
	}
}



