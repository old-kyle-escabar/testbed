package dev.test

import dev.test.annotations.Api

/**
 * Represents a [Class] target where an API interface
 * needs to be implemented.
 *
 * @param name The class path name. ("dev/test/engine/RSEngine")
 * The name needs to be the class the target class. NOT the api class.
 */
class ClassFile(private val injector: Injector, val targetClass: Class<*>) {

    lateinit var targetName: String
    lateinit var apiClass: Class<*>
    lateinit var apiName: String

    init {
        this.process()
    }

    private fun process() {
        val apiAnnotation = targetClass.getAnnotation(Api::class.java) ?: return

        targetName = (injector.TARGET_PACKAGE + "." + targetClass.simpleName).replace(".", "/")
        apiName = (injector.API_PACKAGE + "." + apiAnnotation.className).replace(".","/")
        apiClass = Class.forName(apiName.replace("/",".")) ?: return
    }
}