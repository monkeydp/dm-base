rootProject.name = "dm-base"
includeBuild("../../") {
    dependencySubstitution {
        substitute(module("com.monkeydp.daios.dms:dms-sdk")).with(project(":dms-sdk"))
    }
}
includeBuild("../../tools") {
    dependencySubstitution {
        substitute(module("com.monkeydp:tools")).with(project(":"))
    }
}