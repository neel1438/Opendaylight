package jquery;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//import org.xml.sax.SAXException;

public class Testcases {
    Node rootTestNode;
    XPath xpath;

    @Before
    public void setup() throws Exception {
        xpath = XPathFactory.newInstance().newXPath();
       // setupRealXml();
        setupHardcodedNode();
       // rootTestNode = new MyNode( rootTestNode );
        System.out.println("Initialized!");
    }

    public void setupRealXml() throws Exception {
        DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = dBuilder.parse("src/jquery/resources/test.xml");
        doc.normalizeDocument();
        rootTestNode = ((NodeList) xpath.evaluate("//a", doc, XPathConstants.NODESET)).item(0);

        //our "a" node that we hardcoded doesn't have a parent document, so simulate that here by
        //removing "a" from the "document".
      rootTestNode.getParentNode().removeChild( rootTestNode );
    }

    public void setupHardcodedNode() {
        //
//        rootTestNode = HardCodedNodeBuilder.root;
        rootTestNode = new HardCodedNodeBuilder().a;
    }

    @Test
    public void selfTest() throws XPathExpressionException {
        System.out.println("Testing1 ..!!");
        NodeList nodelist = (NodeList) xpath.evaluate(".", rootTestNode, XPathConstants.NODESET);
        assertNotNull(nodelist);
        assertEquals(1, nodelist.getLength());
        Node root = nodelist.item(0);
        assertNotNull(root);
        assertEquals("a", root.getNodeName());

    }

    /*
	 *
	 */
    public void evaluate(String xpathexp, String expectedValue) throws XPathExpressionException {
        String node = (String) xpath.evaluate(xpathexp, rootTestNode, XPathConstants.STRING);
        System.out.println( "'" + node + "'" );
        assertEquals(expectedValue, node);
    }

    /**
     * Takes an expression which returns a list of elements such as:
     *
     *    //*
     *
     * and a list of expected values
     *
     *   'a', 'b', 'c1', 'c2'
     *
     * and first validates that the xpath returns the expected number of values, and then
     * continues on to verify, via xpath, that we got each expected node in the order we were
     * expecting by running additional xpath calls, essentially:
     *
     *    name( (//*)[1] ) = 'a'
     *    name( (//*)[2] ) = 'b'
     *    name( (//*)[3] ) = 'c1'
     *    name( (//*)[4] ) = 'c2'
     *
     * Note, in xpath, applying [#] to a node set ( in this case //* returns a set of nodes) will
     * essentially index in and get the 1st, second etc node. Then we apply the name function to
     * return the name of the returned node.
     *
     * @param xpathexp
     * @param expectedValues
     * @throws XPathExpressionException
     */
    public void evaluateExpectList(String xpathexp, String ...expectedValues ) throws XPathExpressionException {
        NodeList node = (NodeList) xpath.evaluate(xpathexp, rootTestNode, XPathConstants.NODESET);
        assertEquals( "Unexpected size for expression: " + xpathexp + ", Results: " +
                       toStringResults( xpathexp ),
                      expectedValues.length, node.getLength() );
        for( int i = 0; i < expectedValues.length; i++ ){
            String newExp = "name((" + xpathexp + ")[" + (i+1) + "])";
            evaluate( newExp, expectedValues[i] );
        }
    }

    public void print( String xpath ) throws XPathExpressionException{
        System.out.println( toStringResults( xpath ));
    }

    /** Prints out the nodes that were found by the given xpath expression */
    public String toStringResults(String xpathexp ) throws XPathExpressionException {
        StringBuilder builder = new StringBuilder();

        NodeList node = (NodeList) xpath.evaluate(xpathexp, rootTestNode, XPathConstants.NODESET);

        if( node.getLength() == 0 ){
            builder.append( "No response." );
        }
        for( int i = 0; i < node.getLength(); i++ ){
            String value = (String) xpath.evaluate( ".", node.item( i ), XPathConstants.STRING );
            if(i != 0 ){
                builder.append( "\n" );
            }
            builder.append( node.item( i ) + ": " + value );
        }
        return builder.toString();
    }

    @Test
    public void testBasicXPath() throws XPathExpressionException {
        evaluate("name(.)", "a");
    }

    @Test
    public void simpleXPathTests() throws XPathExpressionException {

        // select node c1 whose text values is "Some Value"
        evaluate("count(//c1)", "1");
        evaluate("//c1", "Some Value");

        // get the nodes whose text values is "Some Value" (Comparing names)
        evaluate("name(//*[text()[.='Some Value']])", "c1");

        // count all the nodes whose text contains the value "Some"
        evaluate("count(//*[text()[contains(.,'Some')]])", "2");

        // verify that c1 is the first node whose text contains the value "Some"
        evaluate("name((//*[text()[contains(., 'Some')]])[1])", "c1");

        // verify that c2 is the second node whose text contains the value
        // "Some"
        System.out.println( "--" );
        evaluate("name((//*[text()[contains(., 'Some')]])[2])", "c2");

        // checking the same thing as above
        evaluate("name((//*[text()[contains(., 'Some')]])[2])='c2'", "true");

    }

    @Test
    public void complexHeirarchyTests() throws Exception{

        //Get all nodes, who have a child named 'c1'.
        evaluateExpectList( "//*[./*[name() = 'c1']]", "b" );

        //Get all children of current node (root in our case)
        evaluateExpectList( "child::*", "b" );

        //Get all nodes who have attribute 'c1' defined
        //Note that not() takes the inverse of a boolean. If given a node, not returns TRUE if
        //the node does NOT exist, or FALSE if it DOES exist.
        evaluateExpectList( "//*[not( c1 ) = false]", "b" );

        //Get all nodes who have attribute c1 = 'Some Value'
        evaluateExpectList( "//*[ c1 = 'Some Value']", "b" );

        //Get all nodes who have *some* attribute = 'Some Value'
        evaluateExpectList( "//*[ * = 'Some Value']", "b" );

        //Get the next sibling of the node which has the value 'Some Value'
        System.out.println( "--" );
        evaluateExpectList( "descendant::*[text()[.='Some Value']]/following-sibling::*", "c2" );

        //Get all nodes, who have a descendant named 'c1'
        evaluateExpectList( "//*[.//*[name() = 'c1']]", "b" );

        //Get the grandparent nodes, which have a descendant named 'c1'.
        //this doesn't work when there is no "root" because //* matches all children of the root document.
        //when we remove the root document, our root "a" because the true "root". This should work if there
        //is a true "Root" document provided.
        evaluateExpectList( "//*[.//*/*[name() = 'c1']]", "a" );


    }
}