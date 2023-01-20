package uk.co.kleindelao.ecf.clubratings.domain

import java.time.LocalDate

data class ClubSeason(val club: Club, val startDate: LocalDate, val playerSeasons: List<PlayerSeason>)
