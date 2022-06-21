package com.example.eventbusexample.event

import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.greenrobot.eventbus.ThreadMode.MAIN


class CallEvent(val message : String)

