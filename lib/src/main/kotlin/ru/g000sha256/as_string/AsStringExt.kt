package ru.g000sha256.as_string

import java.lang.reflect.Field

val Any?.asString: String?
    get() = asString()

fun Any?.asString(): String? {
    this ?: return null
    val javaClass = javaClass
    val isAnnotationPresent = javaClass.isAnnotationPresent(AsString::class.java)
    if (!isAnnotationPresent) return null
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
    fields.addAll(javaClass.superclass.declaredFields)
    fields.addAll(javaClass.declaredFields)
    fields.forEachIndexed { index, field ->
        if (index > 0) stringBuilder.append(", ")
        field.isAccessible = true
        val value = field.get(this)
        field.isAccessible = false
        val param = value?.let { it.asString() ?: it.toString() }
        stringBuilder.append(field.name)
        stringBuilder.append(": ")
        stringBuilder.append(param)
    }
    return stringBuilder.toString()
}