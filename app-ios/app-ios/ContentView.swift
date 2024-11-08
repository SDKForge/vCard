import SwiftUI
import shared

struct ContentView: View {
	var body: some View {
        VStack(
            alignment: .center,
            spacing: 8
        ) {
            Text("Platform name: \(Platform_iosKt.currentPlatform.name)")
            Text("Platform version: \(Platform_iosKt.currentPlatform.version)")
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
