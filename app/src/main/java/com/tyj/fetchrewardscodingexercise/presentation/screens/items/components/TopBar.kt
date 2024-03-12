package com.tyj.fetchrewardscodingexercise.presentation.screens.items.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavController
import com.tyj.fetchrewardscodingexercise.presentation.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    onBackPressed: () -> Unit,
    title: String,
    certainListId: Int,
    sortedListIds: Set<Int>,
    navController: NavController,
) {

    // state of the menu
    var expanded by remember { mutableStateOf(false) }
    var selectedListId by remember { mutableIntStateOf(certainListId) }

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleLarge,

            )
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = "arrow back",
                modifier = Modifier
                    .clickable { onBackPressed() },
                tint = MaterialTheme.colorScheme.secondary
            )
        },
        modifier = Modifier.background(MaterialTheme.colorScheme.background),

        actions = {

            IconButton(
                onClick = {
                    expanded = true
                },
            ) {
                Icon(
                    imageVector = Icons.Default.FilterAlt,
                    contentDescription = "Open Options",
                )
            }

            // drop down menu
            DropdownMenu(
                modifier = Modifier
                    .width(150.dp)
                    .background(color = MaterialTheme.colorScheme.surfaceContainer),
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                },
                // adjust the position
                offset = DpOffset(x = (-5).dp, y = (0).dp),
                properties = PopupProperties()
            ) {
                val currentRoute = navController.currentBackStackEntry?.destination?.route
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        selectedListId = -1
                        if(currentRoute != Screen.Home.route) {
                            navController.popBackStack()
                            //navController.navigate(Screen.Home.route)
                        }
                    },
                    enabled = true,
                    text = {
                        Text(
                            text = "All Items",
                            fontWeight = FontWeight.Medium,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    },
                    modifier = if(selectedListId == -1)
                        Modifier.background(color = MaterialTheme.colorScheme.secondary)
                    else
                        Modifier.background(color = MaterialTheme.colorScheme.surfaceContainer)
                )

                sortedListIds.forEach { listId ->
                    DropdownMenuItem(
                        onClick = {
                            expanded = false
                            selectedListId = listId
                            if(currentRoute != Screen.Home.route) navController.popBackStack()
                            navController.navigate("${Screen.GroupedItems.route}/${listId}")
                        },
                        enabled = true,
                        text = {
                            Text(
                                text = "List $listId",
                                fontWeight = FontWeight.Medium,
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        },
                        modifier = if(selectedListId == listId)
                            Modifier.background(color = MaterialTheme.colorScheme.secondary)
                        else
                            Modifier.background(color = MaterialTheme.colorScheme.surfaceContainer)
                    )
                }

            }
        }

    )
}