package org.ocp.collections;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HashMapTest {

    private HashMap<String, Integer> map;

    private Map.Entry[] table;

    private Map<String, Class<?>> innerClassesHashMap;

    @BeforeEach
    void setUp() throws Exception {
        map = new HashMap<>();
        table = extractTable(map);
        innerClassesHashMap = initHashMapInnerClasses(map);
    }

    static synchronized void synchronizedMethod() {

    }


    @Test
    @Tag("ch3")
    @DisplayName("This will throw an exception at runtime because the keys of a TreeMap must be mutually comparable. " +
                         "Here, String, Integer, and Double are not mutually comparable.")
    void comparableTest() {
        Map m = new TreeMap();
        m.put("1", new ArrayList());    //1
        assertThrows(ClassCastException.class, () -> m.put(1, new Object()));    //2
    }

    @Test
    @DisplayName("assertEquals(1, map.size()); //1 in AbstractMap it would be true")
    @Tag("ch3")
    void deleteNullKeyTest() {
        Map<String, String> map = new HashMap<>();
        assertEquals(0, map.size());
        map.put(null, null);
        assertEquals(1, map.size());
        map.remove(null);
        assertEquals(0, map.size()); //1
    }


    @Test
    @DisplayName("")
    @Tag("ch3")
    void notSuchMethodAddTest() {
        Map<String, String> map = new HashMap<>(4);
        //map.add("key", "value");
    }

    @Test
    @DisplayName("")
    @Tag("ch3")
    void mergeTest() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 10);
        map.put(2, 20);
        map.put(3, null);

        System.out.println(map);

        map.merge(1, 3, (a, b) -> a + b);
        assertEquals(Integer.valueOf(13), map.get(1));
        map.merge(3, 3, (a, b) -> a + b);
        assertEquals(Integer.valueOf(3), map.get(3));
        map.merge(2, 2, (a, b) -> null);
        assertNull(map.get(2));
    }

    @Test
    void compute() {
        //given
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 10);
        map.put(2, 20);
        map.put(3, null);
        //when - then
        assertEquals(Integer.valueOf(45), map.compute(1, (k, v) -> 45));
        assertEquals(Integer.valueOf(45), map.get(1));
        assertNull(map.compute(2, (k, v) -> null));
        assertFalse(map.containsKey(2));
        assertEquals(Integer.valueOf(55), map.compute(5, (k, v) -> 55));
    }

    @Test
    void computeIfAbsent() {
        //given
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 10);
        map.put(2, 20);
        map.put(3, null);
        //when - then
        assertEquals(Integer.valueOf(45), map.computeIfAbsent(15, (k) -> 45));
        assertEquals(Integer.valueOf(45), map.get(15));
        assertEquals(Integer.valueOf(10), map.computeIfAbsent(1, (k) -> 45));
        assertNull(map.computeIfAbsent(55, (k) -> null));
    }

    @Test
    void computeIfPresent() {
        //given
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 10);
        map.put(2, 20);
        map.put(3, null);
        //when - then
        assertEquals(Integer.valueOf(45), map.computeIfPresent(1, (k, v) -> 45));
        assertEquals(Integer.valueOf(45), map.get(1));
        assertNull(map.computeIfPresent(1, (k, v) -> null));
        assertNull(map.computeIfPresent(55, (k, v) -> null));
    }

    @Test
    void table() throws Exception {

        assertNull(table);

        map.put("key", 1);

        Field tableField = map.getClass().getDeclaredField("table");
        tableField.setAccessible(true);

        table = (Map.Entry[]) tableField.get(map);

        assertNotNull(table);
        assertEquals(16, table.length);
    }


    @Test
    void node() {
        Node<String, Integer> node = new Node<>("key", 1);
        Node<String, Integer> next = node.getNext();
        assertNull(next);

        node.setNext(new Node<>("key2", 1));

        assertNull(next);
    }

    @Test
    void nodeWithoutGetters() {
        Node<String, Integer> node = new Node<>("key", 1);
        Node<String, Integer> next = node.next;
        assertNull(next);

        node.next = new Node<>("key2", 1);

        assertNull(next);
    }

    @Test
    void treeifyTest() throws Exception {
        Map<InvalidKeyWrapper, Integer> map = new HashMap<>();

        Class<?> treeNodeClass = innerClassesHashMap.get("TreeNode");

        boolean isNotFound = true;

        for (int i = 0; i < 64; i++) {

            if (map.size() == 9) {
                map.put(InvalidKeyWrapper.getRundomKey("a"), i);
            } else {
                map.put(InvalidKeyWrapper.getRundomKey("a"), i);
            }

            table = extractTable((HashMap) map);

            Map.Entry node = Arrays.stream(table).filter(Objects::nonNull).findFirst().orElseThrow(AssertionError::new);

            if (isNotFound && node.getClass().equals(treeNodeClass)) {
                System.out.println("Treeify map size: " + map.size());
                isNotFound = false;
            }

        }
    }

    @Test
    void sameLast4bitsInHash() {
        map.put("DFHXR", 1);
        map.put("YSXFJ", 1);
        map.put("TUDDY", 1);
        map.put("AXVUH", 1);
        map.put("RUTWZ", 1);
        map.put("DEDUC", 1);
        map.put("WFCVW", 1);
        map.put("ZETCU", 1);
        map.put("GCVUR", 1);
    }

    @Test
    void initialCapacity() throws Exception {

        map = new HashMap<>(6);


        map.put("key1", 1);

        table = extractTable(map);
        assertEquals(8, table.length);

        map.put("key2", 2);
        map.put("key3", 3);
        map.put("key4", 4);
        map.put("key5", 5);
        map.put("key6", 6);
        map.put("key7", 7);

        table = extractTable(map);

        assertEquals(16, table.length);
    }


    @Test
    void nonOverrideHashCode() {

        BookStore bs = new BookStore();

        Book b = new Book();
        b.setIsbn("111");
        bs.addBook(b, 10);

        System.out.println(bs.getNumberOfCopies(b));
        Book b2 = new Book();
        b2.setIsbn("111");

        assertThrows(NullPointerException.class, () -> bs.getNumberOfCopies(b2));

        assertNull(bs.getNumberOfCopiesWithoutUnboxing(b2));
    }

    class Book {
        private String title, isbn;

        public boolean equals(Object o) {
            return (o instanceof Book && ((Book) o).isbn.equals(this.isbn));
        }

        public void setIsbn(String idbn) {
            this.isbn = isbn;
        }
    }

    class BookStore {
        Map<Book, Integer> map = new HashMap<>();

        int getNumberOfCopies(Book b) { //npe
            return map.get(b);
        }

        Integer getNumberOfCopiesWithoutUnboxing(Book b) { //npe
            return map.get(b);
        }

        void addBook(Book b, int numberofcopies) {
            map.put(b, numberofcopies);
        }
    }




    private static class Node<K, V> implements Map.Entry<K, V> {

        private K key;

        private V value;

        private Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            return this.value = value;
        }

        void setNext(Node<K, V> next) {
            this.next = next;
        }

        Node<K, V> getNext() {
            return next;
        }
    }

    private static class InvalidKeyWrapper {

        private final String key;

        InvalidKeyWrapper(String key) {
            this.key = key;
        }

        private static InvalidKeyWrapper getRundomKey(String prefix) {
            return new InvalidKeyWrapper(prefix + RandomStringUtils.random(10));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            InvalidKeyWrapper that = (InvalidKeyWrapper) o;
            return Objects.equals(key, that.key);
        }

        @Override
        public int hashCode() {
            return key.charAt(0);
        }
    }

    private Map<String, Class<?>> initHashMapInnerClasses(Map map) {
        Class<?>[] classes = map.getClass().getDeclaredClasses();

        return Arrays.stream(classes).collect(Collectors.toMap(Class::getSimpleName, Function.identity()));
    }


    private Map.Entry[] extractTable(HashMap map) throws Exception {

        Field tableField = map.getClass().getDeclaredField("table");
        tableField.setAccessible(true);

        return (Map.Entry[]) tableField.get(map);
    }
}
