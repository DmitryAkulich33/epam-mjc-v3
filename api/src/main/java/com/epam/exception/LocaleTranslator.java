package com.epam.exception;

public interface LocaleTranslator {
    String getString(String msgCode);
    String getString(String msgCode, Object... args);
}
