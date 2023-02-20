package uk.co.kleindelao.ecf.clubratings.app

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.form.DropDownChoice
import org.apache.wicket.model.Model
import org.apache.wicket.model.PropertyModel
import org.apache.wicket.request.mapper.parameter.PageParameters
import uk.co.kleindelao.ecf.clubratings.domain.BasicClubInfo
import uk.co.kleindelao.ecf.clubratings.domain.services.getAllClubs

@WicketHomePage
class HomePage(pageParameters: PageParameters? = null) : WebPage(pageParameters) {
    init {
        val allClubs = getAllClubs().sortedBy { it.name }
        val clubSelection = DropDownChoice(
            "club", Model(selectedClub(allClubs, pageParameters)),
            allClubs,
            BasicClubInfo::name
        )

        add(clubSelection)
        add(Label("selectedClub", PropertyModel<BasicClubInfo>(clubSelection.modelObject, "code")))
    }

    private fun selectedClub(
        allClubs: List<BasicClubInfo>,
        pageParameters: PageParameters?
    ) = when (pageParameters) {
        null -> {
            null
        }

        else -> {
            allClubs[pageParameters.get("club").toInt()]
        }
    }
}
