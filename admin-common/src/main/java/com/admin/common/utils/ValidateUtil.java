package com.admin.common.utils;

import java.util.Collection;
import java.util.Map;

/**
 * @description: 校验工具
 * @author: Zhaotianyi
 * @time: 2022/1/10 15:30
 */
public class ValidateUtil {
    /** IPV4正则表达式 */
    public static final String REGEX_IPV4 = "^((25[0-5]|2[0-4]\\d|[0-1]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[0-1]?\\d\\d?)$";
    /** IPV6正则表达式 */
    public static final String REGEX_IPV6 = "^\\s*((([0-9A-Fa-f]{1,4}:){7}(([0-9A-Fa-f]{1,4})|:))|(([0-9A-Fa-f]{1,4}:){6}(:|((25[0-5]|2[0-4]\\d|[01]?\\d{1,2})(\\.(25[0-5]|2[0-4]\\d|[01]?\\d{1,2})){3})|(:[0-9A-Fa-f]{1,4})))|(([0-9A-Fa-f]{1,4}:){5}((:((25[0-5]|2[0-4]\\d|[01]?\\d{1,2})(\\.(25[0-5]|2[0-4]\\d|[01]?\\d{1,2})){3})?)|((:[0-9A-Fa-f]{1,4}){1,2})))|(([0-9A-Fa-f]{1,4}:){4}(:[0-9A-Fa-f]{1,4}){0,1}((:((25[0-5]|2[0-4]\\d|[01]?\\d{1,2})(\\.(25[0-5]|2[0-4]\\d|[01]?\\d{1,2})){3})?)|((:[0-9A-Fa-f]{1,4}){1,2})))|(([0-9A-Fa-f]{1,4}:){3}(:[0-9A-Fa-f]{1,4}){0,2}((:((25[0-5]|2[0-4]\\d|[01]?\\d{1,2})(\\.(25[0-5]|2[0-4]\\d|[01]?\\d{1,2})){3})?)|((:[0-9A-Fa-f]{1,4}){1,2})))|(([0-9A-Fa-f]{1,4}:){2}(:[0-9A-Fa-f]{1,4}){0,3}((:((25[0-5]|2[0-4]\\d|[01]?\\d{1,2})(\\.(25[0-5]|2[0-4]\\d|[01]?\\d{1,2})){3})?)|((:[0-9A-Fa-f]{1,4}){1,2})))|(([0-9A-Fa-f]{1,4}:)(:[0-9A-Fa-f]{1,4}){0,4}((:((25[0-5]|2[0-4]\\d|[01]?\\d{1,2})(\\.(25[0-5]|2[0-4]\\d|[01]?\\d{1,2})){3})?)|((:[0-9A-Fa-f]{1,4}){1,2})))|(:(:[0-9A-Fa-f]{1,4}){0,5}((:((25[0-5]|2[0-4]\\d|[01]?\\d{1,2})(\\.(25[0-5]|2[0-4]\\d|[01]?\\d{1,2})){3})?)|((:[0-9A-Fa-f]{1,4}){1,2})))|(((25[0-5]|2[0-4]\\d|[01]?\\d{1,2})(\\.(25[0-5]|2[0-4]\\d|[01]?\\d{1,2})){3})))(%.+)?\\s*$";
    /** 中文正则表达式 */
    public static final String REGEX_CHINESE = "[\u4e00-\u9fa5]*";
    /** 邮箱正则表达式 */
    public static final String REGEX_EMAIL = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
    /** URL正则表达式 */
    public static final String REGEX_URL = "http://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
    /** 手机号码正则表达式 */
    public static final String REGEX_PHONE = "\\d{11}";
    /** 登录名称正则表达式(不包括特殊符号 3-20位长度) */
    public static final String REGEX_LOGIN_NAME = "/^([A-Za-z0-9]{3,20})$/";
    /** 密码正则表达式(6-20长度) */
    public static final String REGEX_PASS_WORD = "^([A-Z]|[a-z]|[0-9]|[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*——+|{}‘；：”“'。，、？]){6,20}$";
    /** Sql正则表达式 */
    public static final String REGEX_SQL_INJECTION = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"
            + "(\\b(select|update|union|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";

    /**
     * 值为null、长度为0（空串/无元素）.
     */
    public static boolean isEmpty(Object validate) {
        if (validate == null) {
            return true;
        }
        if (validate instanceof String) {
            if (((String) validate).length() == 0) {
                return true;
            }
        } else if (validate instanceof Map) {
            if (((Map<?, ?>) validate).size() == 0) {
                return true;
            }
        } else if (validate instanceof Collection<?>) {
            if (((Collection<?>) validate).size() == 0) {
                return true;
            }
        } else if (validate instanceof Object[]) {
            if (((Object[]) validate).length == 0) {
                return true;
            }
        } else if (validate instanceof byte[]) {
            if (((byte[]) validate).length == 0) {
                return true;
            }
        } else if (validate instanceof short[]) {
            if (((short[]) validate).length == 0) {
                return true;
            }
        } else if (validate instanceof int[]) {
            if (((int[]) validate).length == 0) {
                return true;
            }
        } else if (validate instanceof long[]) {
            if (((long[]) validate).length == 0) {
                return true;
            }
        } else if (validate instanceof float[]) {
            if (((float[]) validate).length == 0) {
                return true;
            }
        } else if (validate instanceof double[]) {
            if (((double[]) validate).length == 0) {
                return true;
            }
        } else if (validate instanceof boolean[]) {
            if (((boolean[]) validate).length == 0) {
                return true;
            }
        } else if (validate instanceof char[]) {
            if (((char[]) validate).length == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 值为null、长度为0（空串/无元素）、全空白（字符串全为空白字符/元素全为null/Map元素的值全为null）.
     */
    public static boolean isBlank(Object validate) {
        if (isEmpty(validate)) {
            return true;
        }
        if (validate instanceof String) {
            String s = (String) validate;
            int strLen = s.length();
            for (int i = 0; i < strLen; i++) {
                if (!Character.isWhitespace(s.charAt(i))) {    //非空白字符
                    return false;
                }
            }
        } else if (validate instanceof Map<?, ?>) {
            Map<?, ?> map = (Map<?, ?>) validate;
            for (Object key : map.keySet()) {
                if (map.get(key) != null) {
                    return false;
                }
            }
        } else if (validate instanceof Collection<?>) {
            Collection<?> cs = (Collection<?>) validate;
            for (Object object : cs) {
                if (object != null) {
                    return false;
                }
            }
        } else if (validate instanceof Object[]) {
            Object[] os = (Object[]) validate;
            for (Object object : os) {
                if (object != null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 值为null、长度为0（空串/无元素）、包含空白（字符串包含空白字符/元素含有null值/Map元素的值含有null值）.
     */
    public static boolean hasBlank(Object validate) {
        if (isEmpty(validate)) {
            return true;
        }
        if (validate instanceof String) {
            String s = (String) validate;
            int strLen = s.length();
            for (int i = 0; i < strLen; i++) {
                if (Character.isWhitespace(s.charAt(i))) {    //空白字符
                    return true;
                }
            }
        } else if (validate instanceof Map<?, ?>) {
            Map<?, ?> map = (Map<?, ?>) validate;
            for (Object key : map.keySet()) {
                if (map.get(key) == null) {
                    return true;
                }
            }
        } else if (validate instanceof Collection<?>) {
            Collection<?> cs = (Collection<?>) validate;
            for (Object object : cs) {
                if (object == null) {
                    return true;
                }
            }
        } else if (validate instanceof Object[]) {
            Object[] os = (Object[]) validate;
            for (Object object : os) {
                if (object == null) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 值为null、长度为0、元素含有null值x.
     */
    public static boolean hasNull(Object... validates) {
        return hasBlank(validates);
    }

    /**
     * 值非null、长度非0且小于等于maxLength.
     */
    public static boolean isMaxLength(Object validate, int maxLength) {
        if (isEmpty(validate)) {
            return false;
        }
        if (validate instanceof String) {
            String s = (String) validate;
            if (s.length() > maxLength) {
                return false;
            }
        } else if (validate instanceof Map<?, ?>) {
            Map<?, ?> map = (Map<?, ?>) validate;
            if (map.size() > maxLength) {
                return false;
            }
        } else if (validate instanceof Collection<?>) {
            Collection<?> cs = (Collection<?>) validate;
            if (cs.size() > maxLength) {
                return false;
            }
        } else if (validate instanceof Object[]) {
            Object[] os = (Object[]) validate;
            if (os.length > maxLength) {
                return false;
            }
        } else if (validate instanceof byte[]) {
            byte[] os = (byte[]) validate;
            if (os.length > maxLength) {
                return false;
            }
        } else if (validate instanceof short[]) {
            short[] os = (short[]) validate;
            if (os.length > maxLength) {
                return false;
            }
        } else if (validate instanceof int[]) {
            int[] os = (int[]) validate;
            if (os.length > maxLength) {
                return false;
            }
        } else if (validate instanceof long[]) {
            long[] os = (long[]) validate;
            if (os.length > maxLength) {
                return false;
            }
        } else if (validate instanceof float[]) {
            float[] os = (float[]) validate;
            if (os.length > maxLength) {
                return false;
            }
        } else if (validate instanceof double[]) {
            double[] os = (double[]) validate;
            if (os.length > maxLength) {
                return false;
            }
        } else if (validate instanceof boolean[]) {
            boolean[] os = (boolean[]) validate;
            if (os.length > maxLength) {
                return false;
            }
        } else if (validate instanceof char[]) {
            char[] os = (char[]) validate;
            if (os.length > maxLength) {
                return false;
            }
        }
        return true;
    }

    /**
     * 值非null、长度非0且大于等于minLength.
     */
    public static boolean isMinLength(Object validate, int minLength) {
        if (isEmpty(validate)) {
            return false;
        }
        if (validate instanceof String) {
            String s = (String) validate;
            if (s.length() < minLength) {
                return false;
            }
        } else if (validate instanceof Map<?, ?>) {
            Map<?, ?> map = (Map<?, ?>) validate;
            if (map.size() < minLength) {
                return false;
            }
        } else if (validate instanceof Collection<?>) {
            Collection<?> cs = (Collection<?>) validate;
            if (cs.size() < minLength) {
                return false;
            }
        } else if (validate instanceof Object[]) {
            Object[] os = (Object[]) validate;
            if (os.length < minLength) {
                return false;
            }
        } else if (validate instanceof byte[]) {
            byte[] os = (byte[]) validate;
            if (os.length < minLength) {
                return false;
            }
        } else if (validate instanceof short[]) {
            short[] os = (short[]) validate;
            if (os.length < minLength) {
                return false;
            }
        } else if (validate instanceof int[]) {
            int[] os = (int[]) validate;
            if (os.length < minLength) {
                return false;
            }
        } else if (validate instanceof long[]) {
            long[] os = (long[]) validate;
            if (os.length < minLength) {
                return false;
            }
        } else if (validate instanceof float[]) {
            float[] os = (float[]) validate;
            if (os.length < minLength) {
                return false;
            }
        } else if (validate instanceof double[]) {
            double[] os = (double[]) validate;
            if (os.length < minLength) {
                return false;
            }
        } else if (validate instanceof boolean[]) {
            boolean[] os = (boolean[]) validate;
            if (os.length < minLength) {
                return false;
            }
        } else if (validate instanceof char[]) {
            char[] os = (char[]) validate;
            if (os.length < minLength) {
                return false;
            }
        }
        return true;
    }

    /**
     * 值非null、长度在minLength与maxLength之间.
     */
    public static boolean isBetweenLength(Object validate, int minLength, int maxLength) {
        if (validate == null) {
            return false;
        }
        if (minLength > maxLength) {
            throw new RuntimeException();
        }
        if (validate instanceof String) {
            String s = (String) validate;
            if (s.length() < minLength || s.length() > maxLength) {
                return false;
            }
        } else if (validate instanceof Map<?, ?>) {
            Map<?, ?> map = (Map<?, ?>) validate;
            if (map.size() < minLength || map.size() > maxLength) {
                return false;
            }
        } else if (validate instanceof Collection<?>) {
            Collection<?> cs = (Collection<?>) validate;
            if (cs.size() < minLength || cs.size() > maxLength) {
                return false;
            }
        } else if (validate instanceof Object[]) {
            Object[] os = (Object[]) validate;
            if (os.length < minLength || os.length > maxLength) {
                return false;
            }
        } else if (validate instanceof byte[]) {
            byte[] os = (byte[]) validate;
            if (os.length < minLength || os.length > maxLength) {
                return false;
            }
        } else if (validate instanceof short[]) {
            short[] os = (short[]) validate;
            if (os.length < minLength || os.length > maxLength) {
                return false;
            }
        } else if (validate instanceof int[]) {
            int[] os = (int[]) validate;
            if (os.length < minLength || os.length > maxLength) {
                return false;
            }
        } else if (validate instanceof long[]) {
            long[] os = (long[]) validate;
            if (os.length < minLength || os.length > maxLength) {
                return false;
            }
        } else if (validate instanceof float[]) {
            float[] os = (float[]) validate;
            if (os.length < minLength || os.length > maxLength) {
                return false;
            }
        } else if (validate instanceof double[]) {
            double[] os = (double[]) validate;
            if (os.length < minLength || os.length > maxLength) {
                return false;
            }
        } else if (validate instanceof boolean[]) {
            boolean[] os = (boolean[]) validate;
            if (os.length < minLength || os.length > maxLength) {
                return false;
            }
        } else if (validate instanceof char[]) {
            char[] os = (char[]) validate;
            if (os.length < minLength || os.length > maxLength) {
                return false;
            }
        }
        return true;
    }


    //***************************************** 字符串 *****************************************************

    /**
     * 值非null、非空串、且匹配正则表达式.
     */
    public static boolean isMatches(String validate, String regex) {
        if (isEmpty(validate)) {
            return false;
        }
        return validate.matches(regex) ? true : false;
    }


    //***************************************** 数字 *****************************************************

    /**
     * 值为null、0.
     */
    public static boolean isNullOrZero(Number validate) {
        if (validate == null) {
            return true;
        }
        if ("0".equals(validate.toString()) || "0.0".equals(validate.toString())) {
            return true;
        }
        return false;
    }

    /**
     * 值非null、且小于等于maxNumber.
     */
    public static boolean isMax(Integer validate, int maxNumber) {
        if (validate == null) {
            return false;
        }
        return validate <= maxNumber ? true : false;
    }

    /**
     * 值非null、且大于等于minNumber.
     */
    public static boolean isMin(Integer validate, int minNumber) {
        if (validate == null) {
            return false;
        }
        return validate >= minNumber ? true : false;
    }

    /**
     * 值非null、且在minNumber与maxNumber之间.
     */
    public static boolean isBetween(Integer validate, int minNumber, int maxNumber) {
        if (validate == null) {
            return false;
        }
        return validate < minNumber || validate > maxNumber ? false : true;
    }

    /**
     * 值非null、且在in范围内.
     */
    public static boolean in(Integer validate, int... in) {
        if (validate == null) {
            return false;
        }
        for (int i : in) {
            if (validate == i) {
                return true;
            }
        }
        return false;
    }

    /**
     * 数组非null、且所有元素非null、并所有元素在min和max范围内、如果length!=0则限制数组长度为length.
     */
    public static boolean isBetween(double min, double max, int length, Double... validates) {
        if (validates == null) {
            return false;
        }
        if (length != 0 && validates.length != length) {
            return false;
        }
        for (Double d : validates) {
            if (d == null) {
                return false;
            }
            if (d < min || d > max) {
                return false;
            }
        }
        return true;
    }
}
