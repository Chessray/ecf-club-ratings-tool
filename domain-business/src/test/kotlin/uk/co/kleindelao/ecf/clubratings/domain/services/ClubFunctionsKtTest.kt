package uk.co.kleindelao.ecf.clubratings.domain.services

import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import uk.co.kleindelao.ecf.clubratings.domain.BasicClubInfo
import java.time.LocalDate
import java.time.Month.FEBRUARY
import java.time.Month.SEPTEMBER

class ClubFunctionsKtTest {
    @Test
    fun shouldGetBasicInfo() {
        val basicInfo = getInfoForClubCode("4YEO")

        then(basicInfo.code).isEqualTo("4YEO")
        then(basicInfo.name).isEqualTo("Yeovil")
    }

    @Test
    fun shouldGetInfoAndPlayers() {
        val club = getClubForClubCode("4YEO")

        then(club.basicInfo.code).isEqualTo("4YEO")
        then(club.basicInfo.name).isEqualTo("Yeovil")
        then(club.players).isNotEmpty
    }

    @Test
    fun shouldGetClubSeasonForDates() {
        val clubSeason = getClubSeason("4YEO", LocalDate.of(2022, SEPTEMBER, 1), LocalDate.of(2023, FEBRUARY, 1))
        then(clubSeason.club.basicInfo.name).isEqualTo("Yeovil")
        then(clubSeason.averageRatingGain).isEqualTo(1)
    }

    @Test
    fun shouldReturnClubs() {
        val clubs = getAllClubs()
        then(clubs).isNotEmpty.anySatisfy { club -> then(club).isEqualTo(BasicClubInfo("4YEO", "Yeovil")) }
    }
}