package ru.g000sha256.as_string

import java.lang.reflect.Field

val Any.asString: String
    get() = asString()

fun Any.asString(): String {
    val javaClass = javaClass
    val name = getName(javaClass)
    val params = getParams(javaClass)
    return "$name($params)"
}

private fun getName(javaClass: Class<*>): String {
    return javaClass
        .name
        .replace(javaClass.`package`.name + ".", "")
        .replace("$", ".")
}

private fun Any.getParams(javaClass: Class<*>): String {
    val stringBuilder = StringBuilder()
    val fields = mutableListOf<Field>()
    fillFields(javaClass, fields)
    var index = 0
    fields.forEach { field ->
        if (index > 0) stringBuilder.append(", ")
        index++
        field.isAccessible = true
        val value = field.get(this)
        field.isAccessible = false
        stringBuilder.append(field.name)
        stringBuilder.append(": ")
        stringBuilder.append(value)
    }
    return stringBuilder.toString()
}

private fun fillFields(javaClass: Class<*>, fields: MutableList<Field>) {
    if (javaClass == Any::class.java) return
    fillFields(javaClass.superclass, fields)
    fields.addAll(javaClass.declaredFields)
}