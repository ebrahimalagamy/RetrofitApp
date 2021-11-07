package com.example.retrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.databinding.ItemTodoBinding

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

    // use list differ -> to make it very  efficient way to update recycler
    // use to calculate the different between lists and enables us to update only new data
    private val diffCallBack = object : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem == newItem
        }

    }

    // create async list differ so that the list differ is tool that will take our two list and
    // compares them and calculates the differences in the background thread
    // that we just pass the adapter and the method
    private val differ = AsyncListDiffer(this, diffCallBack)
    var todo: List<Todo>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            ItemTodoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.binding.apply {
            val todo = todo[position]
            tvTitle.text = todo.title
            checkBox.isChecked = todo.completed

        }
    }

    override fun getItemCount(): Int {
        return todo.size
    }


}