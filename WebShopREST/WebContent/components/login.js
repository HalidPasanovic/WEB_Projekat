Vue.component("login", { 
	data: function () {
	    return {
	      products: null
	    }
	},
	    template: ` 
    	<div>
    		<table>
			<tr><td>Username</td><td><input type="text" name="username" ></td></tr>
			<tr><td>Password</td><td><input type="password" name="password"></td></tr>
			<tr><td><input type="submit" v-on:click = "Log" v-bind:value = "this.value"></td></tr>
		</table>
    	</div>		  
    	`,
    mounted () {
		
		try {
			axios.get('rest/admins/')
			.then(response => (this.products = response.data))
		}
		catch(error)
		{
			console.log(error);
		}
		
    },
    methods: {
		Log : function(){
			router.push(`/products`);
		}
    }
});