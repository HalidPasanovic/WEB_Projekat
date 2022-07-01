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
    		<table>
			<tr><td>Username</td><td><input type="text" name="username" v-model="username2"></td></tr>
			<tr><td>Password</td><td><input type="password" name="password" v-model="password"></td></tr>
			<tr><td>Name</td><td><input type="text" name="username" v-model="name"></td></tr>
			<tr><td>Surname</td><td><input type="text" name="username" v-model="surname"></td></tr>
			<tr><td>Birthday Date:</td><td><input type="date" v-model="birthday" name="birthday"></td></tr>
			<tr><td>Gender</td><td>
			<select v-model="gender">
  				<option disabled value="">Please select one</option>
  				<option>Male</option>
  				<option>Female</option>
  				<option>Other</option>
			</select></td></tr>
			<tr><td><input type="submit" value="Create" v-on:click = "Create"></td></tr>
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
				.then(response => (this.mode2 = "ACCEPTED"))
				.catch((e) => { this.mode = "REJECT";this.temp = this.username2; this.username2="";})
			}
			else
			{
				axios
			.post('rest/trainers/', {"username":''+this.username2,"password":''+this.password, "name":''+this.name,"surname":''+this.surname,"gender":''+this.gender,"role":'Trainer',"dateOfBirth":''+this.birthday})
				.then(response => (this.mode3 = "ACCEPTED"))
				.catch((e) => { this.mode = "REJECT";this.temp = this.username2; this.username2="";})
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