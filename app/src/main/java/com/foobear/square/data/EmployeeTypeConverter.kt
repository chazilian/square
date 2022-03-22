package com.foobear.square.data

import androidx.room.TypeConverter
import com.foobear.square.data.entity.responses.EmpTypeEnumEntity

class EmployeeTypeConverter {
    @TypeConverter
    fun toHealth(value: String) = enumValueOf<EmpTypeEnumEntity>(value)

    @TypeConverter
    fun fromHealth(value: EmpTypeEnumEntity) = value.name
}