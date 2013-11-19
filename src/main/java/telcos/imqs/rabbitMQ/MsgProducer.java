package telcos.imqs.rabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import net.sf.json.JSONObject;
import telcos.imqs.Constants;

import java.io.IOException;

public class MsgProducer {

    //TODO: Add ACK functionality to confirm sent status

    /**
     * This method will receive the JSONObject sent by the RealTime Service and then send it to the RabbitMQ Exchange.
     * The exchange will then route the messages to the correct queue's for the relevant subscribers. The Two queue's
     * for the time being is for the Alarms and Raw Packet unpacking.
     *
     * @param obj JSONObject containing the Raw packet values in json format.
     * @return A boolean value that will indicate if a packet was sent successfully.
     */
    public static boolean sendJSON(JSONObject obj) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(Constants.RABBIT_HOST);
        Connection connection;
        try {
            connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(Constants.RABBIT_EXCHANGE_NAME, "fanout");
            //Declare the relevant queue's and add them to the applicable exchange
            channel.queueDeclare(Constants.RABBIT_QUEUE_RAW_INPUT, false, false, false, null);
            channel.queueDeclare(Constants.RABBIT_QUEUE_RAW_ALARMS, false, false, false, null);
            //Publish to the exchange and Bind the different Queues

            channel.basicPublish(Constants.RABBIT_EXCHANGE_NAME, "", null, obj.toString().getBytes());
            channel.queueBind(Constants.RABBIT_QUEUE_RAW_INPUT, Constants.RABBIT_EXCHANGE_NAME, "");
            channel.queueBind(Constants.RABBIT_QUEUE_RAW_ALARMS, Constants.RABBIT_EXCHANGE_NAME, "");

            System.out.println("[x] Sent '" + obj.toString() + "'");
            channel.close();
            connection.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
