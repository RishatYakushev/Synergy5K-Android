package com.synergy.android.main

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.synergy.android.main.entities.Book

class BookAdapter(private val bookList: List<Book>) : RecyclerView.Adapter<BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BookViewHolder.create(parent)

    override fun getItemCount(): Int =
        bookList.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) =
        holder.bind(bookList[position])
}
