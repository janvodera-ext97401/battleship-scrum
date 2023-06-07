package model

data class Ship(
    var position: Point,
    val length: Int,
    var direction: Direction,
    var ownerName: String,
    var hits: Int= 0,
) {
    fun isSunk() = hits == length


    fun hit() {
        hits++
    }
}
