
/*
 * Copyright (c) 2021
 */

package com.nidhi.app.exception.type;

import lombok.Data;
import org.apache.logging.log4j.message.ParameterizedMessage;

@Data
public class CustomException extends RuntimeException {

    private static final CustomExceptionCodeReader EXP_CODE_READER = new CustomExceptionCodeReader();
    private final ExceptionType expType;
    private final String expCode;
    private final Object[] replaceableMsgParams;
    private String expMessage;

    public CustomException(final ExceptionType expType, final String expCodeThrown, final Object... replaceableMsgParams) {
        this(expType, expCodeThrown, null, replaceableMsgParams);
    }

    public CustomException(final ExceptionType expType, final String expCodeThrown, final Throwable exp, final Object... replaceableMsgParams) {
        super(exp);
        this.expCode = expCodeThrown;
        this.replaceableMsgParams = replaceableMsgParams;
        this.expType = expType;
    }

    public String getMessage() {
        return ParameterizedMessage.format(EXP_CODE_READER.exchangeExpCode(expCode), replaceableMsgParams);
    }
}

