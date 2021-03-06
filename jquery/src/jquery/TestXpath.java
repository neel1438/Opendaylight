/*
 * Copyright (c) 2014 Brocade Communications Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package jquery;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/*
 * This class is just to do random tests and play around with Nodes
 */
public class TestXpath {

	private static void printNote(NodeList nodeList) {
		for (int count = 0; count < nodeList.getLength(); count++) {
			Node tempNode = nodeList.item(count);
			// get node name and value
			System.out.println("\nNode Name =" + tempNode.getNodeName()
					+ " [OPEN]");
			// System.out.println("Node Value =" + tempNode.getTextContent());
			if (tempNode.hasChildNodes()) {
				// loop again if has child nodes
				printNote(tempNode.getChildNodes());
			}
			System.out.println("Node Name =" + tempNode.getNodeName()
					+ " [CLOSE]");
		}
	}

	public static void main(String[] args) throws ParserConfigurationException,
			SAXException, IOException {

		XPath xpath = XPathFactory.newInstance().newXPath();

		try {
			Document doc = buildDocument("src/jquery/resources/test.xml");
			
			  NodeList nodelist = (NodeList) xpath.evaluate("//b/*",
			  doc.getFirstChild(), XPathConstants.NODESET);
			  
			  // System.out.println(nodelist.getLength()); //
			  System.out.println(nodelist.item(0).getTextContent());
			  
			  for (int i = 0; i < nodelist.getLength(); i++) {
			  
			  Node node = nodelist.item(i);
			  
			  System.out.println(node.getNodeName()); }
			 

			printNote(doc.getChildNodes());
			System.out.println("--------------------------------");
			NodeList nodeList = (NodeList) xpath.evaluate("//*",
					new HardCodedNodeBuilder().a, XPathConstants.NODESET);
			System.out.println(nodeList.getLength());
			// System.out.println(new
			// HardCodedNodeBuilder().root.getFirstChild());
			for (int i = 0; i < nodeList.getLength(); i++) {

				Node node = nodeList.item(i);

				System.out.println(node.getNodeName());

			}
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

	}

	public static Document buildDocument(String file)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		Document doc = dBuilder.parse(file);
		doc.normalizeDocument();
		// System.out.println(dBuilder.getDOMImplementation());
		return doc;
	}

}
