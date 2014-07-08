package jquery;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class NodeBuilderText extends ThrowExceptionText{
	short NodeType;
	String NamespaceURI;
	boolean hasChildren;
	boolean hasAttributes;
	NamedNodeMap Attributes;
	String NodeName;
	Node Parent;
	String LocalName;
	Node NextSibling;
	Node FirstChild;
	Node LastChild;
	String NodeValue;
	String TextContent;

	
	public NodeBuilderText(String Name,short Type,String Nodevalue,String Textcontent,boolean hasattributes,NamedNodeMap attributes,String Local,String NameSpaceURI,Node parent,Node nextsibling,Boolean haschildren,Node getFirst,Node getLast)
	{
		NodeName=Name;
		NodeType=Type;
		NamespaceURI=NameSpaceURI;
		NodeValue=Nodevalue;
		hasAttributes=hasattributes;
		Attributes=attributes;
		LocalName=Local;
		Parent=parent;
		NextSibling=nextsibling;
		hasChildren=haschildren;
		FirstChild=getFirst;
		LastChild=getLast;
		TextContent=Textcontent;
		
	}

	public short getNodeType() {
		return NodeType;

	}
	public String getNodeValue()
	{
		return NodeValue;
	}

	public String getNamespaceURI() {
		return NamespaceURI;

	}

	public boolean hasChildNodes() {
		return hasChildren;
	}

	public NamedNodeMap getAttributes() {
		return Attributes;
	}

	public String getNodeName() {

		return NodeName;
	}

	public Node getParentNode() {
		return Parent;
	}

	public String getLocalName() {
		return LocalName;
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
