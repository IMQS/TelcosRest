package telcos.imqs;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: MaletshaM
 * Date: 2013/11/05
 * Time: 10:49 AM
 * To change this template use File | Settings | File Templates.
 */

/**
 * Jetty running on port 9001
 */
public class JettyRunner {
    private static Logger logger = Logger.getLogger(JettyRunner.class.getName());
    public static void main(String[] args) throws Exception {
        Server server = new Server(9001);

        WebAppContext context = new WebAppContext();
        context.setDescriptor("src/webapp/WEB-INF/web.xml");
        context.setResourceBase("src/main/webapp");
        context.setContextPath("/");
        context.setParentLoaderPriority(true);

        server.setHandler(context);

        server.start();
        server.join();
    }

}
