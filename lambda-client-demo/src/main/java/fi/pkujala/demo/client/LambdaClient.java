package fi.pkujala.demo.client;

import java.io.UnsupportedEncodingException;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;

/**
 * 
 * @author pjkujala
 *
 */
public class LambdaClient {

	public static void main(String args[]) {

		// (1a) Instantiate credentials
		BasicAWSCredentials credentials = new BasicAWSCredentials("asdfsadf",
				"asdfasf");

		// (1) Define the AWS Region in which the function is to be invoked
		Regions region = Regions.fromName("eu-central-1");
		// (2) Instantiate AWSLambdaClientBuilder to build the Lambda client
		AWSLambdaClientBuilder builder = AWSLambdaClientBuilder.standard().withRegion(region);

		// (2b) Modify to leverage credentials
		// AWSLambdaClientBuilder builder = AWSLambdaClientBuilder.standard()
		// .withCredentials(new
		// AWSStaticCredentialsProvider(credentials)).withRegion("eu-central-1");

		// (3) Build the client, which will ultimately invoke the function
		AWSLambda client = builder.build();
		// (4) Create an InvokeRequest with required parameters
		InvokeRequest req = new InvokeRequest().withFunctionName("MyFunction").withPayload("{\n" + 
				"	\"firstName\": \"John\",\n" + 
				"	\"lastName\": \"Doe\"\n" + 
				"\n" + 
				"}");
		// (5) Invoke the function and capture response
		InvokeResult result = client.invoke(req);
		// (6) Handle result
		System.out.println(result);
		
		String rawJson = null;
		
		try {
			rawJson = new String(result.getPayload().array(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(rawJson);
	}

}
