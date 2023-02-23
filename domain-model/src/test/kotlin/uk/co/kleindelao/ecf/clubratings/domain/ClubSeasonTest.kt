package uk.co.kleindelao.ecf.clubratings.domain

import net.bytebuddy.utility.RandomString
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import java.time.LocalDate
import kotlin.math.roundToInt
import kotlin.random.Random

class ClubSeasonTest {
    private fun randomPlayer() = Player(RandomString.make(), RandomString.make(), RandomString.make())

    private fun randomBasicInfo() = BasicClubInfo(RandomString.make(), RandomString.make())

    private fun listOfRandomPlayers(): List<Player> =
        1.rangeTo(Random.nextInt(1, 20)).map { randomPlayer() }.toList()

    private fun randomClub() = Club(randomBasicInfo(), listOfRandomPlayers())

    private fun randomRating() = Random.nextInt(100, 3000)

    @Test
    fun shouldComputeAverageWhenAllPLayersHaveRatingsOnBothEnds() {
        val club = randomClub()
        val playerSeasons =
            club.players.map { player -> PlayerSeason(player, randomRating(), randomRating()) }
                .toList()
        val clubSeason = ClubSeason(club, LocalDate.now().minusMonths(4), LocalDate.now(), playerSeasons)
        val expectedAverage =
            playerSeasons.map { playerSeason -> playerSeason.endRating!! - playerSeason.startRating!! }.average()
                .roundToInt()

        then(clubSeason.averageRatingGain).isNotNull.isEqualTo(expectedAverage)
        then(clubSeason.formattedGain).isNotNull
            .isEqualTo(expectedFormattedGain(expectedAverage))
    }

    @Test
    fun shouldIgnorePlyersWithoutStartRating() {
        val club = randomClub()
        val regularPlayerSeasons =
            club.players.map { player -> PlayerSeason(player, randomRating(), randomRating()) }
                .toList()
        val newPlayerSeason = PlayerSeason(randomPlayer(), null, randomRating())
        val playerSeasons = regularPlayerSeasons.plus(newPlayerSeason)
        val clubSeason = ClubSeason(club, LocalDate.now().minusMonths(4), LocalDate.now(), playerSeasons)
        val expectedAverage =
            regularPlayerSeasons.map { playerSeason -> playerSeason.endRating!! - playerSeason.startRating!! }
                .average()
                .roundToInt()

        then(clubSeason.averageRatingGain).isNotNull.isEqualTo(expectedAverage)
        then(clubSeason.formattedGain).isNotNull
            .isEqualTo(expectedFormattedGain(expectedAverage))
    }

    @Test
    fun shouldIgnorePlayersWithoutCurrentRating() {
        val club = randomClub()
        val regularPlayerSeasons =
            club.players.map { player -> PlayerSeason(player, randomRating(), randomRating()) }
                .toList()
        val impossiblePlayerSeason = PlayerSeason(randomPlayer(), randomRating(), null)
        val playerSeasons = regularPlayerSeasons.plus(impossiblePlayerSeason)
        val clubSeason = ClubSeason(club, LocalDate.now().minusMonths(4), LocalDate.now(), playerSeasons)
        val expectedAverage =
            regularPlayerSeasons.map { playerSeason -> playerSeason.endRating!! - playerSeason.startRating!! }
                .average()
                .roundToInt()

        then(clubSeason.averageRatingGain).isNotNull.isEqualTo(expectedAverage)
        then(clubSeason.formattedGain).isNotNull
            .isEqualTo(expectedFormattedGain(expectedAverage))
    }

    private fun expectedFormattedGain(expectedAverage: Int) =
        if (expectedAverage >= 0) "+${expectedAverage}" else expectedAverage.toString()
}