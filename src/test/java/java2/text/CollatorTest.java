package java2.text;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CollatorTest {

    private Collator collator;

    private List<String> uits;

    @BeforeEach
    void setUp() {
        uits = setUpUits();

        collator = Collator.getInstance(Locale.US);
    }

    @Test
    void getInstance() {
        collator = Collator.getInstance();

        assertEquals(Collator.TERTIARY, collator.getStrength());
        assertEquals(Collator.NO_DECOMPOSITION, collator.getDecomposition());
        assertTrue(collator instanceof Comparator);
    }

    @Test
    void getAvailableLocales() {

        Locale[] locales = Collator.getAvailableLocales();

        assertEquals(160, locales.length);
    }

    @Test
    void sort() {

        collator = Collator.getInstance();

        System.out.println(uits);

        uits.sort(collator);

        System.out.println(uits);
    }


    //s, S & SS,ß
    @Test
    void compareStrengthPrimary() {
        collator.setStrength(Collator.PRIMARY);

        assertEquals(-1, collator.compare("1", "2"));
        assertEquals(-1, collator.compare("1", "a"));
        assertEquals(0, collator.compare("a", "A"));
        assertEquals(0, collator.compare("ß", "SS"));
        assertEquals(-1, collator.compare("S", "SS"));
        assertEquals(-1, collator.compare("S", "ß"));
        assertEquals(1, collator.compare("ac", "Ab"));
        assertEquals(0, collator.compare("aA", "Aa"));
    }

    @Test
    void compareStrengthSecondary() {
        collator.setStrength(Collator.SECONDARY);

        assertEquals(-1, collator.compare("1", "2"));
        assertEquals(-1, collator.compare("1", "a"));
        assertEquals(0, collator.compare("a", "A"));
        assertEquals(0, collator.compare("ß", "SS"));
        assertEquals(-1, collator.compare("S", "SS"));
        assertEquals(-1, collator.compare("S", "ß"));
        assertEquals(1, collator.compare("ac", "Ab"));
    }

    @Test
    void compareStrengthTertiary() {
        collator.setStrength(Collator.TERTIARY);

        assertEquals(-1, collator.compare("1", "2"));
        assertEquals(-1, collator.compare("1", "a"));
        assertEquals(-1, collator.compare("a", "A"));
        assertEquals(1, collator.compare("ß", "SS"));
        assertEquals(-1, collator.compare("S", "SS"));
        assertEquals(-1, collator.compare("S", "ß"));
        assertEquals(1, collator.compare("ac", "Ab"));
    }

    @Test
    void compareStrengthIdentical() {
        collator.setStrength(Collator.IDENTICAL);

        assertEquals(-1, collator.compare("1", "2"));
        assertEquals(-1, collator.compare("1", "a"));
        assertEquals(-1, collator.compare("a", "A"));
        assertEquals(1, collator.compare("ß", "SS"));
        assertEquals(-1, collator.compare("S", "SS"));
        assertEquals(-1, collator.compare("S", "ß"));
        assertEquals(1, collator.compare("ac", "Ab"));
    }

    private List<String> setUpUits() {

        uits = new ArrayList<>(20);

        uits.add("AcqLdBSg87qRx");
        uits.add("1C6ZJYYER4NgG");
        uits.add("1anPGQEqcNrsk");
        uits.add("aae5EFctkTIKD");
        uits.add("BH764IS9xnSrk");
        uits.add("2AfaQSqZ4oYQx");
        uits.add("a6KTwtBMCKz8X");
        uits.add("bFWFzgTnNq2nK");

        return uits;
    }
}
