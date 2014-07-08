package jquery;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class NodeBuilderElement extends ThrowExceptionElement{
	
	boolean hasChildren;
	String NodeName;
	Node Parent;
	Node NextSibling;
	Node FirstChild;
	Node LastChild;
	String TextContent;

	
	public NodeBuilderElement(String Name,String Textcontent,Node parent,Node nextsibling,Boolean haschildren,Node getFirst,Node getLast)
	{
		NodeName=Name;
		Parent=parent;
		NextSibling=nextsibling;
		hasChildren=haschildren;
		FirstChild=getFirst;
		LastChild=getLast;
		TextContent=Textcontent;
		
	}

	public short getNodeType() {
		return Node.ELEMENT_NODE;

	}

	public String getNamespaceURI() {
		return null;

	}
	public String getNodeValue()
	{
		return null;
	}

	public boolean hasChildNodes() {
		return hasChildren;
	}
	public boolean hasAttributes()
	{
		return false;
	}
	public NamedNodeMap getAttributes() {
		return null;
	}

	public String getNodeName() {

		return NodeName;
	}

	public Node getParentNode() {
		return Parent;
	}

	public String getLocalName() {
		return NodeName;
	}

	public Node getNextSibling() {
		return NextSibling;
	}
	public Node getFirstChild() {

		return FirstChild;
	}

	public Node getLastChild() {

		return LastChild;
	}
	public String getTextContent()
	{
		return TextContent;
		
	}
}
