package com.itmo.tpo.task3.model.impl;

import com.itmo.tpo.task3.model.Describable;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.Objects;

@Data
@Builder
public class Passage implements Describable {

    @NonNull
    private String name;
    @NonNull
    private Environment thisSide;
    @NonNull
    private Environment otherSide;
    private boolean locked;
    private String secretSwitch;

    public boolean tryUnlock(@NonNull String probablySecretSwitch) {
        if(probablySecretSwitch.equals(secretSwitch)) {
            locked = false;
        }
        return Objects.isNull(secretSwitch) || probablySecretSwitch.equals(secretSwitch);
    }

    @Override
    public String description() {
        return getName();
    }
}
