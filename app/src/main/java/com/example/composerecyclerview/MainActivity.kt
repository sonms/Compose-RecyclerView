package com.example.composerecyclerview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composerecyclerview.ui.theme.ComposeRecyclerViewTheme
import com.example.composerecyclerview.ui.theme.Typography

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeRecyclerViewTheme {
               MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    //scaffold : 처음 안드로이드 screen 구조를 잡을 때 좋다.
    Scaffold(backgroundColor = MaterialTheme.colors.background) {
        recyclerViewContent()
    }
}

@Composable
fun recyclerViewContent() {
    //remember -> 초기에 실행하여 구성했을 때 저장한 값을 상태가 변했을 때
    //도 데이터 그대로 사용할 수 있게 도와줌
    //여기서는 초기에 저장한 personList를 상태 변환 시에도 사용할 수 있도록 도움
    //사실 지금은 필요없지만, 실제 리스트가 변할 일이 있을 땐 많이 사용함
    val person = remember { PersonData.personList}
    //이게 컴포즈의 recyclerview
    //items -> the data list = 아이템 리스트
    //itemContent ->  the content displayed by a single item = 한 아이템
    LazyColumn(contentPadding = PaddingValues(16.dp, 8.dp)) {
        items(
            items = person,
            itemContent = { personList(person = it)}
        )
    }
}

@Composable
fun personList(person : Person) {//adapter의 fun bind역할?
    Card(
        modifier = Modifier.fillMaxWidth().padding(0.dp, 12.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        border = BorderStroke(1.dp, MaterialTheme.colors.primary)
    ) {
        Row {
            personImage(person = person)
            Column(modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterVertically)
            ) {
                Text(text = person.name, style = Typography.h6)
                Text(text = person.age.toString(), style = Typography.caption)
                Text(text = person.disease, style = Typography.caption)
                Text(text = person.disease, style = Typography.caption)
            }
        }
    }
}

@Composable
fun personImage(person : Person) {
    Image(
        painter = painterResource(id = person.image),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(84.dp)
            .clip(RoundedCornerShape(CornerSize(16.dp)))
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeRecyclerViewTheme {
        MyApp()
    }
}