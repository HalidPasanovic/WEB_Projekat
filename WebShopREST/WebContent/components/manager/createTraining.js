Vue.component("create-training", { 
	data: function () {
	    return {
	      name : null,
	      duration : null,
	      cost : null,
	      description : null,
	      manager : null,
	      facilities : null,
	      trainers : null,
	      selectedTrainer : {id:null},
	      types : null,
	      selectedType : {id: null, deleted: false, name: null},
	      checked : false
	    }
	},
	    template: `
	    <div>
	    <input type="checkbox" id="checkbox" v-model="checked">
	    <label for="checkbox">Create Training</label>
		<table>
			<tr><td>Name</td><td><input type = "text" v-model = "name" name = "name"></td></tr>
			<template v-if="checked">
			<template v-if="types">
			<tr>
				<td>Training Types</td>
				<td>
					<select v-model = "selectedType">
						<option v-for="(f, index) in types" :value = "f">{{f.name}}</option>
					</select>
				</td>
			</tr>
			</template>
			<tr><td>Duration</td><td><input type = "text" v-model = "duration" name = "duration"></td></tr>
			<tr><td>Description</td><td><input type = "text" v-model = "description" name = "description"></td></tr>
			<tr><td>Additional Cost</td><td><input type = "number" v-model = "cost" name = "cost"></td></tr>
			<template v-if="trainers">
			<tr>
				<td>Trainers</td>
				<td>
					<select v-model = "selectedTrainer">
						<option v-for="(f, index) in trainers" :value = "f">{{f.name}} {{f.surname}}</option>
					</select>
				</td>
			</tr>
			</template>
			</template>
			<tr><td><input type = "submit" v-on:click = "createFacility"></td></tr>
		</table>
	</div>
    	`,
    mounted () {
		axios.get('rest/login/loginstat')
          .then(response => 
            {
                axios.get('rest/managers/' + response.data.id)
                .then(response => {this.manager = response.data;
                	
                	axios.get('rest/facility/' + response.data.facilities[0].id)
                	.then(response => {this.facilities = response.data})
                	})
                })
         axios.get('rest/trainers/')
         .then(response => (this.trainers = response.data))
         
         axios.get('rest/trainingType/')
         .then(response => (this.types = response.data))
    },
    methods: {
		createFacility : function() {
			if(this.checked)
			{
				axios.post('rest/training/', {"name":''+this.name,"duration":''+this.duration, "aditionalCost":''+this.cost,"description":''+this.description, "type":{"id":''+this.selectedType.id, "deleted":''+this.selectedType.deleted,"name":''+this.selectedType.name}, "trainer":{"id":''+this.selectedTrainer.id}, "facility":{"id":'' + this.facilities.id}})
			.then(alert("Created successfully"))
			.catch((e) => { alert("Exception")})
			}
			else
			{
				axios.post('rest/recreationtypes/create' ,{"name":''+this.name})
				.then(response => {
					axios.put('rest/facility/create/' + this.facilities.id + '&' + response.data)
					.then("Created successfully")
					.catch((e) => { alert("Exception 2")})
				})
				.catch((e) => { alert("Exception 1")})
			}
			
		}
	}
});