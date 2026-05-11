//
//  UserFormScreen.swift
//  iosApp
//
//  Created by Theo Sementa on 11/05/2026.
//

import SwiftUI

struct UserFormScreen: View {

    @Environment(\.dismiss) private var dismiss
    @State private var viewModel = ViewModel()

    var body: some View {
        NavigationStack {
            Form {
                Section("Informations") {
                    TextField("Prénom", text: $viewModel.firstName)
                    TextField("Nom", text: $viewModel.lastName)
                    Stepper("Âge : \(viewModel.age)", value: $viewModel.age, in: 0...120)
                }
            }
            .navigationTitle("Nouvel utilisateur")
            .navigationBarTitleDisplayMode(.inline)
            .toolbar {
                ToolbarItem(placement: .cancellationAction) {
                    Button("Annuler") { dismiss() }
                }
                ToolbarItem(placement: .confirmationAction) {
                    Button("Enregistrer") {
                        Task {
                            await viewModel.create()
                            dismiss()
                        }
                    }
                    .disabled(viewModel.firstName.isEmpty || viewModel.lastName.isEmpty)
                }
            }
        }
    }
}

#Preview {
    UserFormScreen()
}
