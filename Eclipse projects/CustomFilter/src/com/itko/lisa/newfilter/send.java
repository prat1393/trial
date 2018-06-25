package com.itko.lisa.newfilter;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
 

public class send {

	public static void main(String[] args) throws JMSException {
		// TODO Auto-generated method stub
		  ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");

		     Connection connection = connectionFactory.createConnection();
             connection.start();

             // Create a Session
             Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

             // Create the destination (Topic or Queue)
             Destination destination = session.createQueue("TEST.FOO");
             MessageProducer producer = session.createProducer(destination);
             Scanner S=new Scanner(System.in);
             String text=S.next();
             TextMessage message = session.createTextMessage(text);
          
             System.out.println("Sent message: "+ message.hashCode() + " : " + Thread.currentThread().getName());
             producer.send(message);
             session.close();
             connection.close();
	}

}
