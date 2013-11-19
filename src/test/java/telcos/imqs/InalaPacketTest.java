package telcos.imqs;

import org.apache.commons.collections.map.MultiValueMap;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class InalaPacketTest {
    static int testPort = 8008;
    static Server server = new Server(testPort);
    static String contextPath = "";
    static String rootURL = "http://localhost:" + testPort + contextPath;

    static RestTemplate rest = new RestTemplate();


    @BeforeClass
    public static void setup() {

        WebAppContext context = new WebAppContext();
        context.setDescriptor("Report testing system");
        context.setResourceBase("src/main/webapp");
        context.setContextPath(contextPath);
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
    try{
   /*     InalaPacketController packet = new InalaPacketController();
        packet.getFile(getZip("src/test/resources/data/rawDump1.zip"));
        //TODO check that 326 entries where dropped in a queue*/
        String pathToFile = "src/test/resources/data/rawDump1.zip";
        Map<String,File> vars = new MultiValueMap();
        vars.put("File", new File("src/test/resources/data/rawDump1.zip"));
         //Now do a rest call to the api
        rest.put("http://localhost:8008/telcosRest/misc/{File}", null);
    }
    catch (Exception e){
        e.printStackTrace();
    }

    }

    @Test
    public void testLoadFile(){
        rest.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
        rest.getMessageConverters().add(new StringHttpMessageConverter());

        String uri = new String("http://localhost:8008/telcosRest/misc/");
        File zip = new File("src/test/resources/data/rawDump1.zip");
         Map<String,String> vars = new HashMap<>();
        vars.put("File2",zip.getName());
        rest.postForObject(uri,zip,File.class,null);
    }
    @Test
    public void itemPut() throws Exception{
        // Add a movie to the database
        rest.put(rootURL + "/telcosRest/misc/Kill Bill Volume 2/pg16/Quentin Tarantino",null);

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
