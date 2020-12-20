//============================================================================//
//                                                                            //
//                Copyright © 2015 - 2020 Subterranean Security               //
//                                                                            //
//  This source file is subject to the terms of the Mozilla Public License    //
//  version 2. You may not use this file except in compliance with the MPL    //
//  as published by the Mozilla Foundation at:                                //
//                                                                            //
//    https://mozilla.org/MPL/2.0                                             //
//                                                                            //
//=========================================================S A N D P O L I S==//

plugins {
	id("org.openbakery.xcode-plugin") version "0.20.1"
}

xcodebuild {
	scheme = "SandpolisClient"
	target = "SandpolisClient"

	//destination {
	//	platform = "iOS Simulator"
	//	name = "iPhone XR"
	//	os = "14.0"
	//}
}

tasks.xcodebuild {

	// Add protobuf task dependencies
	dependsOn(":module:com.sandpolis.core.foundation:generateProto")
	dependsOn(":module:com.sandpolis.core.instance:generateProto")
	dependsOn(":module:com.sandpolis.core.net:generateProto")
	dependsOn(":module:com.sandpolis.core.clientserver:generateProto")
	dependsOn(":module:com.sandpolis.plugin.desktop:generateProto")
}
