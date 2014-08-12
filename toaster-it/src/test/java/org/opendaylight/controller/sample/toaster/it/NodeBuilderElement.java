package org.opendaylight.controller.sample.toaster.it;



import java.util.Iterator;

import org.opendaylight.yangtools.yang.data.api.YangInstanceIdentifier.PathArgument;
import org.opendaylight.yangtools.yang.data.api.schema.ContainerNode;
import org.opendaylight.yangtools.yang.data.api.schema.DataContainerChild;
import org.opendaylight.yangtools.yang.data.api.schema.DataContainerNode;
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


	public NodeBuilderElement(DataContainerNode<?> node)
	{
		nodeName=node.getIdentifier().toString();
		if(node.getValue() instanceof Iterable)
		{
			textContent=null;
			hasChildren=true;
            Iterator<DataContainerChild<? extends PathArgument, ?>> iterator = node.getValue().iterator();
			firstChild=new NodeBuilderElement((ContainerNode) iterator.next());
		}
		else
		{
			textContent=node.getValue().toString();
			hasChildren=false;
			firstChild=null;
			lastChild=null;
		}

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