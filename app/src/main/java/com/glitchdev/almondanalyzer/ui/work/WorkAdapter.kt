package com.glitchdev.almondanalyzer.ui.work

import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.glitchdev.almondanalyzer.R

class WorkAdapter(
    private val items: MutableList<WorkItem>
) : RecyclerView.Adapter<WorkAdapter.WorkViewHolder>() {

    inner class WorkViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val date: TextView = view.findViewById(R.id.txtDate)
        val type: TextView = view.findViewById(R.id.txtType)
        val cost: TextView = view.findViewById(R.id.txtCost)
        val comment: TextView = view.findViewById(R.id.txtComment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_work, parent, false)
        return WorkViewHolder(v)
    }

    override fun onBindViewHolder(h: WorkViewHolder, p: Int) {
        val i = items[p]
        h.date.text = i.date
        h.type.text = "Тип: ${i.type}"
        h.cost.text = "Стоимость: ${i.cost}"
        h.comment.text = "Комментарий: ${i.comment}"
    }

    override fun getItemCount() = items.size

    fun addWork(work: WorkItem){
        items.add(work)
        notifyItemInserted(items.size-1)
    }
}
