package br.pucminas.svardb.worker;

public class DynamoBDConnect {
    private final static String accessKey = "YOUR_ACCESS_KEY";
    private final static String secretKey = "YOUR_SECRET_KEY";
    private final static String region = "us-west-2"; // Replace with your desired region
    private final static String endpoint = "https://dynamodb.us-west-2.amazonaws.com"; // Replace with your DynamoDB endpoint

    public DynamoBDConnect() {
       // Create DynamoDB client
        client = AmazonDynamoDBClientBuilder.standard()
               .withCredentials(new AWSStaticCredentialsProvider(credentials))
               .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpoint, region))
               .build();

       // Create DynamoDB object
       DynamoDB dynamoDB = new DynamoDB(client);
    }
}
