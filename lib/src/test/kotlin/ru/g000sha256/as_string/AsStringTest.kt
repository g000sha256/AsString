package ru.g000sha256.as_string

import org.junit.Assert
import org.junit.Test

class AsStringTest {

    @Test
    fun `check fields with annotation`() {
        val click = UiAction.Click()

        val actualString = click.toString()

        val expectedString = "UiAction.Click(baseField: baseField, nonNullField: nonNullField, nullableField: null, privateField: privateField)"
        Assert.assertEquals(expectedString, actualString)
    }

    @Test
    fun `check fields without annotation`() {
        val show = UiAction.Show()

        val actualString = show.toString()

        val expectedString = "UiAction.Show(baseField: baseField, customStringObject: NonStringObject.toString, nonStringObject: ru.g000sha256.as_string.NonStringObject@1)"
        Assert.assertEquals(expectedString, actualString)
    }

}

abstract class BaseAction {

    override fun toString(): String {
        return asString ?: super.toString()
    }

}

@AsString
sealed class UiAction(
    val baseField: String = "baseField"
) : BaseAction() {

    @AsString
    class Click(
        val nonNullField: String = "nonNullField",
        val nullableField: String? = null,
        private val privateField: String = "privateField"
    ) : UiAction()

    @AsString
    class Show(
        val customStringObject: CustomStringObject = CustomStringObject(),
        val nonStringObject: NonStringObject = NonStringObject()
    ) : UiAction()

}

class CustomStringObject {

    override fun toString(): String {
        return "NonStringObject.toString"
    }

}

class NonStringObject {

    override fun hashCode(): Int {
        return 1
    }

}