package com.itdeveapps.newsly

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.itdeveapps.newsly.mapper.HumanDate
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class DateInstrumentedTest {
    lateinit var appContext :Context
    @Before
    fun setUp(){
        appContext= InstrumentationRegistry.getInstrumentation().targetContext
    }
    @Test
    fun testDays() {
        val humanDate = HumanDate(appContext)
        val time = humanDate.timePeriod("2019-11-03T20:11:00Z", "2019-11-04T14:05:00Z")
        assertEquals(time,"1 day ago")

    }

    @Test
    fun testMonths() {
        val humanDate = HumanDate(appContext)
        val time = humanDate.timePeriod("2019-11-03T20:11:00Z", "2019-12-03T20:05:00Z")
        assertEquals(time,"1 month ago")

    }

    @Test
    fun testHours() {
        val humanDate = HumanDate(appContext)
        val time = humanDate.timePeriod("2019-11-03T20:11:00Z", "2019-11-03T21:11:00Z")
        assertEquals(time,"1h ago")

    }

    @Test
    fun TestMinutes() {
        val humanDate = HumanDate(appContext)
        val time = humanDate.timePeriod("2019-11-03T20:11:00Z", "2019-11-03T20:13:00Z")
        assertEquals(time,"2 min ago")

    }
}
