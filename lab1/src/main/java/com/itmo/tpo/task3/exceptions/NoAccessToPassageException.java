package com.itmo.tpo.task3.exceptions;

import com.itmo.tpo.task3.model.Describable;
import com.itmo.tpo.task3.model.Movable;
import com.itmo.tpo.task3.model.impl.Passage;

public class NoAccessToPassageException extends RuntimeException {

    private Describable subject;
    private Passage passage;

    public NoAccessToPassageException(Describable subject, Passage passage) {
        this.subject = subject;
        this.passage = passage;
    }

    public NoAccessToPassageException(){

    }

    @Override
    public String getMessage() {
        return  subject.description() + "не имеет доступа  к " + passage.description() + ".";
    }

}
