package telcos.imqs;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.logging.Logger;


/**
 * Created with IntelliJ IDEA.
 * User: MaletshaM
 * Date: 2013/11/04
 * Time: 3:56 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/misc/")
public class InalaPacketController {
    private static Logger logger = Logger.getLogger(InalaPacketController.class.getName());
    ZipToJson zipToJson;

    /**
     * Files are pushed into this service.
     * @param zipFile
     */
    @RequestMapping(value = "/{File}")
    @ResponseBody
    public void getFile(@RequestParam(value = "File", required = true) File zipFile) {
        ZipFilePacket packets = new ZipFilePacket();
        zipToJson = new ZipToJson();
        logger.info("Received a new zip file "+zipFile);
        packets.setFile(zipFile);
        zipToJson.unzipFile(packets);
    }

    /**
     * Publishes a service that enables the consumer to load a zip file and it converts it into a
     * a JSON
     * @param zipFile
     */
    @RequestMapping( method = RequestMethod.POST)
    @ResponseBody
    public void loadFile(@RequestBody File zipFile){
        ZipFilePacket packets = new ZipFilePacket();
        zipToJson = new ZipToJson();
        logger.info("Received a new zip file "+zipFile);
        packets.setFile(zipFile);
        zipToJson.unzipFile(packets);
    }

    @RequestMapping(value = "/{name:.+}/{rating:.+}/{director:.+}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public void addMovie(@PathVariable("name") String name, @PathVariable("rating") String rating,
                         @PathVariable("director") String director) {
        System.out.println("Yes the put method executed!!!!");
    }

  /*  // Request of the form /example/rest
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    String hello() {
        return "Hey Its working!!!";
    }
*/


}
