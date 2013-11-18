package telcos.imqs;

import net.sf.json.JSONObject;
import telcos.imqs.rabbitMQ.MsgProducer;


import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: MaletshaM
 * Date: 2013/11/06
 * Time: 1:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class PostToQueue {
    private static Logger logger = Logger.getLogger(PostToQueue.class.getName());
    private MsgProducer mq ;

    /**
     * Json object is received and it is send to RabbitMQ
     * @param data
     */
    public void sendToQueue(JSONObject data) {
        mq = new MsgProducer();
        boolean send = mq.sendJSON(data);
    }
}
