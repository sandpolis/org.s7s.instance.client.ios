//============================================================================//
//                                                                            //
//                         Copyright © 2015 Sandpolis                         //
//                                                                            //
//  This source file is subject to the terms of the Mozilla Public License    //
//  version 2. You may not use this file except in compliance with the MPL    //
//  as published by the Mozilla Foundation.                                   //
//                                                                            //
//============================================================================//
import Foundation

struct Oid {

	let namespace: String
	let path: [String]

    init?(_ path: String) {
        let components = path.components(separatedBy: ":")
        switch components.count {
        case 1:
            self.init("com.sandpolis.core.instance", path)
        case 2:
            self.init(components.first!, components.last!)
        default:
            return nil
        }
    }

	init(_ namespace: String, _ path: String) {
		self.namespace = namespace
        self.path = path.components(separatedBy: "/")
	}
    
    func child(_ id: String) -> Oid {
        return Oid(self.namespace, "\(self.path.joined(separator: "/"))/\(id)")
    }
    
    public var description: String {
        return path.joined(separator: "/")
    }
}
