package com.example.myapplication.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.DetailProfileTemanSekelasActivity
import com.example.myapplication.databinding.ItemRecyclerviewProfileSekelasBinding
import com.example.myapplication.model.TemanSekelas

class AdapterREProfileSekelas(private val context: Context, private val item: List<TemanSekelas>) :
    RecyclerView.Adapter<AdapterREProfileSekelas.ViewHolderTemanSekelas>() {
    inner class ViewHolderTemanSekelas(private val binding: ItemRecyclerviewProfileSekelasBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(temanSekelas: TemanSekelas) {

            //Text
            binding.tvNamaTeman.text = temanSekelas.nama
            binding.tvSekolahTeman.text = temanSekelas.sekolah

//            itemView.tvNamaTeman.text = temanSekelas.nama
//            itemView.tvSekolahTeman.text = temanSekelas.sekolah
            //Image
            Glide.with(itemView.context).load(temanSekelas.gambar).into(binding.ivProfileTeman)
//            Glide.with(itemView.context).load(temanSekelas.gambar).into(itemView.ivProfileTeman)
//
            itemView.setOnClickListener {
                val maju = Intent(itemView.context, DetailProfileTemanSekelasActivity::class.java)
                maju.putExtra("NAMA", temanSekelas.nama)
                maju.putExtra("SEKOLAH", temanSekelas.sekolah)
                maju.putExtra("GENDER", temanSekelas.gender)
                maju.putExtra("GAMBAR", temanSekelas.gambar)
                itemView.context.startActivity(maju)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderTemanSekelas {
        val tampilan = ItemRecyclerviewProfileSekelasBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolderTemanSekelas(tampilan)
    }

    override fun onBindViewHolder(holder: ViewHolderTemanSekelas, position: Int) {
        holder.bind(item[position])
    }

    override fun getItemCount(): Int {
        return item.size
    }
}

