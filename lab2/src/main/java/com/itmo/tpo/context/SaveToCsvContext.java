package com.itmo.tpo.context;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SaveToCsvContext {

    @NonNull
    private String filePath;
    @NonNull
    private Double start;
    @NonNull
    private Double limit;
    @NonNull
    private Double step;
    @Builder.Default
    private String delimiter = ", ";
    @Builder.Default
    private Integer decimalPlaces = 3;
}
