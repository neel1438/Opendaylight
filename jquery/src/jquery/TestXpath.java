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
import org.w3c.dom.NamedNodeMap;
//import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TestXpath {
	 final static Node c1 = new ThrowExceptionNode(){
  	
  	    public short getNodeType()
  	    {
				return Node.ELEMENT_NODE;
				
  	    }
  	    public String getNamespaceURI()
  	    {
			return "a/b/c1";
  	    	
  	    }
  	    public boolean hasChildNodes()
  	    {
  	    	return false;
  	    }
  	    public NamedNodeMap getAttributes()
  	    {
  	    	return null;
  	    }
  	 
  	    public String getNodeName(){
  	    
  	        return "c1";
  	    }
  	    public Node getParentNode()
  	    {
  	    	return b;
  	    }
  	    public String getLocalName()
  	    {
  	    	return "c1";
  	    }
  	};

  	final static Node c2 = new ThrowExceptionNode(){
   	   
   	  public short getNodeType()
	    {
				return Node.ELEMENT_NODE;
	    	
	    }
   	 public NamedNodeMap getAttributes()
	    {
	    	return null;
	    }
   	 
   	  public String getNamespaceURI()
	    {
			return "a/b/c2";
	    	
	    }
   	public boolean hasChildNodes()
	    {
	    	return false;
	    }
	 
   	public String getLocalName()
	    {
	    	return "c2";
	    }
   	    public String getNodeName(){
   	    
   	        return "c2";
   	    }
   	 public Node getParentNode()
	    {
	    	return b;
	    }
   	};


  	final static Node b= new ThrowExceptionNode(){
  		//Node nextsibling;
  	   
  		public String getNodeName(){ 
  		   return "b";
  	    	}
  		 public short getNodeType()
   	    {
				return Node.ELEMENT_NODE;
   	    	
   	    }
  		 public NamedNodeMap getAttributes()
   	    {
   	    	return null;
   	    }
  		  public String getNamespaceURI()
    	    {
  			return "a/b";
    	    	
    	    }
  		public boolean hasChildNodes()
  	    {
  	    	return true;
  	    }
  		public String getLocalName()
  	    {
  	    	return "b";
  	    }
    	 
  	    
  	//    Node nextSibling = null;
  		public Node getParentNode()
  	    {
  	    	return root;
  	    }
  	    
  	    public ThrowExceptionNode getFirstChild(){
  	      // nextSibling = c2;
  	        return  (ThrowExceptionNode) c1;
  	        }
  	        
  	       public Node getNextSibling(){
  	    	   //nextSibling=null;
  	            return  c2;
  	            }
  	};

  	static Node root = new ThrowExceptionNode(){

  	   public String getNodeName(){
  	            
  	                return "a";
  	     }
  	 public String getLocalName()
	    {
	    	return "a";
	    }
  	 public NamedNodeMap getAttributes()
	    {
	    	return null;
	    }
  	public boolean hasChildNodes()
	    {
	    	return true;
	    }
  	   public ThrowExceptionNode getFirstChild(){
  	        return (ThrowExceptionNode) b;
  	    }
  	   public short getNodeType()
 	    {
				return Node.ELEMENT_NODE;
 	    	
 	    }
  	 public Node getParentNode()
	    {
	    	return null;
	    }
  	  public String getNamespaceURI()
	    {
			return "a";
	    	
	    }
	 
  	};
	
    public static void main(String[] args) {
    	//Node dummy1 = null,dummy2 = null,dummy3=null,dummy4=null;
    	
    	
    	     XPath xpath = XPathFactory.newInstance().newXPath();
            /*
			// below we start with an initial XPath expression that gets a list of nodes, basically all "nodes with name 'c'"
           
           NodeList nodeList = (NodeList)xpath.evaluate( "//b/c", doc, XPathConstants.NODESET );
           for( int i = 0; i < nodeList.getLength(); i++ ){
			  //illustrating here how you can use a single node as your starting point, and just query below that.
			  Node node = nodeList.item( i);
			  System.out.println( xpath.evaluate( "@id", node, XPathConstants.STRING ) );
			  
            */
    	     
    	   try {
				NodeList nodelist=(NodeList)xpath.evaluate("//b",root,XPathConstants.NODESET);
				System.out.println(nodelist.getLength());
				for(int i=0;i<nodelist.getLength();i++)
				{
					
					Node node=nodelist.item(i);
					
					System.out.println(node.getNodeName());
				}
			} catch (XPathExpressionException e) {
				e.printStackTrace();
			}

    }

    public static MyDocument buildDocument( String file ) throws ParserConfigurationException, SAXException, IOException{
        DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = dBuilder.parse(file);
        doc.normalizeDocument();
        return new MyDocument(doc);
    }

}
