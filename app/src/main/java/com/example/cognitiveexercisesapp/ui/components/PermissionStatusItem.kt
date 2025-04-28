package com.example.cognitiveexercisesapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cognitiveexercisesapp.ui.data.languages.LanguageManager

@Composable
fun PermissionStatusItem(
    title: String,
    isGranted: Boolean,
    onRequest: () -> Unit
) {
    var lang = LanguageManager.language.permissionStatus

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = if (isGranted) lang.buttonTextOn else lang.buttonTextOff,
                style = MaterialTheme.typography.bodyMedium,
                color = if (isGranted)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.error
            )
        }

        Switch(
            checked = isGranted,
            onCheckedChange = { onRequest() },
            modifier = Modifier.padding(start = 8.dp),
            thumbContent = if (isGranted) {
                {
                    Icon(
                        imageVector = androidx.compose.material.icons.Icons.Rounded.Check,
                        contentDescription = null,
                        modifier = Modifier.size(SwitchDefaults.IconSize)
                    )
                }
            } else null
        )
    }
}