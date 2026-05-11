import Foundation
import Shared

extension UserListScreen {

    @Observable @MainActor
    final class ViewModel {

        var users: [UserDomain] = []
        var isShowingForm: Bool = false

        private let repository = UserRepositoryHelper().getUserRepository()

        func fetchUsers() async {
            do {
                users = try await repository.fetchAllUsers()
            } catch {}
        }

        func deleteUser(id: Int32) async {
            do {
                try await repository.deleteUser(userId: id)
                await fetchUsers()
            } catch {}
        }
    }
}
