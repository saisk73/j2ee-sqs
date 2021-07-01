package com.example.demo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//aws sqs
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;


@WebServlet(name = "sendMessage", value = "/post-message")
public  class SendMessage extends HttpServlet {
    private  String message;

    public void doPost(HttpServletRequest req, HttpServletResponse response) throws IOException {
        message = req.getParameter("message");
        System.out.println(message);
        BasicAWSCredentials awsCreds = new BasicAWSCredentials("", "mOhfipCwLlDoEDke2dRFTQQTRB+1lQSUQ7VnkBla");
        AmazonSQS sqs = AmazonSQSClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .withRegion(Regions.AP_SOUTH_1).build();
        String queueUrl = "https://sqs.ap-south-1.amazonaws.com/784558332964/salesken";
        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody(message)
                .withDelaySeconds(5);
        sqs.sendMessage(send_msg_request);
        response.sendRedirect("form.jsp");
    }
}
