/**
 * Tämä JS-luokka on käytännössä tarpeeton nykyisessä toiminnallisuudessa. Se on jätetty projektiin Ajax-kehittämistä varten.
 */
async function removeProduct(id) {
	let response = await fetch(`/list?id=${id}`, { method: "DELETE" });

	if (response.status === 200) {
		console.log(`product-${id}`);
		let element = document.getElementById(`product-${id}`);
		element.remove();
	} else {
		alert("Something went wrong");
	}
}