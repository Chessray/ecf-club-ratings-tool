package uk.co.kleindelao.ecf.clubratings.apiclient

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.squareup.okhttp.OkHttpClient
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import uk.co.kleindelao.ecf.clubratings.domain.BasicClubInfo
import uk.co.kleindelao.ecf.clubratings.domain.Player
import java.time.LocalDate

class EcfApiClientTest {
    private val underTest = EcfApiClient(OkHttpClient(), jacksonObjectMapper())

    @Test
    fun shouldReturnPlayer() {
        val player = underTest.getPlayer(297723)

        then(player.ecfCode).isEqualTo("297723A")
        then(player.firstName).isEqualTo("Raimund")
        then(player.lastName).isEqualTo("Klein")
    }

    @Test
    fun shouldReturnKnownRating() {
        val rating = underTest.getRatingOnDay(Player("297723A", "Raimund", "Klein"), LocalDate.of(2023, 1, 1))
        then(rating).isEqualTo(1966)
    }

    @Test
    fun shouldReturnNullForUnratedPlayer() {
        val rating = underTest.getRatingOnDay(Player("341117F", "Marian", "Chiriac Popescu"), LocalDate.of(2023, 1, 1))
        then(rating).isNull()
    }

    @Test
    fun shouldReturnClubInfo() {
        val basicInfo = underTest.getClub("4YEO")
        then(basicInfo.code).isEqualTo("4YEO")
        then(basicInfo.name).isEqualTo("Yeovil")
    }

    @Test
    fun shouldReturnClubs() {
        val clubs = underTest.getAllClubs()
        then(clubs).isNotEmpty().anySatisfy { club -> then(club).isEqualTo(BasicClubInfo("4YEO", "Yeovil")) }
    }
}