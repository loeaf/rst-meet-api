package com.loeaf.common.misc;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class ReflectionUtile {
    /**
     *
     * @param klass Input Reflection Class
     * @param order Input Order, 0 or 1
     * @param <T>
     * @return
     */
    public static <T extends Enum<T> & EnumMaster> Map<String, String> getEnum2Map(
            Class<T> klass, Integer order) {

        Map<String, String> vals = new HashMap<String, String>(0);

        try {
            Method m = klass.getMethod("values", null);
            Object obj = m.invoke(null, null);

            for (Enum<T> enumval : (Enum<T>[]) obj) {
                if(order == 0) {
                    vals.put(enumval.toString(), enumval.name());
                } else {
                    vals.put(enumval.name(), enumval.toString());
                }
            }

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return vals;
    }

    /**
     * reflection이용.  domain의 fieldName의 값을 value로 설정
     * field가 없거나 오류 발생하면 아무런값도 set하지 않음
     * @param domain 도메인
     * @param fieldName 필드명
     * @param value 값
     * @since
     * 	20200811	init
     */
    public static void setFieldValue(Object domain, String fieldName, Object value) {
        //
        Field[] fields = domain.getClass().getDeclaredFields();

        //
        try {
            for(Field f : fields) {
                if(fieldName.equals(f.getName())) {
                    f.setAccessible(true);
                    f.set(domain, value);
                }
            }
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * reflection이용. domain의 fieldName의 값 추출
     * @param domain 도메일
     * @param fieldName 필드명
     * @return field의 값. 필드없거나 오류 발생하면 null 리턴
     * @since
     * 	20200811	init
     */
    public static Object getFieldValue(Object domain, String fieldName) {
        Object value = null;

        try {
            //
            Field[] fields = domain.getClass().getDeclaredFields();

            //
            for(Field f : fields) {
                if(fieldName.equals(f.getName())) {
                    f.setAccessible(true);
                    value = f.get(domain);
                    break;
                }
            }
        } catch (IllegalAccessException | IllegalArgumentException | SecurityException e) {
        }

        //
        return value;

    }

    /**
     * reflection이용. domain의 fieldName목록 추출
     * @param domain 도메일
     * @return fieldName목록. 오류발생|field가 없으면 빈 목록 리턴
     * @since
     * 	20200811	init
     */
    public static Set<String> getFieldNames(Object domain) {
        Set<String> fieldNames = new HashSet<>();


        try {
            Field[] fields = domain.getClass().getDeclaredFields();

            //
            for(Field f : fields) {
                fieldNames.add(f.getName());
            }

        } catch (IllegalArgumentException | SecurityException e) {
        }

        //
        return fieldNames;
    }


    /**
     * 모든 필드 목록 추출
     * 재귀호출. 부모의 필드 목록까지 추출
     * @param currentClass 클랙스
     * @param fields 필드 목록. 리턴값
     * @since 20200821	init
     */
    public static void getFieldsUpTo(Class<?> currentClass, List<Field> fields){

        if(null == currentClass) {
            return;
        }

        List<Field> list = Arrays.asList(currentClass.getDeclaredFields());
        if(Utils.isEmpty(list)) {
            return;
        }

        //
        fields.addAll(list);

        //
        Class<?> parentClass = currentClass.getSuperclass();
        if(null != parentClass) {
            getFieldsUpTo(parentClass, fields);
        }
    }
}
