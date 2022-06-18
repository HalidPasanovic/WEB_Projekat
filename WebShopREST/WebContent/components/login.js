Vue.component("login", { 
	data: function () {
	    return {
	      username : "",
	      password : "",
	      result : null
	    }
	},
	    template: ` 
    	<div>
    		<table>
			<tr><td>Username</td><td><input type="text" name="username" v-model="username"></td></tr>
			<tr><td>Password</td><td><input type="password" name="password" v-model="password"></td></tr>
			<tr><td><input type="submit" v-on:click = "Log"></td></tr>
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
		Log : function(){
			axios.post('rest/login/' + this.username + '&' + this.password).then(response => (router.push(`/products/${response.data}`)))
		}
    }
});