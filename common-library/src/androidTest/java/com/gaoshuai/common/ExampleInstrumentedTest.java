package com.gaoshuai.common;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.gaoshuai.common_library.test", appContext.getPackageName());
    }

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
