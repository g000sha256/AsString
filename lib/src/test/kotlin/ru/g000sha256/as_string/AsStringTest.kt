package ru.g000sha256.as_string

import org.junit.Assert
import org.junit.Test

class AsStringTest {

    @Test
    fun test() {
        // GIVEN
        val action = Action.Click()

        // WHEN
        val actualString = action.toString()

        // THEN
        val expectedString = "Action.Click(" +
                "baseField: baseField, " +
                "nonNullField: nonNullField, " +
                "nullableField: null, " +
                "privateField: privateField, " +
                "customStringObject: custom string, " +
                "nonStringObject: ru.g000sha256.as_string.NonStringObject@1" +
                ")"
        Assert.assertEquals(expectedString, actualString)
    }

}

sealed class Action(
    val baseField: String = "baseField"
) {

    override fun toString(): String {
        return asString
    }

    class Click(
        val nonNullField: String = "nonNullField",
        val nullableField: String? = null,
        private val privateField: String = "privateField",
        val customStringObject: CustomStringObject = CustomStringObject(),
        val nonStringObject: NonStringObject = NonStringObject()
    ) : Action()

}

class CustomStringObject(
    val field: String = "field"
) {

    override fun toString(): String {
        return "custom string"
    }

}

class NonStringObject(
    val field: String = "field"
) {

    override fun equals(other: Any?): Boolean {
        return this === other && javaClass == other.javaClass
    }

    override fun hashCode(): Int {
        return 1
    }

}