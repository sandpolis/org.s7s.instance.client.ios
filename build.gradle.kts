//============================================================================//
//                                                                            //
//                         Copyright © 2015 Sandpolis                         //
//                                                                            //
//  This source file is subject to the terms of the Mozilla Public License    //
//  version 2. You may not use this file except in compliance with the MPL    //
//  as published by the Mozilla Foundation.                                   //
//                                                                            //
//============================================================================//

import org.openbakery.xcode.Destination

plugins {
	id("sandpolis-module")
	id("org.openbakery.xcode-plugin") version "0.20.1"
}

val protoDependencies = listOf(
	":module:com.sandpolis.core.foundation",
	":module:com.sandpolis.core.instance",
	":module:com.sandpolis.core.net",
	":module:com.sandpolis.core.clientserver",
	":plugin:com.sandpolis.plugin.desktop",
	":plugin:com.sandpolis.plugin.shell"
)

xcodebuild {
	scheme = "SandpolisClient"
	target = "SandpolisClient"

	setDestination(Destination("iOS Simulator", "iPhone 12", "14.4"))
}

if (project.getParent() == null) {
	repositories {
		protoDependencies.map { it.split(".").takeLast(2) }.forEach {
			maven("https://maven.pkg.github.com/sandpolis/com.sandpolis.${it.first()}.${it.last()}") {
				credentials {
					username = System.getenv("GITHUB_ACTOR")
					password = System.getenv("GITHUB_TOKEN")
				}
			}
		}
	}

	val proto by configurations.creating

	dependencies {
		protoDependencies.map { it.split(".").takeLast(2) }.forEach {
			proto("com.sandpolis:${it.first()}.${it.last()}-swift:+@zip")
		}
	}

	val assembleProto by tasks.creating(Copy::class) {

		into("SandpolisClient/Gen")

		proto.files.forEach { dep ->
			var path = dep.absolutePath.split("-").takeLast(3)
			path = path.first().split("\\.|/".toRegex()).takeLast(2)

			into("com.sandpolis.${path[0]}.${path[1]}") {
				with(copySpec {
					from(zipTree(dep))
				})
			}
		}
	}

} else {

	val assembleProto by tasks.creating(Copy::class) {

		into("SandpolisClient/Gen")

		for (dep in protoDependencies) {
			dependsOn("${dep}:generateProto")

			into(dep.substring(dep.lastIndexOf(":") + 1)) {
				with (copySpec {
					from(project(dep).file("gen/main/swift"))
				})
			}
		}
	}
}

tasks.xcodebuild {
	dependsOn(tasks.findByName("assembleProto"))
}
