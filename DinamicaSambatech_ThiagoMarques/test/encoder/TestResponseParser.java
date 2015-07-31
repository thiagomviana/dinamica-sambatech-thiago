package encoder;

import java.io.ByteArrayInputStream;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestResponseParser {

    private ResponseParser rp;
    private Encoding enc;
    private String request;

    public TestResponseParser() {
        enc = new Encoding();

        request += "<?xml version='1.0'?>" + '\n';
        request += "<query>" + '\n';
        request += "    <userid>test</userid>" + '\n';
        request += "    <userkey>test</userkey>" + '\n';
        request += "    <action>getStatus</action>" + '\n';
        request += "    <mediaid>1</mediaid>" + '\n';
        request += "</query>";

    }

    @Test
    public void testConstructor() {
        ByteArrayInputStream sbip;

        sbip = new ByteArrayInputStream(request.getBytes());
        rp = new ResponseParser(sbip);

        assertNotNull(rp);
    }

    @Test
    public void testGetValueByElementNameFound() {
        ByteArrayInputStream sbip;

        sbip = new ByteArrayInputStream(request.getBytes());
        rp = new ResponseParser(sbip);

        assertEquals(rp.getValueByElementName("action"), "getStatus");
    }

    @Test
    public void testGetValueByElementNameNotFound() {
        ByteArrayInputStream sbip;

        sbip = new ByteArrayInputStream(request.getBytes());
        rp = new ResponseParser(sbip);

        assertNull(rp.getValueByElementName("test"));
    }
}
