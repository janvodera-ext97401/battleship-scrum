package model

data class Ship(
    val position: Point,
    val length: Int,
    val direction: Direction,
    val ownerName: String,
    var hits: Int= 0,
) {
    fun isSunk() = hits == length


    fun hit() {
        hits++
    }
}
