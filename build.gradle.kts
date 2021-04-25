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

xcodebuild {
	scheme = "SandpolisClient"
	target = "SandpolisClient"

	setDestination(Destination("iOS Simulator", "iPhone 11", "14.2"))
}

tasks.xcodebuild {

	// Add protobuf task dependencies
	dependsOn(":module:com.sandpolis.core.foundation:generateProto")
	dependsOn(":module:com.sandpolis.core.instance:generateProto")
	dependsOn(":module:com.sandpolis.core.net:generateProto")
	dependsOn(":module:com.sandpolis.core.clientserver:generateProto")
	dependsOn(":plugin:com.sandpolis.plugin.desktop:generateProto")
}
