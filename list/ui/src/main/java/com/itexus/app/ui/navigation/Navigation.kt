package com.itexus.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.itexus.app.ui.details.DetailsScreen
import com.itexus.app.ui.list.ListItemsScreen
import com.itexus.app.ui.navigation.Routes.ITEM_DETAIL_SCREEN
import com.itexus.app.ui.navigation.Routes.LIST_ITEMS_SCREEN
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = LIST_ITEMS_SCREEN,
    ) {

        composable(
            route = LIST_ITEMS_SCREEN
        ) {
            ListItemsScreen(
                viewModel = getViewModel()
            ) { itemId ->
                navController.navigate("$ITEM_DETAIL_SCREEN$itemId")
            }
        }

        composable(
            route = "$ITEM_DETAIL_SCREEN{itemId}",
            arguments = listOf(navArgument("itemId") { type = NavType.IntType })
        ) { backEntry ->
            DetailsScreen(
                viewModel = getViewModel {
                    parametersOf(backEntry.arguments?.getInt("itemId"))
                }
            )
        }
    }
}
