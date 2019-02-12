package org.ocp.jdk.internal.misc;

import org.junit.jupiter.api.Test;
import sun.misc.VM;

import static org.junit.jupiter.api.Assertions.assertTrue;

class VMTest {


    @Test
    void VM() {
        System.out.println(VM.allowArraySyntax());
        System.out.println(VM.getFinalRefCount());
        System.out.println(VM.getPeakFinalRefCount());
        System.out.println(VM.getSavedProperty(""));
        System.out.println(VM.isBooted());
        System.out.println(VM.isDirectMemoryPageAligned());
        System.out.println(VM.isSystemDomainLoader(this.getClass().getClassLoader()));
        System.out.println(VM.latestUserDefinedLoader());
        System.out.println(VM.latestUserDefinedLoader0());
        System.out.println(VM.maxDirectMemory());
        System.out.println(VM.toThreadState(2));
        System.out.println(VM.suspendThreads());
    }

    @Test
    void booted() {
        assertTrue(VM.isBooted());
    }

    @Test
    void initializred() {
        VM.initializeOSEnvironment();
    }
}
