package com.example.demo;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnection;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

import javax.jms.*;
 
@WebListener
public class TestListener implements ServletContextListener {
 
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("Server stopped");
    }
 
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        System.out.println("Server started");  
        BasicAWSCredentials awsCreds = new BasicAWSCredentials("", "mOhfipCwLlDoEDke2dRFTQQTRB+1lQSUQ7VnkBla");
        String queueUrl = "https://sqs.ap-south-1.amazonaws.com/784558332964/salesken";
        SQSConnectionFactory connectionFactory=new SQSConnectionFactory(
                new ProviderConfiguration(),
                AmazonSQSClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .withRegion(Regions.AP_SOUTH_1).build()
        );
        SQSConnection connection;
		try {
			connection = connectionFactory.createConnection();
			Session session=connection.createSession(false,Session.CLIENT_ACKNOWLEDGE);

		    Queue queue=session.createQueue("salesken");

		    MessageConsumer consumer=session.createConsumer(queue);
		    consumer.setMessageListener(new MyListener1());
		    connection.start();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


        
    }
}

class MyListener1 implements MessageListener {
    @Override
    public void onMessage(Message message) {
        try{
            String text = ((TextMessage) message).getText();
            System.out.println("received:" + text);
            message.acknowledge();

        }catch(JMSException e){
            System.out.println("Error processing message "+e.getMessage());
            e.printStackTrace();
        }
    }
}
