package model

enum class FieldType {
    WATER, MISS, HIT, SUNK
}

data class Field(val fieldType: FieldType)
