package persistence;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * XML beolvasására szolgáló osztály.
 */
public class ReadInXML {
	
	/**
	 * XML fájlban szereplő egységekent String listaként visszatéríti.
	 * 
	 * @param xmlFile bemeneti fájl
	 * @return output egységek az XML fájlból
	 */
	public static List<String> readXML(File xmlFile){
		List<String> output = new ArrayList<String>();
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
						
			Document doc = builder.parse(xmlFile);
						
			NodeList nodeLista = doc.getElementsByTagName("profile");
			
			for(int i=0;i<nodeLista.getLength();i++){
				Node node = nodeLista.item(i);
				
				if(node.getNodeType() == Node.ELEMENT_NODE){
					Element size = (Element) node;
					
					output.add(size.getElementsByTagName("unit1")
							.item(0).getTextContent());
					output.add(size.getElementsByTagName("unit2")
							.item(0).getTextContent());
					output.add(size.getElementsByTagName("unit3")
							.item(0).getTextContent());
					output.add(size.getElementsByTagName("unit4")
							.item(0).getTextContent());
					output.add(size.getElementsByTagName("unit5")
							.item(0).getTextContent());
					output.add(size.getElementsByTagName("unit6")
							.item(0).getTextContent());
				}
				
			}
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
	}
	
	/**
	 * JAR-ban lévő XML fájlban szereplő egységekent String listaként visszatéríti.
	 * 
	 * @param xmlFile JAR-ból kinyert xml dokumentum
	 * @return output egységek az XML fájlból
	 */
	public static List<String> readXML(InputStream xmlFile){
		List<String> output = new ArrayList<String>();
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
						
			Document doc = builder.parse(xmlFile);
						
			NodeList nodeLista = doc.getElementsByTagName("profile");
			
			for(int i=0;i<nodeLista.getLength();i++){
				Node node = nodeLista.item(i);
				
				if(node.getNodeType() == Node.ELEMENT_NODE){
					Element size = (Element) node;
					
					output.add(size.getElementsByTagName("unit1")
							.item(0).getTextContent());
					output.add(size.getElementsByTagName("unit2")
							.item(0).getTextContent());
					output.add(size.getElementsByTagName("unit3")
							.item(0).getTextContent());
					output.add(size.getElementsByTagName("unit4")
							.item(0).getTextContent());
					output.add(size.getElementsByTagName("unit5")
							.item(0).getTextContent());
					output.add(size.getElementsByTagName("unit6")
							.item(0).getTextContent());
				}
				
			}
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
	}
}
