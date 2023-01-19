package uk.co.kleindelao.ecf.clubratings.scraper

import net.bytebuddy.utility.RandomString
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import uk.co.kleindelao.ecf.clubratings.domain.BasicClubInfo
import uk.co.kleindelao.ecf.clubratings.domain.Player

class ScraperFunctionsKtTest {
    @Test
    fun shouldReturnClubWithNonEmptyListOfPLayers() {
        val clubInfo = BasicClubInfo("4YEO", "Yeovil")
        val club = addPlayers(clubInfo) { _: Int ->
            Player(
                RandomString.make(),
                RandomString.make(),
                RandomString.make()
            )
        }
        then(club.basicInfo).isEqualTo(clubInfo)
        then(club.players).isNotEmpty
    }
}