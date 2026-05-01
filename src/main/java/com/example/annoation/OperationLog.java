package com.example.annoation;

import com.example.eums.OperationAction;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationLog {

    OperationAction operation();

    String entity();

    String description() default "{operator} {op} {entity}，ID: {targetId}";
}
