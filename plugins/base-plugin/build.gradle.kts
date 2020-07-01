val versionTalaiot = com.talaiot.ver.Versions.version

plugins {
    "talaiotPlugin"
  //  `java-gradle-plugin`
  //  `maven-publish`
  //  `kotlin-dsl`
}


//jacoco {
//    toolVersion = "0.8.3"
//}



//publishing {
//    repositories {
//        maven {
//            name = "Snapshots"
//            url = uri("http://oss.jfrog.org/artifactory/oss-snapshot-local")
//
//            credentials {
//                username = System.getenv("USERNAME_SNAPSHOT")
//                password = System.getenv("PASSWORD_SNAPSHOT")
//            }
//        }
//    }
//    publications {
//        create<MavenPublication>("maven") {
//            groupId = "com.cdsap"
//            artifactId = "base"
//            version = versionTalaiot
//            from(components["kotlin"])
//            // from(components["java"])
//            //      artifact(sourcesJar.source)
//
//        }
//    }
//}
//
//val test by tasks.getting(Test::class) {
//    useJUnitPlatform { }
//}
//
//tasks.jacocoTestReport {
//    reports {
//        xml.isEnabled = true
//        csv.isEnabled = true
//        html.isEnabled = true
//        html.destination = file("$buildDir/reports/coverage")
//    }
//}
