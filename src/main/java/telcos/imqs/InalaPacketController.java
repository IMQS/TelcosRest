package telcos.imqs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @RequestMapping(value = "/{File}", method =  RequestMethod.PUT)
    @ResponseBody
    public void getFile(@PathVariable("File") File zipFile) {
        ZipFilePacket packets = new ZipFilePacket();
        zipToJson = new ZipToJson();
        logger.info("Received a new zip file "+zipFile);
        packets.setFile(zipFile);
        zipToJson.unzipFile(packets);

    }



}
