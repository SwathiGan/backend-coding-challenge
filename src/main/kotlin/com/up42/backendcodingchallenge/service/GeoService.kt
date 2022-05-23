package com.up42.backendcodingchallenge.service

import Feature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.up42.backendcodingchallenge.dto.FeatureCollection
import org.springframework.core.io.ClassPathResource
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class GeoService {

    fun readFeatures():List<Feature>{
       return  ClassPathResource("/static/source-data.json").file.readText()
            .let { jsonString ->
                jacksonObjectMapper().readValue<List<FeatureCollection>>(jsonString)
            }.flatMap {
                it.features
            }.ifEmpty {
                throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No features found")
            }
    }

    fun getImageByFeatureId(featureId: String):ByteArray{
        val base64Image = ClassPathResource("/static/source-data.json").file.readText()
            .let { jsonString ->
                jacksonObjectMapper().readValue<List<FeatureCollection>>(jsonString)
            }.flatMap {
                it.features
            }.ifEmpty {
                throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No image found")
            }.filter {
                it.properties?.id.equals(featureId)
            }.map {
                it.properties?.quicklook
            }.first().orEmpty()

        var imageByteArray: ByteArray = Base64.getDecoder().decode(base64Image)
        return imageByteArray
    }
}