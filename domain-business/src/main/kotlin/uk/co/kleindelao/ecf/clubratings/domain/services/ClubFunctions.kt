package uk.co.kleindelao.ecf.clubratings.domain.services

import uk.co.kleindelao.ecf.clubratings.apiclient.ecfApiClient
import uk.co.kleindelao.ecf.clubratings.domain.BasicClubInfo
import uk.co.kleindelao.ecf.clubratings.domain.Club
import uk.co.kleindelao.ecf.clubratings.domain.ClubSeason
import uk.co.kleindelao.ecf.clubratings.domain.PlayerSeason
import uk.co.kleindelao.ecf.clubratings.scraper.addPlayers
import java.time.LocalDate

fun getInfoForClubCode(code: String): BasicClubInfo {
    return ecfApiClient().getClub(code)
}

fun getClubForClubCode(code: String): Club {
    return addPlayers(getInfoForClubCode(code), ecfApiClient()::getPlayer)
}

fun getClubSeason(clubCode: String, startDate: LocalDate, endDate: LocalDate): ClubSeason {
    val club = getClubForClubCode(clubCode)
    val playerSeasons = club.players.map { player ->
        PlayerSeason(
            player,
            ecfApiClient().getRatingOnDay(player, startDate),
            ecfApiClient().getRatingOnDay(player, endDate)
        )
    }.toList()
    return ClubSeason(club, startDate, endDate, playerSeasons)
}