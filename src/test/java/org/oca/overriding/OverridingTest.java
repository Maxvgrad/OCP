package org.oca.overriding;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class OverridingTest {


}

class BaseProcessor {
    protected Collection<Long> process() {
        return Collections.emptyList();
    }
}


class SubProcessor extends BaseProcessor {

    // 2. Java 5 onwards supports co-variant return types,
    // which means that an overriding method can declare any sub-class of the
    // return type declared in the overridden method as its return type.
    // However, long is not a subclass of int. Even Long is not a sub class of Integer. So option 2 is wrong.

    @Override
    protected List<Long> process() {
        return Collections.emptyList();
    }
}
