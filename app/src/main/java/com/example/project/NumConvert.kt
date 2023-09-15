package com.example.project

object NumConvert {
    private val list = listOf("Первый", "Второй", "Третий", "Четвертый", "Пятый",
        "Шестой", "Седьмой", "Восьмой", "Девятый", "Десятый")

    fun getWordFromNumber(num: Int): String{
        return list[num]
    }
}