/*
 * Author : Neel Bommisetty
 * Email : neel250294@gmail.com
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.controller.sample.toaster.it;




import static org.junit.Assert.assertEquals;
import static org.opendaylight.controller.test.sal.binding.it.TestHelper.baseModelBundles;
import static org.opendaylight.controller.test.sal.binding.it.TestHelper.bindingAwareSalBundles;
import static org.opendaylight.controller.test.sal.binding.it.TestHelper.configMinumumBundles;
import static org.opendaylight.controller.test.sal.binding.it.TestHelper.flowCapableModelBundles;
import static org.opendaylight.controller.test.sal.binding.it.TestHelper.junitAndMockitoBundles;
import static org.opendaylight.controller.test.sal.binding.it.TestHelper.mdSalCoreBundles;
import static org.ops4j.pax.exam.CoreOptions.mavenBundle;
import static org.ops4j.pax.exam.CoreOptions.options;
import static org.ops4j.pax.exam.CoreOptions.systemPackages;
import static org.ops4j.pax.exam.CoreOptions.systemProperty;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.opendaylight.controller.md.sal.common.api.data.LogicalDatastoreType;
import org.opendaylight.controller.md.sal.common.api.data.ReadFailedException;
import org.opendaylight.controller.md.sal.dom.api.DOMDataBroker;
import org.opendaylight.controller.md.sal.dom.api.DOMDataReadOnlyTransaction;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.data.api.YangInstanceIdentifier;
import org.opendaylight.yangtools.yang.data.api.YangInstanceIdentifier.PathArgument;
import org.opendaylight.yangtools.yang.data.api.schema.DataContainerChild;
import org.opendaylight.yangtools.yang.data.api.schema.DataContainerNode;
import org.opendaylight.yangtools.yang.data.api.schema.NormalizedNode;
import org.opendaylight.yangtools.yang.data.impl.codec.BindingIndependentMappingService;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.options.DefaultCompositeOption;
import org.ops4j.pax.exam.util.Filter;
import org.ops4j.pax.exam.util.PathUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.google.common.base.Optional;
import com.google.common.util.concurrent.CheckedFuture;

@RunWith(PaxExam.class)
public class ToasterTest {

	XPath xpath;

    @Inject
    @Filter(timeout=60*1000)
    DataBroker dataBroker;

    @Inject
    @Filter(timeout=60*1000)
    BindingIndependentMappingService mappingService;

    @Inject
    @Filter(timeout=60*1000)
    DOMDataBroker domBroker;

	Element rootTestNode;

    @Before
    public void setup() throws Exception {
        xpath = XPathFactory.newInstance().newXPath();
        //setupRealXml();
       setupNormalizedNode();
       System.out.println("Initialized!");
    }

    public void setupRealXml() throws Exception {
        DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = dBuilder.parse("src/test/resources/toaster.xml");
        doc.normalizeDocument();
        rootTestNode = (Element) xpath.evaluate("//toaster", doc, XPathConstants.NODE);


    }

    public void setupNormalizedNode() throws URISyntaxException, InterruptedException, ExecutionException {



    	DOMDataReadOnlyTransaction readTx2 = domBroker.newReadOnlyTransaction();
        @SuppressWarnings("deprecation")
		YangInstanceIdentifier build = YangInstanceIdentifier.builder( QName.create( new URI( "http://netconfcentral.org/ns/toaster"), Date.valueOf( "2009-11-20" ), "toaster" ) ).build();
        CheckedFuture<Optional<NormalizedNode<?, ?>>, ReadFailedException> read2 = readTx2.read( LogicalDatastoreType.OPERATIONAL, build );
        NormalizedNode<?, ?> normalizedNode = read2.get().get();
        rootTestNode=new NodeBuilderElement(normalizedNode,null);
        DataContainerNode<?> dataContainerNode=(DataContainerNode<?>) normalizedNode;
		Iterator<DataContainerChild<? extends PathArgument, ?>> iterator = dataContainerNode.getValue().iterator();
		while(iterator.hasNext())
		{
			System.out.println("the child node is :"+iterator.next().getValue());

		}
    }
    @Configuration
    public Option[] config() {
        return options(systemProperty("osgi.console").value("2401"), mavenBundle("org.slf4j", "slf4j-api")
                .versionAsInProject(), //
                          mavenBundle("org.slf4j", "log4j-over-slf4j").versionAsInProject(), //

                                systemProperty("logback.configurationFile").value(
                        "file:" + PathUtils.getBaseDir()
                                + "/src/test/resources/logback.xml"),
                mavenBundle("ch.qos.logback", "logback-core").versionAsInProject(), //
                mavenBundle("ch.qos.logback", "logback-classic").versionAsInProject(), //
                systemProperty("osgi.bundles.defaultStartLevel").value("4"),
                systemPackages("sun.nio.ch"),

                toasterBundles(),
                mdSalCoreBundles(),

                bindingAwareSalBundles(),
                configMinumumBundles(),
                // BASE Models
                baseModelBundles(),
                flowCapableModelBundles(),

                // Set fail if unresolved bundle present
                systemProperty("pax.exam.osgi.unresolved.fail").value("true"),
                junitAndMockitoBundles());
    }

    private Option toasterBundles() {
        return new DefaultCompositeOption(
                mavenBundle("org.opendaylight.controller.samples", "sample-toaster-provider").versionAsInProject(),
                mavenBundle("org.opendaylight.controller.samples", "sample-toaster-consumer").versionAsInProject(),
                mavenBundle("org.opendaylight.controller.samples", "sample-toaster").versionAsInProject(),
                mavenBundle("org.openexi", "nagasena").versionAsInProject(),
                mavenBundle("org.openexi", "nagasena-rta").versionAsInProject()
        );
    }

    public void evaluate(String xpathexp, String expectedValue) throws XPathExpressionException {
        String node =  (String) xpath.evaluate(xpathexp, rootTestNode, XPathConstants.STRING);
        System.out.println( "'" + node + "'" );
        assertEquals(expectedValue, node);
    }
/*
    @Test
    public void selfTest() throws XPathExpressionException {
        System.out.println("Testing1 ..!!");
        NodeList nodelist = (NodeList) xpath.evaluate(".", rootTestNode, XPathConstants.NODESET);
        assertNotNull(nodelist);
        assertEquals(1, nodelist.getLength());
        Node root = nodelist.item(0);
        assertNotNull(root);
        assertEquals("toaster", root.getNodeName());

    }*/


    @Test
    public void testToaster() throws Exception {
    	System.out.println("First Child is " + rootTestNode.getFirstChild().getNodeName());
    	System.out.println("Next Child is " + rootTestNode.getFirstChild().getNextSibling().getNodeName());
    	System.out.println("Next Child is " + rootTestNode.getFirstChild().getNextSibling().getNextSibling().getNodeName());

    	evaluate("name(.)","toaster");
     	evaluate("toasterModelNumber","Model 1 - Binding Aware");
     	evaluate("toasterStatus","up");
    	evaluate("toasterManufacturer","Opendaylight");

    }

}
