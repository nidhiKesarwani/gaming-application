
/*
 * Copyright (c) 2021
 */

package com.nidhi.app.exception.handler;

import com.nidhi.app.exception.type.CustomException;
import com.nidhi.app.exception.type.ExceptionType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * The Constant log.
     */
    private static final Logger log = LogManager.getLogger(GlobalExceptionHandler.class.getName());

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExpMsg> anyException(final RuntimeException ex, WebRequest request) throws IOException {
        log.error(ex.getMessage(), ex);

        if (!Objects.isNull(ex.getClass())) {

            if (ex instanceof CustomException) {
                CustomException ce = (CustomException) ex;
                if (ExceptionType.USERDEFINED.equals(ce.getExpType())) {
                    return new ResponseEntity(new ExpMsg(ce.getExpCode(), ce.getMessage(), ce.getMessage(), new Date(),
                            ExceptionType.USERDEFINED.name(), request.getDescription(true)), HttpStatus.EXPECTATION_FAILED);
                } else {
                    return new ResponseEntity(new ExpMsg(ce.getExpCode(), ex.getMessage(), "Internal server exception. Please contact support.",
                            new Date(), ExceptionType.TECHNICAL.name(), request.getDescription(true)), HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        }
        return new ResponseEntity(new ExpMsg("IntSerExp", ex.getClass().getTypeName(),
                ex.getMessage() != null ? ex.getMessage() : "No exception message available.", new Date(), ExceptionType.TECHNICAL.name(),
                request.getDescription(true)), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExpMsg> anyOtherException(final Exception ex, WebRequest request) throws IOException {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity(new ExpMsg("IntSerExp", ex.getMessage() != null ? ex.getMessage() : "No exception message available.", "",
                new Date(), ExceptionType.TECHNICAL.name(), request.getDescription(true)), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    class ExpMsg {
        private String code;
        private String msg;
        private String businessMsg;
        private Date time;
        private String type;
        private Object data;

        public ExpMsg(String code, String msg, String businessMsg, Date time, String type, Object data) {
            super();
            this.code = code;
            this.msg = msg;
            this.businessMsg = businessMsg;
            this.time = time;
            this.type = type;
            this.data = data;
        }

        public final String getCode() {
            return code;
        }

        public final void setCode(String code) {
            this.code = code;
        }

        public final String getMsg() {
            return msg;
        }

        public final void setMsg(String msg) {
            this.msg = msg;
        }

        public final String getBusinessMsg() {
            return businessMsg;
        }

        public final void setBusinessMsg(String businessMsg) {
            this.businessMsg = businessMsg;
        }

        public final Date getTime() {
            return time;
        }

        public final void setTime(Date time) {
            this.time = time;
        }

        public final String getType() {
            return type;
        }

        public final void setType(String type) {
            this.type = type;
        }

        public final Object getData() {
            return data;
        }

        public final void setData(Object data) {
            this.data = data;
        }

    }

}
