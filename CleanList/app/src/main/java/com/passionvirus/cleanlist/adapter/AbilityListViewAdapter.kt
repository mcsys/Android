package com.passionvirus.cleanlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.passionvirus.cleanlist.databinding.AbilitylistItemBinding

class AbilityListViewAdapter : RecyclerView.Adapter<AbilityListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilityListViewHolder {
        val binding = AbilitylistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AbilityListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: AbilityListViewHolder, position: Int) {
    }
}