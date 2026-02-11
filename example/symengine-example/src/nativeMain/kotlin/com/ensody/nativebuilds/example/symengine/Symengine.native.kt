package com.ensody.nativebuilds.example.symengine

import com.ensody.nativebuilds.example.symengine.*
import kotlinx.cinterop.*
import platform.posix.*

@kotlinx.cinterop.ExperimentalForeignApi
public actual fun getSymengineVersion(): String = memScoped {
    // Allocate symbolic objects
    val pi = basic_new_heap()
    val num = basic_new_heap()

    // pi (symbolic)
    basic_const_pi(pi)

    // numeric evaluation (MPFR if enabled)
    basic_evalf(num, pi, 256)

    // convert to string
    val buf = basic_str(num)
    val str = char_buffer_c_str(buf)?.toKString()
        ?: error("null string")

    println(str)

    // cleanup
    char_buffer_free(buf)
    basic_free_heap(num)
    basic_free_heap(pi)
    str
}

//public actual fun getSymengineStr(): String =
//    checkNotNull(symengineString()).toKString()
