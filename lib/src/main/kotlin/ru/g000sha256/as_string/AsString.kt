package ru.g000sha256.as_string

import java.lang.reflect.Field

/**
 * @return an object as a string.
 */
val Any.asString: String
    get() = asString()

/**
 * @return an object as a string.
 */
fun Any.asString(): String {
    return "$name($params)"
}

private val Any.name: String
    get() {
        return javaClass
            .name
            .replace(javaClass.`package`.name + ".", "")
            .replace("$", ".")
    }

private val Any.params: String
    get() {
        val stringBuilder = StringBuilder()
        val fields = mutableListOf<Field>()
        fillFields(javaClass, fields)
        fields
            .filter { it.name != "\$jacocoData" }
            .forEachIndexed { index, field ->
                if (index > 0) stringBuilder.append(", ")
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