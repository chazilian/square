package com.foobear.square.data.entity.responses

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Employee(
    @PrimaryKey @NonNull @SerializedName("uuid")
    var id: String,

    @NonNull @SerializedName("full_name")
    var fullName: String,

    @Nullable @SerializedName("phone_number")
    var phoneNumber: String? = "Phone number pending",

    @NonNull @SerializedName("email_address")
    var emailAddress: String,

    @Nullable
    var biography: String? = "Biography pending",

    @Nullable @SerializedName("photo_url_small")
    var photoSmall: String?,

    @Nullable @SerializedName("photo_url_large")
    var photoLarge: String?,

    @NonNull
    var team: String,

    @NonNull @SerializedName("employee_type")
    var employeeType: EmpTypeEnumEntity,

    @NonNull
    var IngestedAt: String

){
}

enum class EmpTypeEnumEntity(val value: String){
    @SerializedName("FULL_TIME")
    FULL_TIME("FULL TIME"),
    @SerializedName("PART_TIME")
    PART_TIME("PART TIME"),
    @SerializedName("CONTRACTOR")
    CONTRACTOR("CONTRACTOR")
}