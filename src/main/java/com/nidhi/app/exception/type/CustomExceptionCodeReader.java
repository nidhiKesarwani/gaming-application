
/*
 * Copyright (c) 2021
 */

package com.nidhi.app.exception.type;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Properties;

public class CustomExceptionCodeReader {
    private static final String EXP_CODE_FILE = "exp_codes.properties";
    private static final Properties READ_EXP_CODES = new Properties();

    public CustomExceptionCodeReader() {
        try (InputStream fStream = CustomExceptionCodeReader.class.getClassLoader().getResourceAsStream(EXP_CODE_FILE)) {

            // exception code file must be present in class path.....
            if (fStream == null)
                throw new FileNotFoundException(EXP_CODE_FILE + " not found in class path........");

            // load the defined codes
            READ_EXP_CODES.load(fStream);

            // assert to check the exception code file is not empty
            if (READ_EXP_CODES.size() == 0)
                throw new NoSuchElementException(
                        "No Exception Code is defined, Please add required Exception Code in \"" + EXP_CODE_FILE + "\" file.");
        } catch (final IOException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    public String exchangeExpCode(final String expCode) {
        return READ_EXP_CODES.getProperty(expCode);
    }


}
