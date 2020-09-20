package com.zukron.jikanime

import com.zukron.jikanime.model.helper.HomeHelper
import org.junit.Test

object Dummy {
    var str: String? = null

    fun getApi(): Dummy {
        str ?: also {
            println("saya di panggil")
            str = "asdasd"
        }

        return Dummy
    }
}


sealed class sate {
    object Winter : sate()
    class Fall(val message: String) : sate()
    class Spring(val message: String) : sate()
    class Summer(val message: String) : sate()
    companion object {
        val yearList: List<Int> = listOf(2016, 2017, 2018, 2019, 2020, 2021, 2022)
    }
}

data class Abc(
        val name: String,
        val score: Int
)

class ExampleUnitTest {
    @Test
    fun testLazyInsideFunction() {
        println(Dummy.getApi())
        println(Dummy.str)
//        println(Dummy.str)
//        println(Dummy.str)
//        println(Dummy.str)
//        println(Dummy.str)
//        println(Dummy.str)
//        println(Dummy.str)
    }

    @Test
    fun testingEnumClass() {
        val dd = sate.yearList
        val winter = sate.Winter
        println(dd)
        println(sate)
        println(winter is sate)
        println(winter == sate.Winter)
        println(winter is sate.Winter)
    }

    @Test
    fun testingSorting() {
        val mutableList = mutableListOf<Abc>()
        mutableList.add(Abc("1 asdasd", 4))
        mutableList.add(Abc("zxc2x", 5))
        mutableList.add(Abc("qwe", 9))
        mutableList.add(Abc("sdzzxc", 2))
        mutableList.add(Abc("xzcxc", 3))
        mutableList.add(Abc("aszxcdas", 8))
        mutableList.add(Abc("asdasdasdas", 4))
        mutableList.add(Abc("asdaxcs", 2))

        println(mutableList.sortedWith { p0, p1 ->
            p0.score.compareTo(p1.score)
        })

        println(mutableList)
    }

    @Test
    fun testingSealedClass() {
        val winter = HomeHelper.SeasonalAnime.Winter
        if (winter is HomeHelper.SeasonalAnime) {
            if (winter is HomeHelper.SeasonalAnime.Winter) {
                println(winter::class.simpleName)
            }
        }
    }
}