package com.example.enpit_p22.analyzesheet

import java.sql.Timestamp
import kotlin.system.measureTimeMillis

class Quetion(
            val q1:String,
            val q2_1:String,val q2_2:Int,
            val q3:String,
            val q4:Int,
            val q5:Int,
            val q6_1:Int,val q6_2:String,
            val q7:String) {
    var timestamp = System.currentTimeMillis()
}
