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

    public static void main(String[] args) {
    	Node dummy1 = null,dummy2 = null,dummy3=null,dummy4=null;
    	 final Node c1 = new MyNode(dummy1){
    	   boolean getHasChildren(){
    	        return false;
    	        }
    	    
    	   public NamedNodeMap getAttributes(){
    	    
    	        //return the ID attribute
    		   return null; 
    	    }
    	    
    	    String getName(){
    	    
    	        return "c1";
    	    }
    	};

    	final Node c2 = new MyNode(dummy2){
     	   boolean getHasChildren(){
     	        return false;
     	        }
     	    
     	   public NamedNodeMap getAttributes(){
     	    
     	        //return the ID attribute
     		   return null; 
     	    }
     	    
     	    public String getNodeName(){
     	    
     	        return "c2";
     	    }
     	};


    	final Node B = new MyNode(dummy3){
    		//MyNode nextsibling;
    	   
    		public String getNodeName(){ 
    		   return "B";
    	    	}
    	    
    	    
    	//    Node nextSibling = null;
    	    
    	    public MyNode getFirstChild(){
    	      // nextSibling = c2;
    	        return  (MyNode) c1;
    	        }
    	        
    	       public MyNode getNextSibling(){
    	    	   //nextSibling=null;
    	            return (MyNode) c2;
    	            }
    	};

    	Node root = new MyNode(dummy4){

    	   public String getNodeName(){
    	            
    	                return "a";
    	     }
    	   public MyNode getFirstChild(){
    	        return (MyNode) B;
    	    }
    	};
    	
    	     try {
                Document doc = buildDocument( "/home/neel/Downloads/test.xml" );

                XPath xpath = XPathFactory.newInstance().newXPath();
                //below we start with an initial XPath expression that gets a list of nodes, basically all "nodes with name 'c'"
                NodeList nodeList = (NodeList)xpath.evaluate( "//b/c", doc, XPathConstants.NODESET );
              for( int i = 0; i < nodeList.getLength(); i++ ){
                    //illustrating here how you can use a single node as your starting point, and just query below that.
                    Node node = nodeList.item( i);
                    System.out.println( xpath.evaluate( "@id", node, XPathConstants.STRING ) );
                }

                //learned everything I know about xpath from here: http://www.w3schools.com/XPath/
                //Note: these java interfaces support XPATH 1.0 only.


            } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
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
