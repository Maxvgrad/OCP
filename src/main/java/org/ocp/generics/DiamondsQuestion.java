package org.ocp.generics;

import org.ocp.Question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiamondsQuestion implements Question {

    @Override
    public void ask() throws Exception {

    }

    /**
     * stateCitiesMapInvalid This will not compile because you can't use a diamond operator within a generic type specification i.e.
     * <String, List<>> is wrong because of List<> being inside of another < and >.
     */
    private void testDiamonds() {
        Map<String , List<? extends CharSequence>> stateCitiesMap = new HashMap<String, List<? extends CharSequence>>();
//        Map<String , List<? extends CharSequence>> stateCitiesMapInvalid = new HashMap<String, List<>>();
        Map<String , List<? extends CharSequence>> stateCitiesMapValid = new HashMap<>();
        Map<String , List<? extends CharSequence>> stateCitiesMapUnchecked = new HashMap();
    }
}
