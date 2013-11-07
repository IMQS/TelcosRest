package telcos.imqs;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.File;


/**
 * Created with IntelliJ IDEA.
 * User: MaletshaM
 * Date: 2013/11/05
 * Time: 8:59 AM
 * To change this template use File | Settings | File Templates.
 */

@Setter
@Getter
@EqualsAndHashCode
public class ZipFilePacket {
    private  long id;
    private File file;
}
