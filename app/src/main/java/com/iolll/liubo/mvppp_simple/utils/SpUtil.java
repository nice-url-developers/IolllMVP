package com.iolll.liubo.mvppp_simple.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Date：2018/2/28
 * Time：14:09
 * author：CH
 * sharePreference保存对象数据、泛型
 */

public class SpUtil {
    private static final String DEFAULT_SP_NAME = "data_sp";

    // 通过类名字去获取一个对象
    public static <T> T getObject(Context context, Class<T> clazz) {
        String key = getKey(clazz);
        String json = getString(context, key, null);
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        try {
            Gson gson = new Gson();
            return gson.fromJson(json, clazz);
        } catch (Exception e) {
            return null;
        }
    }
     // 通过类名字去获取一个对象
    public static <T> T getObject( Class<T> clazz) {
        String key = getKey(clazz);
        String json = getString(Utils.getContext(), key, null);
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        try {
            Gson gson = new Gson();
            return gson.fromJson(json, clazz);
        } catch (Exception e) {
            return null;
        }
    }

    // 通过Type去获取一个泛型对象
    public static <T> T getObject(Context context, Type type) {
        String key = getKey(type);
        String json = getString(context, key, null);
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        try {
            Gson gson = new Gson();
            return gson.fromJson(json, type);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 保存一个对象，object必须是普通类，而不是泛型，如果是泛型,请使用 {@link SpUtil#putObject(Context, Object, Type)}
     *
     * @param context
     * @param object
     */
    public static void putObject(Context context, Object object) {
        String key = getKey(object.getClass());//没有对object对象判空
        Gson gson = new Gson();
        String json = gson.toJson(object);
        putString(context, key, json);
    }

    /**
     * 如果需要保存的对象为null时，尽量使用三个参数的putObject()方法
     * 为了保证能一切正常，我们需要新增一个参数，这样的话key就不会出问题
     */
    public static void putObject(Context context, Object object, Class<?> clazz) {
        String key;
        if (object == null) {//如果需要存储的对象为null
            key = getKey(clazz);
        }else {
            key = getKey(object.getClass());
        }
        Gson gson = new Gson();
        String json = gson.toJson(object);
        putString(context, key, json);
    }

    /**
     * 保存一个泛型对象
     *
     * @param context
     * @param object
     * @param type    如果你要保存 List<Person> 这个类, type应该 传入 new TypeToken<List<Person>>() {}.getType()
     */
    public static void putObject(Context context, Object object, Type type) {
        String key = getKey(type);
        Gson gson = new Gson();
        String json = gson.toJson(object);
        putString(context, key, json);
    }

    public static void removeObject(Context context, Class<?> clazz) {
        remove(context, getKey(clazz));
    }

    public static void removeObject(Context context, Type type) {
        remove(context, getKey(type));
    }

    public static String getKey(Class<?> clazz) {
        return clazz.getName();
    }

    public static String getKey(Type type) {
        return type.toString();
    }

    public static void remove(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(DEFAULT_SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.remove(key);
        edit.commit();
    }

    public static void putString(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(DEFAULT_SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key, value);
        edit.commit();
    }

    public static String getString(Context context, String key, String defValue) {
        SharedPreferences sp = context.getSharedPreferences(DEFAULT_SP_NAME, Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }
}
