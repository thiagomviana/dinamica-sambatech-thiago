package encoder;

public interface Encoder {
    public int encode(String source, String destination, String format);
    public boolean refreshStatus();
    public String getEncodingStatus();
    public String getProgress();    
    public String getRemainingTime();
    public String getDestinationURL();
    
}
