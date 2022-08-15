package com.yousufjamil.sweetfeetzltd

import android.os.Parcel
import android.os.Parcelable

class Player constructor(var type: String, var colour: String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString().toString(),
            parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(type)
        parcel.writeString(colour)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Player> {
        override fun createFromParcel(parcel: Parcel): Player {
            return Player(parcel)
        }

        override fun newArray(size: Int): Array<Player?> {
            return arrayOfNulls(size)
        }
    }
}
