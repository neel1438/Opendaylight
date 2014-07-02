package jquery;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class HardCodedNodeBuilder {
	final static Node c1 = new ThrowExceptionNode() {

		public short getNodeType() {
			return Node.DOCUMENT_NODE;

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
	};

	final static Node c2 = new ThrowExceptionNode() {

		public short getNodeType() {
			return Node.DOCUMENT_NODE;

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
	};

	final static Node b = new ThrowExceptionNode() {
		// Node nextsibling;

		public String getNodeName() {
			return "b";
		}

		public short getNodeType() {
			return Node.DOCUMENT_NODE;

		}

		public NamedNodeMap getAttributes() {
			return null;
		}

		public String getNamespaceURI() {
			return "a/b";

		}

		public boolean hasChildNodes() {
			return true;
		}

		public String getLocalName() {
			return "b";
		}

		// Node nextSibling = null;
		public Node getParentNode() {
			return root;
		}

		public ThrowExceptionNode getFirstChild() {
			// nextSibling = c2;
			return (ThrowExceptionNode) c1;
		}

		public Node getNextSibling() {
			// nextSibling=null;
			return null;
		}

	};

	static Node root = new ThrowExceptionNode() {

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

		public ThrowExceptionNode getFirstChild() {
			return (ThrowExceptionNode) b;
		}

		public short getNodeType() {
			return Node.DOCUMENT_NODE;

		}

		public Node getParentNode() {
			return null;
		}

		public String getNamespaceURI() {
			return "a";

		}

		public Node getNextSibling() {
			return null;

		}

	};

}
