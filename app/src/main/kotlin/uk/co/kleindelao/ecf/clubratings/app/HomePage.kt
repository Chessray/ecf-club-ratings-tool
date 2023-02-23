package uk.co.kleindelao.ecf.clubratings.app

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage
import org.apache.wicket.AttributeModifier
import org.apache.wicket.extensions.markup.html.form.datetime.LocalDateTextField
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.form.DropDownChoice
import org.apache.wicket.model.Model
import org.apache.wicket.model.PropertyModel
import org.apache.wicket.request.mapper.parameter.PageParameters
import uk.co.kleindelao.ecf.clubratings.domain.BasicClubInfo
import uk.co.kleindelao.ecf.clubratings.domain.ClubSeason
import uk.co.kleindelao.ecf.clubratings.domain.services.getAllClubs
import uk.co.kleindelao.ecf.clubratings.domain.services.getClubSeason
import java.time.LocalDate
import java.time.Month.SEPTEMBER

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
        val startDateField = dateField("startDate", startDate(pageParameters))
        val endDateField = dateField("endDate", endDate(pageParameters))
        val clubSeason = clubSeason(clubSelection.modelObject, startDateField.modelObject, endDateField.modelObject)

        add(Label("selectedClub", PropertyModel<BasicClubInfo>(clubSelection.modelObject, "name")))
        add(Label("numberOfPlayers", PropertyModel<ClubSeason?>(clubSeason, "playerSeasons.size")))
        add(Label("averageRatingDevelopment", PropertyModel<ClubSeason?>(clubSeason, "formattedGain")))
    }

    private fun clubSeason(clubInfo: BasicClubInfo?, startDate: LocalDate?, endDate: LocalDate?): ClubSeason? {
        if (clubInfo != null && startDate != null && endDate != null) {
            return getClubSeason(clubInfo.code, startDate, endDate)
        }
        return null
    }

    private fun dateField(id: String, modelDate: LocalDate?): LocalDateTextField {
        val dateField = LocalDateTextField(id, Model(modelDate), "yyyy-MM-dd")
        dateField.add(AttributeModifier.replace("type", "date"))
        add(dateField)
        return dateField
    }

    private fun startDate(pageParameters: PageParameters?) = when (pageParameters) {
        null -> {
            val baseDate = LocalDate.now().withDayOfMonth(1).withMonth(SEPTEMBER.value)
            when {
                baseDate.isAfter(LocalDate.now()) -> {
                    baseDate.withYear(baseDate.year - 1)
                }
                else -> {
                    baseDate
                }
            }
        }

        else -> {
            LocalDate.parse(pageParameters.get("startDate").toString())
        }
    }

    private fun endDate(pageParameters: PageParameters?) = when (pageParameters) {
        null -> {
            LocalDate.now()
        }

        else -> {
            LocalDate.parse(pageParameters.get("endDate").toString())
        }
    }

    private fun selectedClub(
        allClubs: List<BasicClubInfo>,
        pageParameters: PageParameters?
    ) = when (pageParameters) {
        null -> {
            null
        }

        else -> {
            val rawClub = pageParameters.get("club")
            when {
                rawClub.isEmpty -> {
                    null
                }
                else -> {
                    allClubs[rawClub.toInt()]
                }
            }
        }
    }
}
