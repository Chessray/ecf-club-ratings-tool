package uk.co.kleindelao.ecf.clubratings.domain

data class PlayerSeason(val player: Player, val startRating: Int?, val currentRating: Int?) {
    val ratingGain: Int? =
        if (startRating == null || currentRating == null)
            null
        else
            currentRating - startRating
}
