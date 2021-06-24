# AsString

[![License](https://img.shields.io/static/v1?color=blue&label=License&message=MIT)](https://github.com/g000sha256/AsString/blob/master/License)
[![JitPack](https://img.shields.io/jitpack/v/github/g000sha256/AsString?color=brightgreen&label=Version)](https://jitpack.io/#g000sha256/AsString)
[![Travis](https://img.shields.io/travis/com/g000sha256/AsString?label=Build)](https://travis-ci.com/github/g000sha256/AsString)
[![Codacy](https://img.shields.io/codacy/coverage/15319dab2d374c8b9a24048ef914e4f3?label=Code%20coverage)](https://www.codacy.com/gh/g000sha256/AsString)
[![Codacy](https://img.shields.io/codacy/grade/15319dab2d374c8b9a24048ef914e4f3?label=Code%20quality)](https://www.codacy.com/gh/g000sha256/AsString)

This library works with reflection and helps to display an object as a string without using Kotlin `datа` classes. There are 2
extensions for this: `Any.asString` and `Any.asString()`. The extension works only with the current class. So for nested fields,
override the `toString` method with the same extension. Fields from super classes are also displayed.

### Setting up the dependency

```groovy
dependencies {
    implementation "com.github.g000sha256:AsString:1.0"
}
repositories {
    maven { url "https://jitpack.io" }
}
```

### Example

```kotlin
class Action(
    val actionParam: String = "actionParam",
    val data: Data = Data()
) {

    override fun toString(): String {
        return asString()
    }

}

class Data(
    val dataParam: String = "dataParam"
) {

    override fun toString(): String {
        return asString()
    }

}
```

```kotlin
val action = Action()
println("$action")
```

displays a

```
Action(actionParam: actionParam, data: Data(dataParam: dataParam))
```

An extended example you can see [here](https://github.com/g000sha256/AsString/blob/master/lib/src/test/kotlin/ru/g000sha256/as_string/AsStringTest.kt).