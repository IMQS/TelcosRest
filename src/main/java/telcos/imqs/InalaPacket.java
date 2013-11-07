package telcos.imqs;

import java.sql.Timestamp;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonProperty;

@Setter
@Getter
@EqualsAndHashCode
public final class InalaPacket {
    @JsonProperty
    private long sequenceNumber;
    @JsonProperty
    private int samId;
    @JsonProperty
    private String rawValues;
    @JsonProperty
    private Timestamp rawDate;

}