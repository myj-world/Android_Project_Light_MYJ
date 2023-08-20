package com.yousufjamil.myjchitchat.navandtopbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.yousufjamil.myjchitchat.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar (
    onNavigationIconClick: () -> Unit
) {
    androidx.compose.material3.TopAppBar(
        title = {
                Text(text = stringResource(id = R.string.app_name))
        },
        navigationIcon = {
            IconButton(onClick = { onNavigationIconClick() }) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Toggle navigation bar")
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}