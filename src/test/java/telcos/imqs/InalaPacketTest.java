package telcos.imqs;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class InalaPacketTest {
    static Server server = new Server();
    static RestTemplate rest = new RestTemplate();


    @BeforeClass
    public static void setup() {

        WebAppContext context = new WebAppContext();
        context.setDescriptor("Report testing system");
        context.setResourceBase("src/main/webapp");
//        context.setContextPath(contextPath);
        context.setParentLoaderPriority(true);



        server.setHandler(context);

        // Kick the server to life, waiting 10 seconds for it to get its act
        // together
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
            fail("Could not start server");
        }
        assertTrue(server.isRunning());
    }

    @AfterClass
    public static void shutdown() {
        try {
            server.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void CollectionReadTest() {
        InalaPacketController packet = new InalaPacketController();
        packet.getFile(getZip("src/test/resources/data/rawDump1.zip"));
        //TODO check that 326 entries where dropped in a queue
    }

    public File getZip(String fileName){
        Path zipFile = Paths.get(fileName);
        File zis = null;
        try{
            zis = new File(fileName);

        }catch(Exception e){
              e.getMessage();
        }
        return zis;
    }


}
