package part5.jjs.shoppingmall.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import part5.jjs.shoppingmall.viewmodel.MainViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainCategoryScreen(viewModel: MainViewModel, navController: NavHostController) {
    val categories by viewModel.categories.collectAsState(initial = listOf())

    LazyVerticalGrid(columns = GridCells.Fixed(3)) {
        items(categories.size, span = { GridItemSpan(1) }) {
            Card(
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(10.dp)
                    .shadow(10.dp),
                onClick = { viewModel.openCategory(navController, categories[it])}
            ) {
                Text(text = categories[it].categoryName,
                    modifier = Modifier.fillMaxSize()
                        .padding(10.dp),
                    textAlign = TextAlign.Center)
            }
        }
    }
}