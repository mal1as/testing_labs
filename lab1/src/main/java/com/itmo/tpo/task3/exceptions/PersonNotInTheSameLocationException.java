package com.itmo.tpo.task3.exceptions;

import com.itmo.tpo.task3.model.impl.Group;
import com.itmo.tpo.task3.model.impl.Person;

public class PersonNotInTheSameLocationException extends RuntimeException {

    private Person person;
    private Group group;

    public PersonNotInTheSameLocationException(Person person, Group group) {
        this.person = person;
        this.group = group;
    }

    @Override
    public String getMessage() {
        return person.description() + " не находится в той же локации, что и группа " + group.description();
    }
}
