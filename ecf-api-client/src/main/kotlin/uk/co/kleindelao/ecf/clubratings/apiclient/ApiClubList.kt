package uk.co.kleindelao.ecf.clubratings.apiclient

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ApiClubList(val players: List<List<String?>>)
