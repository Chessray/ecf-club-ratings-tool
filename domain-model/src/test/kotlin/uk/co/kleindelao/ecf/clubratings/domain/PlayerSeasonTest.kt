package uk.co.kleindelao.ecf.clubratings.domain

import net.bytebuddy.utility.RandomString
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import kotlin.random.Random

class PlayerSeasonTest {
    @Test
    fun shouldCalculateRatingDifferenceFromStartRatingAndEndRating() {
        val startRating = Random.nextInt(3000)
        val endRating = Random.nextInt(3000)
        val playerSeason =
            PlayerSeason(
                Player(RandomString.make(), RandomString.make(), RandomString.make()),
                startRating,
                endRating
            )

        val actualDifference = playerSeason.ratingGain
        then(actualDifference).isEqualTo(endRating - startRating)
    }

    @Test
    fun shouldReturnNullIfStartRatingIsNull() {
        val startRating = null
        val endRating = Random.nextInt(3000)
        val playerSeason =
            PlayerSeason(
                Player(RandomString.make(), RandomString.make(), RandomString.make()),
                startRating,
                endRating
            )

        val actualDifference = playerSeason.ratingGain
        then(actualDifference).isNull()
    }

    @Test
    fun shouldReturnNullIfEndRatingIsNull() {
        val startRating = Random.nextInt(3000)
        val endRating = null
        val playerSeason =
            PlayerSeason(
                Player(RandomString.make(), RandomString.make(), RandomString.make()),
                startRating,
                endRating
            )

        val actualDifference = playerSeason.ratingGain
        then(actualDifference).isNull()
    }

    @Test
    fun shouldReturnNullIfStartRatingAndEndRatingAreNull() {
        val startRating = null
        val endRating = null
        val playerSeason =
            PlayerSeason(
                Player(RandomString.make(), RandomString.make(), RandomString.make()),
                startRating,
                endRating
            )

        val actualDifference = playerSeason.ratingGain
        then(actualDifference).isNull()
    }
}