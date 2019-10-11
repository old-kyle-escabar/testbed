package dev.test

import dev.test.annotations.Api
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassWriter
import org.reflections.Reflections


class Injector {
    var TARGET_PACKAGE = "dev.test.engine"
    var API_PACKAGE = "dev.test.api"
    var BUILD_DIR = "build/"

    val reflections = Reflections("dev")

    private val hookMap = hashMapOf<String, ClassFile>()

    fun scan() {
        val classes = reflections.getTypesAnnotatedWith(Api::class.java)
        classes.forEach { clazz ->
            hookMap[clazz.simpleName] = ClassFile(this, clazz)
        }

        println("Found ${hookMap.size} api hooks.")
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> inject(target: String): T {
        val predicate = hookMap[target] ?: throw Exception("The class $target has no api hooks.")

        val reader = ClassReader(predicate.targetName)
        val writer = ClassWriter(ClassWriter.COMPUTE_FRAMES)

        val injected = ApiHookInjector(predicate.apiName, writer)
        reader.accept(injected, 0)

        val bytes = writer.toByteArray()

        val injectionLoader = InjectedClassLoader()
        val injectedClass = injectionLoader.injectClass(predicate.targetName.replace("/","."), bytes)

        return injectedClass.getDeclaredConstructor().newInstance() as T
    }
}