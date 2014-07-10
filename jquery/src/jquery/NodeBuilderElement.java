package jquery;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class NodeBuilderElement extends ThrowExceptionElement{
	
	boolean hasChildren;
	String nodeName;
	Node parent;
	Node nextSibling;
	Node firstChild;
	Node lastChild;
	String textContent;
	Node[] children;

	
	public NodeBuilderElement(String Name,String Textcontent,Node parentNode,Node nextsibling)
	{
		nodeName=Name;
		parent=parentNode;
		nextSibling=nextsibling;
		textContent=Textcontent;
		
		
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

		return nodeName;
	}

	public Node getParentNode() {
		return parent;
	}

	public String getLocalName() {
		return nodeName;
	}

	public Node getNextSibling() {
		return nextSibling;
	}
	public Node getFirstChild() {

		return firstChild;
	}

	public Node getLastChild() {

		return lastChild;
	}
	public String getTextContent()
	{
		return textContent;
		
	}
	public void setChildren(Node... childNodes)
	{
		hasChildren=true;
		firstChild=childNodes[0];
		lastChild=childNodes[childNodes.length -1];
		children=childNodes;
	}
		
}
