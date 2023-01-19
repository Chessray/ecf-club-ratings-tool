package uk.co.kleindelao.ecf.clubratings.scraper

import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import uk.co.kleindelao.ecf.clubratings.domain.BasicClubInfo
import uk.co.kleindelao.ecf.clubratings.domain.Club
import uk.co.kleindelao.ecf.clubratings.domain.Player

fun addPlayers(basicInfo: BasicClubInfo, playerRetriever: (ratingCode: Int) -> Player): Club {
    val clubListUrl = "https://www.ecfrating.org.uk/v2/new/list_players.php?club_code=${basicInfo.code}"
    val clubDocument = Jsoup.connect(clubListUrl).get()
    val ecfRatingCodes = clubDocument.select("table tbody tr td:eq(1)")
    val players = ecfRatingCodes.stream().map(Element::text)
        .map { codeWithLetter: String -> codeWithLetter.substring(0, codeWithLetter.length - 1) }
        .mapToInt(String::toInt).mapToObj(playerRetriever::invoke).toList()
    return Club(basicInfo, players)
}