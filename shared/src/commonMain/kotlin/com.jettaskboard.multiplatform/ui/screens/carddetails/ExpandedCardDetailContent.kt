package com.jettaskboard.multiplatform.ui.screens.carddetails

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetapps.jettaskboard.carddetailscomponents.EditTextCard
import com.jetapps.jettaskboard.carddetailscomponents.QuickActionsCard
import com.jettaskboard.multiplatform.util.imageattachment.ImageAttachmentView

@Composable
fun ExpandedCardDetailContent(
    leftScrollState: ScrollState,
    rightScrollState: ScrollState,
    cardDetails: CardDetail,
    viewModel: CardViewModel
) {

    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        ) {
            LeftPane(leftScrollState, cardDetails, viewModel)
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        ) {
            RightPane(rightScrollState, cardDetails, viewModel)
        }
    }
}

@Composable
fun RightPane(
    rightScrollState: ScrollState,
    cardDetails: CardDetail,
    viewModel: CardViewModel,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rightScrollState)
            .padding(16.dp)
    ) {
        val members by remember { mutableStateOf(cardDetails.authorName ?: "Members...") }
        ItemRow(
            leadingIcon = {
                Icon(
                    modifier = Modifier.padding(16.dp),
                    imageVector = Icons.Outlined.Person,
                    contentDescription = "Leading Icon"
                )
            },
            text = members
        )

        LabelRow(viewModel)

        TimeItemRow(
            modifier = Modifier,
            icon = Icons.Default.Timer,
            topText = viewModel.startDateText.value,
            bottomText = viewModel.dueDateText.value,
            onStartDateClick = {
                viewModel.isTopText.value = true
                viewModel.isBottomText.value = false
            },
            onDueDateClick = {
                viewModel.isBottomText.value = true
                viewModel.isTopText.value = false
            }
        ) {

        }

        ImageAttachmentView()

        Divider()
    }
}

@Composable
fun LeftPane(leftScrollState: ScrollState, cardDetails: CardDetail, viewModel: CardViewModel) {
    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.999f)
                .verticalScroll(leftScrollState)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            Image(
//                painter = painterResource("backlog"),
//                contentDescription = "Backlog",
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(180.dp)
//            )

            Text(
                modifier = Modifier.padding(16.dp),
                text = cardDetails.title ?: "Backlog",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = "${(cardDetails.boardName) ?: "Praxis"} in list ${(cardDetails.listName) ?: "Backlog"}",
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(8.dp))
            Divider()
            Spacer(modifier = Modifier.height(8.dp))

            EditTextCard(viewModel = viewModel, isExpanded = true)

            Divider()
            Spacer(modifier = Modifier.height(8.dp))

            QuickActionsCard(isExpanded = true)

            Spacer(modifier = Modifier.height(8.dp))
            Divider()
            Spacer(modifier = Modifier.height(8.dp))
        }
        Divider(
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )
    }
}
