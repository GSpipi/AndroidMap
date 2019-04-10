package com.gaoshuai.common;

import android.util.Log;

import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void test(){
        try {
            boolean debug = (boolean) getStaticFiledValue(Class.forName("com.gaoshuai.business.BuildConfig"), "isDebug");
            String nioType = (String) getStaticFiledValue(Class.forName("com.gaoshuai.business.BuildConfig"), "nioType");
            Log.e("DBNameHelper", "debug:" + debug);
            Log.e("DBNameHelper", "nioType:" + nioType);
            System.out.println("debug:" + debug);
            System.out.println("nioType:" + nioType);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Object getStaticFiledValue(Class classType, String member) {
        Field field;
        while (classType != Object.class) {
            try {
                field = classType.getDeclaredField(member);
                field.setAccessible(true);
                return field.get(null);
            } catch (Exception e) {
                classType = classType.getSuperclass();
            }
        }
        return null;
    }
}