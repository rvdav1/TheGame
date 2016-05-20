package persistence;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import gui.*;

public class TestPersistence {
	List<String> units = Arrays.asList("Peasant","Peasant","Peasant","Peasant","Peasant","Peasant");
	WriteInXML wrt = new WriteInXML();
 	
	@Test
	public void testRead(){
		Main.location = wrt.getClass().getProtectionDomain().getCodeSource().getLocation().toString();
		assertEquals(true,units.equals(ReadInXML.readXML(wrt.getClass().getResourceAsStream("/profiles/.xml"))));
	}

}
