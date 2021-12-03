//============================================================================//
//                                                                            //
//                         Copyright © 2015 Sandpolis                         //
//                                                                            //
//  This source file is subject to the terms of the Mozilla Public License    //
//  version 2. You may not use this file except in compliance with the MPL    //
//  as published by the Mozilla Foundation.                                   //
//                                                                            //
//============================================================================//

import org.gradle.api.file.DuplicatesStrategy
import org.gradle.internal.os.OperatingSystem
import org.openbakery.xcode.Destination

plugins {
	id("com.sandpolis.build.module")
	id("com.sandpolis.build.instance")
	id("org.openbakery.xcode-plugin") version "0.20.1"
}

xcodebuild {
	scheme = "SandpolisClient"
	target = "SandpolisClient"

	setDestination(Destination("iOS Simulator", "iPhone 12", "14.4"))
}

dependencies {
	proto("com.sandpolis:core.foundation:+:swift@zip")
	proto("com.sandpolis:core.instance:+:swift@zip")
	proto("com.sandpolis:core.net:+:swift@zip")
	proto("com.sandpolis:core.clientserver:+:swift@zip")
	proto("com.sandpolis:plugin.desktop:+:swift@zip")
	proto("com.sandpolis:plugin.shell:+:swift@zip")
}

// Relocate generated sources
tasks.findByName("assembleProto")!!.doLast {
	copy {
		from("src/gen/swift")
		into("SandpolisClient/Gen")
		duplicatesStrategy = DuplicatesStrategy.INCLUDE
	}
}

tasks.findByName("clean")?.doLast {
	delete("SandpolisClient/Gen")
}

tasks.xcodebuild {
	dependsOn("assembleProto")
}

// Disable some tasks if we're not running on macOS
if (!OperatingSystem.current().isMacOsX()) {
	tasks.findByName("keychainClean")?.setEnabled(false)
	tasks.findByName("xcodebuild")?.setEnabled(false)
	tasks.findByName("xcodebuildConfig")?.setEnabled(false)
}
