//============================================================================//
//                                                                            //
//                         Copyright © 2015 Sandpolis                         //
//                                                                            //
//  This source file is subject to the terms of the Mozilla Public License    //
//  version 2. You may not use this file except in compliance with the MPL    //
//  as published by the Mozilla Foundation.                                   //
//                                                                            //
//============================================================================//
import MapKit

class ClientAnnotation: NSObject, MKAnnotation {

	var coordinate: CLLocationCoordinate2D
	var title: String?

	let profile: SandpolisProfile

	init?(_ profile: SandpolisProfile) {

		self.profile = profile
		self.title = profile.hostname.value
		if let latitude = profile.ipLocationLatitude.value, let longitude = profile.ipLocationLongitude.value {
			self.coordinate = CLLocationCoordinate2DMake(CLLocationDegrees(latitude), CLLocationDegrees(longitude))
		} else {
			return nil
		}
	}
}
