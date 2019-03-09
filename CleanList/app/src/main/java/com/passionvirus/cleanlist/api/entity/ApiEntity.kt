package com.passionvirus.cleanlist.api.entity

class ApiEntity {

    class AbilityList {
        val count = 0
        val next : String? = ""
        val previous : String? = ""
        val results = ArrayList<Ability>()

        inner class Ability {
            val name = ""
            val url = ""
        }
    }

}