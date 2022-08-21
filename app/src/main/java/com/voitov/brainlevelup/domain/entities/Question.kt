package com.voitov.brainlevelup.domain.entities

data class Question(
    val sum: Int,
    val visibleValue: Int,
    val options: List<Int>,
)