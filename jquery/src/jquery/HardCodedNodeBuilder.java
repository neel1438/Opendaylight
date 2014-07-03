package jquery;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class HardCodedNodeBuilder {
	final static Node textc1 = new ThrowExceptionNode() {

		public short getNodeType() {
			return Node.TEXT_NODE;

		}

		public String getNamespaceURI() {
			return "a/b/c1";

		}

		public boolean hasChildNodes() {
			return false;
		}

		public NamedNodeMap getAttributes() {
			return null;
		}

		public String getNodeName() {

			return "#textnode";
		}

		public Node getParentNode() {
			return c1;
		}

		public String getLocalName() {
			return "c1/text()";
		}

		public Node getNextSibling() {
			return null;

		}

		public String getNodeValue() {
			return "Some Value";
		}
	};
	final static Node textc2 = new ThrowExceptionNode() {

		public short getNodeType() {
			return Node.TEXT_NODE;

		}

		public String getNamespaceURI() {
			return "a/b/c2";

		}

		public boolean hasChildNodes() {
			return false;
		}

		public NamedNodeMap getAttributes() {
			return null;
		}

		public String getNodeName() {

			return "#textnode";
		}

		public Node getParentNode() {
			return c2;
		}

		public String getLocalName() {
			return "c2/text()";
		}

		public Node getNextSibling() {
			return null;

		}

		public String getNodeValue() {
			return "Some Other Value";
		}
	};
	final static Node texta = new ThrowExceptionNode() {

		public short getNodeType() {
			return Node.TEXT_NODE;

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

			return "#textnode";
		}

		public Node getParentNode() {
			return a;
		}

		public String getLocalName() {
			return "a/text()";
		}

		public Node getNextSibling() {
			return b;

		}

		public String getNodeValue() {
			return "/n/n";
		}
	};
	final static Node textb = new ThrowExceptionNode() {

		public short getNodeType() {
			return Node.TEXT_NODE;

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

			return "#textnode";
		}

		public Node getParentNode() {
			return b;
		}

		public String getLocalName() {
			return "a/b/text()";
		}

		public Node getNextSibling() {
			return c1;

		}

		public String getNodeValue() {
			return "/n/n";
		}
	};
	final static Node c1 = new ThrowExceptionNode() {

		public short getNodeType() {
			return Node.ELEMENT_NODE;

		}

		public String getNamespaceURI() {
			return "a/b/c1";

		}

		public boolean hasChildNodes() {
			return true;
		}

		public NamedNodeMap getAttributes() {
			return null;
		}

		public String getNodeName() {

			return "c1";
		}

		public Node getParentNode() {
			return b;
		}

		public String getLocalName() {
			return "c1";
		}

		public Node getNextSibling() {
			return c2;

		}

		public ThrowExceptionNode getFirstChild() {

			return (ThrowExceptionNode) textc1;
		}

		public ThrowExceptionNode getLastChild() {
			return (ThrowExceptionNode) textc1;
		}
	};

	final static Node c2 = new ThrowExceptionNode() {

		public short getNodeType() {
			return Node.ELEMENT_NODE;

		}

		public NamedNodeMap getAttributes() {
			return null;
		}

		public String getNamespaceURI() {
			return "a/b/c2";

		}

		public boolean hasChildNodes() {
			return false;
		}

		public String getLocalName() {
			return "c2";
		}

		public String getNodeName() {

			return "c2";
		}

		public Node getParentNode() {
			return b;
		}

		public Node getNextSibling() {
			return null;

		}

		public ThrowExceptionNode getFirstChild() {

			return (ThrowExceptionNode) textc2;
		}

		public ThrowExceptionNode getLastChild() {
			return (ThrowExceptionNode) textc2;
		}
	};

	final static Node b = new ThrowExceptionNode() {
		// Node nextsibling;

		public String getNodeName() {
			return "b";
		}

		public short getNodeType() {
			return Node.ELEMENT_NODE;

		}

		public NamedNodeMap getAttributes() {
			return null;
		}

		public String getNamespaceURI() {
			return null;

		}
		public String getNodeValue()
		{
			return null;
		}
		public boolean hasChildNodes() {
			return true;
		}

		public String getLocalName() {
			return "b";
		}

		// Node nextSibling = null;
		public Node getParentNode() {
			return a;
		}

		public Node getFirstChild() {

			return textb;
		}

		public Node getLastChild() {

			return c2;
		}

		public Node getNextSibling() {
			// nextSibling=null;
			return null;
		}

	};

	static Node a = new ThrowExceptionNode() {

		public String getNodeName() {

			return "a";
		}

		public String getLocalName() {
			return null;
		}

		public NamedNodeMap getAttributes() {
			return null;
		}

		public boolean hasChildNodes() {
			return true;
		}

		public Node getFirstChild() {
			return texta;
		}

		public Node getLastChild() {
			return b;
		}

		public short getNodeType() {
			return Node.ELEMENT_NODE;

		}

		public Node getParentNode() {
			return null;
		}

		public String getNamespaceURI() {
			return null;

		}

		public Node getNextSibling() {
			return null;

		}
		public String getNodeValue()
		{
			return null;
		}

	};
	static Node root = new ThrowExceptionNode() {

		public String getNodeName() {

			return "#document";
		}

		public String getLocalName() {
			return null;
		}

		public NamedNodeMap getAttributes() {
			return null;
		}

		public boolean hasChildNodes() {
			return true;
		}

		public Node getFirstChild() {
			return (Node) a;
		}

		public Node getLastChild() {
			return a;
		}

		public short getNodeType() {
			return Node.DOCUMENT_NODE;

		}

		public Node getParentNode() {
			return null;
		}

		public String getNamespaceURI() {
			return null;

		}

		public Node getNextSibling() {
			return null;

		}
	};

}
