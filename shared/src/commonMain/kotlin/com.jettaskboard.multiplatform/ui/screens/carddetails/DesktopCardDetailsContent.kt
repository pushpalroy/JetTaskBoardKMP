package com.jettaskboard.multiplatform.ui.screens.carddetails

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.Attachment
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CopyAll
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Label
import androidx.compose.material.icons.filled.LocalActivity
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhotoAlbum
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material.icons.filled.WrapText
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jettaskboard.multiplatform.ui.theme.CardGray
import com.jettaskboard.multiplatform.ui.theme.LabelBlue
import com.jettaskboard.multiplatform.ui.theme.WindowsCardDetailBGColor
import com.jettaskboard.multiplatform.ui.theme.WindowsCardDetailPlaceholderTextColor
import com.jettaskboard.multiplatform.ui.theme.WindowsCardDetailTextColor

@Composable
fun DesktopCardDetailsContent(
    leftScrollState: ScrollState,
    rightScrollState: ScrollState,
    onBackClick: () -> Unit,
    viewModel: CardViewModel
) {

    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1.6f)
        ) {
            LeftPane(leftScrollState)
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.4f)
        ) {
            RightPane(rightScrollState, onBackClick)
        }
    }
}

@Composable
fun LeftPane(leftScrollState: ScrollState) {

    var commentText by remember { mutableStateOf("") }
    var cardDescriptionText by remember { mutableStateOf("Card Details") }
    var isEditCardDescriptionTextClick by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(leftScrollState)
            .background(WindowsCardDetailBGColor)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                imageVector = Icons.Default.CreditCard,
                contentDescription = "Card Name",
                tint = WindowsCardDetailTextColor
            )

            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Praxis",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = WindowsCardDetailTextColor
                )

                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = "in list Backlog",
                    fontSize = 12.sp,
                    color = WindowsCardDetailTextColor
                )

                Text(
                    modifier = Modifier.padding(top = 28.dp),
                    text = "Notifications",
                    fontSize = 12.sp,
                    color = WindowsCardDetailTextColor
                )

                IconCard(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(top = 8.dp),
                    leadingIcon = Icons.Default.RemoveRedEye,
                    cardText = "Watch",
                    textPaddingValues = PaddingValues(end = 16.dp)
                )
            }
        }

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                imageVector = Icons.Default.WrapText,
                contentDescription = "Description",
                tint = WindowsCardDetailTextColor
            )

            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Description",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = WindowsCardDetailTextColor
                    )

                    if (isEditCardDescriptionTextClick) {
                        Icon(
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .size(12.dp),
                            imageVector = Icons.Outlined.Info,
                            contentDescription = "More info",
                            tint = WindowsCardDetailTextColor
                        )
                    } else {
                        IconCard(
                            modifier = Modifier
                                .padding(horizontal = 8.dp),
                            cardText = "Edit",
                            textPaddingValues = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
                            onCardClick = {
                                isEditCardDescriptionTextClick = true
                            }
                        )
                    }
                }

                if(isEditCardDescriptionTextClick) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    ) {
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp)
                                .height(350.dp),
                            value = cardDescriptionText,
                            onValueChange = { textFieldValue ->
                                cardDescriptionText = textFieldValue
                            },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                textColor = WindowsCardDetailTextColor,
                                cursorColor = LabelBlue,
                                focusedBorderColor = LabelBlue,
                                unfocusedBorderColor = Color.DarkGray
                            )
                        )

                        Row(
                            modifier = Modifier
                                .padding(top = 4.dp)
                                .padding(horizontal = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button(
                                content = {
                                    Text(
                                        modifier = Modifier
                                            .padding(vertical = 4.dp, horizontal = 8.dp),
                                        text = "Save",
                                        color = Color.White
                                    )
                                },
                                onClick = {
                                    isEditCardDescriptionTextClick = false
                                },
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = LabelBlue
                                )
                            )

                            Text(
                                modifier = Modifier
                                    .padding(start = 16.dp)
                                    .clickable {
                                        isEditCardDescriptionTextClick = false
                                    },
                                text = "Cancel",
                                color = WindowsCardDetailTextColor
                            )
                        }
                    }
                } else {
                    Text(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .clickable { isEditCardDescriptionTextClick = true },
                        text = cardDescriptionText,
                        fontSize = 12.sp,
                        color = WindowsCardDetailTextColor
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Icon(
                    imageVector = Icons.Default.LocalActivity,
                    contentDescription = "Activity",
                    tint = WindowsCardDetailTextColor
                )

                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = "Activity",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = WindowsCardDetailTextColor
                )
            }

            IconCard(
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                cardText = "Show Details",
                textPaddingValues = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "name",
                tint = WindowsCardDetailTextColor
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                value = commentText,
                placeholder = {
                    Text(
                        "Write a comment... ",
                        color = WindowsCardDetailPlaceholderTextColor
                    )
                },
                onValueChange = { textFieldValue ->
                    commentText = textFieldValue
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = WindowsCardDetailTextColor,
                    cursorColor = LabelBlue,
                    focusedBorderColor = LabelBlue,
                    unfocusedBorderColor = Color.DarkGray
                )
            )
        }
    }
}

@Composable
fun RightPane(
    rightScrollState: ScrollState,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WindowsCardDetailBGColor)
            .verticalScroll(rightScrollState)
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                modifier = Modifier.clickable { onBackClick() },
                imageVector = Icons.Default.Close,
                contentDescription = "Close",
                tint = WindowsCardDetailTextColor
            )
        }

        Text(
            modifier = Modifier.padding(top = 28.dp),
            text = "Add to card",
            fontSize = 12.sp,
            color = WindowsCardDetailTextColor
        )

        IconCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            leadingIcon = Icons.Default.Person,
            cardText = "Members",
            textPaddingValues = PaddingValues(8.dp)
        )

        IconCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            leadingIcon = Icons.Default.Label,
            cardText = "Labels",
            textPaddingValues = PaddingValues(8.dp)
        )

        IconCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            leadingIcon = Icons.Default.Checklist,
            cardText = "Checklist",
            textPaddingValues = PaddingValues(8.dp)
        )

        IconCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            leadingIcon = Icons.Default.DateRange,
            cardText = "Dates",
            textPaddingValues = PaddingValues(8.dp)
        )

        IconCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            leadingIcon = Icons.Default.Attachment,
            cardText = "Attachment",
            textPaddingValues = PaddingValues(8.dp)
        )

        IconCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            leadingIcon = Icons.Default.CreditCard,
            cardText = "Cover",
            textPaddingValues = PaddingValues(8.dp)
        )

        IconCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            leadingIcon = Icons.Default.TextFields,
            cardText = "Custom Fields",
            textPaddingValues = PaddingValues(8.dp)
        )

        Text(
            modifier = Modifier.padding(top = 28.dp),
            text = "Power-Ups",
            fontSize = 12.sp,
            color = WindowsCardDetailTextColor
        )

        IconCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            leadingIcon = Icons.Default.Add,
            cardText = "Add Power-Ups",
            textPaddingValues = PaddingValues(8.dp),
            backgroundColor = WindowsCardDetailBGColor
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Automation",
                fontSize = 12.sp,
                color = WindowsCardDetailTextColor
            )

            Icon(
                modifier = Modifier
                    .size(12.dp),
                imageVector = Icons.Outlined.Info,
                contentDescription = "Automation Info",
                tint = WindowsCardDetailTextColor
            )
        }


        IconCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            leadingIcon = Icons.Default.Add,
            cardText = "Add Button",
            textPaddingValues = PaddingValues(8.dp),
            backgroundColor = WindowsCardDetailBGColor
        )

        Text(
            modifier = Modifier.padding(top = 28.dp),
            text = "Actions",
            fontSize = 12.sp,
            color = WindowsCardDetailTextColor
        )

        IconCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            leadingIcon = Icons.Default.KeyboardArrowRight,
            cardText = "Move",
            textPaddingValues = PaddingValues(8.dp)
        )

        IconCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            leadingIcon = Icons.Default.CopyAll,
            cardText = "Copy",
            textPaddingValues = PaddingValues(8.dp)
        )

        IconCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            leadingIcon = Icons.Default.PhotoAlbum,
            cardText = "Make template",
            textPaddingValues = PaddingValues(8.dp)
        )

        Divider(
            modifier = Modifier.padding(top = 8.dp),
            color = WindowsCardDetailTextColor
        )

        IconCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            leadingIcon = Icons.Default.Archive,
            cardText = "Archive",
            textPaddingValues = PaddingValues(8.dp)
        )

        IconCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            leadingIcon = Icons.Default.Share,
            cardText = "Share",
            textPaddingValues = PaddingValues(8.dp)
        )

    }
}

@Composable
fun IconCard(
    cardText: String,
    leadingIcon: ImageVector? = null,
    textPaddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    backgroundColor: Color = CardGray,
    onCardClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .clickable { onCardClick() },
        backgroundColor = backgroundColor
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (leadingIcon != null) {
                Icon(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(18.dp),
                    imageVector = leadingIcon,
                    contentDescription = leadingIcon.name,
                    tint = WindowsCardDetailTextColor
                )
            }
            Text(
                modifier = Modifier.padding(textPaddingValues),
                text = cardText,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                color = WindowsCardDetailTextColor
            )
        }
    }
}
