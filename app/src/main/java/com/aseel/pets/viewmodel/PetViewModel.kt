package com.aseel.pets.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aseel.pets.interfaceApi.PetApiService
import com.aseel.pets.model.Pet
import com.aseel.pets.repo.PetRepo
import com.aseel.pets.singleton.RetroFitHelper
import kotlinx.coroutines.launch

class PetViewModel : ViewModel() {

    private val petApiService = RetroFitHelper.getInstance().create(PetApiService::class.java)
    private val repository = PetRepo(petApiService)

    var pets by mutableStateOf(listOf<Pet>())
    var errorMsg: String? by mutableStateOf(null)

    interface PetOperationCallback{
        fun onSuccess()
        fun onError(errorMessage: String)
    }
    var petOperationCallback: PetOperationCallback? = null
    //connector
    init {
        fetchData()
    }
    fun fetchData() {
        viewModelScope.launch {
            try {
                pets = repository.getAllpets()

            } catch (e: Exception) {
                print("error"+ e )
                errorMsg = e.toString()

            }
        }
    }

    fun addPet(pet: Pet){
        viewModelScope.launch {
            try{

                var response = petApiService.addPet(pet)

                if(response.isSuccessful && response.body() != null){
                    petOperationCallback?.onSuccess()
                    //print("Successful")
                }else{
                    petOperationCallback?.onError("Error adding pet")

                    //print("Error")
                }


            } catch(e:Exception){
                petOperationCallback?.onError("There is a Network Issue: ${e.message}")
                //print("There is a Network Issue"+ e )

            }        }
    }

    fun deletepet(petID:Int){
        viewModelScope.launch {
            try {
                var response = petApiService.deletePet(petID)

                if(response.isSuccessful){
                    // pet has been deleted
                } else{
                    //
                }
            } catch (e:Exception){

//
            }
        }
    }



}