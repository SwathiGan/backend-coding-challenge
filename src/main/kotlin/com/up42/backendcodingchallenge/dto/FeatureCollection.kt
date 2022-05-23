package com.up42.backendcodingchallenge.dto

import Feature
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class FeatureCollection(var features: List<Feature>){

}