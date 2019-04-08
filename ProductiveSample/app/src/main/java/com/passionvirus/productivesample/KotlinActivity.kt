package com.passionvirus.productivesample

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.util.Log
import android.util.Range

class KotlinActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        /*
        val numbers= listOf(1 ,2, 3, 4, 5, 6)
        val sum = numbers.filter { num -> num % 2 == 0 }.sum() // 12
        val folded = numbers.fold(1) { sum , num -> sum + num } // 20
        val reduced = numbers.reduce { sum, num -> sum + num } // 21
        val grouped = numbers.groupBy { num -> num % 2 == 1 } // {true=[1, 3, 5], false=[2, 4, 6]}
            .map { group -> group.value } // [[1, 3, 5], [2, 4, 6]]
            .minBy { nums -> nums.sum() } // [1, 3, 5]
            ?.map { num -> num * 2 } // [2, 6, 10]
            ?.sum()
        */

        /*
        val x = listOf(1, 2, 3)
        val y = arrayListOf(1, 2, 3)
//        Log.d("TEST1234", "${x is List<*>}") //  true
//        Log.d("TEST1234", "${x is MutableList<*>}") //  true
//        Log.d("TEST1234", "${x is java.util.List<*>}") //  true
//        Log.d("TEST1234", "${y is List<*>}") //  true
//        Log.d("TEST1234", "${y is MutableList<*>}") //  true
        Log.d("TEST1234", "${y is java.util.List<*>}") //  true
        */

        /*
        val i = 3
        when (i) {
            in 4..1 -> println("in")
            !in 4..1 -> println("!in")
            else -> println("else")
        }
        println("i : $i")
        // !in
        */

        /*
        println(decemalDigitValue())
        println('0'.toInt()) // to ASCII
        println("0".toInt()) // to int
        */

        /*
        1?.let {
            println(it) // 1
            2.apply {
                println(this) // 2
                (3 as Int?).also {
                    println(it) // 3
                    4.run {
//                        println(this) //  4
//                        println(it + this) // Compile error
                        println(it?.plus(this)) // 7
                    }
                }
            }
        }
        */

        /*
        println(0 to (1 to 2 to 3) to 4 to (5 to 6)) // ((0, (1, 2, 3), 4), (5, 6))
        println(0 to (1 to 2 to 3) to 4 to (5 to 6) to 7) // ((0, (1, 2, 3), 4), (5, 6), 7)
        println(0 to (1 to 2 to 3) to 4 to (5 to 6 to 7)) // ((0, (1, 2, 3), 4), (5, 6, 7))
        println(0 to (1 to 2 to 3) to 4 to (5 to 6 to 7) to 8) // ((0, (1, 2, 3), 4), (5, 6, 7), 8)
        println(0 to (1 to 2 to 3) to 4 to (5 to 6 to 7) to 8 to 9) // (((0, (1, 2, 3), 4), (5, 6, 7), 8), 9)
        println(0 to (1 to 2 to 3) to 4 to (5 to 6 to 7) to 8 to 9 to 10) // (((0, (1, 2, 3), 4), (5, 6, 7), 8), 9, 10)
        */

        /*
        val dot = 1..10 // 1..10
        val rangeTo = 1.rangeTo(10) // 1..10
        val downTo = (10 downTo 1) // 10 downTo 1 step 1 | 1..10 step 1
        val until = 1 until 10 // 1..9
        val intRange = IntRange(1, 10) // 1..10
        val range = Range(1, 10) // [1, 10]
        */

        /*
        boxes.filter { it.number != null }
            .minBy { it.number!! }
            .let { println("it:$it") }
        */

        val arr = arrayOf(Foo(), Bar())
        f(arr)
    }

    fun decemalDigitValue(): String =
        when {
            '0'.toInt() - "0".toInt() < 0 -> "negative"
            '0'.toInt() - "0".toInt() == 0 -> "zero"
            else -> "Positive"
        }


    infix fun <A, B, C>  Pair<A, B>.to(third: C) = Triple(first, second, third)


    data class Box(
        val number: Int? = null,
        val text: String? = null
    )

    val boxes: List<Box> = listOf(
        Box(text = "A"),
        Box(text = "B"),
        Box(text = "C"),
        Box(text = "D"),
        Box(text = "E")
    )


    open class Foo
    open class Bar : Foo()
    class Baz : Bar()

    fun f(x: Array<in Bar>) = println("in Bar")
//    fun f(x: Array<Bar>) = println("in Bar") // not work
//    fun f(x: Array<out Bar>) = println("out Bar") // not work
}
