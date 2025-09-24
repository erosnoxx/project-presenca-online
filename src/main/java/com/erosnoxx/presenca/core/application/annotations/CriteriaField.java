package com.erosnoxx.presenca.core.application.annotations;

import java.lang.annotation.*;

@Target(ElementType.RECORD_COMPONENT)
@Retention(RetentionPolicy.RUNTIME)
public @interface CriteriaField {
    String value();
}
