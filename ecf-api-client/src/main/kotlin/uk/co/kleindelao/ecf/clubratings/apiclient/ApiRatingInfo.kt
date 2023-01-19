package uk.co.kleindelao.ecf.clubratings.apiclient

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ApiRatingInfo(@JsonAlias("original_rating") val originalRating: Int?)
