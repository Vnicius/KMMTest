import SwiftUI
import shared

struct ContentView: View {
	let greeting = Greeting()
    
    @State var greet = "Loading..."
    
    func load() {
        greeting.getPokemons { result, error in
            if let result = result {
                var resultString = ""
                
                result.forEach { item in
                    resultString += "\(item.name)\n"
                }
                
                self.greet = resultString
            } else if let error = error {
                greet = "Error: \(error)"
            }
        }
    }
 
	var body: some View {
        Text(greet).onAppear {
            load()
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
