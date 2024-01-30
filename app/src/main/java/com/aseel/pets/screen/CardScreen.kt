package com.aseel.pets.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.aseel.pets.model.Pet
import com.aseel.pets.viewmodel.PetViewModel
import org.jetbrains.annotations.Async


@Composable
fun petListScreen(viewModel: PetViewModel, modifier: Modifier = Modifier){
    val pets = viewModel.pets

    LazyColumn(modifier=Modifier){

        items(pets){pet ->

            petItem(pet,viewModel)


        }


    }
}
@Composable
fun petItem(pet: Pet, petViewModel: PetViewModel = viewModel()){
    Card(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
        ,
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),

        ) {
        Row (
            Modifier

                .padding(10.dp)
                .height(150.dp),
            verticalAlignment = Alignment.CenterVertically)


        {

            AsyncImage(model = pet.image,
                contentDescription = null,
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
                    .clip(RoundedCornerShape(55.dp))
            )
            Spacer(modifier = Modifier.width(25.dp))
            Column {
                Text(
                    text = "Name: "+pet.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text("Id: " + pet.id.toString())


                Text("Gender: " + pet.gender)
                Text("is Adopted: " + pet.adopted.toString())


            }}}
Button(onClick = { petViewModel.fetchData()
    petViewModel.deletepet(pet.id) }) {
    Text(text = "Delete")
    
}
}