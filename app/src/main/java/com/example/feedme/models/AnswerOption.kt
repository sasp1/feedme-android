package com.example.feedme.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class AnswerOption(
    @SerializedName("_id") val _id: String,
    @SerializedName("value") val value: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(value)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AnswerOption> {
        override fun createFromParcel(parcel: Parcel): AnswerOption {
            return AnswerOption(parcel)
        }

        override fun newArray(size: Int): Array<AnswerOption?> {
            return arrayOfNulls(size)
        }
    }
}
