package com.chanj.week1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chanj.week1.data.Student

class LetActivity : AppCompatActivity() {
    var hasFriend = false
    var friendList: ArrayList<Student>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        intent.extras?.let { bundle ->
            hasFriend = bundle.getBoolean("HAS_FRIEND")
            friendList = bundle.getParcelableArray("FRIEND_LIST") as ArrayList<Student>
            // ...
        }
    }
}