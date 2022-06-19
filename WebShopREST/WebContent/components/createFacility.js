Vue.component("create-facility", {
	data: function () {
		    return {
		      title: "Kreiraj objekat",
		      value: "Kreiraj",
		      product: {id: '', name:null, type:{id:0 , name:null }, recreationTypes:[{}], status: null, workRange: null, location:{id: 0, latitude: null,  longitude: null, adress: {id: 0, street: null, number: null, place: null, shipingCode: null}}},
		      facilityTypes: null,
		      recreationTypes: null,
		      selectedRecreation: null
		    }
	},
	template: ` 
	<div>
		{{title}}
		<form>
		<table>
			<tr><td>Ime</td><td><input type = "text" v-model = "product.name" name = "name"></td></tr>
			<tr>
				<td>Tip</td>
				<td>
					<select v-model = "product.type">
						<option v-for="(f, index) in facilityTypes" :value = "f.id">{{f.name}}</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>Rekreacija</td>
				<td>
					<select v-model = "product.recreationTypes[0]">
						<option v-for="(f, index) in recreationTypes" :value = "f.id">{{f.name}}</option>
					</select>
				</td>
			</tr>
			<tr><td>Status</td><td><input type = "checkbox" v-model = "product.status" name = "status"></td></tr>
			<tr><td>Radno vreme</td><td><input type = "text" v-model = "product.workRange" name = "workRange"></td></tr>
			<tr><td>Latitude</td><td><input type = "number" v-model = "product.location.latitude" name = "latitude"></td></tr>
			<tr><td>Longitude</td><td><input type = "number" v-model = "product.location.longitude" name = "longitude"></td></tr>
			<tr><td>Ulica</td><td><input type = "text" v-model = "product.location.adress.street" name = "street"></td></tr>
			<tr><td>Broj</td><td><input type = "number" v-model = "product.location.adress.number" name = "number"></td></tr>
			<tr><td>Mesto</td><td><input type = "text" v-model = "product.location.adress.place" name = "number"></td></tr>
			<tr><td>Sifra za postu</td><td><input type = "number" v-model = "product.location.adress.shipingCode" name = "number"></td></tr>
			<tr><td><input type = "submit" v-on:click = "createFacility" v-bind:value = "this.value"></td></tr>
		</table>
		</form>
	</div>		  
	`
	, 
	methods : {
		createFacility : function () {
			event.preventDefault();
			axios.post('rest/facility', this.product).
			then(response => (router.push(`/products/`)));
		}
	},
	mounted () {
		try {
			axios.get('rest/facilitytypes/')
			.then(response => (this.facilityTypes = response.data))
			axios.get('rest/recreationtypes/')
			.then(response => (this.recreationTypes = response.data))
		}
		catch(error)
		{
			console.log(error);
		}
    }
});