package org.opendaylight.controller.sample.toaster.it;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class NodeBuilderText extends ThrowExceptionText{
	Node parent;
	Node nextSibling;
	String nodeValue;



	public NodeBuilderText(String Nodevalue,Element parentNode,Node nextsibling)
	{
		nodeValue=Nodevalue;
		parent=parentNode;
		nextSibling=nextsibling;
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
		return nodeValue;
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
		return parent;
	}

	public String getLocalName() {
		return "#text";
	}

	public Node getNextSibling() {
		return nextSibling;
	}
	public Node getFirstChild() {

		return null;
	}

	public Node getLastChild() {

		return null;
	}
	public String getTextContent()
	{
		return nodeValue;

	}
}
