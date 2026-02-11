package com.ensody.nativebuilds.example.symengine

public actual fun getSymengineVersion(): String =
    SymengineWrapper.getSymengineVersion()
