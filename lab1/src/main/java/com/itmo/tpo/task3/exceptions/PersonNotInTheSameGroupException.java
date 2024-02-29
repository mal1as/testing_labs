package com.itmo.tpo.task3.exceptions;

import com.itmo.tpo.task3.model.impl.Person;

public class PersonNotInTheSameGroupException extends RuntimeException {

    private Person person1;
    private Person person2;

    public PersonNotInTheSameGroupException(Person person1, Person person2) {
        this.person1 = person1;
        this.person2 = person2;
    }

    public PersonNotInTheSameGroupException() {

    }

    @Override
    public String getMessage() {
        return person1.description() + " и " + person2.description() + " не находятся в одной группе. " +
                "Взаимодействие с членом не входящим в текущую группу невозможно.";
    }
}
