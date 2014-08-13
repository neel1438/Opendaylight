package org.opendaylight.controller.sample.toaster.it;




import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.opendaylight.yangtools.yang.data.api.YangInstanceIdentifier.PathArgument;
import org.opendaylight.yangtools.yang.data.api.schema.DataContainerChild;
import org.opendaylight.yangtools.yang.data.api.schema.DataContainerNode;
import org.opendaylight.yangtools.yang.data.api.schema.NormalizedNode;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class NodeBuilderElement extends NewElement{

	boolean hasChildren;
	NormalizedNode<?,?> node;
	NodeBuilderElement parent;
	NodeBuilderElement firstChild;
	NodeBuilderElement lastChild;
	NodeBuilderElement[] Children;


	public NodeBuilderElement(NormalizedNode<?,?> node1,NodeBuilderElement parent1)
	{
		node=node1;
		parent=parent1;
		if(node1.getValue() instanceof Iterable)
		{
			hasChildren=true;
			List<DataContainerChild<? extends PathArgument, ?>>  children = new ArrayList<DataContainerChild<? extends PathArgument, ?>>();
			DataContainerNode<?> dataContainerNode=(DataContainerNode<?>) node1;
		  	Iterator<DataContainerChild<? extends PathArgument, ?>> iterator = dataContainerNode.getValue().iterator();
		  	while (iterator.hasNext()){
		     children.add(iterator.next());
		    }
		  	firstChild=new NodeBuilderElement(children.get(0),this);
		  	lastChild=new NodeBuilderElement(children.get(children.size()-1),this);
		  	for(int i=0;i<children.size();i++)
		  	{
//set siblings using this loop
		  	}

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

		return firstChild;
	}

	@Override
	public Node getLastChild() {

		return lastChild;
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