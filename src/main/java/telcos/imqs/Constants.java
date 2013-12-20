package telcos.imqs;

/**
 * User: Russel Mupfumira
 * Date: 2013/05/22
 * Time: 12:23 PM
 */
public final class Constants {
     // Jetty Server config
     public static final int JETTY_SERVER_PORT = 9001;


    /**
     * Rabbit MQ Queue Constants
     */
    public static final String RABBIT_QUEUE_RAW_ALARMS = "TelcosRawAlarms";
    public static final String RABBIT_EXCHANGE_NAME = "RawExchange";
    //ND updating this from Tunafix to localhost so we can deploy and run in any environment
    // - Dev, QA and/or Production
    public static final String RABBIT_HOST = "127.0.0.1";
    public static final String RABBIT_QUEUE_RAW_INPUT = "TelcosRawInput";

}
