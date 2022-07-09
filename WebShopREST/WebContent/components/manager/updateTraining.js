Vue.component("update-training", { 
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
	      checked : false,
	      id:null,
	      training : null
	    }
	},
	    template: `
	    <div>
		<table v-if="training">
			<tr><td>Name</td><td><input type = "text" v-model = "training.name" name = "name"></td></tr>
			<template v-if="types">
			<tr>
				<td>Training Types</td>
				<td>
					<select v-model = "training.type">
						<option v-for="(f, index) in types" :value = "f">{{f.name}}</option>
					</select>
				</td>
			</tr>
			</template>
			<tr><td>Duration</td><td><input type = "text" v-model = "training.duration" name = "duration"></td></tr>
			<tr><td>Description</td><td><input type = "text" v-model = "training.description" name = "description"></td></tr>
			<tr><td>Additional Cost</td><td><input type = "number" v-model = "training.aditionalCost" name = "cost"></td></tr>
			<template v-if="trainers">
			<tr>
				<td>Trainers</td>
				<td>
					<select v-model = "training.trainer">
						<option v-for="(f, index) in trainers" :value = "f">{{f.name}} {{f.surname}}</option>
					</select>
				</td>
			</tr>
			</template>
			<tr><td><input type = "submit" v-on:click = "createFacility"></td></tr>
		</table>
	</div>
    	`,
    mounted () {
		/*axios.get('rest/login/loginstat')
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
         .then(response => (this.types = response.data))*/
         this.id = this.$route.params.id;
         
         axios.get('rest/training/'+this.id)
         .then(response=>(this.training = response.data))
         
         axios.get('rest/trainers/')
         .then(response => (this.trainers = response.data))
         
         axios.get('rest/trainingType/')
         .then(response => (this.types = response.data))
    },
    methods: {
		createFacility : function() {
				axios.put('rest/training/' , this.training)
			.then(alert("Updated successfully"))
			.catch((e) => { alert("Exception")})
			
		}
	}
});