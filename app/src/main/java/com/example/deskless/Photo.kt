package com.example.deskless
//
//import android.util.Log
//import com.google.android.gms.common.api.ApiException
//import com.google.android.libraries.places.api.model.Place
//import com.google.android.libraries.places.api.net.FetchPhotoRequest
//import com.google.android.libraries.places.api.net.FetchPhotoResponse
//import com.google.android.libraries.places.api.net.FetchPlaceRequest
//
//class Photo {
//
//    // Define a Place ID.
//    val placeId = "INSERT_PLACE_ID_HERE"
//
//    // Specify fields. Requests for photos must always have the PHOTO_METADATAS field.
//    val fields = listOf(Place.Field.PHOTO_METADATAS)
//
//    // Get a Place object (this example uses fetchPlace(), but you can also use findCurrentPlace())
//    val placeRequest = FetchPlaceRequest.newInstance(placeId, fields)
//
//    placesClient.fetchPlace(placeRequest).addOnSuccessListener { response: FetchPlaceResponse ->
//        val place = response.place
//
//        // Get the photo metadata.
//        val metada = place.photoMetadatas
//        if (metada == null || metada.isEmpty()) {
//            Log.w(TAG, "No photo metadata.")
//            return @addOnSuccessListener
//        }
//        val photoMetadata = metada.first()
//
//        // Get the attribution text.
//        val attributions = photoMetadata?.attributions
//
//        // Create a FetchPhotoRequest.
//        val photoRequest = FetchPhotoRequest.builder(photoMetadata)
//            .setMaxWidth(500) // Optional.
//            .setMaxHeight(300) // Optional.
//            .build()
//        placesClient.fetchPhoto(photoRequest)
//            .addOnSuccessListener { fetchPhotoResponse: FetchPhotoResponse ->
//                val bitmap = fetchPhotoResponse.bitmap
//                imageView.setImageBitmap(bitmap)
//            }.addOnFailureListener { exception: Exception ->
//                if (exception is ApiException) {
//                    Log.e(TAG, "Place not found: " + exception.message)
//                    val statusCode = exception.statusCode
//                    TODO("Handle error with given status code.")
//                }
//            }
//    }
//
//
//}