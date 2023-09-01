package com.example.englern

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.englern.databinding.ActivityMainBinding
import com.example.englern.tabs.InputFragment
import com.example.englern.tabs.RecyclerMessage
import com.example.englern.tabs.StartPage
import com.example.englern.tabs.HistoryFragment

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.panel, StartPage())
            replace(R.id.recycler, RecyclerMessage())
            replace(R.id.content, InputFragment())
            commit()
        }
        binding?.bottomNav?.selectedItemId = R.id.dialogBottomNav

        binding?.bottomNav?.setOnItemSelectedListener { item ->

            when(item.itemId){
                R.id.dialogBottomNav -> supportFragmentManager.beginTransaction().apply {
                    replace(R.id.panel, StartPage())
                    replace(R.id.recycler, RecyclerMessage())
                    replace(R.id.content, InputFragment())
                    commit()
                }
                R.id.questionBottomNav -> supportFragmentManager.beginTransaction().apply {
                    replace(R.id.panel, StartPage())
                    replace(R.id.recycler, HistoryFragment())
                    replace(R.id.content, InputFragment())
                    commit()
                }
                R.id.historyBottomNav -> supportFragmentManager.beginTransaction().apply {
                    replace(R.id.panel, StartPage())
                    replace(R.id.recycler, HistoryFragment())
                    //replace(R.id.content, InputFragment())
                    commit()
                }
            }
            return@setOnItemSelectedListener true
        }
    }
}