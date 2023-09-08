package be.technifutur.androidpersistanceeval

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.util.Date

class Converters {
    @TypeConverter
    fun fromTimeStamp(value :Long?) : LocalDate? {
        value?.let {
            return Instant.ofEpochMilli(value).atZone(ZoneId.systemDefault()).toLocalDate()
        }
        return null
    }

    @TypeConverter
    fun dateToTimestamp(date :LocalDate?) :Long?{
        date?.let {
            return date.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli()
        }
        return null
    }
}