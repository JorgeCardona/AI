// api url
const api_url =
	"http://localhost:5555";

// Defining async function
async function getapi(url) {
	
	// Storing response
	const response = await fetch(url);
	
	// Storing data in form of JSON
	var data = await response.json();
	console.log(data);
	if (response) {
		hideloader();
	}
	const myArray = JSON.stringify(data, null, 6);

	document.getElementById("ApiResponse").innerHTML = myArray;
	
	//showResponse(data);
}
// Calling that async function
getapi(api_url);

// Function to hide the loader
function hideloader() {
	document.getElementById('loading').style.display = 'none';
}

function showResponse(data){
	// convierte un objecto tipo json a string
	const myArray = JSON.stringify(data, null, 6);

	document.getElementById("ApiResponse").innerHTML = myArray;
}

