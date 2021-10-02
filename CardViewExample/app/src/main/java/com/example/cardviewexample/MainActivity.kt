package com.example.cardviewexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.CardStackView
import com.yuyakaido.android.cardstackview.Direction

class MainActivity : AppCompatActivity() {

    lateinit var cardStackView : CardStackView
    lateinit var manager : CardStackLayoutManager
    lateinit var cardStackViewAdapter: CardStackViewAdapter
    lateinit var items : ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cardStackView = findViewById(R.id.cardStackView)


        //CardStackLayoutManager
        manager = CardStackLayoutManager(this, object : CardStackListener{
            override fun onCardDragging(direction: Direction?, ratio: Float) {

            }

            override fun onCardSwiped(direction: Direction?) {

            }

            override fun onCardRewound() {

            }

            override fun onCardCanceled() {

            }

            override fun onCardAppeared(view: View?, position: Int) {

            }

            override fun onCardDisappeared(view: View?, position: Int) {

            }
        })
        //CardStackLayoutManager

        items = ArrayList<String>()
        for(i in 0..100){
            items.add(i.toString())
        }
        cardStackViewAdapter = CardStackViewAdapter(items)
        cardStackView.adapter = cardStackViewAdapter

    }
}