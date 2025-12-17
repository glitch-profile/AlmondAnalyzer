package com.glitchdev.almondanalyzer.ui.work

import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.glitchdev.almondanalyzer.R

class WorkFragment : Fragment() {

    private lateinit var adapter: WorkAdapter
    private val workList = mutableListOf<WorkItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val v = inflater.inflate(R.layout.fragment_work, container, false)

        val recycler = v.findViewById<RecyclerView>(R.id.workRecycler)
        val btn = v.findViewById<Button>(R.id.btnAddWork)

        adapter = WorkAdapter(workList)

        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        btn.setOnClickListener { addDialog() }

        return v
    }

    private fun addDialog(){
        val d = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_work, null)
        val date = d.findViewById<EditText>(R.id.editDate)
        val type = d.findViewById<EditText>(R.id.editType)
        val cost = d.findViewById<EditText>(R.id.editCost)
        val comm = d.findViewById<EditText>(R.id.editComment)

        AlertDialog.Builder(requireContext())
            .setTitle("Добавить работу")
            .setView(d)
            .setPositiveButton("Добавить"){_,_->
                adapter.addWork(
                    WorkItem(
                        date.text.toString(),
                        type.text.toString(),
                        cost.text.toString(),
                        comm.text.toString()
                    )
                )
            }
            .setNegativeButton("Отмена",null)
            .show()
    }
}
