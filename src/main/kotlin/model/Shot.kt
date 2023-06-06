package model

data class Shot(
    val position: Point,
    val result: ShotResult,
    val ownerName: String
)
