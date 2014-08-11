package org.opendaylight.controller.sample.toaster.it;



import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class NodeBuilderElement extends NewElement{

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
		textContent=Textcontent;
		parent=parentNode;
		nextSibling=nextsibling;
		hasChildren=false;

	}

	@Override
	public short getNodeType() {
		return Node.ELEMENT_NODE;

	}

	@Override
	public String getNamespaceURI() {
		return null;

	}
	@Override
	public String getNodeValue()
	{
		return null;
	}

	@Override
	public boolean hasChildNodes() {
		return hasChildren;
	}
	@Override
	public boolean hasAttributes()
	{
		return false;
	}
	@Override
	public NamedNodeMap getAttributes() {
		return null;
	}

	@Override
	public String getNodeName() {

		return nodeName;
	}

	@Override
	public Node getParentNode() {
		return parent;
	}

	@Override
	public String getLocalName() {
		return nodeName;
	}

	@Override
	public Node getNextSibling() {
		return nextSibling;
	}
	@Override
	public Node getFirstChild() {

		return firstChild;
	}

	@Override
	public Node getLastChild() {

		return lastChild;
	}
	@Override
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