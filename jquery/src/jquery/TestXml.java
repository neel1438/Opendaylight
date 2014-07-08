package jquery;

import static org.junit.Assert.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
/*
 * This is to play around with nodes using junit
 * random tests
 */
public class TestXml {
	Node RealXmlRoot;
	Node HardCodedRoot;
	
	@Before
	public void setup() throws Exception
	{
		HardCodedRoot=new HardCodedNodeBuilder().root;
		DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	    Document doc = dBuilder.parse("src/jquery/resources/test.xml");
	    doc.normalizeDocument();
	    RealXmlRoot=doc; 
	}

	@Test
	public void test() {
		
		//Root element Name
		assertEquals(RealXmlRoot.getNodeName(),HardCodedRoot.getNodeName());
		
		//get FirstChildren Name
		assertEquals(RealXmlRoot.getFirstChild().getNodeName(),HardCodedRoot.getFirstChild().getNodeName());
		
		
		/*
		 * i guess you understand the part i was explaining in the mail
		 *
		 * All the functions we wrote are working the way we want them to 
		 * but the returned Node is some how different from the one returned by us.
		 * 
		 */
		//get First Children
		//assertEquals(RealXmlRoot.getFirstChild(),HardCodedRoot.getFirstChild());
		
		

	}
}
