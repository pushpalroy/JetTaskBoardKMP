package com.jettaskboard.multiplatform.ui.screens.carddetails

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import com.jettaskboard.multiplatform.ui.theme.LabelBlue
import com.jettaskboard.multiplatform.ui.theme.LabelGreen
import com.jettaskboard.multiplatform.ui.theme.LabelOrange
import com.jettaskboard.multiplatform.ui.theme.LabelPeach
import com.jettaskboard.multiplatform.ui.theme.LabelViolet
import com.jettaskboard.multiplatform.ui.theme.LabelYellow
import com.jettaskboard.multiplatform.util.krouter.ViewModel

class CardViewModel: ViewModel() {

    val cardModel = mutableStateOf(CardDetail(coverImageUrl = "fsd"))

//    val imageAttachmentList = mutableStateListOf<Uri>()

    val labels = mutableStateListOf(
        LabelColor(LabelGreen),
        LabelColor(LabelYellow),
        LabelColor(LabelPeach),
        LabelColor(LabelOrange),
        LabelColor(LabelViolet),
        LabelColor(LabelBlue)
    )

    val selectedColors = mutableStateListOf<Color>()

    var isLabelRowClicked = mutableStateOf(false)

    var inputValue = mutableStateOf(TextFieldValue(cardModel.value.description ?: ""))

    val isTopText = mutableStateOf(false)

    val isBottomText = mutableStateOf(false)

    val startDateText = mutableStateOf(cardModel.value.startDate ?: "Start Date...")

    val dueDateText = mutableStateOf(cardModel.value.dueDate ?: "Due Date...")
}
