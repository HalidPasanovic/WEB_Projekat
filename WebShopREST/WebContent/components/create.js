Vue.component("create", { 
	data: function () {
	    return {
	      username : "",
	      password : "",
	      name : "",
	      surname : "",
	      gender : "",
	      temp: null
	    }
	},
	    template: ` 
    	<div>
    		<table>
			<tr><td>Username</td><td><input type="text" name="username" v-model="username"></td></tr>
			<tr><td>Password</td><td><input type="password" name="password" v-model="password"></td></tr>
			<tr><td>Name</td><td><input type="text" name="username" v-model="name"></td></tr>
			<tr><td>Surname</td><td><input type="text" name="username" v-model="surname"></td></tr>
			<tr><td>Gender</td><td>
			<select v-model="gender">
  				<option disabled value="">Please select one</option>
  				<option>Male</option>
  				<option>Female</option>
  				<option>Other</option>
			</select></td></tr>
			<tr><td><input type="submit" value="Create" v-on:click = "Create"></td></tr>
		</table>
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
			axios
			.post('rest/customers/add', {"username":''+this.username,"password":''+this.password, "name":''+this.name,"surname":''+this.surname,"gender":''+this.gender,"role":'Customer'})
			.then(response => (toast("Customer added to the list")))
			}
	}
});