# Remenber to first install the dependencies from this tutorial https://repost.aws/knowledge-center/iot-core-publish-mqtt-messages-python
# pip install awsiotsdk
# pip install AWSIoTPythonSDK
# Must download de certificates given in the report

import time as t
import json

import AWSIoTPythonSDK.MQTTLib as AWSIoTPyMQTT
from awscrt import mqtt, http
from awsiot import mqtt_connection_builder

print("comeco")
message = {
   "temperature": 28,
   "humidity": 10,
   "barometer": 1013,
   "wind": {
     "velocity": 22,
     "bearing": 255
   }
}

print("Inicio request")

mqtt_connection = mqtt_connection_builder.mtls_from_path(
        endpoint="a1oc20hcf7d57x-ats.iot.us-west-2.amazonaws.com",
        cert_filepath="connect_device_package/python_device_simulator.cert.pem",
        pri_key_filepath="connect_device_package/python_device_simulator.private.key",
        ca_filepath="connect_device_package/root-CA.crt",
        client_id="basicPubSub")

print("Conectando")
connect_future = mqtt_connection.connect()
connect_future.result()

print("Publicando")
mqtt_connection.publish(
                topic="device/111/data",
                payload=json.dumps(message),
                qos=mqtt.QoS.AT_LEAST_ONCE)

print("Disconnecting...")
disconnect_future = mqtt_connection.disconnect()
disconnect_future.result()
print("Disconnected!")