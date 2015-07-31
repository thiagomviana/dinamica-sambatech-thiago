package encoder;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Thiago
 */
public class TestEncoding {
    
    @Test
    public void testEncode() {
        Encoder encoder = new Encoding();
                
        assertEquals(encoder.encode("http://dinamica-sambatech.s3.amazonaws.com/sample.dv", "", "MP4 H264 3G 240p 350k"), 0);
    }
    
    @Test
    public void testEncodeFailSourceURL() {
        Encoder encoder = new Encoding();
                
        assertEquals(encoder.encode("", "", "MP4 H264 3G 240p 350k"), 1);
    }
    
    @Test
    public void testEncodeFailVideoFormat() {
        Encoder encoder = new Encoding();
                
        assertEquals(encoder.encode("http://dinamica-sambatech.s3.amazonaws.com/sample.dv", "", ""), 1);
    }
    
    @Test
    public void testRefreshStatus() {
        Encoder encoder = new Encoding();
                
        encoder.encode("http://dinamica-sambatech.s3.amazonaws.com/sample.dv", "", "MP4 H264 3G 240p 350k");
        assertTrue(encoder.refreshStatus());
    }
}
