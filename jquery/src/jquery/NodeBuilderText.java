package jquery;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class NodeBuilderText extends ThrowExceptionText{
	Node Parent;
	Node NextSibling;
	String NodeValue;
	

	
	public NodeBuilderText(String Nodevalue,Node parent,Node nextsibling)
	{
		NodeValue=Nodevalue;
		Parent=parent;
		NextSibling=nextsibling;
	}

	public short getNodeType() {
		return Node.TEXT_NODE;

	}
	public boolean hasAttributes()
	{
		return false;
	}
	public String getNodeValue()
	{
		return NodeValue;
	}

	public String getNamespaceURI() {
		return null;

	}

	public boolean hasChildNodes() {
		return false;
	}

	public NamedNodeMap getAttributes() {
		return null;
	}

	public String getNodeName() {

		return "#text";
	}

	public Node getParentNode() {
		return Parent;
	}

	public String getLocalName() {
		return "#text";
	}

	public Node getNextSibling() {
		return NextSibling;
	}
	public Node getFirstChild() {

		return null;
	}

	public Node getLastChild() {

		return null;
	}
	public String getTextContent()
	{
		return NodeValue;
		
	}
}
