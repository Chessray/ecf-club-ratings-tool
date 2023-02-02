package uk.co.kleindelao.ecf.clubratings.apiclient

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.squareup.okhttp.OkHttpClient

fun ecfApiClient() = EcfApiClient(OkHttpClient(), jacksonObjectMapper())