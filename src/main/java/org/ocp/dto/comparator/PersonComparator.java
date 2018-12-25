package org.ocp.dto.comparator;

import org.ocp.dto.PersonDto;

import java.util.Comparator;

public class PersonComparator implements Comparator<PersonDto> {

    @Override
    public int compare(PersonDto p1, PersonDto p2) {
        return p1.getInterest().hashCode() - p2.getInterest().hashCode();
    }
}
