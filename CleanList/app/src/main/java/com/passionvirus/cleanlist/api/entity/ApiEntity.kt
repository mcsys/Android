package com.passionvirus.cleanlist.api.entity

import com.passionvirus.cleanlist.adapter.AbilityListViewItem

class ApiEntity {

    class AbilityList {
        val count = 0
        val next : String? = ""
        val previous : String? = ""
        val results = ArrayList<AbilityListViewItem>()
    }

}