package uk.co.kleindelao.ecf.clubratings.apiclient

import com.fasterxml.jackson.databind.ObjectMapper
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import com.squareup.okhttp.ResponseBody
import uk.co.kleindelao.ecf.clubratings.domain.BasicClubInfo
import uk.co.kleindelao.ecf.clubratings.domain.Player
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class EcfApiClient(private val httpClient: OkHttpClient, private val objectMapper: ObjectMapper) {

    fun getPlayer(ratingCode: Int): Player {
        val playerUrl = "https://www.ecfrating.org.uk/v2/new/api.php?v2/players/code/$ratingCode"
        val apiPlayer = getInformation(playerUrl, ApiPlayer::class.java)
        val nameParts = apiPlayer.fullName.split(',', ignoreCase = true, limit = 2)
        return Player(apiPlayer.ecfCode, nameParts[1].trim(), nameParts[0].trim())
    }

    private fun responseBody(request: Request): ResponseBody =
        httpClient.newCall(request).execute().body()

    private fun createGetRequest(url: String): Request =
        Request.Builder().url(url).get().build()

    fun getRatingOnDay(player: Player, date: LocalDate): Int? {
        val ratingCode = player.ecfCode.substring(0, player.ecfCode.length - 1)
        val formattedDate = date.format(DateTimeFormatter.ISO_DATE)
        val ratingUrl = "https://www.ecfrating.org.uk/v2/new/api.php?v2/ratings/S/$ratingCode/$formattedDate"
        return getInformation(ratingUrl, ApiRatingInfo::class.java).originalRating
    }

    private fun <T> getInformation(ratingUrl: String, responseClass: Class<T>): T =
        objectMapper.readValue(responseBody(createGetRequest(ratingUrl)).string(), responseClass)

    fun getClub(clubCode: String): BasicClubInfo {
        val clubUrl = "https://www.ecfrating.org.uk/v2/new/api.php?v2/clubs/code/$clubCode"
        return basicClubInfo(getInformation(clubUrl, ApiClub::class.java))
    }

    private fun basicClubInfo(apiClub: ApiClub) =
        BasicClubInfo(apiClub.code, apiClub.name)

    fun getAllClubs(): List<BasicClubInfo> {
        val allClubsUrl = "https://www.ecfrating.org.uk/v2/new/api.php?v2/clubs/all_active"
        return getInformation(allClubsUrl, ApiAllClubs::class.java).clubs.map { basicClubInfo(it) }
    }
}
