package uk.co.kleindelao.ecf.clubratings.apiclient

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ApiClub(@JsonAlias("club_code") val code: String, @JsonAlias("club_name") val name: String)
