package com.example.listview_20231029

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.listview_20231029.adapters.StudentAdapter
import com.example.listview_20231029.databinding.ActivityMainBinding
import com.example.listview_20231029.datas.StudentData

class MainActivity : AppCompatActivity() {

    val  mStudentList = ArrayList<StudentData>()
    lateinit var mStdAdapter: StudentAdapter
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

//        화면이 켜질 때, 학생 목록을 ArrayList에 수기로 추가 ( 임시 활용, 실무에서는 X)

        mStudentList.add( (StudentData("김나무", 1980, "010-1234-5678")))
        mStudentList.add( (StudentData("홍길동", 1981, "010-8342-5623")))
        mStudentList.add( (StudentData("서수지", 1981, "010-1257-1236")))
        mStudentList.add( (StudentData("최민수", 1979, "010-5672-5567")))
        mStudentList.add( (StudentData("김민지", 1985, "010-1723-5125")))
        mStudentList.add( (StudentData("주호야", 1990, "010-1113-5125")))

//        어댑터 변수도 객체 생성
        mStdAdapter = StudentAdapter(this, R.layout.student_list_item, mStudentList)

//        만들어진 어댑터를 리스트뷰의 어댑터로 연결
        binding.studentListView.adapter = mStdAdapter


//        한 명의 학생을 클릭하면 => 토스트로 "이름 : 연락처" 토스트로 출력
        binding.studentListView.setOnItemClickListener { adapterView, view, position, l ->
            // 이 함수의 세 번재 (i, position) 변수 => 클릭 되 위치를 알려주는 역할.

//            mStudentList 중, 클릭된 위치에 맞는 학생 추출 => 활용

            val clickedStd = mStudentList[position]

            Toast.makeText(this, "${clickedStd.name} : ${clickedStd.phoneNum}", Toast.LENGTH_SHORT).show()
        }

//        한 명의 학생을 오래 클릭하면 => 해당 학생 삭제
        binding.studentListView.setOnItemLongClickListener { adapterView, view, position, l ->

            // 오래 클릭된 학생 => (목록에서) 삭제
            mStudentList.removeAt(position)

            // 어댑터에게 통보
            mStdAdapter.notifyDataSetChanged()

            // LongClick 이벤트는 Boolean 타입의 리턴값을 받도록 되어 있음

            return@setOnItemLongClickListener true
        }


    }
}