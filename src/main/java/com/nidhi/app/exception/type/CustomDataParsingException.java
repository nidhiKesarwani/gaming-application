
/*
 * Copyright (c) 2021
 */

package com.nidhi.app.exception.type;

public class CustomDataParsingException extends CustomException {

    private static final String EXP_CD_PREFIX = "CUSTOM_DATA_PARSE_EXCEPTION";

    public CustomDataParsingException(final ExceptionType expType, final String expCodeThrown, final Object... replaceableMsgParams) {
        super(expType, expCodeThrown, replaceableMsgParams);
    }

    public CustomDataParsingException(final ExceptionType expType, final String expCodeThrown, final Throwable exp,
                                      final Object... replaceableMsgParams) {
        super(expType, expCodeThrown, exp, replaceableMsgParams);
    }

    protected String getExceptionCodePrefix() {
        return EXP_CD_PREFIX;
    }
}


