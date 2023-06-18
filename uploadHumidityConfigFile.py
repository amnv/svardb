# Lambda file to transform mqtt messages in rows at DynamoDB
import json
import boto3
import configparser

def lambda_handler(event, context):
    s3 = boto3.client('s3')
    configParser = configparser.RawConfigParser()   
    try:
        response = s3.get_object(Bucket='svadb', Key='humidity.config')
        print(configParser.read(response['Body']))

        print("CONTENT TYPE: " + response['ContentType'])
        #print(response['Body'].read())
        for i in response['Body']:
            print(i)
    except Exception as e:
        print(e)
        print('Error getting object {} from bucket {}. Make sure they exist and your bucket is in the same region as this function.'.format(key, bucket))
        raise e
   
