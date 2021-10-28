package com.ipn.datastructuredemo

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity()
{
    private var queue: Queue<String> = LinkedList()
    private var array: ArrayList<String> = ArrayList()

    private lateinit var listView: ListView
    private lateinit var root: ConstraintLayout
    private lateinit var dialog: AlertDialog
    private lateinit var adapter: ArrayAdapter<String>

    private lateinit var builder: AlertDialog.Builder
    private lateinit var text: TextView
    private lateinit var close: ImageButton
    private lateinit var value: EditText
    private lateinit var continueBt: Button
    private lateinit var chipGroup: ChipGroup
    private lateinit var p: EditText

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for(i in 0.. 30) queue.add(i.toString())

        array.addAll(queue)

        listView = findViewById(R.id.listview)
        root = findViewById(R.id.root)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, array)
        listView.adapter = adapter

        initDialog()

        val addButton = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        addButton.setOnClickListener { showAlertDialogAdd() }

        val searchButton = findViewById<FloatingActionButton>(R.id.floatingActionButton2)
        searchButton.setOnClickListener { showAlertDialogSearch() }

        val deleteButton = findViewById<FloatingActionButton>(R.id.floatingActionButton3)
        deleteButton.setOnClickListener { showAlertDialogRemove() }
    }

    private fun initDialog()
    {
        builder = this.let {
            AlertDialog.Builder(it)
        }

        val layoutInflater = LayoutInflater.from(this)
        val view = layoutInflater.inflate(R.layout.add_view, root, false)

        text = view.findViewById(R.id.textView)

        close = view.findViewById(R.id.imageButton)
        close.setOnClickListener { dialog.dismiss() }

        value = view.findViewById(R.id.editTextTextPersonName)
        p = view.findViewById(R.id.editTextPosition)

        continueBt = view.findViewById(R.id.button)

        chipGroup = view.findViewById(R.id.chip_group)

        chipGroup.setOnCheckedChangeListener { _, checkedId ->
            if(checkedId == R.id.chipValue)
                value.inputType = InputType.TYPE_CLASS_TEXT
            else if(checkedId == R.id.chipPosition)
                value.inputType = InputType.TYPE_CLASS_NUMBER
        }

        builder.setView(view)
        dialog = builder.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setOnDismissListener {
            value.setText("")
            chipGroup.check(R.id.chipValue)
            p.setText("")
        }
    }

    private fun showAlertDialogAdd()
    {
        text.text = getString(R.string.add)
        p.visibility = View.GONE
        chipGroup.visibility = View.GONE
        continueBt.setOnClickListener()
        {
            if (value.text.isNotEmpty())
            {
                queue.add(value.text.toString())
                array.clear()
                array.addAll(queue)
                //stack.add(value.text.toString())
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }
        }

        dialog.show()
    }

    private fun showAlertDialogSearch()
    {
        text.text = getString(R.string.search)
        p.visibility = View.GONE
        chipGroup.visibility = View.VISIBLE

        continueBt.setOnClickListener()
        {
            val data = value.text.toString()
            if (data.isNotEmpty())
            {
                val position: Int
                when (chipGroup.checkedChipId)
                {
                    R.id.chipValue ->
                    {
                        position = adapter.getPosition(data)
                        if(position != -1)
                        {
                            listView.smoothScrollToPosition(position)
                            dialog.dismiss()
                        }
                        else
                            Toast.makeText(this, getString(R.string.noExists), Toast.LENGTH_SHORT).show()

                    }
                    R.id.chipPosition ->
                    {
                        position = Integer.parseInt(data)
                        if(position < queue.size)
                        {
                            listView.smoothScrollToPosition(position)
                            dialog.dismiss()
                        }
                        else
                            Toast.makeText(this, getString(R.string.noExists), Toast.LENGTH_SHORT).show()
                    }
                    else -> Toast.makeText(this, getString(R.string.selectType), Toast.LENGTH_SHORT).show()
                }

            }
        }

        dialog.show()
    }

    private fun showAlertDialogRemove()
    {
        queue.remove()
        array.clear()
        array.addAll(queue)
        adapter.notifyDataSetChanged()
        dialog.dismiss()
    }
}