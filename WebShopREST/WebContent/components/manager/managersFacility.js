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
	    <div>
	    <table v-if="facilities">
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
	    <td>Work Range:<td>
	    <td>{{facilities.location.adress.street + " " + facilities.location.adress.number + " " + facilities.location.adress.place}}</td>
	    </tr>
	    <tr>
	    <td>Average Grade:<td>
	    <td>0.0</td>
	    </tr>
	    </table>
	    <img src="pictures/picture.png" alt="Logo of the Sport Facillity" width="150" height="150">
	    <table>
	    <th>Name</th>
	    <th>Surname</th>
	    <th>Username</th>
	    <tr v-for="(t, index) in trainers">
					<td>{{t.name}}</td>
					<td>{{t.surname}}</td>
					<td>{{t.username}}</td>
		</tr>
		</table>
		<table>
	    <th>Name</th>
	    <th>Surname</th>
	    <th>Username</th>
		<tr v-for="(t, index) in customers">
					<td>{{t.name}}</td>
					<td>{{t.surname}}</td>
					<td>{{t.username}}</td>
		</tr>
	    </table>
	    <table>
	    <th>Name</th>
	    <th>Type Name</th>
	    <th>Trainer</th>
	    <th>Description</th>
		<tr v-for="(t, index) in trainings" v-on:click="update(t.id)">
					<td>{{t.name}}</td>
					<td>{{t.type.id}}</td>
					<td>{{t.trainer.id}}</td>
					<td>{{t.description}}</td>
		</tr>
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