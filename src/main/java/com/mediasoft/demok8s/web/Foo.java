package com.mediasoft.demok8s.web;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Foo {

    private Status status;

    private String msg;

    public enum Status {
        OK,
        FAIL
    }
}
