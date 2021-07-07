package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.database.student.Siswa
import com.example.myapplication.databinding.ItemRecyclerviewSiswaBinding

class AdapterRETambahSiswa(private val items: List<Siswa>, private val onItemClick: (siswa: Siswa) -> Unit) :
    RecyclerView.Adapter<AdapterRETambahSiswa.ViewHolderTambahSiswa>() {

    inner class ViewHolderTambahSiswa(val viewDataBinding: ItemRecyclerviewSiswaBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        fun bind(siswa: Siswa?) {
            viewDataBinding.siswa = siswa
            viewDataBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderTambahSiswa {
        return ViewHolderTambahSiswa(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_recyclerview_siswa,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolderTambahSiswa, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener { onItemClick(items[position]) }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}