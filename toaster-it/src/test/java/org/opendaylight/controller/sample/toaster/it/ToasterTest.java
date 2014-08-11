/*
 * Copyright (c) 2014 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.controller.sample.toaster.it;



//import org.junit.Assert;
//import static org.junit.Assert.assertEquals;
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
import java.sql.Date;
import java.util.Iterator;

import javax.inject.Inject;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

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

import com.google.common.base.Optional;
import com.google.common.util.concurrent.CheckedFuture;
//import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.md.sal.dom.rev131028.DomDataBroker;
@SuppressWarnings("deprecation")
@RunWith(PaxExam.class)
public class ToasterTest {


    @Inject
    @Filter(timeout=60*1000)
    DataBroker dataBroker;

    @Inject
    @Filter(timeout=60*1000)
    BindingIndependentMappingService mappingService;

    @Inject
    @Filter(timeout=60*1000)
    DOMDataBroker domBroker;



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


    @Test
    public void testToaster() throws Exception {
/*
          InstanceIdentifier<Toaster> iidToaster = InstanceIdentifier.builder(Toaster.class).build();
          ReadOnlyTransaction readTx = dataBroker.newReadOnlyTransaction();
          ListenableFuture<Optional<Toaster>> read = readTx.read(LogicalDatastoreType.OPERATIONAL, iidToaster);
          readTx.close();
          Toaster t = read.get().get();
          System.out.println("Got the toaster t:"+t);

          CompositeNode node = mappingService.toDataDom(t);
          System.out.println("compostite node is :"+node);

*/
          DOMDataReadOnlyTransaction readTx2 = domBroker.newReadOnlyTransaction();

          YangInstanceIdentifier build = YangInstanceIdentifier.builder( QName.create( new URI( "http://netconfcentral.org/ns/toaster"), Date.valueOf( "2009-11-20" ), "toaster" ) ).build();
          CheckedFuture<Optional<NormalizedNode<?, ?>>, ReadFailedException> read2 = readTx2.read( LogicalDatastoreType.OPERATIONAL, build );
          NormalizedNode<?, ?> normalizedNode = read2.get().get();

          System.out.println("Normalized Node is : " + normalizedNode);
          System.out.println("Normalized Node's identifier  is : " + normalizedNode.getIdentifier());
          System.out.println("Normalized Node's node type is : " + normalizedNode.getNodeType());
          System.out.println("Normalized Node's node value is : " + normalizedNode.getValue());

          DataContainerNode<?> node=(DataContainerNode<?>) normalizedNode;
          Iterator<DataContainerChild<? extends PathArgument, ?>> iterator = node.getValue().iterator();

          while (iterator.hasNext()){

        	  System.out.println("Child node is :" + iterator.next());
            }
          XPath xpath=XPathFactory.newInstance().newXPath();

          NodeBuilderElement element=new NodeBuilderElement("name","text", null, null);
          //element.setChildren();

          String answer=(String)xpath.evaluate("/", element, XPathConstants.STRING);
          System.out.println(answer);
      /*  MBeanServer platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName providerOn = new ObjectName("org.opendaylight.controller:instanceName=toaster-provider-impl,type=RuntimeBean,moduleFactoryName=toaster-provider-impl");

        long toastsMade = (long) platformMBeanServer.getAttribute(providerOn, "ToastsMade");
        assertEquals(0, toastsMade);

        boolean success = true;

        // Make toasts using OSGi service
        success &= kitchenService.makeBreakfast( EggsType.SCRAMBLED, HashBrown.class, 4).get().isSuccessful();
        success &= kitchenService.makeBreakfast( EggsType.POACHED, WhiteBread.class, 8 ).get().isSuccessful();

        Assert.assertTrue("Not all breakfasts succeeded", success);

        // Verify toasts made count on provider via JMX/config-subsystem
        toastsMade = (long) platformMBeanServer.getAttribute(providerOn, "ToastsMade");
        assertEquals(2, toastsMade);*/


    }

}
