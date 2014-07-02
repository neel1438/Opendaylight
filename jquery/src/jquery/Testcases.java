package jquery;

import static org.junit.Assert.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
//import org.xml.sax.SAXException;

public class Testcases {
	Node rootTestNode;
	XPath xpath;
	
	@Before
	public void setup() throws Exception
	{
		xpath=XPathFactory.newInstance().newXPath();
		//setupRealXml();
		 setupHardcodedNode();
        System.out.println("Initialized!");
	}
	public void  setupRealXml() throws Exception
	{
		DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = dBuilder.parse("src/jquery/resources/test.xml");
        doc.normalizeDocument();
        rootTestNode=((NodeList) xpath.evaluate("//a",doc,XPathConstants.NODESET)).item(0);
	 
	}
	public void setupHardcodedNode()
	{
		//
		rootTestNode=HardCodedNodeBuilder.root;
	}
	
	@Test
	public void selfTest() throws XPathExpressionException
	{
		System.out.println("Testing1 ..!!");
		NodeList nodelist=(NodeList) xpath.evaluate(".",rootTestNode,XPathConstants.NODESET);
		assertNotNull(nodelist);
		assertEquals(1,nodelist.getLength());
		Node root=nodelist.item(0);
		assertNotNull(root);
		assertEquals("a",root.getNodeName());
		
	}
	/*
	 * 
	 */
	public void evaluate(String xpathexp,String expectedValue) throws XPathExpressionException
	{
		String node=(String) xpath.evaluate(xpathexp,rootTestNode,XPathConstants.STRING);
		assertEquals(expectedValue,node);
		
		
	}
	
	
	@Test
	public void test() throws XPathExpressionException
	{
		
		
		//select node c1 whose text values is "Some Value"
		evaluate("//c1/text()","Some Value");
		
		// get the nodes whose text values is "Some Value" (Comparing names)
		evaluate("name(//*[(text()='Some Value')])","c1");
		
		//count all the nodes whose text contains the value "Some"
		evaluate("count(//*[contains(text(),'Some')])","2"); 
		
		//verify that c1 is the first node whose text contains the value "Some"
		evaluate("name(//*[contains(text(),'Some')][1])","c1"); 
		
		//verify that c2 is the second node whose text contains the value "Some"
		evaluate("name(//*[contains(text(),'Some')][2])","c2"); 
		
		//checking the same thing as above
		evaluate("name(//*[contains(text(),'Some')][2])='c2'","true"); 
				
	}
	
	/*
	 * 		"/", "/*" and "/a/b/c1"
	 * 
	 * 		select grandparent (ancestors) of text node 
	 * 		
	 * 
	 */
	
	public void test1() throws XPathExpressionException
	{
		System.out.println("Testing3 ..!!");
		Node node=(Node) xpath.evaluate("//c1/text()",rootTestNode,XPathConstants.NODE);
		assertNotNull(node);
		assertEquals(false,node.hasChildNodes());
		assertEquals("Some Value",node.getNodeValue());
	}
	
	
	
}
