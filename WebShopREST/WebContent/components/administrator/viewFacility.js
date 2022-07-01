Vue.component("view-facility", { 
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
	      pomocna: null	
	    }
	},
	    template: `
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
			
			<div style="margin-left: 1%;">
				<div class="search-container">
					<button type="submit" v-on:click = "addProduct">Dodaj</button>
				</div>
			</div>
			<h5>{{username}}</h5>
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
				<tr v-for="(f, index) in resultQuery" v-on:click="Proba(f)">
					<td>{{f.name}}</td>
					<td>{{f.type.name}}</td>
					<td>{{f.location.adress.street + " " + f.location.adress.number + " " + f.location.adress.place}}</td>
					<td>0</td>
					<td>{{f.workRange}}</td>
				</tr>
			</table>
		</div>
    	`,
    mounted () {
        axios
          .get('rest/facility/')
          .then(response => (this.facilities = response.data));
         
        this.username = this.$route.params.username;
    },
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
			.post('rest/managers/add', {"username":''+this.username2,"password":''+this.password, "name":''+this.name,"surname":''+this.surname,"gender":''+this.gender,"role":'Manager'})
				.then(response => (this.mode2 = "ACCEPTED"))
				.catch((e) => { this.mode = "REJECT";this.temp = this.username2; this.username2="";})
			}
			else
			{
				axios
			.post('rest/trainers/addtrainer', {"username":''+this.username2,"password":''+this.password, "name":''+this.name,"surname":''+this.surname,"gender":''+this.gender,"role":'Manager'})
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
		},
		Proba : function(f) {
			router.push(`/facility/` + f.id);
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