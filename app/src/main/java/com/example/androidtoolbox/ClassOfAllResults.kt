package com.example.androidtoolbox


class AllResults (
    var results: Array<User>
)


class User  (
    var gender: String? ,
    var name: Name?,
    var location: Location?,
    var email: String?,
    var picture: Picture?
)

class Name (
    var title: String?,
    var first: String?,
    var last: String?
)

class Location (
    var street: Street?,
    var city: String?,
    var state: String?,
    var contry: String?,
    var postcode: String?,
    var coordinates: Coordinates?

)

class Street (
    var number: String?,
    var name: String?
)

class Coordinates (
    var latitude: String?,
    var longitude: String?
)

class Picture (
    var large: String?
)

