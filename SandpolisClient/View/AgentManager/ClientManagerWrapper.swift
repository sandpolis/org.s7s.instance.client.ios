//============================================================================//
//                                                                            //
//                         Copyright © 2015 Sandpolis                         //
//                                                                            //
//  This source file is subject to the terms of the Mozilla Public License    //
//  version 2. You may not use this file except in compliance with the MPL    //
//  as published by the Mozilla Foundation.                                   //
//                                                                            //
//============================================================================//
import UIKit

/// A wrapper for direct connections
class ClientManagerWrapper: UINavigationController {

	var name: String!

	override func viewDidLoad() {
		if let dest = self.children.first as? ClientManager {
			dest.loginType = .direct(name)
		}
	}
}
