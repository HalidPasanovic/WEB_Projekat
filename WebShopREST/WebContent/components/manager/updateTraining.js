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
	    <div style="display: flex; justify-content: center;">
					<h5>Update training</h5>
				</div>
		<table v-if="training" style="position: absolute; top: 40%; left: 55%; transform: translate(-50%, -50%);">
			<tr><td style="padding:16px">Name</td><td style="padding:16px"><input class="form-control" type = "text" v-model = "training.name" name = "name"></td></tr>
			<template v-if="types">
			<tr>
				<td style="padding:16px">Training Types</td>
				<td style="padding:16px">
					<select class="form-control" v-model = "training.type">
						<option v-for="(f, index) in types" :value = "f">{{f.name}}</option>
					</select>
				</td>
			</tr>
			</template>
			<tr><td style="padding:16px">Duration</td><td style="padding:16px"><input class="form-control" type = "text" v-model = "training.duration" name = "duration"></td></tr>
			<tr><td style="padding:16px">Description</td><td style="padding:16px"><input class="form-control" type = "text" v-model = "training.description" name = "description"></td></tr>
			<tr><td style="padding:16px">Additional Cost</td><td style="padding:16px"><input class="form-control" type = "number" v-model = "training.aditionalCost" name = "cost"></td></tr>
			<template v-if="trainers">
			<tr>
				<td style="padding:16px">Trainers</td>
				<td style="padding:16px">
					<select class="form-control" v-model = "training.trainer">
						<option v-for="(f, index) in trainers" :value = "f">{{f.name}} {{f.surname}}</option>
					</select>
				</td>
			</tr>
			</template>
			<tr><td colspan="2" style="padding:16px"><input class="w-100 btn btn-lg btn-dark" type = "submit" v-on:click = "createFacility"></td></tr>
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