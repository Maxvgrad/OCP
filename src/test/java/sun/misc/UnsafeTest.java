package sun.misc;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UnsafeTest {

    private static Unsafe unsafe;

    private OffHeapArray offHeapArray;

    @BeforeAll
    static void initClass() throws Exception {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");

        field.setAccessible(true);

        unsafe = (Unsafe) field.get(null);
    }

    @AfterEach
    void cleanUp() throws Exception {
        if (offHeapArray != null) {
            offHeapArray.freeMemory();
        }
    }

    @Test
    void getUnsafeWithException() {
        assertThrows(SecurityException.class, () -> Unsafe.getUnsafe());
    }


    @Test
    void getUnsafeWithReflection() throws Exception {

        Field field = Unsafe.class.getDeclaredField("theUnsafe");

        field.setAccessible(true);

        Unsafe unsafe = (Unsafe) field.get(null);

        assertNotNull(unsafe);

    }

    @Test
    void allocateInstance() throws Exception {

        ClassWithoutConstructorInvocation instance = (ClassWithoutConstructorInvocation) unsafe
                .allocateInstance(ClassWithoutConstructorInvocation.class);
        assertNull(instance.getIntegerWrapper());

        assertEquals(0, instance.getInteger());

    }

    @Test
    void putInt() throws Exception {

        ClassWithoutConstructorInvocation instance = (ClassWithoutConstructorInvocation) unsafe
                .allocateInstance(ClassWithoutConstructorInvocation.class);

        Field field = instance.getClass().getDeclaredField("integer");

        unsafe.putInt(instance, unsafe.objectFieldOffset(field), -1);


        assertNull(instance.getIntegerWrapper());

        assertEquals(-1, instance.getInteger());

    }


    @Test
    void putIntegerWrapper() throws Exception {

        ClassWithoutConstructorInvocation instance = (ClassWithoutConstructorInvocation) unsafe
                .allocateInstance(ClassWithoutConstructorInvocation.class);

        Field field = instance.getClass().getDeclaredField("integerWrapper");

        unsafe.putObject(instance, unsafe.objectFieldOffset(field), -1);

        assertEquals(Integer.valueOf(-1), instance.getIntegerWrapper());

    }

    @Test // Static block is invoked anyway
    void atomicBoolean() throws Exception {

        AtomicBoolean atomicBoolean = (AtomicBoolean) unsafe.allocateInstance(AtomicBoolean.class);

        Field offsetField = atomicBoolean.getClass().getDeclaredField("valueOffset");
        offsetField.setAccessible(true);
        long offset = offsetField.getLong(atomicBoolean);

        assertEquals(12L, offset);
    }

    @Test
    void throwException() {
        assertThrows(IOException.class, () -> unsafe.throwException(new IOException()));
    }

    @Test
    void offHeapArray() throws Exception {

        offHeapArray = new OffHeapArray(5);

        assertEquals(5, offHeapArray.getSize());

        int accumulator = 0;

        for(int i = 0; i < 5; i++) {
            offHeapArray.set(i, (byte) 5);

            accumulator += offHeapArray.get(i);
        }

        assertEquals(25, accumulator);
    }

    @Test //TODO
    void lockFreeIncrementAlgorithm() throws Exception {
        CASCounter counter = new CASCounter();
        int numOfThreads = 10;
        int numOfIncrementInvocations = 100;

        ExecutorService service = Executors.newFixedThreadPool(numOfThreads);
        IntStream.range(0, numOfThreads).forEach(i -> service
                .submit(() -> IntStream.range(0, numOfIncrementInvocations).forEach(iter -> counter.increment())));

        service.shutdown();
        service.awaitTermination(5, TimeUnit.SECONDS);
        assertTrue(service.isTerminated());
        assertEquals(numOfThreads * numOfIncrementInvocations, counter.getCounter());
    }

    private class ClassWithoutConstructorInvocation {

        private final Integer integerWrapper;

        private final int integer;


        public ClassWithoutConstructorInvocation(Integer integerWeapper, int integer) {
            this.integerWrapper = integerWeapper;
            this.integer = integer;
        }

        Integer getIntegerWrapper() {
            return integerWrapper;
        }

        int getInteger() {
            return integer;
        }
    }

    private class OffHeapArray {
        private static final int BYTE = 1;

        private long size;

        private long address;

        public OffHeapArray(long size) throws Exception {

            this.size = size;
            address = getUnsafe().allocateMemory(size * BYTE);

        }

        public void set(long l, byte value) throws Exception {
            getUnsafe().putByte(address + l * BYTE, value);
        }

        public int get(long idx) throws Exception {
            return getUnsafe().getByte(address + idx * BYTE);
        }

        public long getAddress() {
            return address;
        }


        private Unsafe getUnsafe() {
            return unsafe;
        }

        public long getSize() {
            return size;
        }

        public void freeMemory() throws Exception {
            getUnsafe().freeMemory(address);
        }

    }

    //CompareAndSwap
    private class CASCounter {

        private long offset;

        private volatile long counter = 0;

        public CASCounter() throws Exception {
            this.offset = unsafe.objectFieldOffset(this.getClass().getDeclaredField("counter"));
        }

        public void increment() {
            long before = counter;
            while (!unsafe.compareAndSwapLong(this, offset, before, before + 1)) {
                before = counter;
            }

        }

        public long getCounter() {
            return counter;
        }
    }

}
