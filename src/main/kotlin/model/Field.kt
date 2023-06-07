package model

enum class FieldType {
    WATER, MISS, HIT, SUNK, SHIP
}

data class Field(var fieldType: FieldType)
