package demo;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String message = "demo1";
        BasicAWSCredentials awsCreds = new BasicAWSCredentials("", "mOhfipCwLlDoEDke2dRFTQQTRB+1lQSUQ7VnkBla");
        AmazonSQS sqs = AmazonSQSClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .withRegion(Regions.AP_SOUTH_1).build();
        String queueUrl = "https://sqs.ap-south-1.amazonaws.com/784558332964/salesken";
        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody(message)
                .withDelaySeconds(5);
        try {
        	sqs.sendMessage(send_msg_request);
        } catch(Exception e) {
        	System.out.println(e);
        }
	}

}
