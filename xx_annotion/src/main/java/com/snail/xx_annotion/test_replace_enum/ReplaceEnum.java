package com.snail.xx_annotion.test_replace_enum;

import androidx.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 以注解替换枚举
 */
public class ReplaceEnum {

    //枚举方式
    enum WeekDay {
        Staturday, Sunday;
    }

    public static WeekDay mCurrentDay;

    public static void setDay(WeekDay weekDay){
        mCurrentDay = weekDay;
    }

    //注解方式
    private final static int Saturday = 6;
    private final static int Sunday = 7;

    @IntDef({Saturday,Sunday})
    @Target({ElementType.FIELD,ElementType.PARAMETER})
    @Retention(RetentionPolicy.SOURCE)
    @interface WekDay{

    }

    @WekDay
    public static int mCurDay;

    public static void setCurDay(@WekDay int curDay){
        mCurDay = curDay;
    }

    public static void main(String[] args) {
        setDay(WeekDay.Sunday);

        setCurDay(Saturday);
    }

}
