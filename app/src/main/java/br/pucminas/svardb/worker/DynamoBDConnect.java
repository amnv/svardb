package br.pucminas.svardb.worker;

import android.content.Context;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.mobileconnectors.dynamodbv2.document.Table;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;

import java.util.List;
import java.util.Map;

import br.pucminas.svardb.R;

public class DynamoBDConnect {

    private BasicAWSCredentials awsCredentialsProvider;
    private static final String DYNAMODB_TABLE = "sensors";
    private static DynamoBDConnect dynamoDB = null;
    private final AmazonDynamoDBClient client;
    private final Table dbTable;

    public static DynamoBDConnect getInstance(Context context) {
        if (dynamoDB == null) {
            dynamoDB = new DynamoBDConnect(context);
        }
        return dynamoDB;
    }

    private DynamoBDConnect(Context context) {
        String key = context.getString(R.string.key);
        String password = context.getString(R.string.password);
        awsCredentialsProvider = new BasicAWSCredentials(key, password);

        // Create DynamoDB client
        client = new AmazonDynamoDBClient(awsCredentialsProvider);
        client.setEndpoint("https://dynamodb.us-west-2.amazonaws.com");
        client.setRegion(Region.getRegion(Regions.US_WEST_2));

        dbTable = Table.loadTable(client, DYNAMODB_TABLE);
    }

    public List<Map<String, AttributeValue>> getAllMemos() {
        return client.scan(new ScanRequest(DYNAMODB_TABLE)).getItems();
    }
}
