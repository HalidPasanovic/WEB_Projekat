Vue.component("create", { 
	data: function () {
	    return {
	      username : "",
	      password : "",
	      name : "",
	      surname : "",
	      gender : "",
	      mode: "IDLE",
	      mode2 : "IDLE",
	      temp: null
	    }
	},
	    template: ` 
    	<div>
    		<table style="position: absolute; top: 40%; left: 55%; transform: translate(-50%, -50%);">
			<tr><td style="padding:16px">Username</td><td style="padding:16px"><input class="form-control" type="text" name="username" v-model="username"></td></tr>
			<tr><td style="padding:16px">Password</td><td style="padding:16px"><input class="form-control" type="password" name="password" v-model="password"></td></tr>
			<tr><td style="padding:16px">Name</td><td style="padding:16px"><input class="form-control" type="text" name="username" v-model="name"></td></tr>
			<tr><td style="padding:16px">Surname</td><td style="padding:16px"><input class="form-control" type="text" name="username" v-model="surname"></td></tr>
			<tr><td style="padding:16px">Gender</td><td style="padding:16px">
			<select class="form-control" v-model="gender">
  				<option disabled value="">Please select one</option>
  				<option>Male</option>
  				<option>Female</option>
  				<option>Other</option>
			</select></td></tr>
			<tr><td colspan="2" style="padding:16px"><input class="w-100 btn btn-lg btn-dark" type="submit" value="Create" v-on:click = "Create"></td></tr>
		</table>
		<p v-bind:hidden="mode == 'IDLE'" style="color:red">Username {{this.temp}} already exists</p>
		<p v-bind:hidden="mode2 == 'IDLE'">Customer added to the list</p>
    	</div>		  
    	`,
    mounted () {
		
		/*try {
			axios.get('rest/managers/')
			.then(response => (this.products = response.data))
		}
		catch(error)
		{
			console.log(error);
		}*/
		
    },
    methods: {
		Create : function(){
			this.mode = "IDLE";
			this.mode2 = "IDLE";
				axios
			.post('rest/customers/add', {"username":''+this.username,"password":''+this.password, "name":''+this.name,"surname":''+this.surname,"gender":''+this.gender,"role":'Customer'})
				.then(response => (alert("Customer Created Successfully")))
				.catch((e) => { alert("Exception")})
			}
	}
});