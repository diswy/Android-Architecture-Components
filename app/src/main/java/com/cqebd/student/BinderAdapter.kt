package com.cqebd.student

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.cqebd.student.databinding.ItemTestBinding

class BinderAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val list = ArrayList<String>()

    init {
        for (i in 1..100){
            list.add(i.toString())
        }
    }

    fun refreshData(){
        list.clear()
        var i: Int = 'A'.toInt()
        while (i < 'z'.toInt()) {
            list.add("" + i.toChar())
            i++
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<ItemTestBinding>(LayoutInflater.from(context), R.layout.item_test, parent, false)
        return VHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder:RecyclerView.ViewHolder, position: Int) {
        (holder as VHolder<*>).bind.setVariable(BR.item,list[position])
        holder.bind.executePendingBindings()
    }

    class VHolder<T : ViewDataBinding>(val bind: T) : RecyclerView.ViewHolder(bind.root)
}