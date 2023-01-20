package uk.co.kleindelao.ecf.clubratings.domain

import net.bytebuddy.utility.RandomString
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import kotlin.random.Random

class PlayerSeasonTest {
    @Test
    fun shouldCalculateRatingDifferenceFromStartRatingAndCurrentRating() {
        val startRating = Random.nextInt(3000)
        val currentRating = Random.nextInt(3000)
        val playerSeason =
            PlayerSeason(
                Player(RandomString.make(), RandomString.make(), RandomString.make()),
                startRating,
                currentRating
            )

        val actualDifference = playerSeason.ratingGain
        then(actualDifference).isEqualTo(currentRating - startRating)
    }

    @Test
    fun shouldReturnNullIfStartRatingIsNull() {
        val startRating = null
        val currentRating = Random.nextInt(3000)
        val playerSeason =
            PlayerSeason(
                Player(RandomString.make(), RandomString.make(), RandomString.make()),
                startRating,
                currentRating
            )

        val actualDifference = playerSeason.ratingGain
        then(actualDifference).isNull()
    }

    @Test
    fun shouldReturnNullIfEndRatingIsNull() {
        val startRating = Random.nextInt(3000)
        val currentRating = null
        val playerSeason =
            PlayerSeason(
                Player(RandomString.make(), RandomString.make(), RandomString.make()),
                startRating,
                currentRating
            )

        val actualDifference = playerSeason.ratingGain
        then(actualDifference).isNull()
    }

    @Test
    fun shouldReturnNullIfStartRatingAndEndRatingAreNull() {
        val startRating = null
        val currentRating = null
        val playerSeason =
            PlayerSeason(
                Player(RandomString.make(), RandomString.make(), RandomString.make()),
                startRating,
                currentRating
            )

        val actualDifference = playerSeason.ratingGain
        then(actualDifference).isNull()
    }
}