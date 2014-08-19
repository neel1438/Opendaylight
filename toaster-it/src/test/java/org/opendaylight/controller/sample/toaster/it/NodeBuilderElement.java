/*
 * Author : Neel Bommisetty
 * Email : neel250294@gmail.com
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.controller.sample.toaster.it;

import java.util.Iterator;

import org.opendaylight.yangtools.yang.data.api.YangInstanceIdentifier.PathArgument;
import org.opendaylight.yangtools.yang.data.api.schema.DataContainerChild;
import org.opendaylight.yangtools.yang.data.api.schema.DataContainerNode;
import org.opendaylight.yangtools.yang.data.api.schema.NormalizedNode;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.google.common.collect.Iterators;

public class NodeBuilderElement extends NewElement {

    boolean hasChildren;
    NormalizedNode<?, ?> node;
    NodeBuilderElement parent;

    NodeBuilderElement lastChild;
    NodeBuilderElement[] Children;

    boolean isFirstInit = false;
    Node firstChild;

    boolean isFirstSiblingInit = false;
    NodeBuilderElement nextSibling;
    Iterator<DataContainerChild<? extends PathArgument, ?>> iterator;

    public NodeBuilderElement(NormalizedNode<?, ?> node1,
            NodeBuilderElement parent1) {
        node = node1;
        parent = parent1;

    }

    NodeBuilderElement(NormalizedNode<?, ?> nodeDelegate,
            NodeBuilderElement parentNode,
            Iterator<DataContainerChild<? extends PathArgument, ?>> nextSib) {
        // store nodeDelegate to class variable
        // store parent to class variable.
        // store the iterator to the next sibling
        node = nodeDelegate;
        parent = parentNode;
        iterator = nextSib;

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
    public String getNodeValue() {
        return null;
    }

    @Override
    public boolean hasChildNodes() {
        return (node.getValue() instanceof Iterable);
    }

    @Override
    public boolean hasAttributes() {
        return false;
    }

    @Override
    public NamedNodeMap getAttributes() {
        return new NamedNodeMapImpl();
    }

    @Override
    public String getNodeName() {

        return node.getNodeType().getLocalName();
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
        if (!isFirstSiblingInit) {
            // if nextSib iterator is not null, then call next() on it to get
            // next sibling.
            // construct a NodeBuilderElement, passing in the new node, the same
            // parent, and the same iterator
            // cache the node builder element to first child

            if (iterator.hasNext()) {
                nextSibling = new NodeBuilderElement(iterator.next(), this,
                        iterator);
            }
            isFirstSiblingInit = true;

        }
        return nextSibling;
    }

    @Override
    public Node getFirstChild() {
        if (!isFirstInit) {

            if(node instanceof org.opendaylight.yangtools.yang.data.api.schema.LeafNode)
            {
                firstChild = new NodeBuilderText(node.getValue().toString(),
                        this, null);
            }
            else if(node instanceof org.opendaylight.yangtools.yang.data.api.schema.UnkeyedListNode )
            {
                // handle list here ..
                @SuppressWarnings("unchecked")
                Iterable<DataContainerChild<? extends PathArgument, ?>> list=((Iterable<DataContainerChild<? extends PathArgument, ?>>) node.getValue());

                Iterator<DataContainerChild<? extends PathArgument, ?>> listiter = list.iterator();

                    DataContainerNode<?> dataContainerNode = (DataContainerNode<?>) listiter.next();

                    Iterator<DataContainerChild<? extends PathArgument, ?>> iterator = dataContainerNode
                            .getValue().iterator();
                    firstChild = new NodeBuilderElement(iterator.next(), this,
                            iterator);
                    this.iterator=Iterators.concat(listiter,this.iterator);



            }
            else if( node instanceof org.opendaylight.yangtools.yang.data.api.schema.LeafSetNode )
            {
                // handle list here ..
                @SuppressWarnings("unchecked")
                Iterable<NormalizedNode<?,?>> list=((Iterable<NormalizedNode<?,?>>)node.getValue());

                Iterator<NormalizedNode<?,?>>listiter = list.iterator();



                    firstChild = new NodeBuilderText(listiter.next().getValue().toString(),this, null);

                    //TypeConflict here
                    //  this.iterator=Iterators.concat(listiter,this.iterator);

            }


            // get iterator on children, get first child from iterator
            else {
                DataContainerNode<?> dataContainerNode = (DataContainerNode<?>) node;
                Iterator<DataContainerChild<? extends PathArgument, ?>> iterator = dataContainerNode
                        .getValue().iterator();
                firstChild = new NodeBuilderElement(iterator.next(), this,
                        iterator);
            }


            // construct a NodeBuilderElement, passing in the new node, the same
            // parent, and the iterator
            // cache the node builder element to first child
            isFirstInit = true;
        }
        return firstChild;
    }

    @Override
    public String getTextContent() {
        if (this.hasChildNodes()) {
            return "Text Content";
        }
        return node.getValue().toString();

    }
}