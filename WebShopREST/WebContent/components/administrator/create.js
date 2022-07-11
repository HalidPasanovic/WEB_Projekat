Vue.component("create", { 
	data: function () {
	    return {
	      facilities: null,
	      searchQuery: null,
	      username : null,
	      facilityView: true,
	      username2 : "",
	      password : "",
	      name : "",
	      surname : "",
	      gender : "",
	      mode: "IDLE",
	      mode2 : "IDLE",
	      mode3 : "IDLE",
	      temp: null,
	      createView:false,
	      createType: null,
	      pomocna: null,
	      birthday : null	
	    }
	},
	    template: `
	    <div>
	    	<div style="display: flex; justify-content: center;">
					<h5>Create manager/trainer</h5>
				</div>
    		<table style="position: absolute; top: 40%; left: 55%; transform: translate(-50%, -50%);">
			<tr><td style="padding:16px">Username</td><td style="padding:16px"><input class="form-control" type="text" name="username" v-model="username2"></td></tr>
			<tr><td style="padding:16px">Password</td><td style="padding:16px"><input class="form-control" type="password" name="password" v-model="password"></td></tr>
			<tr><td style="padding:16px">Name</td><td style="padding:16px"><input class="form-control" type="text" name="username" v-model="name"></td></tr>
			<tr><td style="padding:16px">Surname</td><td style="padding:16px"><input class="form-control" type="text" name="username" v-model="surname"></td></tr>
			<tr><td style="padding:16px">Birthday Date:</td><td style="padding:16px"><input class="form-control" type="date" v-model="birthday" name="birthday"></td></tr>
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
			<p v-bind:hidden="mode2 == 'IDLE'">Manager added to the list</p>
			<p v-bind:hidden="mode3 == 'IDLE'">Trainer added to the list</p>
			
		</div>
    	`,
    mounted () {
        /*axios
          .get('rest/facility/')
          .then(response => (this.facilities = response.data));*/
         
        this.createType = this.$route.params.id;
    },
    updated(){this.createType = this.$route.params.id;},
    methods: {
    	addProduct : function() {
    		router.push(`/createFacility/`);
    	},
    	editProduct : function(id) {
    		router.push(`/products/${id}`);
    	},
    	promeni: function(){
			if(!this.facilityView)
			{
				this.createView = false;
				this.facilityView = !(this.facilityView);
			}	
		},
    	deleteProduct : function(id, index) {
    		r = confirm("Are you sure?")
    		if (r){
	    		axios
	            .delete('rest/products/' + id)
	            .then(response => (this.facilities.splice(index, 1)))
    		}
    	},
    	Create : function(){
			this.mode = "IDLE";
			this.mode2 = "IDLE";
			this.mode3 = "IDLE";
			if(this.createType == "manager")
			{
				axios
			.post('rest/managers/', {"username":''+this.username2,"password":''+this.password, "name":''+this.name,"surname":''+this.surname,"gender":''+this.gender,"role":'Manager',"dateOfBirth":''+this.birthday})
				.then(response => {alert("Created successfully")})
				.catch((e) => { alert("Exception")})
			}
			else
			{
				axios
			.post('rest/trainers/', {"username":''+this.username2,"password":''+this.password, "name":''+this.name,"surname":''+this.surname,"gender":''+this.gender,"role":'Trainer',"dateOfBirth":''+this.birthday})
				.then(response => {alert("Created successfully")})
				.catch((e) => { alert("Exception")})
			}
		},
		promeni_create : function(type){
			if(!this.createView){
			this.facilityView = false;
			this.createView = !(this.createView);
			}
			this.createType = type;
			this.mode = "IDLE";
			this.mode2 = "IDLE";
			this.mode3 = "IDLE";
			this.username2 = "";
	      	this.password = "";
	      	this.name = "";
	      	this.surname = "";
	      	this.gender = "";
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