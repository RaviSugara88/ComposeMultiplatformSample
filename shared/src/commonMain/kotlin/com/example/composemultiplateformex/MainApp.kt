package com.example.composemultiplateformex

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.example.composemultiplateformex.ui.LoginScree
import com.example.composemultiplateformex.ui.data.HomePage
import com.example.composemultiplateformex.ui.screen.EmailList
import com.example.composemultiplateformex.ui.screen.EmailListUI
import com.example.composemultiplateformex.ui.screen.ErrorScreen
import com.example.composemultiplateformex.ui.screen.LoadingScreen
import com.example.composemultiplateformex.ui.viewmodel.ProductsViewModel
import com.example.composemultiplateformex.utils.CustomMessage
import com.example.composemultiplateformex.utils.emailData
import com.example.composemultiplateformex.utils.items
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import kotlinx.coroutines.flow.update
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

    MyAppTheme {
        val productsViewModel =
            getViewModel(Unit, viewModelFactory { ProductsViewModel() })
        var selectedItemIndex by rememberSaveable {
            mutableStateOf(0)
        }
        var appTitle by rememberSaveable{
            mutableStateOf("Product List")
        }
        Surface (
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ){
            val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
            val emailListData = emailData()
            /** [pinnedScrollBehavior] Change top bar  color when scroll behavior
             *  [enterAlwaysScrollBehavior] hide top bar when scroll up and show when scroll down
             *  [exitUntilCollapsedScrollBehavior] hide top bar when scroll up and show when scroll down all */

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
                                Text(text = appTitle)
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
                                IconButton(onClick = {

                                }) {
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
                    Column(
                        Modifier.fillMaxSize().padding(values)
                    ) {
                        when(selectedItemIndex){
                            0->{
                                appTitle = "Product List"
                                HomePage(productsViewModel)
                            }
                            1->{
                                appTitle = "Product Email"
                                LazyColumn(
                                    modifier = Modifier
                                        .fillMaxSize()
                                ) {
                                    items(emailListData.size) {po ->
                                        EmailListUI(
                                            EmailList(
                                                "${emailListData[po].emailTitle}",
                                                "${emailListData[po].subject}",
                                                "${emailListData[po].des}"
                                            )
                                        )
                                    }
                                }

                            }
                           /* 2->{
                                appTitle = "Support"
                                LoadingScreen()
                            }*/
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
