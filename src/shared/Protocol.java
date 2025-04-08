package shared;

public class Protocol {
    
    // available commands for the server
    public static final String GET_ROW = "GET_ROW";
    public static final String GET_ALL = "GET_ALL";
    public static final String SEARCH = "SEARCH";



    // answers by server
    public static final String OK = "OK";
    public static final String ERROR = "ERROR";


    // separators
    public static final String SEPARATOR = "|";
    public static final String LINE_SEPARATOR = "||";

}
