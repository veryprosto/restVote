package ru.veryprosto.restVote.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public final class Util {

    public static String safetyConvertToUTF8(String base) {
        if (base == null) {
            return null;
        }
        if (Charset.forName("ISO_8859_1").newEncoder().canEncode(base)) {
            try {
                return new String(base.getBytes("ISO_8859_1"), StandardCharsets.UTF_8);
            } catch (UnsupportedEncodingException e) {
                return base;
            }
        } else {
            return base;
        }
    }
}