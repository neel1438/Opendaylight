package jquery;



import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


public class ConstructXml  {
	
	public static Node getDoc() throws ParserConfigurationException {
		 
		
	 
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	 
			// root element
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("a");
			doc.appendChild(rootElement);
	 
			//Element B
			Element b = doc.createElement("b");
			rootElement.appendChild(b);
	 
				 
			 
			// C1 element
			Element c1 = doc.createElement("c1");
			c1.appendChild(doc.createTextNode("Some Value"));
			b.appendChild(c1);
	 
			// C2 element
			Element c2 = doc.createElement("c2");
			c2.appendChild(doc.createTextNode("Some Other Value"));
			b.appendChild(c2);
			
			return doc; 
		}

}
