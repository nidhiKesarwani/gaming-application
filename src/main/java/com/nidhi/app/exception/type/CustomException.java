
/*
 * Copyright (c) 2021
 */

package com.nidhi.app.exception.type;

import lombok.Data;
import org.apache.logging.log4j.message.ParameterizedMessage;

@Data
public class CustomException extends RuntimeException {


    private final ExceptionType expType;
    private final String expCode;

    public CustomException(final ExceptionType expType, final String expCodeThrown) {
        this(expType, expCodeThrown, null);
    }

    public CustomException(final ExceptionType expType, final String expCodeThrown, final Throwable exp) {
        super(exp);
        this.expCode = expCodeThrown;
        this.expType = expType;
    }

}

