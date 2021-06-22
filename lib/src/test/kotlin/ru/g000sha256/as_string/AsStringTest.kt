package ru.g000sha256.as_string

import org.junit.Assert
import org.junit.Test

class AsStringTest {

    @Test
    fun test() {
        // GIVEN
        val uiAction = UiAction.Click()

        // WHEN
        val actualString = uiAction.toString()

        // THEN
        val expectedString = "UiAction.Click(" +
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

sealed class UiAction(
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
    ) : UiAction()

}

class CustomStringObject {

    override fun toString(): String {
        return "custom string"
    }

}

class NonStringObject {

    override fun equals(other: Any?): Boolean {
        return this === other && javaClass == other.javaClass
    }

    override fun hashCode(): Int {
        return 1
    }

}