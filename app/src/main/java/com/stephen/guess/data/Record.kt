package com.stephen.guess.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity
class Record(
    @NonNull
    @ColumnInfo(name = "nick")
    var nickname: String,
    @NotNull
    var counter: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

@Entity
class Word {
    @PrimaryKey
    var name: String = ""
    var means: String = ""
    var star: Int = 0
}