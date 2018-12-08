package com.aleks.aleksiev.codewars.utils

import android.os.Bundle
import java.io.Serializable
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

sealed class BundleDelegate<T>(protected val key: kotlin.String) : ReadWriteProperty<Bundle, T> {

    class BundleInt(key: kotlin.String, private val defaultValue: kotlin.Int = 0) : BundleDelegate<Int>(key) {

        override fun getValue(thisRef: Bundle, property: KProperty<*>): kotlin.Int = thisRef.getInt(key, defaultValue)

        override fun setValue(thisRef: Bundle, property: KProperty<*>, value: kotlin.Int) = thisRef.putInt(key, value)
    }

    class BundleLong(key: kotlin.String, private val defaultValue: kotlin.Long = 0L) : BundleDelegate<Long>(key) {

        override fun getValue(thisRef: Bundle, property: KProperty<*>): kotlin.Long = thisRef.getLong(key, defaultValue)

        override fun setValue(thisRef: Bundle, property: KProperty<*>, value: kotlin.Long) = thisRef.putLong(key, value)
    }

    class BundleString(key: kotlin.String, private val defaultValue: kotlin.String = Constants.EMPTY_STRING) : BundleDelegate<String>(key) {

        override fun getValue(thisRef: Bundle, property: KProperty<*>): kotlin.String = thisRef.getString(key, defaultValue)

        override fun setValue(thisRef: Bundle, property: KProperty<*>, value: kotlin.String) = thisRef.putString(key, value)
    }

    class BundleBoolean(key: kotlin.String, private val defaultValue: kotlin.Boolean = false) : BundleDelegate<Boolean>(key) {

        override fun getValue(thisRef: Bundle, property: KProperty<*>): kotlin.Boolean = thisRef.getBoolean(key, defaultValue)

        override fun setValue(thisRef: Bundle, property: KProperty<*>, value: kotlin.Boolean) = thisRef.putBoolean(key, value)
    }

    class BundleSerializable(key: kotlin.String) : BundleDelegate<Serializable?>(key) {

        override fun getValue(thisRef: Bundle, property: KProperty<*>): Serializable? = thisRef.getSerializable(key)

        override fun setValue(thisRef: Bundle, property: KProperty<*>, value: Serializable?) {
            value?.let {
                thisRef.putSerializable(key, value)
            }
        }
    }
}