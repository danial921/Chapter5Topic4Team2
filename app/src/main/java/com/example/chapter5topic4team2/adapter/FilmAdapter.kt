package com.example.chapter5topic4team2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chapter5topic4team2.databinding.ItemFilmBinding
import com.example.chapter5topic4team2.model.FilmResponseItem

class FilmAdapter(val onClickListener : FilmInterface) : RecyclerView.Adapter<FilmAdapter.ViewHolder>() {

    private var diffCallback = object : DiffUtil.ItemCallback<FilmResponseItem>(){
        override fun areItemsTheSame(oldItem: FilmResponseItem, newItem: FilmResponseItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FilmResponseItem, newItem: FilmResponseItem): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

    }

    private val differ = AsyncListDiffer(this, diffCallback)

    inner class ViewHolder(private val binding: ItemFilmBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(film : FilmResponseItem){
                binding.apply {
                    dataFilm = film

                    Glide.with(itemView)
                        .load(film.image)
                        .into(ivFilm)

                    itemView.setOnClickListener {
                        onClickListener.onItemClick(film)
                    }

                    btnToEdit.setOnClickListener {
                        onClickListener.editFilm(film)
                    }

                    btnDeleteData.setOnClickListener {
                        onClickListener.deleteFilm(film.id.toInt())
                    }
                }
            }
    }

    interface FilmInterface {
        fun onItemClick(film: FilmResponseItem)
        fun editFilm(film: FilmResponseItem)
        fun deleteFilm(id: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val film = differ.currentList[position]
        holder.bind(film)
    }

    override fun getItemCount(): Int = differ.currentList.size

    fun setData(data : List<FilmResponseItem>){
        differ.submitList(data)
    }

}