package com.passionvirus.ksp

import com.passionvirus.ksp.buildprocessor.FirebaseEvent
import com.passionvirus.ksp.buildprocessor.Param

@FirebaseEvent
class KSPSample {
    @Param
    var age: Long = 0
    @Param
    var name: String = ""
}