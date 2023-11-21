package cl.rogarciap.marketapp.views.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cl.rogarciap.marketapp.views.DrawerItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerContentComponent(
    items: Array<DrawerItem>,
    onMenuClick: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        items.forEach {
            NavigationDrawerItem(
                label = {
                    Text(text = it.title)
                },
                icon = {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = null
                    )
                },
                selected =false ,
                onClick = {
                    onMenuClick(it.route)
                }
            )
        }
    }
}