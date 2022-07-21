package com.happycoder.mymarket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.happycoder.mymarket.models.Product
import com.happycoder.mymarket.util.ApiHelper
import kotlinx.coroutines.launch

class ProductListViewModel : ViewModel() {

    private val list = MutableLiveData<ArrayList<Product>>()

    val productList: LiveData<ArrayList<Product>> get() = list

    init {
        viewModelScope.launch {
            list.value = ApiHelper.getListOfProducts()
        }
    }
}