package com.jeff.isalev3.models

import android.os.Parcel
import android.os.Parcelable

data class SignUp(
    val business_name: String,
    val kra_pin: String,
    val business_email: String,
    val business_phone: String,
    val business_address: String,
    val business_nature: String,
    val first_name: String,
    val username: String,
    val password: String,
    val position: String,
    val address: String,
    val phone: String,
    val email: String,
    val nationalid: String,
    val licence_id: String,
    val licence_count: Int,
    val branch: String ? = ""
) : Parcelable {

    // Write data to Parcel
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(business_name)
        parcel.writeString(kra_pin)
        parcel.writeString(business_email)
        parcel.writeString(business_phone)
        parcel.writeString(business_address)
        parcel.writeString(business_nature)
        parcel.writeString(first_name)
        parcel.writeString(username)
        parcel.writeString(password)
        parcel.writeString(position)
        parcel.writeString(address)
        parcel.writeString(phone)
        parcel.writeString(email)
        parcel.writeString(nationalid)
        parcel.writeString(licence_id)
        parcel.writeInt(licence_count)
        parcel.writeString(branch)
    }

    override fun describeContents(): Int = 0

    // Companion object to recreate the object from a Parcel
    companion object CREATOR : Parcelable.Creator<SignUp> {
        override fun createFromParcel(parcel: Parcel): SignUp {
            return SignUp(
                business_name = parcel.readString() ?: "",
                kra_pin = parcel.readString() ?: "",
                business_email = parcel.readString() ?: "",
                business_phone = parcel.readString() ?: "",
                business_address = parcel.readString() ?: "",
                business_nature = parcel.readString() ?: "",
                first_name = parcel.readString() ?: "",
                username = parcel.readString() ?: "",
                password = parcel.readString() ?: "",
                position = parcel.readString() ?: "",
                address = parcel.readString() ?: "",
                phone = parcel.readString() ?: "",
                email = parcel.readString() ?: "",
                nationalid = parcel.readString() ?: "",
                licence_id = parcel.readString() ?: "",
                licence_count = parcel.readInt(),
                branch = parcel.readString() ?: ""
            )
        }

        override fun newArray(size: Int): Array<SignUp?> = arrayOfNulls(size)
    }
}
