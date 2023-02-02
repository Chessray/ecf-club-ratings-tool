package uk.co.kleindelao.ecf.clubratings.apiclient

import org.assertj.core.api.BDDAssertions.thenCode
import org.junit.jupiter.api.Test

class ApiClientFunctionsKtTest {
    @Test
    fun shouldReturnApiClient() {
        thenCode { ecfApiClient() }.doesNotThrowAnyException()
    }
}