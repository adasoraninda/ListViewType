package com.codetron.listviewtype

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val listAdapter by lazy {
        ListAdapter()
    }

    private lateinit var listData: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listData = findViewById(R.id.lst_item)

        listData.adapter = listAdapter.apply {
            setData(
                header = Resources.getHeader(this@MainActivity),
                body = Resources.getModels(),
                footer = Resources.getFooter()
            )
        }
        listData.setHasFixedSize(true)

    }

    private object Resources {

        fun getHeader(context: Context): String {
            return context.getString(R.string.app_name)
        }

        fun getModels(): List<Model> {
            val list = ArrayList<Model>()
            repeat(20) {
                list.add(Model("Element $it"))
            }
            return list
        }

        fun getFooter(): String {
            return "Footer"
        }

    }

}