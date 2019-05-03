package com.passionvirus.sample.tddmvvm.viewmodel

import androidx.lifecycle.ViewModel
import javax.sql.DataSource


class MainViewModel(dataSource: DataRepository) : ViewModel() {

    fun UserViewModel(dataSource: DataSource) {
    }

    fun getContributors() {

    }

    override fun onCleared() {
        super.onCleared()
    }
}