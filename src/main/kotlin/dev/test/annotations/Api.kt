package dev.test.annotations


 @Target(AnnotationTarget.CLASS)
 @Retention(AnnotationRetention.RUNTIME)
annotation class Api(val className: String)