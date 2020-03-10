package com.passionvirus.cleanlist.api

import android.os.Parcel
import android.os.Parcelable


data class Ability(val name: String, val url: String) : Parcelable {

    companion object {
        const val name = "ability"

        @JvmField
        val CREATOR = object : Parcelable.Creator<Ability> {
            override fun createFromParcel(parcel: Parcel) = Ability(parcel)
            override fun newArray(size: Int) = arrayOfNulls<Ability>(size)
        }
    }

    /*
    companion object {
        val CREATOR = object : Parcelable.Creator<Ability> {
            override fun createFromParcel(parcel: Parcel) = Ability(parcel)
            override fun newArray(size: Int) = arrayOfNulls<Ability>(size)
        }
    }
    */
    
    /*
    companion object CREATOR : Parcelable.Creator<Ability> {
        override fun createFromParcel(parcel: Parcel): Ability {
            return Ability(parcel)
        }

        override fun newArray(size: Int): Array<Ability?> {
            return arrayOfNulls(size)
        }
    }
    */

    constructor(parcel: Parcel) : this(
        name = parcel.readString(),
        url = parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(url)
    }

    override fun describeContents() = 0
}

/*
@Parcelize
class Item(
    var title: String,
    var url: String,
) : Parcelable
*/
