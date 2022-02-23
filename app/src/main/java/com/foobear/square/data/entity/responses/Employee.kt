package com.foobear.square.data.entity.responses

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import com.google.gson.annotations.SerializedName

data class Employee(
    @NonNull @SerializedName("uuid")
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
    var employeeType: EmpTypeEnumEntity

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