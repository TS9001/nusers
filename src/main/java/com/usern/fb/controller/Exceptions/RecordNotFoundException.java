package com.usern.fb.controller.Exceptions;

import java.util.function.Supplier;

public class RecordNotFoundException implements Supplier<String> {
    public RecordNotFoundException(String userFbId) {
    }

    @Override
    public String get() {
        return null;
    }
}
