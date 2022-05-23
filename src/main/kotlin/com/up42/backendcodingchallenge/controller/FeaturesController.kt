package com.up42.backendcodingchallenge.controller
import Feature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.up42.backendcodingchallenge.dto.FeatureCollection
import com.up42.backendcodingchallenge.service.GeoService
import org.springframework.core.io.ClassPathResource
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.util.*

@RestController
class FeaturesController(val geoService: GeoService) {
    @GetMapping("/features")
    fun getFeatures(): List<Feature> =geoService.readFeatures()

    @GetMapping("/features/{featureId}/quicklook", produces = [MediaType.IMAGE_PNG_VALUE])
    fun getImageData(@PathVariable featureId:String):ResponseEntity<ByteArray>{
        val imageByFeatureId = this.geoService.getImageByFeatureId(featureId)
        return ResponseEntity.ok(imageByFeatureId)
    }
}



