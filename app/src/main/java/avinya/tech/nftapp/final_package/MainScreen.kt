package avinya.tech.nftapp.final_package

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import avinya.tech.nftapp.R

val headingColor = Color(0xFF06033D)

@Composable
fun NFTScreen() {
    val nfts =
        arrayListOf<NFT>(
            NFT(painterResource(id = R.drawable.nft_one), name = "Yellow Warrior", "10.88 ETH"),
            NFT(painterResource(id = R.drawable.nft_two), name = "Ninja Purple", "05.45 ETH"),
            NFT(painterResource(id = R.drawable.nft_three), name = "Silver Protector", "00.56 ETH"),
            NFT(painterResource(id = R.drawable.nft_four), name = "Neon Lighter", "25.12 ETH"),
            NFT(painterResource(id = R.drawable.nft_five), name = "Orange Peeler", "12.00 ETH"),
            NFT(painterResource(id = R.drawable.nft_six), name = "Silver Saviour", "09.80 ETH"),
            NFT(painterResource(id = R.drawable.nft_seven), name = "Maroon Man", "08.23 ETH"),
            NFT(painterResource(id = R.drawable.nft_eight), name = "Purple Person", "06.75 ETH"),
        )

    var selectedTabIndex by remember {
        mutableStateOf(0)
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF5F7FB),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 14.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(14.dp))
            TopCard()
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = "MEET MIYANI NFTS",
                color = headingColor,
                fontFamily = robotoFont,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "Meet Miyani",
                color = headingColor,
                fontFamily = robotoFont,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            NFTStatusSection()
            Spacer(modifier = Modifier.height(28.dp))
            NFTTabView(
                textList = arrayListOf("Items", "Activity", "Favourite", "Collected")
            ) {
                selectedTabIndex = it
            }
            when (selectedTabIndex) {
                0 -> {
                    NFTPosts(nfts = nfts)
                }
            }
        }
    }
}

@Composable
fun TopCard(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(4F / 3F)
    ) {
        Image(
            painter = painterResource(id = R.drawable.header_image),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxHeight(0.825F)
                .clip(RoundedCornerShape(16.dp)),
        )
        Image(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            alignment = Alignment.Center,
            modifier = Modifier
                .size(36.dp)
                .offset(16.dp, 16.dp)
                .align(Alignment.TopStart)
                .background(Color(0xCCFFFFFF), CircleShape),
            contentScale = ContentScale.Inside,
        )
        Image(
            imageVector = Icons.Default.MoreVert,
            contentDescription = null,
            alignment = Alignment.Center,
            modifier = Modifier
                .size(36.dp)
                .offset((-16).dp, (16).dp)
                .align(Alignment.TopEnd)
                .rotate(90F)
                .background(Color(0xCCFFFFFF), CircleShape),
            contentScale = ContentScale.Inside,
        )
        Image(
            painter = painterResource(id = R.drawable.ic_profile_image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight(0.35F)
                .align(Alignment.BottomCenter)
                .aspectRatio(1F, true)
                .border(width = 5.dp, color = Color.White, shape = CircleShape)
                .padding(3.dp)
                .clip(CircleShape)
        )
    }
}

@Composable
fun NFTStatusSection(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NFTStatus(numberText = "19.3K", text = "Items")
        NFTStatus(numberText = "10.5K", text = "Floor Price")
        NFTStatus(numberText = "30", text = "Price")
        NFTStatus(numberText = "396.9K", text = "Total Volume")
    }
}

@Composable
fun NFTStatus(
    modifier: Modifier = Modifier, numberText: String, text: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = numberText,
            fontFamily = robotoFont,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = headingColor
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = text,
            fontFamily = robotoFont,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = Color(0XFFBDBFCC)
        )
    }
}

@Composable
fun NFTTabView(
    modifier: Modifier = Modifier,
    textList: List<String>,
    onTabSelected: (selectedTabIndex: Int) -> Unit
) {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }
    val inactiveColor = Color(0xFF9292A4)
    val activeColor = headingColor

    TabRow(
        selectedTabIndex = selectedTabIndex,
        backgroundColor = Color.Transparent,
        contentColor = activeColor,
        modifier = modifier
    ) {
        textList.forEachIndexed { index, tabString ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = {
                    selectedTabIndex = index
                    onTabSelected(selectedTabIndex)
                },
                selectedContentColor = activeColor,
                unselectedContentColor = Color.Transparent,
            ) {
                Text(
                    text = tabString,
                    color = if (selectedTabIndex == index) activeColor else inactiveColor,
                    modifier = Modifier.padding(10.dp),
                    fontFamily = robotoFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NFTPosts(
    modifier: Modifier = Modifier, nfts: List<NFT>
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        modifier = modifier.fillMaxWidth()
    ) {
        items(nfts.size) {
            val nft = nfts[it]
            NFTHolder(painter = nft.image, name = nft.name, price = nft.price)
        }
    }
}

@Composable
fun NFTHolder(
    modifier: Modifier = Modifier, painter: Painter, name: String, price: String
) {
    Box(
        modifier = modifier
            .padding(10.dp)
            .background(Color.White, RoundedCornerShape(8.dp))
            .padding(6.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1F)
                    .clip(RoundedCornerShape(6.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = name,
                fontFamily = robotoFont,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = headingColor,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Price Bid",
                    fontFamily = robotoFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = Color(0xFF9292A4),
                    textAlign = TextAlign.Start,
                )
                Text(
                    text = price,
                    fontFamily = robotoFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color(0xFF4576BE),
                    textAlign = TextAlign.End,
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        headingColor,
                        RoundedCornerShape(28.dp)
                    )
                    .height(28.dp)
                    .clickable {

                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Buy",
                    fontFamily = robotoFont,
                    fontSize = 14.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

val robotoFont = FontFamily(
    Font(R.font.roboto_thin, weight = FontWeight.Thin),
    Font(R.font.roboto_light, weight = FontWeight.Light),
    Font(R.font.roboto_regular, weight = FontWeight.Normal),
    Font(R.font.roboto_medium, weight = FontWeight.Medium),
    Font(R.font.roboto_bold, weight = FontWeight.Bold),
    Font(R.font.roboto_black, weight = FontWeight.Black),
)
