Vue.component("login", { 
	data: function () {
	    return {
	      username : "",
	      password : "",
	      mode: "IDLE",
	      facilities: null,
	      temp: null,
	      searchQuery: null
	    }
	},
	    template: ` 
    	<div>
    		<table>
				<tr><td>Username</td><td><input type="text" name="username" v-model="username"></td></tr>
				<tr><td>Password</td><td><input type="password" name="password" v-model="password"></td></tr>
				<tr><td><input type="submit" name="Create" value="Log in" v-on:click = "Log"></td></tr>
			</table>
			<p v-bind:hidden="mode == 'IDLE'" style="color:red">Incorrect password or username</p>
			<p>
			<a href="#/create">Create account</a>
			</p>
	    	<div>
				<!-- Header -->
				  <header class="w3-container" style="padding-top:22px">
				    <h5><b><i class="fa fa-dashboard"></i> My Dashboard</b></h5>
				  </header>
				<div style="float: right; margin-right: 1.5%;">
					<div class="search-container">
						<input type="text" class="form-control" v-model="searchQuery" placeholder="Search.." name="search">
					</div>
				</div>
				<h5>Feeds</h5>
				<table class="w3-table w3-striped w3-white">
					<tr>
						<th>
							Naziv
						</th>
						<th>
							Tip
						</th>
						<th>
							Lokacija
						</th>
						<th>
							Prosecna ocena
						</th>
						<th>
							Radno vreme
						</th>
					</tr>
					<tr v-for="(f, index) in resultQuery">
						<td>{{f.name}}</td>
						<td>{{f.type.name}}</td>
						<td>{{f.location.adress.street + " " + f.location.adress.number + " " + f.location.adress.place}}</td>
						<td>0</td>
						<td>{{f.workRange}}</td>
					</tr>
				</table>
			</div>	
		</div>	  
    	`,
    mounted () {
		 axios
          .get('rest/facility/')
          .then(response => (this.facilities = response.data))
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
				router.push('/products/')
				
			}
			else
			{
				this.mode = "REJECT";
				this.username = "";
				this.password = "";
			}
		})
		}
    },
    computed: {
    resultQuery(){
      if(this.searchQuery){
      return this.facilities.filter((item)=>{
        return this.searchQuery.toLowerCase().split(' ').every(v => item.name.toLowerCase().includes(v) 
        	|| item.type.name.toLowerCase().includes(v) 
        	|| item.location.adress.street.toLowerCase().includes(v) 
        	|| item.location.adress.number.toString().toLowerCase().includes(v)
        	|| item.location.adress.place.toLowerCase().includes(v))
      })
      }else{
        return this.facilities;
      }
    }
  }
});