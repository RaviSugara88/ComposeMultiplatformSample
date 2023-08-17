package com.example.composemultiplateformex

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.DismissibleNavigationDrawer
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun MyAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(primary = Color.Black),
        shapes = MaterialTheme.shapes.copy(
            small = AbsoluteCutCornerShape(0.dp),
            medium = AbsoluteCutCornerShape(0.dp),
            large = AbsoluteCutCornerShape(0.dp)
        )
    ) {
        content()
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    data class BottomNavigationItem(
        val title: String,
        val selectedIcon: ImageVector,
        val unselectedIcon: ImageVector,
        val hasNews: Boolean,
        val badgeCount: Int? = null
    )
    MyAppTheme {

        val items = listOf(
            BottomNavigationItem(
                title = "Home",
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home,
                hasNews = false,
            ),
            BottomNavigationItem(
                title = "Chat",
                selectedIcon = Icons.Filled.Email,
                unselectedIcon = Icons.Outlined.Email,
                hasNews = false,
                badgeCount = 45
            ),
            BottomNavigationItem(
                title = "Settings",
                selectedIcon = Icons.Filled.Settings,
                unselectedIcon = Icons.Outlined.Settings,
                hasNews = true,
            ),
        )
        var selectedItemIndex by rememberSaveable {
            mutableStateOf(0)
        }
        Surface (
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ){
            val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

            /** [pinnedScrollBehavior] Change top bar  color when scroll behavior
             *  [enterAlwaysScrollBehavior] hide top bar when scroll up and show when scroll down
             *  [exitUntilCollapsedScrollBehavior] hide top bar when scroll up and show when scroll down all*/

            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed,)
            val scope = rememberCoroutineScope()
            var selectedItemIndex by rememberSaveable{
                mutableStateOf(0)
            }
            /** In Android compose material 3 navigation Drawer are three type
             * 1. [ModalNavigationDrawer] just like simple drawer
             * 2. [PermanentNavigationDrawer] full screen drawer not able to close
             * 3. [DismissibleNavigationDrawer] full screen drawer */

            ModalNavigationDrawer(
                drawerContent = {
                    ModalDrawerSheet {
                        Spacer(Modifier.height(16.dp))
                        items.forEachIndexed{index,item ->
                            NavigationDrawerItem(
                                label = {
                                    Text(text = item.title)
                                },
                                selected = index == selectedItemIndex,
                                onClick = {
                                    selectedItemIndex = index
                                    scope.launch {
                                        drawerState.close()
                                    }
                                },
                                icon = {
                                    Icon(
                                        imageVector = if (index == selectedItemIndex) {
                                            item.selectedIcon
                                        } else item.unselectedIcon,
                                        contentDescription = item.title
                                    )
                                },
                                badge = {
                                    item.badgeCount?.let {
                                        Text(text = item.badgeCount.toString())
                                    }
                                },
                                modifier = Modifier
                                    .padding(NavigationDrawerItemDefaults.ItemPadding)

                            )
                        }
                    }
                },
                drawerState =drawerState
            ){
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                        .nestedScroll(scrollBehavior.nestedScrollConnection),
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = "My App")
                            },
                            navigationIcon = {
                                /*back press drawal farst item*/
                                IconButton(onClick = {
                                    scope.launch {
                                        drawerState.open()
                                    }

                                }){
                                    Icon(
                                        imageVector = Icons.Default.Menu,
                                        contentDescription = "Go back"
                                    )
                                }

                            },
                            actions = {
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(
                                        imageVector = Icons.Default.FavoriteBorder,
                                        contentDescription = "Mark as favorite"
                                    )
                                }
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(
                                        imageVector = Icons.Default.Edit,
                                        contentDescription = "Edit notes"
                                    )
                                }
                            },
                            scrollBehavior = scrollBehavior
                        )
                    },
                    bottomBar = {
                        NavigationBar {
                            items.forEachIndexed{index, item ->
                                NavigationBarItem(
                                    selected = selectedItemIndex ==index,
                                    onClick = {
                                        selectedItemIndex = index
                                    },
                                    //
                                    label = {
                                        Text(text = item.title)
                                    },
                                    alwaysShowLabel = false,
                                    icon = {
                                        BadgedBox(
                                            badge = {
                                                if(item.badgeCount != null) {
                                                    Badge {
                                                        Text(text = item.badgeCount.toString())
                                                    }
                                                } else if(item.hasNews) {
                                                    Badge()
                                                }
                                            }
                                        ){
                                            Icon(
                                                imageVector = if (index == selectedItemIndex) {
                                                    item.selectedIcon
                                                } else item.unselectedIcon,
                                                contentDescription = item.title
                                            )
                                        }
                                    }
                                )

                            }
                        }
                    }

                ) { values ->

                    //   LoginScreen(values)
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(values)
                    ) {
                        items(100) {
                            Text(
                                text = "Item$it",
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }

                }

            }


        }

    }
}

@Composable
fun HomeScreen() {

/*
    Column(verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(start = 16.dp)) {
        Text("Jai mata di ", style = TextStyle(
            fontSize = 22.sp
        ))
    }
*/
}
