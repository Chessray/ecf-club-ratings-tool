package uk.co.kleindelao.ecf.clubratings.domain

data class PlayerSeason(val player: Player, val startRating: Int?, val endRating: Int?) {
    val ratingGain: Int? =
        if (startRating == null || endRating == null)
            null
        else
            endRating - startRating
}
