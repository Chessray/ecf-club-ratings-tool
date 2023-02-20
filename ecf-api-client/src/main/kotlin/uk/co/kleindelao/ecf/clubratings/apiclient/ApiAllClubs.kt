package uk.co.kleindelao.ecf.clubratings.apiclient

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ApiAllClubs(val clubs: List<ApiClub>)
