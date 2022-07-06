Vue.component("create-facility", {
	data: function () {
		    return {
		      title: "Kreiraj objekat",
		      value: "Kreiraj",
		      product: {id: '', name:null, type:{id:0 , name:null },logoLocation:null, recreationTypes:[], status: null, workRange: null, location:{latitude: null,  longitude: null, adress: {street: null, number: null, place: null, shipingCode: null}}},
		      facilityTypes: null,
		      recreationTypes: null,
		      selectedRecreation: null,
		      managers : null,
		      selectedManager : null,
		      managersBool : null,
		      manager : {id: '', name: null, surname:null, username:null,password:null, gender:null,dateOfBirth:null, role:'Manager', facilities: []}
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
			<tr><td>Status</td><td><input type = "checkbox" v-model = "product.status" name = "status"></td></tr>
			<tr><td>Radno vreme</td><td><input type = "text" v-model = "product.workRange" name = "workRange"></td></tr>
			<tr><td>Logo location</td><td><input type = "text" v-model = "product.logoLocation" name = "logoLocation"></td></tr>
			<tr><td>Latitude</td><td><input type = "number" v-model = "product.location.latitude" name = "latitude"></td></tr>
			<tr><td>Longitude</td><td><input type = "number" v-model = "product.location.longitude" name = "longitude"></td></tr>
			<tr><td>Ulica</td><td><input type = "text" v-model = "product.location.adress.street" name = "street"></td></tr>
			<tr><td>Broj</td><td><input type = "number" v-model = "product.location.adress.number" name = "number"></td></tr>
			<tr><td>Mesto</td><td><input type = "text" v-model = "product.location.adress.place" name = "number"></td></tr>
			<tr><td>Sifra za postu</td><td><input type = "number" v-model = "product.location.adress.shipingCode" name = "number"></td></tr>
			<template v-if="managersBool">
			<tr>
				<td>Managers</td>
				<td>
					<select v-model = "selectedManager">
						<option v-for="(f, index) in managers" :value = "f.id">{{f.name}} {{f.surname}}</option>
					</select>
				</td>
			</tr>
			</template>
			<template v-else>
			<tr><td>Make new Manager:</td></tr>
			<tr><td>Username</td><td><input type="text" name="username" v-model="manager.username"></td></tr>
			<tr><td>Password</td><td><input type="password" name="password" v-model="manager.password"></td></tr>
			<tr><td>Name</td><td><input type="text" name="name" v-model="manager.name"></td></tr>
			<tr><td>Surname</td><td><input type="text" name="date" v-model="manager.surname"></td></tr>
			<tr><td>Birthday Date:</td><td><input type="date" v-model="manager.dateOfBirth" name="birthday"></td></tr>
			<tr><td>Gender</td><td>
			<select v-model="manager.gender">
  				<option disabled value="">Please select one</option>
  				<option>Male</option>
  				<option>Female</option>
  				<option>Other</option>
			</select></td></tr>
			</template>
			<tr><td><input type = "submit" v-on:click = "createFacility" v-bind:value = "this.value"></td></tr>
		</table>
		</form>
	</div>		  
	`
	, 
	methods : {
		createFacility : function () {
			event.preventDefault();
			axios.post('rest/facility/createandreturn', this.product).
			then(response => {
				this.recreationTypes = response.data;
				if(!this.managersBool)
				{
					axios.post('rest/managers/createandreturn/',this.manager)
					.then(response => {
					this.selectedManager = response.data;
					axios.post('rest/managers/updatefacility/' + this.selectedManager + '&' + this.recreationTypes)
					.then(alert("Updated successfully!"))
					.catch((e) => { alert("Exception")})
					})
					.catch((e) => { alert("Exception")})
				}
				else
				{
					axios.post('rest/managers/updatefacility/' + this.selectedManager + '&' + this.recreationTypes)
					.then(alert("Updated successfully!"))
					.catch((e) => { alert("Exception")})
				}			
			});
		}
	},
	mounted () {
		try {
			axios.get('rest/facilitytypes/')
			.then(response => (this.facilityTypes = response.data))
			
			axios.get('rest/managers/empty')
			.then(response => {
				this.managers = response.data
				if(this.managers.length == 0)
				{
					this.managersBool = false;
				}
				else
				{
					this.managersBool = true;
				}
			})
		}
		catch(error)
		{
			console.log(error);
		}
    }
});