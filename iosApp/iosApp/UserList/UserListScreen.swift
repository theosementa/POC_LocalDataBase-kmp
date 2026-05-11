//
//  UserListScreen.swift
//  iosApp
//
//  Created by Theo Sementa on 11/05/2026.
//

import SwiftUI
import Shared

struct UserListScreen: View {

    @State private var viewModel = ViewModel()

    var body: some View {
        NavigationStack {
            List {
                ForEach(viewModel.users, id: \.id) { user in
                    VStack(alignment: .leading, spacing: 4) {
                        Text("\(user.firstName) \(user.lastName)")
                            .font(.headline)
                        Text("Âge : \(user.age)")
                            .font(.subheadline)
                            .foregroundStyle(.secondary)
                    }
                    .padding(.vertical, 4)
                }
                .onDelete { indexSet in
                    for index in indexSet {
                        let userId = viewModel.users[index].id
                        Task { await viewModel.deleteUser(id: userId) }
                    }
                }
            }
            .navigationTitle("Utilisateurs")
            .toolbar {
                ToolbarItem(placement: .primaryAction) {
                    Button {
                        viewModel.isShowingForm = true
                    } label: {
                        Image(systemName: "plus")
                    }
                }
            }
            .sheet(isPresented: $viewModel.isShowingForm) {
                Task { await viewModel.fetchUsers() }
            } content: {
                UserFormScreen()
            }
            .task {
                await viewModel.fetchUsers()
            }
            .overlay {
                if viewModel.users.isEmpty {
                    ContentUnavailableView(
                        "Aucun utilisateur",
                        systemImage: "person.slash",
                        description: Text("Appuie sur + pour en ajouter un.")
                    )
                }
            }
        }
    }
}

#Preview {
    UserListScreen()
}
