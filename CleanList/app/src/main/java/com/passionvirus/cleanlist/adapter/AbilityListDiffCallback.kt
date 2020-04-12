package com.passionvirus.cleanlist.adapter

import androidx.recyclerview.widget.DiffUtil


class AbilityListDiffCallback(
    private val oldAbilityList: List<AbilityListViewItem>,
    private val newAbilityList: List<AbilityListViewItem>
) : DiffUtil.Callback() {


    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldAbilityList[oldItemPosition].hashCode() == oldAbilityList[newItemPosition].hashCode()
    }

    override fun getOldListSize(): Int {
        return oldAbilityList.size
    }

    override fun getNewListSize(): Int {
        return newAbilityList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldAbilityList[oldItemPosition] == newAbilityList[newItemPosition]
    }

}