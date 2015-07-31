package encoder;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ResponseParser { //faz o parsing das respostas XML do encoding.com

    private Document response;

    public ResponseParser(InputStream is) {
        parseResponse(is);
    }

    private void parseResponse(InputStream is) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;

        try {
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        try {
            response = dBuilder.parse(is);
            is.close();
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public String getValueByElementName(String elementName) { //retorna valores de elementos "folha" do xml
        NodeList nList;
        Node node;
        String content;

        nList = response.getDocumentElement().getChildNodes();

        for (int i = 0; i < nList.getLength(); i++) {
            node = nList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                content = node.getLastChild().getTextContent().trim();

                if (node.getNodeName().equals(elementName)) {
                    return content;
                }
            }
        }

        return null;
    }
}
