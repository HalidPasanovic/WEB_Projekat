Vue.component("login", { 
	data: function () {
	    return {
	      username : "",
	      password : "",
	      mode: "IDLE",
	      temp: null
	    }
	},
	    template: ` 
    	<div>
    		<table>
			<tr><td>Username</td><td><input type="text" name="username" v-model="username"></td></tr>
			<tr><td>Password</td><td><input type="password" name="password" v-model="password"></td></tr>
			<tr><td><input type="submit" name="Create" value="Log in" v-on:click = "Log"></td></tr>
		</table>
		<p v-bind:hidden="mode == 'IDLE'" style="color:red">INCORRECT PASSWORD OR USERNAME</p>
		<p>
		<a href="#/create">Create account</a>
		</p>
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
		Log : function(){
			axios
          .post('rest/login/' + this.username + '&' + this.password)
          .then(response =>
          {
			if(response.data == 1)
			{
				this.temp = this.username;
				router.push('rest/products/1')
				
			}
			else
			{
				this.mode = "REJECT";
				this.username = "";
				this.password = "";
			}
})
		}
    }
});