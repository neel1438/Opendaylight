package org.opendaylight.controller.sample.toaster.it;




import java.util.Iterator;

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

	NodeBuilderElement lastChild;
	NodeBuilderElement[] Children;

	boolean isFirstInit = false;
	NodeBuilderElement firstChild;

	boolean isFirstSiblingInit = false;
	NodeBuilderElement nextSibling;
	Iterator<DataContainerChild<? extends PathArgument, ?>> iterator;


	public NodeBuilderElement(NormalizedNode<?,?> node1,NodeBuilderElement parent1)
	{
		node=node1;
		parent=parent1;
	/*	if(node1.getValue() instanceof Iterable)
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
		}*/

	}
	  NodeBuilderElement( NormalizedNode<?,?> nodeDelegate, NodeBuilderElement parentNode, Iterator<DataContainerChild<? extends PathArgument, ?>> nextSib ){
	        //store nodeDelegate to class variable
	        //store parent to class variable.
	        //store the iterator to the next sibling
		  node=nodeDelegate;
		  parent=parentNode;
		  iterator=nextSib;

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
		return (node.getValue() instanceof Iterable);
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
		 if( !isFirstSiblingInit ){
	            //if nextSib iterator is not null, then call next() on it to get next sibling.
	            //construct a NodeBuilderElement, passing in the new node, the same parent, and the same iterator
	            //cache the node builder element to first child
			 if(iterator.hasNext()){
			 nextSibling=new NodeBuilderElement(iterator.next(),this,iterator);
			 }
			 isFirstSiblingInit=true;

	        }
		 return nextSibling;
	}
	@Override
	public Node getFirstChild() {
		  if( !isFirstInit ){

	            //get iterator on children, get first child from iterator
			  DataContainerNode<?> dataContainerNode=(DataContainerNode<?>) node;
			  Iterator<DataContainerChild<? extends PathArgument, ?>> iterator = dataContainerNode.getValue().iterator();
			  firstChild=new NodeBuilderElement(iterator.next(),this,iterator);


	            //construct a NodeBuilderElement, passing in the new node, the same parent, and the iterator
	            //cache the node builder element to first child
			  isFirstInit=true;
	        }
	        return firstChild;
	}

	@Override
	public String getTextContent()
	{
		if(this.hasChildNodes())
		{
			return null;
		}
		return node.getValue().toString();

	}
}