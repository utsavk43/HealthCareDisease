package com.healthcare.disease.network.di

import javax.inject.Inject

class NetworkConfigurationImpl @Inject constructor() : NetworkConfiguration {

    override val apiBaseUrl: String
        get() = "https://run.mocky.io/v3/"
}
