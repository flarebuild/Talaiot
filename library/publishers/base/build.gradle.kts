plugins {
    id("publisherPlugin")
}

publisher{
    name = "base-publisher"
}

dependencies {
    implementation("com.google.code.gson:gson:2.8.5")
}