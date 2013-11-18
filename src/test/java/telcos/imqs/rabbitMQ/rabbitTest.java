package telcos.imqs.rabbitMQ;

import net.sf.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;
import telcos.rabbitMQ.MsgReceiver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: armandw
 * Date: 2013/11/04
 * Time: 3:18 PM
 */
public class rabbitTest {

    //This test contains an infinite while loop to run as a service. it will hold up all the tests if not ignored
    @Ignore
    @Test
    public void testMsgReceiver() {
        JSONObject js = new JSONObject();
        js.put("Name", "test");
        assertTrue(MsgProducer.sendJSON(js));
        JSONObject obj = MsgReceiver.getJSONReceived();
        assertEquals(obj.get("Name").toString(), "test");
    }


    @Test
    public void testSendAlarmJSON() {
        JSONObject packet = new JSONObject();
        packet.put("sequenceNumber", "11495592");
        packet.put("samId", "393");
        packet.put("rawValues", "12100100293600008000000000100000C900000000001080747474749D329D57000000000000000D018A0000008800F3" +
                "01000000000A9D0400EA00DE00F000400065002F00C005AE0868041EF0");
        MsgProducer.sendJSON(packet);
       assertTrue(packet.getString("rawValues").length() == 154);
    }
}
