package com.example.calendarview

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.widget.TextView
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.MaterialCalendarView

class MainActivity : AppCompatActivity() {

    lateinit var calendar : MaterialCalendarView
    lateinit var textDate : TextView
    lateinit var textSelected : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textDate = findViewById(R.id.text_date)
        textSelected = findViewById(R.id.text_selected)


        //1.기본 세팅 부분
        calendar = findViewById(R.id.calendarView)
        calendar.setSelectedDate(CalendarDay.today())


        //calendar를 꾸미기위한 Decorator추가
        calendar.addDecorator(TodayDecorator())

        //날짜 선택시 이벤트 발생
        calendar.setOnDateChangedListener { widget, date, selected ->
            textDate.text = date.toString()
            textSelected.text = selected.toString()
        }
        
    }

    //2.calendar를 꾸미기위한 Decorator부분
    inner class TodayDecorator: DayViewDecorator {
        private var date = CalendarDay.today()

        override fun shouldDecorate(day: CalendarDay?): Boolean {
            return day?.equals(date)!!
        }

        override fun decorate(view: DayViewFacade?) {
            view?.addSpan(StyleSpan(Typeface.BOLD))
            view?.addSpan(RelativeSizeSpan(1.7f))
            view?.addSpan(ForegroundColorSpan(Color.parseColor("#ffffff")))
        }
    }


}