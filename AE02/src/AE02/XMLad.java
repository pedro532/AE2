package AE02;

import java.io.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

public class XMLad {
	public static String [] xml() {
		// TODO Auto-generated method stub
		String [] array = new String[3];
		String URL = "";
		String user = "";
		 String password = "";
		 try {
	            File archivoXML = new File("admin.xml");

	            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder builder = factory.newDocumentBuilder();
	            Document doc = builder.parse(archivoXML);

	            
	            doc.getDocumentElement().normalize();


	            NodeList nodeList = doc.getElementsByTagName("connection");

	            for (int i = 0; i < nodeList.getLength(); i++) {
	                Node node = nodeList.item(i);

	                if (node.getNodeType() == Node.ELEMENT_NODE) {
	                    Element element = (Element) node;
	                    URL = element.getElementsByTagName("url").item(0).getTextContent();
	                    user = element.getElementsByTagName("user").item(0).getTextContent();
	                    password = element.getElementsByTagName("password").item(0).getTextContent();
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		 array [0] = URL;
		 array [1] = user;
		 array [2] = password;
		 return array;
	}

}
