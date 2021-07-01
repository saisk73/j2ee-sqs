package com.example.demo;

//aws sqs
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.DeleteMessageResult;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import com.google.gson.*;


public class ListMessages extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials("", "mOhfipCwLlDoEDke2dRFTQQTRB+1lQSUQ7VnkBla");
        String queueUrl = "https://sqs.ap-south-1.amazonaws.com/784558332964/salesken";
        AmazonSQS sqs = AmazonSQSClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .withRegion(Regions.AP_SOUTH_1).build();
        ReceiveMessageRequest receive_request = new ReceiveMessageRequest()
                .withQueueUrl(queueUrl)
                .withWaitTimeSeconds(10)
                .withVisibilityTimeout(20)
                .withMaxNumberOfMessages(10);
        ArrayList<Message> messages = (ArrayList<Message>) sqs.receiveMessage(receive_request).getMessages();
//        request.setAttribute("messages", messages);
        for (Message m: messages) {
            System.out.println(m.toString());
            DeleteMessageResult result = sqs.deleteMessage(queueUrl, m.getReceiptHandle());
            System.out.println(result.getSdkResponseMetadata());
        }
        response.setContentType("application/json");
        response.getWriter().println(new Gson().toJson(messages));
//        RequestDispatcher rd = request.getRequestDispatcher("list-messages.jsp");

//        try {
//            rd.forward(request, response);
//        } catch (Exception e) {
//            response.sendRedirect("form.jsp");
//        }
    }

    public void destroy() {
    }
}