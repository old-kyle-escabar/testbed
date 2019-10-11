package dev.test

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.Opcodes.ASM5
import org.objectweb.asm.Opcodes.V1_8

class ApiHookInjector(val apiName: String, cv: ClassVisitor) : ClassVisitor(ASM5, cv) {

    override fun visit(
        version: Int,
        access: Int,
        name: String?,
        signature: String?,
        superName: String?,
        interfaces: Array<String>?
    ) {
        val injectedInterfaces = interfaces!!.copyOf(interfaces.size + 1)
        injectedInterfaces[injectedInterfaces.size - 1] = apiName

        return cv.visit(V1_8, access, name, signature, superName, injectedInterfaces)
    }

}