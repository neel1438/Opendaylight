package jquery;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.UserDataHandler;

public class MyNode implements Node {
	Node node;
	 public MyNode(Node nodde)
	 {
		 this.node=nodde;
	 }
	 
	@Override
	public String getNodeName() {
		System.out.println("getNodeName()");
		return node.getNodeName();
	}

	@Override
	public String getNodeValue() throws DOMException {
		System.out.println("getNodeValue()");
		return node.getNodeValue();
	}

	@Override
	public void setNodeValue(String nodeValue) throws DOMException {
		System.out.println("setNodeValue()");
		node.setNodeValue(nodeValue);
		
	}

	@Override
	public short getNodeType() {
		System.out.println("getNodeType()");
		return node.getNodeType();
	}

	@Override
	public Node getParentNode() {
		System.out.println("getParentNode()");
		return node.getParentNode();
	}

	@Override
	public NodeList getChildNodes() {
		System.out.println("getChildNodes()");
		return node.getChildNodes();
	}

	@Override
	public MyNode getFirstChild() {
		System.out.println("getFirstChild()");
		return new MyNode(node.getFirstChild());
	}

	@Override
	public MyNode getLastChild() {
		System.out.println("getLastChild()");
		return new MyNode(node.getLastChild());
	}

	@Override
	public MyNode getPreviousSibling() {
		System.out.println("getPreviousSibling()");
		return new MyNode(node.getPreviousSibling());
	}

	@Override
	public MyNode getNextSibling() {
		System.out.println("getNextSibling()");
		return new MyNode(node.getNextSibling());
	}

	@Override
	public NamedNodeMap getAttributes() {
		System.out.println("getAttributes()");
		return node.getAttributes();
	}

	@Override
	public Document getOwnerDocument() {
		System.out.println("getOwnerDocument");
		return node.getOwnerDocument();
	}

	@Override
	public MyNode insertBefore(Node newChild, Node refChild) throws DOMException {
		System.out.println("insertBefore("+newChild.getNodeName()+","+refChild.getNodeName()+")");
		return new MyNode(node.insertBefore(newChild, refChild));
	}

	@Override
	public MyNode replaceChild(Node newChild, Node oldChild) throws DOMException {
		System.out.println("replaceChild("+newChild.getNodeName()+","+oldChild.getNodeName()+")");
		return new MyNode(node.replaceChild(newChild, oldChild));
	}

	@Override
	public MyNode removeChild(Node oldChild) throws DOMException {
		System.out.println("insertBefore("+oldChild.getNodeName()+")");
		return new MyNode(node.removeChild(oldChild));
	}

	@Override
	public MyNode appendChild(Node newChild) throws DOMException {
		System.out.println("appendChild("+newChild.getNodeName()+")");
		return new MyNode(node.appendChild(newChild));
	}

	@Override
	public boolean hasChildNodes() {
		System.out.println("hasChildNodes()");
		return node.hasChildNodes();
	}

	@Override
	public MyNode cloneNode(boolean deep) {
		System.out.println("cloneNode("+deep+")");
		return new MyNode(node.cloneNode(deep));
	}

	@Override
	public void normalize() {
		System.out.println("normalize()");
		node.normalize();
	}

	@Override
	public boolean isSupported(String feature, String version) {
		System.out.println("insertBefore("+feature+","+version+")");
		return node.isSupported(feature, version);
	}

	@Override
	public String getNamespaceURI() {
		System.out.println("getNamespaceURI()");
		return node.getNamespaceURI();
	}

	@Override
	public String getPrefix() {
		System.out.println("getPrefix()");
		return node.getPrefix();
	}

	@Override
	public void setPrefix(String prefix) throws DOMException {
		System.out.println("setPrefix("+prefix+")");
		node.setPrefix(prefix);
	}

	@Override
	public String getLocalName() {
		System.out.println("getLocalName()");
		return node.getLocalName();
	}

	@Override
	public boolean hasAttributes() {
		System.out.println("hasAttributes()");
		return node.hasAttributes();
	}

	@Override
	public String getBaseURI() {
		System.out.println("getBaseURI()");
		return node.getBaseURI();
	}

	@Override
	public short compareDocumentPosition(Node other) throws DOMException {
		System.out.println("compareDocumentPosition("+other.getNodeName()+")");
		return node.compareDocumentPosition(other);
	}

	@Override
	public String getTextContent() throws DOMException {
		System.out.println("getTextContent()");
		return node.getTextContent();
	}

	@Override
	public void setTextContent(String textContent) throws DOMException {
		System.out.println("setTextContent("+textContent+")");
		node.setTextContent(textContent);
	}

	@Override
	public boolean isSameNode(Node other) {
		System.out.println("isSameNode("+other.getNodeName()+")");
		return node.isSameNode(other);
	}

	@Override
	public String lookupPrefix(String namespaceURI) {
		System.out.println("lookupPrefix("+namespaceURI+")");
		return node.lookupPrefix(namespaceURI);
	}

	@Override
	public boolean isDefaultNamespace(String namespaceURI) {
		System.out.println("isDefaultNamespace("+namespaceURI+")");
		return node.isDefaultNamespace(namespaceURI);
	}

	@Override
	public String lookupNamespaceURI(String prefix) {
		System.out.println("lookupNamespaceURI("+prefix+")");
		return node.lookupNamespaceURI(prefix);
	}

	@Override
	public boolean isEqualNode(Node arg) {
		System.out.println("isEqualNode("+arg.getNodeName()+")");
		return node.isEqualNode(arg);
	}

	@Override
	public Object getFeature(String feature, String version) {
		System.out.println("getFeature("+feature+","+version+")");
		return node.getFeature(feature, version);
	}

	@Override
	public Object setUserData(String key, Object data, UserDataHandler handler) {
		System.out.println("setUserData("+key+","+data.toString()+","+handler.toString()+")");
		return node.setUserData(key, data, handler);
	}

	@Override	
	public Object getUserData(String key) {
		System.out.println("getUserData("+key+")");
		return node.getUserData(key);
	}
}

