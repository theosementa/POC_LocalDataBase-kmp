//
//  UserFormScreen+ViewModel.swift
//  iosApp
//
//  Created by Theo Sementa on 11/05/2026.
//

import Foundation
import Shared

extension UserFormScreen {
    
    @Observable @MainActor
    final class ViewModel {
        
        var firstName: String = ""
        var lastName: String = ""
        var age: Int = 0
        
        func create() async {
            let repository = UserRepositoryHelper().getUserRepository()
            do {
                try await repository.insertUser(firstName: firstName, lastName: lastName, age: Int32(age))
            } catch {
                
            }
        }
        
    }
    
}
