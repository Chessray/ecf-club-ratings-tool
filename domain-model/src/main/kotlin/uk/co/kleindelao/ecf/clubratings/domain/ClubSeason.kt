package uk.co.kleindelao.ecf.clubratings.domain

import java.text.DecimalFormat
import java.time.LocalDate
import kotlin.math.roundToInt

data class ClubSeason(val club: Club, val startDate: LocalDate, val endDate: LocalDate, val playerSeasons: List<PlayerSeason>) {
    val averageRatingGain: Int =
        playerSeasons.filterNot { playerSeason -> playerSeason.ratingGain == null }
            .map { playerSeason -> playerSeason.ratingGain!! }.average()
            .roundToInt()

    val formattedGain = DecimalFormat("+#######;-########").format(averageRatingGain)
}
