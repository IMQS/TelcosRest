package telcos.imqs;

import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Class that checks that the file persisted is a zip folder.
 * unzip the file and return a JSON object
 */

public class ZipToJson {
    private static Logger logger = Logger.getLogger(ZipToJson.class.getName());
    /**
     * Accepts java bean of type ZipFilePacket and checks if the file is a zip, unzip the file
     * @param packets  java bean that has a file
     * @return JSON Object is returned
     */
    public void unzipFile(ZipFilePacket packets){
        PostToQueue mq = new PostToQueue();
        File file = packets.getFile();
        JSONObject data = null;
        InalaPacket pkt = null;

        ZipInputStream zis;
        BufferedReader reader = null;
        String line = "";
        int count = 0;
        try{
            zis = new ZipInputStream(new FileInputStream(file));
            ZipEntry zi = zis.getNextEntry();
            if (!zi.isDirectory()) {
                InputStreamReader inputStreamReader = new InputStreamReader(zis);
                reader = new BufferedReader(inputStreamReader);
                while ((line = reader.readLine()) != null){
                    String[] p = line.split(",");
                    pkt = new InalaPacket();
                    pkt.setSequenceNumber(Long.valueOf(p[0]));
                    pkt.setSamId(Integer.valueOf(p[1]));
                    pkt.setRawValues(p[2]);
                    pkt.setRawDate(Timestamp.valueOf(p[3]));
                    data = convertToJson(pkt);
                    mq.sendToQueue(data);
                    count ++;
                }
                logger.info("Found "+count+" entries in csv file");
            }else{
               logger.warn("File is not a Zip file");
            }

        }catch(Exception e ){
            logger.error(e.getMessage());
        }

    }

    /**
     * Helper to convert java bean to JSONObject
     * @param data  java bean of Type InalaPacket
     * @retun JSONObject
     */
    private JSONObject convertToJson(InalaPacket data) {
        JSONObject jObj = new JSONObject();
        jObj.put("sequenceNumber", data.getSequenceNumber());
        jObj.put("samId", data.getSamId());
        jObj.put("rawValues", data.getRawValues());
        jObj.put("rawDate", data.getRawDate().toString());
        return jObj;
    }




}
