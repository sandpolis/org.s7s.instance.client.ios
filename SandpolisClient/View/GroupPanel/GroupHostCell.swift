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

class GroupHostCell: UITableViewCell {

	@IBOutlet weak var platform: UIImageView!
	@IBOutlet weak var hostname: UILabel!

	func setContent(_ profile: SandpolisProfile) {
		hostname.text = profile.hostname.value
		platform.image = profile.platformIcon
	}
}
