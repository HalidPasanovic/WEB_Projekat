Vue.component("facilitym", { 
	data: function () {
	    return {
	      manager : null,
	      facilities : null,
	      trainers : null,
	      customers : null,
	      trainings: null
	    }
	},
	    template: `
	    <main class="d-flex flex-nowrap" style="overflow-y: auto;">
	    <div style="width: 70%; margin: auto;">
	    <div style="display: flex; justify-content: center;">
                    <h3>Facility Details</h3><br>
        </div>
	    <table v-if="facilities" class="table table-striped table-hover table-dark">
	    <tbody>
	    <tr>
	    <td>Name:<td>
	    <td>{{facilities.name}}</td>
	    </tr>
	    <tr>
	    <td>Type:<td>
	    <td>{{facilities.type.name}}</td>
	    </tr>
	    <tr>
	    <td>Status:<td>
	    <td>{{facilities.status}}</td>
	    </tr>
	    <tr>
	    <td>Work Range:<td>
	    <td>{{facilities.workRange}}</td>
	    </tr>
	    <tr>
	    <td>Address:<td>
	    <td>{{facilities.location.adress.street + " " + facilities.location.adress.number + " " + facilities.location.adress.place}}</td>
	    </tr>
	    <tr>
	    <td>Average Grade:<td>
	    <td>0.0</td>
	    </tr>
	    <tr>
	    <td>Logo:<td>
	    <td><img src="pictures/picture.png" alt="Logo of the Sport Facillity" width="50" height="50"></td>
	    </tr>
	    </tbody>
	    </table>
	    <br><h5>Trainers in this facility:</h5>
	    <table class="table table-striped table-hover table-dark">
	    <thead>
	    <tr>
	    <th>Name</th>
	    <th>Surname</th>
	    <th>Username</th>
	    </tr>
	    </thead>
	    <tbody>
	    <tr v-for="(t, index) in trainers">
					<td>{{t.name}}</td>
					<td>{{t.surname}}</td>
					<td>{{t.username}}</td>
		</tr>
		</tbody>
		</table>
		<br><h5>Customers in this facility:</h5>
		<table class="table table-striped table-hover table-dark">
		<thead>
		<tr>
	    <th>Name</th>
	    <th>Surname</th>
	    <th>Username</th>
	    </tr>
	    </thead>
	    <tbody>
		<tr v-for="(t, index) in customers">
					<td>{{t.name}}</td>
					<td>{{t.surname}}</td>
					<td>{{t.username}}</td>
		</tr>
		</tbody>
	    </table>
	    <br><h5>Trainings in this facility:</h5>
	    <table class="table table-striped table-hover table-dark">
	    <thead>
	    <tr>
	    <th>Name</th>
	    <th>Type Name</th>
	    <th>Trainer</th>
	    <th>Description</th>
	    <th>Trainer</th>
	    </tr>
	    </thead>
	    <tbody>
		<tr v-for="(t, index) in trainings" v-on:click="update(t.id)">
					<td>{{t.name}}</td>
					<td>{{t.type.id}}</td>
					<td>{{t.trainer.id}}</td>
					<td>{{t.description}}</td>
					<td>{{t.trainer.name}}</td>
		</tr>
		</tbody>
	    </table>
		</div>
		</main>
    	`,
    mounted () {
		axios.get('rest/login/loginstat')
          .then(response => 
            {
                axios.get('rest/managers/' + response.data.id)
                .then(response => {this.manager = response.data;
                	
                	axios.get('rest/facility/' + response.data.facilities[0].id)
                	.then(response => {this.facilities = response.data
                	
                		axios.get('rest/training/facility/' + this.facilities.id)
                		.then(response => {
							this.trainers = response.data
							axios.get('rest/customers/getfacility/' + this.facilities.id)
							.then(response => {this.customers = response.data
							axios.get('rest/training/getfacility/' + this.facilities.id)
							.then(response => (this.trainings = response.data))
							})
							})
						
                	})	
                })
            })
    },
    methods: {
	
		update : function(id){
			router.push('/update/' + id)
		}
	}
});