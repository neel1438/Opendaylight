package jquery;
 
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
//import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import jquery.MyDocument;
 
/*
 * Parsing the xml doc with Default DOM Parser
 */
public class DOMParse {
	static File xmlfile;
	static MyDocument parsedDocument;
	
	
	/*
	 * constructor
	 * file - path to file
	 * xml file 
	 * Parsed XMl File
	 * Normalize the xml file to avoid extra TextNodes
	 * 
	 */
	DOMParse(String file) throws ParserConfigurationException, SAXException, IOException
	{
		xmlfile=new File(file);
		DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	 	Document document = dBuilder.parse(xmlfile);
		document.normalizeDocument();
		parsedDocument=new MyDocument(document);
	}
	
	/*
	 * root- Root Node whose children to search
	 * type - type of node required eg. "flow" ,"table" ....
	 * id - id of type node
	 * 
	 * returns list of nodes whose tag is type and id is id 
	 *  
	 *  First isolate the type nodes into List<Node> 
	 *  then iterate through the List andcheck for ID (searchType -function)
	 */
	
	public static List<Node> searchTree(Node root,String type,String id)
	{
		List<Node> result= new LinkedList<Node>();
		NodeList nodes=root.getChildNodes();
		
		for(int i=0;i<nodes.getLength();i++)
		{	
			
			Node node=nodes.item(i);
	
			if(node.getNodeType()== Node.ELEMENT_NODE){
				if(node.getNodeName()==type)
				{
					result.add(node);
				}
				else
				{
					result.addAll(searchTree(node,type,id));
					
				}
			}
		}
		return searchType(result,id);
	}
	/*
	 * List<Node> types list of type nodes
	 * id -Id to search for
	 * 
	 * returns the types whose id is id;
	 */
	private static List<Node> searchType(List<Node> types,String id)
	{
		List<Node> result=new LinkedList<Node>();
		for(Node type:types )
		{
			
			NodeList nodes=type.getChildNodes();
			for(int i=0;i<nodes.getLength();i++)
			{
				Node node=nodes.item(i);
				if(node.getNodeType()== Node.ELEMENT_NODE)
				{
					if(node.getNodeName()== "id")
					{
						if(node.getTextContent().equals(id))
						{
							result.add(type);
						}
						
					}
				}
			}
		}
		return result;
	}
	
	public static void main(String[] args) 
	{
 		try 
 		{
 			new DOMParse("/home/neel/odl/a.xml");
 			System.out.println(searchTree(parsedDocument.getDocumentElement(),"flow","70998994"));
 		}
 		catch (Exception e)
 		{
 			System.out.println(e.getMessage());
 		}
 
	}

 }
 