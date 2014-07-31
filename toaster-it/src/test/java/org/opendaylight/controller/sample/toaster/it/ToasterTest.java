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
import javassist.ClassPool;

import javax.inject.Inject;
//import javax.management.MBeanServer;
//import javax.management.ObjectName;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.opendaylight.controller.md.sal.binding.api.ReadOnlyTransaction;
import org.opendaylight.controller.md.sal.common.api.data.LogicalDatastoreType;
import org.opendaylight.controller.md.sal.dom.api.DOMDataBroker;
//import org.opendaylight.controller.sample.kitchen.api.EggsType;
//import org.opendaylight.controller.sample.kitchen.api.KitchenService;
//import org.opendaylight.yang.gen.v1.http.netconfcentral.org.ns.toaster.rev091120.HashBrown;
import org.opendaylight.yang.gen.v1.http.netconfcentral.org.ns.toaster.rev091120.Toaster;
import org.opendaylight.yangtools.sal.binding.generator.impl.ModuleInfoBackedContext;
import org.opendaylight.yangtools.sal.binding.generator.impl.RuntimeGeneratedMappingServiceImpl;
//import org.opendaylight.yang.gen.v1.http.netconfcentral.org.ns.toaster.rev091120.WhiteBread;
//import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.md.sal.dom.rev131028.DomDataBroker;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.opendaylight.yangtools.yang.binding.YangModuleInfo;
import org.opendaylight.yangtools.yang.binding.util.BindingReflections;
import org.opendaylight.yangtools.yang.data.api.CompositeNode;
import org.opendaylight.yangtools.yang.data.impl.codec.BindingIndependentMappingService;
import org.opendaylight.yangtools.yang.model.api.SchemaContext;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.options.DefaultCompositeOption;
import org.ops4j.pax.exam.util.Filter;
import org.ops4j.pax.exam.util.PathUtils;

import com.google.common.base.Optional;
import com.google.common.util.concurrent.ListenableFuture;
//import java.lang.management.ManagementFactory;
@RunWith(PaxExam.class)
public class ToasterTest {

    @Inject
    @Filter(timeout=60*1000)
//    KitchenService kitchenService;
    DataBroker dataBroker;
    DOMDataBroker domDataBroker;
    BindingIndependentMappingService mappingService;
    SchemaContext context;


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
    public static final SchemaContext getSchemaContext() {
        Iterable<YangModuleInfo> moduleInfos;
        moduleInfos = BindingReflections.loadModuleInfos();
        ModuleInfoBackedContext moduleContext = ModuleInfoBackedContext.create();
        moduleContext.addModuleInfos(moduleInfos);
        return moduleContext.tryToCreateSchemaContext().get();
        }

        public void setup() {
            System.out.println("Building context");
            context = getSchemaContext();
            System.out.println("Context built");
            System.out.println("Building mapping service");
            mappingService = new RuntimeGeneratedMappingServiceImpl(ClassPool.getDefault());
            ((RuntimeGeneratedMappingServiceImpl)mappingService).onGlobalContextUpdated(context);
            System.out.println("Mapping service built");
       }

    @Test
    public void testToaster() throws Exception {
        System.out.println("Test Starts");
      //  MBeanServer platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
        //ObjectName providerOn = new ObjectName("org.opendaylight.controller:instanceName=toaster-provider-impl,type=RuntimeBean,moduleFactoryName=toaster-provider-impl");

        //long toastsMade = (long) platformMBeanServer.getAttribute(providerOn, "ToastsMade");
       // assertEquals(0, toastsMade);
        InstanceIdentifier<Toaster> iidToaster = InstanceIdentifier.builder(Toaster.class).build();
        ReadOnlyTransaction readTx = dataBroker.newReadOnlyTransaction();
        ListenableFuture<Optional<Toaster>> read = readTx.read(LogicalDatastoreType.OPERATIONAL, iidToaster);
        readTx.close();
        Toaster t = read.get().get();
        System.out.println("Got the toaster t:"+t);

        setup();
        CompositeNode node = mappingService.toDataDom(t);
        System.out.println("compostite node is :"+node);
      //System.out.println(domDataBroker);
     // DOMDataWriteTransaction writeTx = domDataBroker.newWriteOnlyTransaction();
      //writeTx.put(LogicalDatastoreType.OPERATIONAL, ,a);

      /*  InstanceIdentifier<Toaster> idToaster =InstanceIdentifier.builder(Toaster.class).build();
        DOMDataReadOnlyTransaction readTx1=domDataBroker.newReadOnlyTransaction();
        ListenableFuture<Optional<Toaster>> read1 = readTx1.read(LogicalDatastoreType.OPERATIONAL, idToaster);
        readTx1.close();
        Toaster t1 = read1.get().get();
        System.out.println("Got the toaster t1:"+t1);

      //  boolean success = true;

        // Make toasts using OSGi service
      //  success &= kitchenService.makeBreakfast( EggsType.SCRAMBLED, HashBrown.class, 4).get().isSuccessful();
       // success &= kitchenService.makeBreakfast( EggsType.POACHED, WhiteBread.class, 8 ).get().isSuccessful();

      //  Assert.assertTrue("Not all breakfasts succeeded", success);

        // Verify toasts made count on provider via JMX/config-subsystem
       // toastsMade = (long) platformMBeanServer.getAttribute(providerOn, "ToastsMade");
      //  assertEquals(2, toastsMade);
       */
    }

}
