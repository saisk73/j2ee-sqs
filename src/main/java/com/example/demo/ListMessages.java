package com.example.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.DeleteMessageResult;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;


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
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        for (Message m: messages) {
            System.out.println(m.toString());
            DeleteMessageResult result = sqs.deleteMessage(queueUrl, m.getReceiptHandle());
            System.out.println(result.getSdkResponseMetadata());
            out.println("<h1>" + m.toString() + "</h1>");
        }
        
        
        out.println("</body></html>");
//        response.setContentType("application/json");
//        response.getWriter().println(new Gson().toJson(messages));
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