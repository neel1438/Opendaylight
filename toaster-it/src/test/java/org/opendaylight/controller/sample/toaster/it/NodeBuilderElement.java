package org.opendaylight.controller.sample.toaster.it;




import org.opendaylight.yangtools.yang.data.api.schema.DataContainerNode;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class NodeBuilderElement extends NewElement{

	boolean hasChildren;
	DataContainerNode<?> node;
	NodeBuilderElement parent;

	public NodeBuilderElement(DataContainerNode<?> node1,NodeBuilderElement parent1)
	{
		node=node1;
		parent=parent1;
		if(node1.getValue() instanceof Iterable)
		{
			hasChildren=true;

		}
		else
		{
			hasChildren=false;
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

		return node.getIdentifier().toString();
	}

	@Override
	public Node getParentNode() {
		return parent;
	}

	@Override
	public String getLocalName() {
		return this.getNodeName();
	}

	@Override
	public Node getNextSibling() {
		return null;
	}
	@Override
	public Node getFirstChild() {

		return null;
	}

	@Override
	public Node getLastChild() {

		return null;
	}
	@Override
	public String getTextContent()
	{
		if(this.hasChildren)
		{
			return null;
		}
		return node.getValue().toString();

	}


}