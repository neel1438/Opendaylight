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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TestXpath {
	 final static Node c1 = new ThrowExceptionNode(){
  	
  	    public short getNodeType()
  	    {
				return Node.DOCUMENT_NODE;
				
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
  	  public Node getNextSibling()
  	  {
		return c2;
  		  
  	  }
  	};

  	final static Node c2 = new ThrowExceptionNode(){
   	   
   	  public short getNodeType()
	    {
				return Node.DOCUMENT_NODE;
	    	
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
     public Node getNextSibling()
 	  {
		return null;
 		  
 	  }
   	};


  	final static Node b= new ThrowExceptionNode(){
  		//Node nextsibling;
  	   
  		public String getNodeName(){ 
  		   return "b";
  	    	}
  		 public short getNodeType()
   	    {
				return Node.DOCUMENT_NODE;
   	    	
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
  	            return  null;
  	            }
  	     
  	};

  	static Node root = new ThrowExceptionNode(){

  	   public String getNodeName(){
  	            
  	                return "a";
  	     }
  	 public String getLocalName()
	    {
	    	return null;
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
				return Node.DOCUMENT_NODE;
 	    	
 	    }
  	 public Node getParentNode()
	    {
	    	return null;
	    }
  	  public String getNamespaceURI()
	    {
			return "a";
	    	
	    }
  	  public Node getNextSibling()
  	  {
		return null;
  		  
  	  }
	 
  	};
	
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
    
    	
    	
    	     XPath xpath = XPathFactory.newInstance().newXPath();
           
    	   try {
    		   Document doc= buildDocument("src/jquery/resources/test.xml");
    		   new ConstructXml();
    		 //  Node doc1=ConstructXml.getDoc();
    		 
    		   
    		   NodeList nodelist=(NodeList)xpath.evaluate("//a",doc,XPathConstants.NODESET);
    		 	
				//System.out.println(nodelist.getLength());
				System.out.println(doc.getFirstChild().getNodeValue());
				
				for(int i = 0;i<nodelist.getLength();i++)
				{
					
					//Node node=nodelist.item(i);
					
				//	System.out.println(node.getNodeName());
				}
				System.out.println("--------------------------------");
				 NodeList nodeList=(NodeList)xpath.evaluate("/",HardCodedNodeBuilder.root,XPathConstants.NODESET);
					//System.out.println(nodeList.getLength());
					System.out.println(HardCodedNodeBuilder.root.getFirstChild().getNodeName());
					for(int i=0;i<nodeList.getLength();i++)
					{
						
					//	Node node=nodeList.item(i);
						
					//	System.out.println(node.getNodeName());
					}
			} catch (XPathExpressionException e) {
				e.printStackTrace();
			}

    }

    public static Document buildDocument( String file ) throws ParserConfigurationException, SAXException, IOException{
        DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = dBuilder.parse(file);
        doc.normalizeDocument();
        return doc;
    }

}
