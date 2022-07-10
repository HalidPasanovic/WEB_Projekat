Vue.component("create-facility", {
	data: function () {
		    return {
		      title: "Create Sport Facility",
		      value: "Create",
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
		<div style="display: flex; justify-content: center;">
                    <h3>{{title}}</h3><br>
        </div>
		<table style="position: absolute; top:90%; left: 55%; transform: translate(-50%, -50%);">
			<tr><td style="padding:16px">Ime</td><td style="padding:16px"><input class="form-control" type = "text" v-model = "product.name" name = "name"></td></tr>
			<tr>
				<td style="padding:16px">Tip</td>
				<td style="padding:16px">
					<select class="form-control" v-model = "product.type">
						<option v-for="(f, index) in facilityTypes" :value = "f.id">{{f.name}}</option>
					</select>
				</td>
			</tr>
			
			<tr><td style="padding:16px">Status</td><td style="padding:16px"><input type = "checkbox" v-model = "product.status" name = "status"></td></tr>
			<tr><td style="padding:16px">Radno vreme</td><td style="padding:16px"><input class="form-control" type = "text" v-model = "product.workRange" name = "workRange"></td></tr>
			<tr><td style="padding:16px">Logo</td><td style="padding:16px"><input class="form-control" type="file" id="avatar" name="avatar" accept="image/png, image/jpeg"></td></tr>
			<tr><td style="padding:16px">Latitude</td><td style="padding:16px"><input class="form-control" type = "number" v-model = "product.location.latitude" name = "latitude"></td></tr>
			<tr><td style="padding:16px">Longitude</td><td style="padding:16px"><input class="form-control" type = "number" v-model = "product.location.longitude" name = "longitude"></td></tr>
			<tr><td style="padding:16px">Ulica</td><td style="padding:16px"><input class="form-control" type = "text" v-model = "product.location.adress.street" name = "street"></td></tr>
			<tr><td style="padding:16px">Broj</td><td style="padding:16px"><input class="form-control" type = "number" v-model = "product.location.adress.number" name = "number"></td></tr>
			<tr><td style="padding:16px">Mesto</td><td style="padding:16px"><input class="form-control" type = "text" v-model = "product.location.adress.place" name = "number"></td></tr>
			<tr><td style="padding:16px">Sifra za postu</td><td style="padding:16px"><input class="form-control" type = "number" v-model = "product.location.adress.shipingCode" name = "number"></td></tr>
			<template v-if="managersBool">
			<tr>
				<td style="padding:16px">Managers</td>
				<td style="padding:16px">
					<select class="form-control" v-model = "selectedManager">
						<option v-for="(f, index) in managers" :value = "f.id">{{f.name}} {{f.surname}}</option>
					</select>
				</td>
			</tr>
			</template>
			<template v-else>
			<tr><td style="padding:16px">Make new Manager:</td></tr>
			<tr><td style="padding:16px">Username</td><td style="padding:16px"><input class="form-control" type="text" name="username" v-model="manager.username"></td></tr>
			<tr><td style="padding:16px">Password</td><td style="padding:16px"><input class="form-control" type="password" name="password" v-model="manager.password"></td></tr>
			<tr><td style="padding:16px">Name</td><td style="padding:16px"><input class="form-control" type="text" name="name" v-model="manager.name"></td></tr>
			<tr><td style="padding:16px">Surname</td><td style="padding:16px"><input class="form-control" type="text" name="date" v-model="manager.surname"></td></tr>
			<tr><td style="padding:16px">Birthday Date:</td><td style="padding:16px"><input class="form-control" type="date" v-model="manager.dateOfBirth" name="birthday"></td></tr>
			<tr><td style="padding:16px">Gender</td><td style="padding:16px">
			<select class="form-control" v-model="manager.gender">
  				<option disabled value="">Please select one</option>
  				<option>Male</option>
  				<option>Female</option>
  				<option>Other</option>
			</select></td></tr>
			</template>
			<tr><td colspan="2" style="padding:16px"><button class="w-100 btn btn-lg btn-dark" v-on:click = "createFacility">Create Facility</button></td></tr>
		</table>
	</div>		  
	`
	, 
	methods : {
		createFacility : function () {
			
			var input = document.getElementById("avatar");
			var file = input.value.split("\\");
			var fileName = file[file.length-1];
			this.product.logoLocation = fileName;
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