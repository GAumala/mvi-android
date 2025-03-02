# MVI

Small collection of classes useful for implementing the Model-View-Intent design 
pattern in Android apps.

* Built on top of [Android architecture components](
  https://developer.android.com/topic/libraries/architecture/)
* Inspired by [The Elm Architecture](https://guide.elm-lang.org/architecture/)
* Tiny, just a handful of classes, ~500 LOC
* Easy to use both in Java and Kotlin

## Install

**Step 1.** Add the JitPack repository to your build file 

``` Groovy
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

**Step 2.** Add the dependency

``` Groovy
dependencies {
        implementation 'com.github.GAumala:mvi-android:1.0'
}
```

