package uk.co.kleindelao.ecf.clubratings.apiclient

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ApiPlayer(@JsonAlias("full_name") val fullName: String, @JsonAlias("ECF_code") val ecfCode: String)
