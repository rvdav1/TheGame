package persistence;

import java.io.File;
import java.nio.file.FileSystems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import gui.Main;

/**
 * XML írásáért felelős osztály.
 */
public class WriteInXML {
	/**
	 * Létrehozza a szükséges mappaszerkezetet a futáshoz,ha nincs még kész.
	 */
	public static void setDep(){
		List<String> depList = Arrays.asList("","Peasant","Peasant","Peasant","Peasant","Peasant","Peasant");
		new File(WriteInXML.getFolder()).mkdirs();
		File depXML = new File(WriteInXML.getFolder() + ".xml");
		if (!depXML.exists())
			WriteInXML.writeXML(depList);
	}
	
	/**
	 * A futtatott állomány elérési újtát adja vissza.
	 * 
	 * @return temp a futtatott állomány elérési útja
	 */
	public static String getRunLoc(){
		String temp;
		String jarName = Main.getName();
		temp = Main.location;
		temp = temp.substring(5, temp.length());
		temp = temp.substring(0, temp.length() - jarName.length() - 1);
		return temp;
	}
	
	/**
	 * Az output adatok elérési útvonalát adja vissza .
	 * 
	 * @return folder output adatok elérézi útvonala
	 */
	public static String getFolder(){
		String result;
		String folder;
		String separator = FileSystems.getDefault().getSeparator();
		String unixSep = "/";
		String winSep = "\\";
		result = WriteInXML.getRunLoc();
		folder = result + separator + "classes" + separator + "profiles" + separator;
		folder = folder.replaceAll(unixSep, separator + separator);
		folder = folder.replaceAll(winSep + winSep, separator + separator);
		if (folder.startsWith(winSep))
			folder = folder.substring(1, folder.length());
		if (folder.startsWith(unixSep))
			while(folder.contains(unixSep + unixSep))
				folder = folder.replaceAll(unixSep + unixSep, separator );
		return folder;
	}
	
	/**
	 * Megvizsgálja,hogy létezik e az az állomány,amivel dolgozni szeretnénk.
	 * 
	 * @param fileName az állomány neve
	 * @return boolean létezik e az adott állomány
	 */
	public static boolean checkDir(String fileName){
		String loc;
		loc = WriteInXML.getFolder() + fileName + ".xml";
		File theFile = new File(loc);
		
		if (theFile.exists())
			return true;
		else
			return false;
	}
	
	/**
	 * XML írása az adott adatokkal.
	 * 
	 * @param names a fájl neve,illetve a fájlba írandó egységek
	 */
	public static void writeXML(List<String> names){
		String s,loc;
		loc = WriteInXML.getFolder() + names.get(0) + ".xml";
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			Document doc = builder.newDocument();
			
			Element gyoker = doc.createElement("player");
			doc.appendChild(gyoker);
			
			Element profile = doc.createElement("profile");
			List<Element> units = new ArrayList<Element>();
			
			for (int i = 0; i < 6; i++){
				s = "unit" + (i + 1);
				units.add(doc.createElement(s));
				units.get(i).appendChild(doc.createTextNode(names.get(i + 1)));
				profile.appendChild(units.get(i));
			}
			
			gyoker.appendChild(profile);
			
			TransformerFactory tFact = TransformerFactory.newInstance();
			Transformer trans = tFact.newTransformer();

			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(loc));
			
			trans.transform(source, result);

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
