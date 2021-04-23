package com.chanj.week1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.chanj.week1.data.Student

class KotlinActivity : AppCompatActivity() {
    var hasFriend = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * let, also, apply, run, with 사용하기
     */
    private fun scopeFunctions(){
        // Scope Function은 Object에 주어지는 임시 스코프(Temporary Scope)로 이름을 생략하고 사용할 수 있다.
        val mStudent = Student("홍길동", 23, "서울시 강남",null)

        mStudent.let {
            Log.d("hi", "나는 ${mStudent.name}입니다")
        }

        mStudent.run {
            Log.d("hi", "나는 ${mStudent.name}입니다")
        }

        let(mStudent)
        also(mStudent)
        apply(mStudent)
        run(mStudent)
    }

    private fun let(student: Student){
        // 기존
        if(student.friends != null){
            for ( friend in student.friends!!){
                Toast.makeText(this, "안녕, ${friend.name}", Toast.LENGTH_SHORT).show()
                Thread.sleep(3000)
            }
        }

        // 적용
        student.friends?.let {
            for ( friend in it){
                Toast.makeText(this, "안녕, ${friend.name}", Toast.LENGTH_SHORT).show()
                Thread.sleep(3000)
            }
        }

        // 이름을 줄 수도 있다.
        student.friends?.let { friends ->
            for ( friend in friends){
                Toast.makeText(this, "안녕, ${friend.name}", Toast.LENGTH_SHORT).show()
                Thread.sleep(3000)
            }
        }

        // Return 값을 가질 수 있다. Null 처리는 Elvis로 처리
        val hasFriend = student.friends?.let {
            true
        }?:false


        val intent = Intent(this@KotlinActivity, LetActivity::class.java)
        intent.putExtra("HAS_FRIEND", hasFriend)
        intent.putExtra("FRIEND_LIST", student.friends)
        startActivity(intent)
    }

    /**
     * Also는 동작을 연달아 할 때 사용할 수 있다. 특히, Method의 Return 쪽에 쓰이면 유용할 수 있다.
     */
    private fun also(student: Student) = (student)
            .also{student.friends = arrayListOf(Student("친구1", 23, student.address, null))
                Toast.makeText(this@KotlinActivity, "친구가 만들어졌습니다.", Toast.LENGTH_SHORT).show()
    }


    /**
     * Apply는 Object에 여러 값을 팅할 때 유용하다.
     */
    private fun apply(student: Student){
        val newFriend = Student("친구2", 23, "서울시 강남", null).apply {
            hasDog = true
            grade = 6
        }

        student.friends = arrayListOf(newFriend)
    }

    /**
     * Apply와 비슷하지만 return값을 줄 수 있다. (마지막에 두는 데이터의 자료형으로 반환)
     */
    private fun run(student: Student) {
        val cnt = student.friends?.run {
            this.size
        }?:0

        Toast.makeText(this@KotlinActivity, "${student.name}의 친구는 ${cnt}명 입니다.", Toast.LENGTH_SHORT).show()
    }

    /**
     *
     */
    private fun with(){

    }
}