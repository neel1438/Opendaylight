package jquery;

import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.w3c.dom.UserDataHandler;

public class MyDocument implements Document {
	private Document doc;
	
	public MyDocument (Document document)
	{
		this.doc=document;
		
	}

	@Override
	public String getNodeName() {
		System.out.println("getNodeName()");
		return doc.getNodeName();
		//return null;
	}

	@Override
	public String getNodeValue() throws DOMException {
		System.out.println("getNodeValue()");
		return doc.getNodeValue();
		//return null;
	}

	@Override
	public void setNodeValue(String nodeValue) throws DOMException {
		System.out.println("setNodeValue("+nodeValue+")");
		doc.setNodeValue(nodeValue);
		
	}

	@Override
	public short getNodeType() {
		System.out.println("getNodeType()");
		return doc.getNodeType();
	}

	@Override
	public Node getParentNode() {
		System.out.println("getParentNode()");
		return doc.getParentNode();
	}

	@Override
	public NodeList getChildNodes() {
		System.out.println("getChildNodes()");
		return doc.getChildNodes();
	}

	@Override
	public Node getFirstChild() {
		System.out.println("getFirstChild()");
		return doc.getFirstChild();
	}

	@Override
	public Node getLastChild() {
		System.out.println("getLastChild()");
		return doc.getLastChild();
	}

	@Override
	public Node getPreviousSibling() {
		System.out.println("getPreviousSibling()");
		return doc.getPreviousSibling();
	}

	@Override
	public Node getNextSibling() {
		System.out.println("getNextSibling()");
		return doc.getNextSibling();
	}

	@Override
	public NamedNodeMap getAttributes() {
		System.out.println("getAttributes()");
		return doc.getAttributes();
	}

	@Override
	public Document getOwnerDocument() {
		System.out.println("getOwnerDocument()");
		return doc.getOwnerDocument();
	}

	@Override
	public Node insertBefore(Node newChild, Node refChild) throws DOMException {
		System.out.println("insertBefore("+newChild.getNodeName()+","+refChild.getNodeName()+")");
		return doc.insertBefore(newChild, refChild);
	}

	@Override
	public Node replaceChild(Node newChild, Node oldChild) throws DOMException {
		System.out.println("replaceChild("+newChild.getNodeName()+","+oldChild.getNodeName()+")");
		return doc.replaceChild(newChild, oldChild);
	}

	@Override
	public Node removeChild(Node oldChild) throws DOMException {
		System.out.println("removeChild("+oldChild.getNodeName()+")");
		return doc.removeChild(oldChild);
	}

	@Override
	public Node appendChild(Node newChild) throws DOMException {
		System.out.println("appendChild("+newChild.getNodeName()+")");
		return doc.appendChild(newChild);
	}

	@Override
	public boolean hasChildNodes() {
		System.out.println("hasChildNodes()");
		return doc.hasChildNodes();
	}

	@Override
	public Node cloneNode(boolean deep) {
		System.out.println("cloneNode("+deep+")");
		return doc.cloneNode(deep);
	}

	@Override
	public void normalize() {
		System.out.println("normalize()");
		doc.normalize();
	}

	@Override
	public boolean isSupported(String feature, String version) {
		System.out.println("isSupported("+feature+","+version+")");
		return doc.isSupported(feature, version);
	}

	@Override
	public String getNamespaceURI() {
		System.out.println("getNamespaceURI()");
		return doc.getNamespaceURI();
	}

	@Override
	public String getPrefix() {
		System.out.println("getPrefix()");
		return doc.getPrefix();
	}

	@Override
	public void setPrefix(String prefix) throws DOMException {
		System.out.println("setPrefix("+prefix+")");
		doc.setPrefix(prefix);
	}

	@Override
	public String getLocalName() {
		System.out.println("getLocalName()");
		return doc.getLocalName();
	}

	@Override
	public boolean hasAttributes() {
		System.out.println("hasAttributes()");
		return doc.hasAttributes();
	}

	@Override
	public String getBaseURI() {
		System.out.println("getBaseURI()");
		return doc.getBaseURI();
	}

	@Override
	public short compareDocumentPosition(Node other) throws DOMException {
		System.out.println("compareDocumentPosition("+other.getNodeName()+")");
		return doc.compareDocumentPosition(other);
	}

	@Override
	public String getTextContent() throws DOMException {
		System.out.println("getTextContent()");
		return doc.getTextContent();
	}

	@Override
	public void setTextContent(String textContent) throws DOMException {
		System.out.println("setTextContent("+textContent+")");
		doc.setTextContent(textContent);
	}

	@Override
	public boolean isSameNode(Node other) {
		System.out.println("isSameNode("+other.getNodeName()+")");
		return doc.isSameNode(other);
	}

	@Override
	public String lookupPrefix(String namespaceURI) {
		System.out.println("lookupPrefix("+namespaceURI+")");
		return doc.lookupPrefix(namespaceURI);
	}

	@Override
	public boolean isDefaultNamespace(String namespaceURI) {
		System.out.println("isDefaultNamespace("+namespaceURI+")");
		return doc.isDefaultNamespace(namespaceURI);
	}

	@Override
	public String lookupNamespaceURI(String prefix) {
		System.out.println("lookupNamespaceURI("+prefix+")");
		return doc.lookupNamespaceURI(prefix);
	}

	@Override
	public boolean isEqualNode(Node arg) {
		System.out.println("isEqualNode("+arg.getNodeName()+")");
		return doc.isEqualNode(arg);
	}

	@Override
	public Object getFeature(String feature, String version) {
		System.out.println("getFeature("+feature+","+version+")");
		return doc.getFeature(feature, version);
	}

	@Override
	public Object setUserData(String key, Object data, UserDataHandler handler) {
		System.out.println("setUserData("+key+","+data.toString()+","+handler.toString()+")");
		return doc.setUserData(key, data, handler);
	}

	@Override
	public Object getUserData(String key) {
		System.out.println("getUserData("+key+")");
		return doc.getUserData(key);
	}

	@Override
	public DocumentType getDoctype() {
		System.out.println("getDoctype()");
		return doc.getDoctype();
	}

	@Override
	public DOMImplementation getImplementation() {
		System.out.println("getImplementation()");
		return doc.getImplementation();
	}

	@Override
	public Element getDocumentElement() {
		System.out.println("getDocumentElement()");
		return doc.getDocumentElement();
	}

	@Override
	public Element createElement(String tagName) throws DOMException {
		System.out.println("createElement("+tagName+")");
		return doc.createElement(tagName);
	}

	@Override
	public DocumentFragment createDocumentFragment() {
		System.out.println("createDocumentFragment()");
		return doc.createDocumentFragment();
	}

	@Override
	public Text createTextNode(String data) {
		System.out.println("createTextNode("+data+")");
		return doc.createTextNode(data);
	}

	@Override
	public Comment createComment(String data) {
		System.out.println("createComment("+data+")");
		return doc.createComment(data);
	}

	@Override
	public CDATASection createCDATASection(String data) throws DOMException {
		System.out.println("createCDATASection("+data+")");
		return doc.createCDATASection(data);
	}

	@Override
	public ProcessingInstruction createProcessingInstruction(String target,
			String data) throws DOMException {
		System.out.println("createProcessingInstruction("+target+","+data+")");
		return doc.createProcessingInstruction(target, data);
	}

	@Override
	public Attr createAttribute(String name) throws DOMException {
		System.out.println("createAttribute("+name+")");
		return doc.createAttribute(name);
	}

	@Override
	public EntityReference createEntityReference(String name)
			throws DOMException {
		System.out.println("createEntityReference("+name+")");
		return doc.createEntityReference(name);
	}

	@Override
	public NodeList getElementsByTagName(String tagname) {
		System.out.println("getElementsByTagName("+tagname+")");
		return doc.getElementsByTagName(tagname);
	}

	@Override
	public Node importNode(Node importedNode, boolean deep) throws DOMException {
		System.out.println("importNode("+importedNode.getNodeName()+","+deep+")");
		return doc.importNode(importedNode, deep);
	}

	@Override
	public Element createElementNS(String namespaceURI, String qualifiedName)
			throws DOMException {
		System.out.println("createElementNS("+namespaceURI+","+qualifiedName+")");
		return doc.createElementNS(namespaceURI, qualifiedName);
	}

	@Override
	public Attr createAttributeNS(String namespaceURI, String qualifiedName)
			throws DOMException {
		System.out.println("createAttributeNS("+namespaceURI+","+qualifiedName+")");
		return doc.createAttributeNS(namespaceURI, qualifiedName);
	}

	@Override
	public NodeList getElementsByTagNameNS(String namespaceURI, String localName) {
		System.out.println("getElementByTagNameNS("+namespaceURI+","+localName+")");
		return doc.getElementsByTagNameNS(namespaceURI, localName);
	}

	@Override
	public Element getElementById(String elementId) {
		System.out.println("getElementById("+elementId+")");
		return doc.getElementById(elementId);
	}

	@Override
	public String getInputEncoding() {
		System.out.println("getInputEncoding()");
		return doc.getInputEncoding();
	}

	@Override
	public String getXmlEncoding() {
		System.out.println("getXmlEncoding()");
		return doc.getXmlEncoding();
	}

	@Override
	public boolean getXmlStandalone() {
		System.out.println("getXmlStandalone()");
		return doc.getXmlStandalone();
	}

	@Override
	public void setXmlStandalone(boolean xmlStandalone) throws DOMException {
		System.out.println("setXmlStandalone("+xmlStandalone +")");
		doc.setXmlStandalone(xmlStandalone);
	}

	@Override
	public String getXmlVersion() {
		System.out.println("getXmlVersion()");
		return doc.getXmlVersion();
	}

	@Override
	public void setXmlVersion(String xmlVersion) throws DOMException {
		System.out.println("setXmlVersion("+xmlVersion+")");
		doc.setXmlVersion(xmlVersion);
	}

	@Override
	public boolean getStrictErrorChecking() {
		System.out.println("getStrictErrorChecking()");
		return doc.getStrictErrorChecking();
	}

	@Override
	public void setStrictErrorChecking(boolean strictErrorChecking) {
		System.out.println("setStrictErrorChecking("+strictErrorChecking+")");
		doc.setStrictErrorChecking(strictErrorChecking);
	}

	@Override
	public String getDocumentURI() {
		System.out.println("getDocumentURI()");
		return doc.getDocumentURI();
	}

	@Override
	public void setDocumentURI(String documentURI) {
		System.out.println("setDocumentURI("+documentURI+")");
		doc.setDocumentURI(documentURI);
	}

	@Override
	public Node adoptNode(Node source) throws DOMException {
		System.out.println("adoptNode("+source.getNodeName()+")");
		return doc.adoptNode(source);
	}

	@Override
	public DOMConfiguration getDomConfig() {
		System.out.println("getDomConfig()");
		return doc.getDomConfig();
	}

	@Override
	public void normalizeDocument() {
		System.out.println("normalizeDccument()");
		doc.normalizeDocument();
	}

	@Override
	public Node renameNode(Node n, String namespaceURI, String qualifiedName)
			throws DOMException {
		System.out.println("renameNode("+n.getNodeName()+","+namespaceURI+","+qualifiedName+")");
		return doc.renameNode(n, namespaceURI, qualifiedName);
	}

}
